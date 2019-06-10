package com.busyqa.crm.message.request;

public class ResumeRequest {
    // we can only reset modificationDate, resumeEndDate, and resumeDoc
    private String resumeEndDate;
    private String resumeDoc;

    public String getResumeEndDate() {
        return resumeEndDate;
    }

    public void setResumeEndDate(String resumeEndDate) {
        this.resumeEndDate = resumeEndDate;
    }

    public String getResumeDoc() {
        return resumeDoc;
    }

    public void setResumeDoc(String resumeDoc) {
        this.resumeDoc = resumeDoc;
    }
}
