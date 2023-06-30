package com.binar.pemesanantiketpesawat.Payload.Response;

public class UserInfoResponse {
    private String response;
    private String message;
    private String token;

    public UserInfoResponse(String response, String message, String token) {
        this.response = response;
        this.message = message;
        this.token = token;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken(){
        return token;
    }

    public void setToken(){
        this.token = token;
    }
}