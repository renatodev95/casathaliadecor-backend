package com.casathaliadecor.dao;

import com.casathaliadecor.POJO.User;
import com.casathaliadecor.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUser();

    List<String> getAllAdmin();

    @Transactional
    @Modifying
    void updateStatus(@Param("status") String status, @Param("id") Integer id);

    User findByEmail(String email);
}
