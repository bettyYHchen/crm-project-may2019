package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.TrainingClassForm;
import com.busyqa.crm.message.response.TrainingClassResponse;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.Instructor;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.services.CourseService;
import com.busyqa.crm.services.InstructorService;
import com.busyqa.crm.services.TrainingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public Course createCourse(@ModelAttribute Course course) {
        return this.courseService.createCourse(course);
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
    public TrainingClass getTrainingClass(@PathVariable("id") Long id) {
        return this.trainingClassService.listTrainingClassById(id);

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
    public Instructor createInstructor(@ModelAttribute Instructor instructor) {
        return this.instructorService.createInstructor(instructor);
    }

    @GetMapping("/instructors/{id}")
    public Instructor getInstructor(@PathVariable("id") Long id) {
        return this.instructorService.listInstructorById(id);

    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable("id") Long id) {
        return instructorService.deleteInstructor(id);
    }



}
