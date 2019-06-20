package com.busyqa.crm.repo;

import com.busyqa.crm.model.academic.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByName(String name);

    @Modifying
    @Transactional
    @Query("update Course c set c.taxPercentage = :taxPercentage")
    void updateAllTaxPercentage(@Param("taxPercentage") double taxPercentage);

    @Modifying
    @Transactional
    @Query("update Course c set c.lateFeeRate = :lateFeeRate")
    void updateAllLateFeeRate(@Param("lateFeeRate") double lateFeeRate);
}
