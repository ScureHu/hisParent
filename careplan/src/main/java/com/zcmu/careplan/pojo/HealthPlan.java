package com.zcmu.careplan.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "zcmu_health_plan")
public class HealthPlan implements Serializable {

    @Id
    private String id;

    private String patientId;

    private String itemId;

    private Date record_Time;

    private String recorderId;

    private String recorderName;

    private String healthFunction;

    private String itemContent;

    private String healthPeople;

    private Date healthTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getRecord_Time() {
        return record_Time;
    }

    public void setRecord_Time(Date record_Time) {
        this.record_Time = record_Time;
    }

    public String getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(String recorderId) {
        this.recorderId = recorderId;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    public String getHealthFunction() {
        return healthFunction;
    }

    public void setHealthFunction(String healthFunction) {
        this.healthFunction = healthFunction;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getHealthPeople() {
        return healthPeople;
    }

    public void setHealthPeople(String healthPeople) {
        this.healthPeople = healthPeople;
    }

    public Date getHealthTime() {
        return healthTime;
    }

    public void setHealthTime(Date healthTime) {
        this.healthTime = healthTime;
    }
}

