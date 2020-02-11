package com.davina.domain;

import java.sql.Timestamp;

public class SysLog {

    private Long id;
    private Timestamp visitTime;
    private String username;
    private String ip;
    private String method;

    public SysLog(Long id, Timestamp visitTime, String username, String ip, String method) {
        this.id = id;
        this.visitTime = visitTime;
        this.username = username;
        this.ip = ip;
        this.method = method;
    }

    public SysLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
