package com.busyqa.crm.repo;



import com.busyqa.crm.model.academic.TrainingClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingClassRepository extends JpaRepository<TrainingClass,Long> {
    Optional<TrainingClass> findByName(String name);

}
