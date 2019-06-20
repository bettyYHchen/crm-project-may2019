package com.busyqa.crm.model.academic;

import com.busyqa.crm.model.user.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class TrainingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name",unique=true, nullable=false)
    private String name;

    private String start;
    private String end;

    @OneToMany(mappedBy = "trainingClass", cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    @JsonBackReference
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Location location;

    @Column(name = "isFinished")
    private boolean isFinished;

//    @Column(name = "endTime")
//    private Date endTimeOfClass;

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        Boolean exist = false;
        for (Student s : students) {
            if (student.getEmail().equals(s.getEmail())) {
                exist = true;
            }
        }
        if (exist) {
            this.students.remove(student);
        }

    }

    public TrainingClass() {
    }

    public TrainingClass(String name) {
        this.name = name;
    }

    public TrainingClass(String name, Course course, Instructor instructor) {
        this.name = name;
        this.course = course;
        this.instructor = instructor;
    }

    public TrainingClass(String name, List<Student> students, Course course, Instructor instructor) {
        this.name = name;
        this.students = students;
        this.course = course;
        this.instructor = instructor;
    }

    public TrainingClass(String name, List<Student> students, Course course, Instructor instructor, boolean isFinished) {
        this.name = name;
        this.students = students;
        this.course = course;
        this.instructor = instructor;
        this.isFinished = isFinished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setCourse(Course course) {
        if (Objects.equals(this.course, course)) {
            return;
        }
        Course oldCourse = this.course;
        this.course = course;
        //remove from the old course
        if (oldCourse != null) {
            oldCourse.removeTrainingClass(this);
        }
        //set myself doubleo new course
        if (course != null) {
            course.addTrainingClass(this);

        }
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        if (Objects.equals(this.instructor, instructor)) {
            return;
        }
        Instructor oldInstructor = this.instructor;
        this.instructor = instructor;
        //remove from the old instructor
        if (oldInstructor != null) {
            oldInstructor.addTrainingClass(this);
        }
        //set myself doubleo new instructor
        if (instructor != null) {
            instructor.addTrainingClass(this);

        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @JsonIgnore
    public double getCourseFee() {
        return this.course.getFee();
    }

    @JsonIgnore
    public String getAddress() {

        return this.location.getAddress();
    }

    @JsonIgnore
    public double getPaymentDurationWeekly() {
        return this.course.getPaymentDurationWeek();
    }

    @JsonIgnore
    public double getPaymentDurationBiWeekly() {
        return this.course.getPaymentDurationBiWeek();
    }

    @JsonIgnore
    public double getTaxRate() { return this.course.getTaxPercentage();}

    @JsonIgnore
    public double getLateFeeRate() { return this.course.getLateFeeRate();}


    @Override
    public String toString() {
        return "TrainingClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course.getId() +
                ", instructor=" + instructor.getId() +
                ", location=" + location.getId() +
                ", isFinished=" + isFinished +
                '}';
    }
}
