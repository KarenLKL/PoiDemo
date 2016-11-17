package com.newbee.poi.entity;

/**
 * 日志实体类
 * <p>
 * Created by Major on 2016/11/17.
 */
public class Log {
    private String userName;
    private String module;
    private String action;
    private String actionTime;
    private String userIP;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    @Override
    public String toString() {
        return "Log{" +
                "userName='" + userName + '\'' +
                ", module='" + module + '\'' +
                ", action='" + action + '\'' +
                ", actionTime='" + actionTime + '\'' +
                ", userIP='" + userIP + '\'' +
                '}';
    }
}
