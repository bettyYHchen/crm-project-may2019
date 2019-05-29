package com.busyqa.crm.model.academic;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "courses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "name",unique=true, nullable=false)
    private String name;

    private int fee;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<TrainingClass> trainingClasses;

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

    public Course() {
    }

    public Course(@NotBlank @Size(min = 3, max = 50) String name, int fee) {
        this.name = name;
        this.fee = fee;
    }

    public Course(@NotBlank @Size(min = 3, max = 50) String name, int fee, List<TrainingClass> trainingClasses) {
        this.name = name;
        this.fee = fee;
        this.trainingClasses = trainingClasses;
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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public List<TrainingClass> getTrainingClasses() {
        return trainingClasses;
    }

    public void setTrainingClasses(List<TrainingClass> trainingClasses) {
        this.trainingClasses = trainingClasses;
    }
}
