package com.busyqa.crm.model.academic;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(
        name = "instructors"
        )
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    private String name;
    private String email;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<TrainingClass> trainingClasses;

    public Instructor() {
    }

    public Instructor(String name, String email, List<TrainingClass> trainingClasses) {
        this.name = name;
        this.email = email;
        this.trainingClasses = trainingClasses;
    }

    public void addTrainingClass(TrainingClass trainingClass) {
        this.trainingClasses.add(trainingClass);
    }

    public void removeTrainingClass(TrainingClass trainingClass) {
        Boolean exist = false;
        for (TrainingClass t: trainingClasses) {
            if (trainingClass.getName().equals(t.getName())) {
                exist = true;
            }
        }
        if (exist) {
            this.trainingClasses.remove(trainingClass);
        }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TrainingClass> getTrainingClasses() {
        return trainingClasses;
    }

    public void setTrainingClasses(List<TrainingClass> trainingClasses) {
        this.trainingClasses = trainingClasses;
    }
}
