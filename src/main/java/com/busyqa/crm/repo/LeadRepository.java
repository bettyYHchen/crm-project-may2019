package com.busyqa.crm.repo;

import com.busyqa.crm.model.user.Lead;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
public interface LeadRepository extends UserBaseRepository<Lead> {

    Optional<Lead> findByEmail(String email);



}