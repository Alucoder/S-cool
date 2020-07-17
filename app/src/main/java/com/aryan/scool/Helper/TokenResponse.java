package com.aryan.scool.Helper;

public class TokenResponse {
    private String status, token, adm;

    public TokenResponse(String status, String token, String adm) {
        this.status = status;
        this.token = token;
        this.adm = adm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }
}
