package com.busyqa.crm.message.response;

public class LeadResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean paidDeposite;
    private String aTrainingClassName;

    public LeadResponse() {
    }

    public LeadResponse(String firstName, String lastName, String phone, String email, boolean paidDeposite, String aTrainingClassName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paidDeposite = paidDeposite;
        this.aTrainingClassName = aTrainingClassName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPaidDeposite() {
        return paidDeposite;
    }

    public void setPaidDeposite(boolean paidDeposite) {
        this.paidDeposite = paidDeposite;
    }

    public String getaTrainingClassName() {
        return aTrainingClassName;
    }

    public void setaTrainingClassName(String aTrainingClassName) {
        this.aTrainingClassName = aTrainingClassName;
    }
}
