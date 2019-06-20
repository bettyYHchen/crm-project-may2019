package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.*;
import com.busyqa.crm.message.response.TrainingClassResponse;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.Instructor;
import com.busyqa.crm.model.academic.Location;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.services.CourseService;
import com.busyqa.crm.services.InstructorService;
import com.busyqa.crm.services.TrainingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/academic")
public class AcademicController {

    @Autowired
    CourseService courseService;

    @Autowired
    TrainingClassService trainingClassService;

    @Autowired
    InstructorService instructorService;

//// Course
    @GetMapping("/courses")
    public List<Course> listCourses(){
        return this.courseService.listCourses();
    }

    @GetMapping("/courses/classes/{name}")
    public List<TrainingClassResponse> listClassesByCourseName(@PathVariable("name") String name) {
        List<TrainingClass> trainingClasses = courseService.listClasses(name);
        List<TrainingClassResponse> trainingClassResponses = new ArrayList<>();
        for (TrainingClass trainingClass: trainingClasses) {
            trainingClassResponses.add(new TrainingClassResponse(trainingClass.getId(), trainingClass.getName(),
                    trainingClass.getInstructor().getName(), trainingClass.getAddress(), trainingClass.getStart(), trainingClass.getEnd()));
        }
        return trainingClassResponses;
    }


    @GetMapping("/getRates")
    public SettingRequest getSetting() {
        List<Course> courses = this.courseService.listCourses();
        Course anyCourse = courses.get(0);
        SettingRequest settingRequest = new SettingRequest();
        settingRequest.setLateFeeRate(anyCourse.getLateFeeRate());
        settingRequest.setTaxPercentage(anyCourse.getTaxPercentage());
        return settingRequest;
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public Course createCourse(@RequestBody CourseRequest courseRequest) {
        return this.courseService.createCourse(courseRequest);
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable("id") Long id) {
        return this.courseService.listCourseById(id);

    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
        return courseService.deleteCourse(id);
    }


    //// TrainingClass
    @GetMapping("/classes")
    public List<TrainingClassResponse> listTrainingClasses(){
        return this.trainingClassService.listTrainingClasses();
    }

    @PostMapping("/classes")
    @ResponseStatus(HttpStatus.OK)
    public TrainingClass createTrainingClass(@RequestBody TrainingClassForm trainingClassForm) {
        return this.trainingClassService.createTrainingClass(trainingClassForm);
    }



    @GetMapping("/classes/{id}")
    public TrainingClassResponse getTrainingClass(@PathVariable("id") Long id) {
        return this.trainingClassService.listTrainingClassById(id);

    }

    @PutMapping("/classes/{id}")
    public ResponseEntity<TrainingClassFinishedStatus>  setClassFinishedStatus(@PathVariable("id") Long id,
                                                     @RequestBody TrainingClassFinishedStatus trainingClassFinishedStatus) {
        return this.trainingClassService.updateClassStatus(id, trainingClassFinishedStatus);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<?> deleteTrainingClass(@PathVariable("id") Long id) {
        return this.trainingClassService.deleteTrainingClass(id);
    }

    //// Instructor
    @GetMapping("/instructors")
    public List<Instructor> listInstructors(){
        return this.instructorService.listInstructors();
    }

    @PostMapping("/instructors")
    @ResponseStatus(HttpStatus.OK)
    public Instructor createInstructor(@RequestBody InstructorRequest instructorRequest) {
        return this.instructorService.createInstructor(instructorRequest);
    }

    @GetMapping("/instructors/{id}")
    public Instructor getInstructor(@PathVariable("id") Long id) {
        return this.instructorService.listInstructorById(id);

    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable("id") Long id) {
        return instructorService.deleteInstructor(id);
    }

    @GetMapping("/classesByLocation/{locationId}")
    public List<TrainingClass> getTrainingClassesByLocation(@PathVariable("locationId") Long locationId) {
        return this.trainingClassService.listTrainingClassesByLocation(locationId);

    }

    @PutMapping("/updateSetting")
    public void updateSetting(@RequestBody SettingRequest settingRequest) {
        this.courseService.updateSetting(settingRequest);
    }



}
