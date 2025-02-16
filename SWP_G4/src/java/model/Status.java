/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Status {
    private int statusId;
    private String statusValue;

    public Status() {
    }

    public Status(int statusId, String statusValue) {
        this.statusId = statusId;
        this.statusValue = statusValue;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public String toString() {
        return "Status{" + "statusId=" + statusId + ", statusValue=" + statusValue + '}';
    }
    
    
}
