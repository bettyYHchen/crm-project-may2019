package com.busyqa.crm.services;

import com.busyqa.crm.model.user.Lead;
import com.busyqa.crm.model.academic.Course;
import com.busyqa.crm.model.academic.TrainingClass;
import com.busyqa.crm.repo.CourseRepository;
import com.busyqa.crm.repo.InstructorRepository;
import com.busyqa.crm.repo.TrainingClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TrainingClassRepository trainingClassRepository;


    public List<Course> listCourses() {return this.courseRepository.findAll();}

    public Course listCourseById(Long id) {return this.courseRepository.getOne(id);}

    public Course createCourse(Course course) {

        Course courseToSave = courseRepository.findByName(course.getName())
                .orElse(new Course());
        courseToSave.setName(course.getName());
        courseToSave.setFee(course.getFee());
        course.setTrainingClasses(course.getTrainingClasses());
        this.courseRepository.save(courseToSave);
        return courseToSave;
    }

    public ResponseEntity<?> deleteCourse(Long id) {
        return courseRepository.findById(id).map(
                record -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }





}
