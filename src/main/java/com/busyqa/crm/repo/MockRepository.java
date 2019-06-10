package com.busyqa.crm.repo;

import com.busyqa.crm.model.user.Mock;
import com.busyqa.crm.model.user.Resume;

import java.util.Optional;

public interface MockRepository extends UserBaseRepository<Mock>{

    Optional<Mock> findByEmail(String email);


}
