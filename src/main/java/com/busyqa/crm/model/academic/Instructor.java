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

    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "email",unique=true, nullable=false)
    private String email;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<TrainingClass> trainingClasses;

    public Instructor() {
    }

    public Instructor(@NotBlank @Size(min = 3, max = 50) String firstName, @NotBlank @Size(min = 3, max = 50) String lastName, @NotBlank @Size(min = 3, max = 50) String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Instructor(@NotBlank @Size(min = 3, max = 50) String firstName, @NotBlank @Size(min = 3, max = 50) String lastName, @NotBlank @Size(min = 3, max = 50) String email, List<TrainingClass> trainingClasses) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
