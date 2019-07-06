package com.busyqa.crm.repo;

import com.busyqa.crm.model.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface UserRepository extends UserBaseRepository<User> {


    @Query("select u from User u where u.dropOff = true")
    List<User> findAllDropOff();

}


