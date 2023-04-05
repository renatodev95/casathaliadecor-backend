package com.casathaliadecor.serviceImpl;

import com.casathaliadecor.JWT.CustomerUsersDetailsService;
import com.casathaliadecor.JWT.JwtFilter;
import com.casathaliadecor.JWT.JwtUtil;
import com.casathaliadecor.POJO.User;
import com.casathaliadecor.constants.StoreConstants;
import com.casathaliadecor.dao.UserDao;
import com.casathaliadecor.service.UserService;
import com.casathaliadecor.utils.EmailUtils;
import com.casathaliadecor.utils.StoreUtils;
import com.casathaliadecor.wrapper.UserWrapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get(StoreConstants.EMAIL));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return StoreUtils.successfullyRegisteredUser();
                } else {
                    return StoreUtils.emailAlreadyExists();
                }
            } else {
                return StoreUtils.invalidData();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StoreUtils.somethingWentWrongResponse();
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get(StoreConstants.EMAIL),
                            requestMap.get(StoreConstants.PASSWORD))
            );

            if (auth.isAuthenticated()) {
                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase(StoreConstants.TRUE)) {
                    return StoreUtils.getResponseEntityToken(getGeneratedToken());
                } else {
                    return StoreUtils.waitForAdminApproval();
                }
            }
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
        }
        return StoreUtils.badCredentials();
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                if (optional.isPresent()) {
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
                    return StoreUtils.getResponseEntity(StoreConstants.USER_STATUS_UPDATED, HttpStatus.OK);
                } else {
                    return StoreUtils.getResponseEntity(StoreConstants.NON_XISTENT_USER, HttpStatus.OK);
                }
            } else {
                return StoreUtils.getResponseEntity(StoreConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.error("{}", ex.getMessage());
        }
        return StoreUtils.somethingWentWrongResponse();
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return StoreUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
            if (userObj != null) {
                if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userDao.save(userObj);
                    return StoreUtils.passwordChangedSuccessfully();
                }
                return StoreUtils.incorrectOldPassword();
            }
            return StoreUtils.somethingWentWrongResponse();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StoreUtils.somethingWentWrongResponse();
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            User user = userDao.findByEmail(requestMap.get(StoreConstants.EMAIL));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
                emailUtils.forgotEmail(user.getEmail(), StoreConstants.CREDENTIALS_CONFIRMATION, user.getPassword());
            return StoreUtils.checkYourEmail();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StoreUtils.somethingWentWrongResponse();
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Conta Aprovada",
                    "USUÁRIO:- " + user + " \n foi aprovado por \nADMIN:- " + jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Conta Desativada",
                    "USUÁRIO:- " + user + " \n foi desativado por \nADMIN:- " + jwtFilter.getCurrentUser(), allAdmin);
        }
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        return User.builder()
                .name(requestMap.get("name"))
                .contactNumber(requestMap.get("contactNumber"))
                .email(requestMap.get("email"))
                .password(requestMap.get("password"))
                .status("false")
                .role("user")
                .build();
    }

    private String getGeneratedToken() {
        return jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                customerUsersDetailsService.getUserDetail().getRole());
    }
}
