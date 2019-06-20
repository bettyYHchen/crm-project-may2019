package com.busyqa.crm.services;

import com.busyqa.crm.message.request.InstructorRequest;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.Instructor;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.repo.CourseRepository;
import com.busyqa.crm.repo.InstructorRepository;
import com.busyqa.crm.repo.TrainingClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TrainingClassRepository trainingClassRepository;

    @Autowired
    private InstructorRepository instructorRepository;


    public List<Instructor> listInstructors() {return this.instructorRepository.findAll();}

    public Instructor listInstructorById(Long id) {return this.instructorRepository.getOne(id);}

    public Instructor createInstructor(InstructorRequest instructorRequest) {
        System.out.println(instructorRequest.getName());
        System.out.println(instructorRequest.getEmail());
        Instructor instructorToSave = instructorRepository.findByEmail(instructorRequest.getEmail())
                .orElse(new Instructor());
        instructorToSave.setName(instructorRequest.getName());
        instructorToSave.setEmail(instructorRequest.getEmail());
        this.instructorRepository.save(instructorToSave);
        return instructorToSave;
    }

    public ResponseEntity<?> deleteInstructor(Long id) {
        return instructorRepository.findById(id).map(
                record -> {
                    instructorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
