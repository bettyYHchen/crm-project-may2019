package com.busyqa.crm.repo;

import com.busyqa.crm.model.user.Intern;

import java.util.Optional;

public interface InternRepository extends UserBaseRepository<Intern>{

    Optional<Intern> findByEmail(String email);


    void deleteByEmail(String email);
}
