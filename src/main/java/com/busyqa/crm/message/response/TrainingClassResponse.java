package com.busyqa.crm.message.response;

public class TrainingClassResponse {
    private String name;
    private String instructorName;

    public TrainingClassResponse() {
    }

    public TrainingClassResponse(String name, String instructorName) {
        this.name = name;
        this.instructorName = instructorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
}
