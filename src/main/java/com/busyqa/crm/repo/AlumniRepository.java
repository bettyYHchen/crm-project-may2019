package com.busyqa.crm.repo;


import com.busyqa.crm.model.user.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface AlumniRepository extends JpaRepository<Alumni,Long> {
    Optional<Alumni> findByEmail(String email);
    List<Alumni> findAll();

    @Modifying
    @Transactional
    @Query("delete from #{#entityName} as u where u.email = :email")
    void deleteByEmail(@Param("email") String email);

}
