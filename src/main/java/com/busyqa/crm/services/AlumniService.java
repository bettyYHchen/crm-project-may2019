package com.busyqa.crm.services;

import com.busyqa.crm.message.request.AlumniRequest;
import com.busyqa.crm.model.user.Alumni;
import com.busyqa.crm.repo.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumniService {

    @Autowired
    AlumniRepository alumniRepository;

    public List<Alumni> listAlumnus() {
        return alumniRepository.findAll();
    }

    public Alumni listAlumniByEmail(String email) {
        return alumniRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Error: alumni not found!"));
    }

    public ResponseEntity<Alumni> updateAlumni(String email, AlumniRequest alumniRequest) {


        return alumniRepository.findByEmail(email).map(recordUpdated -> {
            recordUpdated.setName(alumniRequest.getName());
            recordUpdated.setEmail(alumniRequest.getEmail());
            recordUpdated.setPhone(alumniRequest.getPhone());
            recordUpdated.setPlacementStatus(alumniRequest.getPlacementStatus());
            recordUpdated.setCompanyName(alumniRequest.getCompanyName());
            recordUpdated.setEmploymentType(alumniRequest.getEmploymentType());
            recordUpdated.setDesignation(alumniRequest.getDesignation());
            recordUpdated.setComment(alumniRequest.getComment());
            this.alumniRepository.save(recordUpdated);
            return ResponseEntity.ok().body(recordUpdated);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<?> deleteAlumni(String email) {
        return alumniRepository.findByEmail(email).map(
                record -> {
                    alumniRepository.deleteByEmail(email);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
