package com.busyqa.crm.message.request;

import java.util.List;

public class EmployeeRequest extends UserRequest{
    private int employeeField;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String name, String username, String email, List<String> positions, String status, String statusAsOfDay, int employeeField) {
        super(name, username, email, positions, status, statusAsOfDay);
        this.employeeField = employeeField;
    }

    public int getEmployeeField() {
        return employeeField;
    }

    public void setEmployeeField(int employeeField) {
        this.employeeField = employeeField;
    }
}
