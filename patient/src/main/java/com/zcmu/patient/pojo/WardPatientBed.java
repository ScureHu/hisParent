package com.zcmu.patient.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "zcmu_ward_patient")
public class WardPatientBed implements Serializable {
    @Id
    private String id;

    private Integer bedNo;

    private String wardName;

    private String patientId;

    private String status;

    private String wardcode;

    public String getWardcode() {
        return wardcode;
    }

    public void setWardcode(String wardcode) {
        this.wardcode = wardcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
