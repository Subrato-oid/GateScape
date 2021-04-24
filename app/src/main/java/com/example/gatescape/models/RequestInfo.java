package com.example.gatescape.models;

public class RequestInfo {

    String reason;
    UserData userData;
    Long createdAt;

    public RequestInfo(String reason, UserData userData, Long createdAt) {
        this.reason = reason;
        this.userData = userData;
        this.createdAt = createdAt;
    }

    public RequestInfo() {

    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
