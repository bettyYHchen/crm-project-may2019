package com.busyqa.crm.services;

import com.busyqa.crm.message.request.CourseRequest;
import com.busyqa.crm.message.request.SettingRequest;
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

    public List<TrainingClass> listClasses(String name) {
        Course course = this.courseRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("Error: course with this name not found!")
        );
        return course.getTrainingClasses();
    }

    public Course createCourse(CourseRequest courseRequest) {

        Course courseToSave = courseRepository.findByName(courseRequest.getName())
                .orElse(new Course());
        courseToSave.setName(courseRequest.getName());
        courseToSave.setFee(courseRequest.getFee());
        courseToSave.setDurationWeek(courseRequest.getDurationWeek());
        courseToSave.setPaymentDurationWeek(courseRequest.getPaymentDurationWeek());
        courseToSave.setPaymentDurationBiWeek(courseRequest.getPaymentDurationBiWeek());
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

    public void updateSetting(SettingRequest settingRequest) {
        double taxPercentage = settingRequest.getTaxPercentage();
        double lateFeeRate = settingRequest.getLateFeeRate();
        courseRepository.updateAllTaxPercentage(taxPercentage);
        courseRepository.updateAllLateFeeRate(lateFeeRate);

    }





}
