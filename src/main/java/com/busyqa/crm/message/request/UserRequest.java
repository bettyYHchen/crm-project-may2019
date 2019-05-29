package com.busyqa.crm.message.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;

import java.util.List;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientRequest.class, name = "clientRequest"),
        @JsonSubTypes.Type(value = EmployeeRequest.class, name = "employeeRequest")
})
public class UserRequest {


    private String name;
    private String username;
    private String email;
    private List<String> positions;
    private String status;
    private String statusAsOfDay;

    public UserRequest() {
    }

    public UserRequest(String name, String username, String email, List<String> positions,
                       String status, String statusAsOfDay) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.positions = positions;
        this.status = status;
        this.statusAsOfDay = statusAsOfDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusAsOfDay() {
        return statusAsOfDay;
    }

    public void setStatusAsOfDay(String statusAsOfDay) {
        this.statusAsOfDay = statusAsOfDay;
    }
}
