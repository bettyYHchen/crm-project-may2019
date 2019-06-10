package com.busyqa.crm.repo;


import com.busyqa.crm.model.user.Resume;

import java.util.Optional;

public interface ResumeRepository extends UserBaseRepository<Resume>{

    Optional<Resume> findByEmail(String email);


}
