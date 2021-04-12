package com.account.repository;

import com.account.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// find by user Email -- by annotation, by sql query
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    //          Composite search find by username and useremail
    //        UserEntity findByUsernameAndUserEmail(username, useremail);
}
