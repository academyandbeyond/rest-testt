package com.resttest.framework.Excel.model;
import com.google.gson.annotations.Expose;

public class Payload {

    @Expose
    private String appCompatibility;
    @Expose
    private String appName;
    @Expose
    private String appVersion;
    @Expose
    private String deviceId;
    @Expose
    private String deviceOs;
    @Expose
    private double deviceOsVersion;
    @Expose
    private String deviceType;

//    private String appCompatibility;
//    private String appName;
//    private String appVersion;
//    private String deviceId;
//    private String deviceOs;
//    private double deviceOsVersion;
//    private String deviceType;

    public String getAppCompatibility() {
        return appCompatibility;
    }

    public void setAppCompatibility(String appCompatibility) {
        this.appCompatibility = appCompatibility;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public double getDeviceOsVersion() {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion(double deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

}


