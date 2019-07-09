package com.busyqa.crm.services;

import com.busyqa.crm.message.request.LocationRequest;
import com.busyqa.crm.message.request.TrainingClassFinishedStatus;
import com.busyqa.crm.message.request.TrainingClassForm;
import com.busyqa.crm.message.response.TrainingClassResponse;
import com.busyqa.crm.model.academic.Location;
import com.busyqa.crm.model.user.Student;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.Instructor;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.repo.*;
import com.busyqa.crm.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Autowired
    private LocationRepository locationRepository;


    public List<TrainingClassResponse> listTrainingClasses() {
        List<TrainingClass> trainingClasses = trainingClassRepository.findAll();
        return trainingClasses.stream().map(trainingClass -> new TrainingClassResponse(trainingClass.getId(),
                trainingClass.getName(), trainingClass.getInstructor().getName(),
                trainingClass.getLocation(), trainingClass.getStart(), trainingClass.getEnd())).
                collect(Collectors.toList());


//        return this.trainingClassRepository.findAll();
    }

    public TrainingClassResponse listTrainingClassById(Long id) {
        TrainingClass trainingClass = trainingClassRepository.getOne(id);
        TrainingClassResponse trainingClassResponse = new TrainingClassResponse();
        trainingClassResponse.setId(trainingClass.getId());
        trainingClassResponse.setName(trainingClass.getName());
        trainingClassResponse.setInstructorName(trainingClass.getInstructor().getName());
        trainingClassResponse.setAddress(trainingClass.getLocation());
        trainingClassResponse.setStart(trainingClass.getStart());
        trainingClassResponse.setEnd(trainingClass.getEnd());
        return trainingClassResponse;

    }

    public TrainingClassResponse listTrainingClassByName(String name) {
        TrainingClass trainingClass = trainingClassRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("Error: training class not found!")
        );
        TrainingClassResponse trainingClassResponse = new TrainingClassResponse();
        trainingClassResponse.setId(trainingClass.getId());
        trainingClassResponse.setName(trainingClass.getName());
        trainingClassResponse.setInstructorName(trainingClass.getInstructor().getName());
        trainingClassResponse.setAddress(trainingClass.getLocation());
        trainingClassResponse.setStart(trainingClass.getStart());
        trainingClassResponse.setEnd(trainingClass.getEnd());
        return trainingClassResponse;

    }

    public TrainingClass createTrainingClass(TrainingClassForm trainingClassForm) {
        String className = trainingClassForm.getCourseName() + " " + trainingClassForm.getBatch();
        Course course = courseRepository.findByName(trainingClassForm.getCourseName()).
                orElseThrow(() -> new RuntimeException("Course not found"));
        Instructor instructor = instructorRepository.findByName(trainingClassForm.getInstructorName())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        TrainingClass trainingClassFound = trainingClassRepository.findByName(className)
                .orElse(null);
        if (null != trainingClassFound) {
            return trainingClassFound;
        }
        TrainingClass trainingClass = new TrainingClass(className);
        trainingClass.setCourse(course);
        trainingClass.setInstructor(instructor);
        trainingClass.setStart(trainingClassForm.getStart());
        trainingClass.setEnd(Common.calculateDateAfterWeeks(trainingClassForm.getStart(), (long) course.getDurationWeek()));
//        Location location = new Location();
//        location.setAddress(trainingClassForm.getAddress());
        trainingClass.setLocation(trainingClassForm.getAddress());
        System.out.println(trainingClass);
//        locationRepository.save(location);
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

    public ResponseEntity<TrainingClassFinishedStatus> updateClassStatus(Long id,
                                                                         TrainingClassFinishedStatus trainingClassFinishedStatus) {
        return trainingClassRepository.findById(id).map(recordUpdated -> {
                    recordUpdated.setFinished(trainingClassFinishedStatus.isFinishStatus());
                    this.trainingClassRepository.save(recordUpdated);
                    return ResponseEntity.ok().body(trainingClassFinishedStatus);
        }).orElse(ResponseEntity.notFound().build());

    }

    public List<Location> listLocations() {
        return locationRepository.findAll();
    }

    public Location createLocation(LocationRequest locationRequest) {
        Location location = new Location();
        location.setAddress(locationRequest.getAddress());
        return locationRepository.save(location);
    }

    public ResponseEntity<?> deleteLocation(Long id) {
        return locationRepository.findById(id).map(
                record -> {
                    locationRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());


    }

//    public List<TrainingClass> listTrainingClassesByLocation(Long locationId) {
//        Pageable pageable = PageRequest.of(0, 30);
//        Page<TrainingClass> trainingClassesPage = trainingClassRepository.findByLocationId(locationId, pageable);
//        return trainingClassesPage.getContent();
//    }
}