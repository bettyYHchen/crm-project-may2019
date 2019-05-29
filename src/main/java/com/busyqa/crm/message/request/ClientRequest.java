package com.busyqa.crm.message.request;

import java.util.List;

public class ClientRequest extends UserRequest{

    private String phone;
    private List<String> courseName;
    private List<String> batch;

    public ClientRequest() {
    }

    public ClientRequest(String name, String username, String email, List<String> positions, String status, String statusAsOfDay, String phone, List<String> courseName, List<String> batch) {
        super(name, username, email, positions, status, statusAsOfDay);
        this.phone = phone;
        this.courseName = courseName;
        this.batch = batch;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(List<String> courseName) {
        this.courseName = courseName;
    }

    public List<String> getBatch() {
        return batch;
    }

    public void setBatch(List<String> batch) {
        this.batch = batch;
    }
}
