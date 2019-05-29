package com.busyqa.crm.services;

import com.busyqa.crm.message.request.TrainingClassForm;
import com.busyqa.crm.message.response.TrainingClassResponse;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.Instructor;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.repo.CourseRepository;
import com.busyqa.crm.repo.InstructorRepository;
import com.busyqa.crm.repo.StudentRepository;
import com.busyqa.crm.repo.TrainingClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingClassService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TrainingClassRepository trainingClassRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;


    public List<TrainingClassResponse> listTrainingClasses() {
        List<TrainingClass> trainingClasses = trainingClassRepository.findAll();
        return trainingClasses.stream().map(trainingClass -> new TrainingClassResponse(trainingClass.getName(),
                trainingClass.getInstructor().getFirstName() + " " + trainingClass.getInstructor().getLastName())).
                collect(Collectors.toList());


//        return this.trainingClassRepository.findAll();
    }

    public TrainingClass listTrainingClassById(Long id) {
        return this.trainingClassRepository.getOne(id);
    }

    public TrainingClass createTrainingClass(TrainingClassForm trainingClassForm) {
        String className = trainingClassForm.getCourseName() + " " + trainingClassForm.getBatch();
        Course course = courseRepository.findByName(trainingClassForm.getCourseName()).
                orElseThrow(() -> new RuntimeException("Course not found"));
        Instructor instructor = instructorRepository.findByEmail(trainingClassForm.getInstructorEmail())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        TrainingClass trainingClass = trainingClassRepository.findByName(className)
                .orElse(new TrainingClass(className));
        trainingClass.setCourse(course);
        trainingClass.setInstructor(instructor);


//        TrainingClass trainingClass = new TrainingClass(className,course,instructor);
        trainingClassRepository.save(trainingClass);
        return trainingClass;

    }

    public ResponseEntity<?> deleteTrainingClass(Long id) {
        return trainingClassRepository.findById(id).map(
                record -> {
                    trainingClassRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());


    }
}