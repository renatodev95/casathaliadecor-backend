package com.casathaliadecor.utils;

import com.casathaliadecor.constants.StoreConstants;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StoreUtils {

    private StoreUtils() {
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(StoreConstants.MESSAGE_TEMPLATE_START + responseMessage +
                StoreConstants.MESSAGE_TEMPLATE_END, httpStatus);
    }

    public static ResponseEntity<String> getResponseEntityToken(String responseMessage) {
        return new ResponseEntity<>(StoreConstants.MESSAGE_TEMPLATE_START_TOKEN + responseMessage +
                StoreConstants.MESSAGE_TEMPLATE_END, HttpStatus.OK);
    }

    public static ResponseEntity<String> somethingWentWrongResponse() {
        return getResponseEntity(StoreConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<String> categoryAddedSuccessfully() {
        return getResponseEntity(StoreConstants.SUCCESSFULLY_REGISTERED_CATEGORY, HttpStatus.OK);
    }

    public static ResponseEntity<String> unauthorizedAccess() {
        return getResponseEntity(StoreConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<String> checkYourEmail() {
        return getResponseEntity(StoreConstants.CHECK_YOUR_EMAIL, HttpStatus.OK);
    }

    public static ResponseEntity<String> passwordChangedSuccessfully() {
        return getResponseEntity(StoreConstants.PASSWORD_CHANGED_SUCCESSFULLY, HttpStatus.OK);
    }

    public static ResponseEntity<String> incorrectOldPassword() {
        return getResponseEntity(StoreConstants.INCORRECT_OLD_PASSWORD, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> successfullyRegisteredUser() {
        return getResponseEntity(StoreConstants.SUCCESSFULLY_REGISTERED_USER, HttpStatus.CREATED);
    }

    public static ResponseEntity<String> emailAlreadyExists() {
        return getResponseEntity(StoreConstants.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> invalidData() {
        return getResponseEntity(StoreConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> waitForAdminApproval() {
        return getResponseEntity(StoreConstants.WAIT_ADMIN_APPROVAL, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> badCredentials() {
        return getResponseEntity(StoreConstants.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> categoryIdDoesNotExist() {
        return getResponseEntity(StoreConstants.UNEXISTENT_CATEGORY_ID, HttpStatus.OK);
    }

    public static ResponseEntity<String> categoryUpdatedSuccessfully() {
        return getResponseEntity(StoreConstants.CATEGORY_UPDATED_SUCCESSFULLY, HttpStatus.OK);
    }

    public static ResponseEntity<String> productAddedSuccessfully() {
        return getResponseEntity(StoreConstants.PRODUCT_ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    public static ResponseEntity<String> productIdDoesNotExist() {
        return getResponseEntity(StoreConstants.UNEXISTENT_PRODUCT_ID, HttpStatus.OK);
    }

    public static ResponseEntity<String> productUpdatedSuccessfully() {
        return getResponseEntity(StoreConstants.PRODUCT_UPDATED_SUCCESSFULLY, HttpStatus.OK);
    }

    public static ResponseEntity<String> productDeletedSuccessfully() {
        return getResponseEntity(StoreConstants.PRODUCT_DELETED_SUCCESSFULLY, HttpStatus.OK);
    }

    public static ResponseEntity<String> productStatusUpdatedSuccessfully() {
        return getResponseEntity(StoreConstants.PRODUCT_STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK);
    }

    public static String getUUID() {
        Date date = new Date();
        long time = date.getTime();
        return "BILL-" + time;
    }

    public static JSONArray getJsonArrayFromString(String data) throws JSONException {
        return new JSONArray(data);
    }

    public static Map<String, Object> getMapFromJson(String data) {
        if (!Strings.isNullOrEmpty(data)) {
            return new Gson().fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
        }
        return new HashMap<>();
    }
}
