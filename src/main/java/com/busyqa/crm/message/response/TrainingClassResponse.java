package com.busyqa.crm.message.response;

public class TrainingClassResponse {
    private long id;
    private String name;
    private String instructorName;
    private String address;
    private String start;
    private String end;

    public TrainingClassResponse() {
    }

    public TrainingClassResponse(long id, String name, String instructorName, String address) {
        this.id = id;
        this.name = name;
        this.instructorName = instructorName;
        this.address = address;
    }

    public TrainingClassResponse(long id, String name, String instructorName, String address, String start, String end) {
        this.id = id;
        this.name = name;
        this.instructorName = instructorName;
        this.address = address;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
