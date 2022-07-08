package com.fyp.iot.project;

public class DataModel {
    private String Alert;
    private String BPM;
    private String Spo2;
    private String Temp;

    public DataModel() {
        this.Alert = "";
        this.BPM = "";
        this.Spo2 = "";
        this.Temp = "";
    }

    public String getAlert() {
        return Alert;
    }

    public void setAlert(String alert) {
        Alert = alert;
    }

    public String getBPM() {
        return BPM;
    }

    public void setBPM(String BPM) {
        this.BPM = BPM;
    }

    public String getSpo2() {
        return Spo2;
    }

    public void setSpo2(String spo2) {
        Spo2 = spo2;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }
}
