package com.busyqa.crm.repo;


import com.busyqa.crm.model.user.Student;

import java.util.Optional;

public interface StudentRepository extends UserBaseRepository<Student>{

    Optional<Student> findByEmail(String email);


}
