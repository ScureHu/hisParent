package com.zcmu.sign.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 `id` varchar(20) NOT NULL COMMENT 'ID',
 `patient_id` varchar(20) DEFAULT NULL COMMENT '病人ID',
 `patient_name` varchar(20) DEFAULT NULL COMMENT '病人名称',
 `measure_time` datetime DEFAULT NULL COMMENT '测量时间点',
 `record_time` datetime DEFAULT NULL COMMENT '体征的录入时间',
 `t` double DEFAULT NULL COMMENT '体温',
 `p` double DEFAULT NULL COMMENT '脉搏',
 `r` double DEFAULT NULL COMMENT '心率',
 `bp` double DEFAULT NULL COMMENT '血压',
 `unit` VARCHAR(20)  DEFAULT NULL COMMENT '体征单位',
 `remark` VARCHAR(200)  DEFAULT NULL COMMENT '备注',
 `source` VARCHAR(1)  DEFAULT NULL COMMENT '数据来源1->首页录入，2->批量录入修改',
 `record_nurse_id` varchar(20) DEFAULT NULL COMMENT '记录人代码',
 `record_nurse_name` varchar(20) DEFAULT NULL COMMENT '记录人名称',
 */
@Entity
@Table(name = "zcmu_sign")
public class SignDefault implements Serializable {

    @Id
    private String id;

    private String patientId;

    private String patientName;

    private Date measureTime;

    private Date recordTime;
    //体温
    private Double t;
    //脉搏
    private Double p;
    //心率
    private Double r;
    //血压
    private Double bp;

    private String unit;

    private String source;

    private String recordNurseId;

    private String recordNurseName;

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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Date measureTime) {
        this.measureTime = measureTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getBp() {
        return bp;
    }

    public void setBp(Double bp) {
        this.bp = bp;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRecordNurseId() {
        return recordNurseId;
    }

    public void setRecordNurseId(String recordNurseId) {
        this.recordNurseId = recordNurseId;
    }

    public String getRecordNurseName() {
        return recordNurseName;
    }

    public void setRecordNurseName(String recordNurseName) {
        this.recordNurseName = recordNurseName;
    }
}
