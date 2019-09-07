package com.example.suggestion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessResponse {

    private String status;

    public SuccessResponse() {
        this.status = "success";
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
