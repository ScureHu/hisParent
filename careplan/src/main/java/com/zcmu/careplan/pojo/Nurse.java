package com.zcmu.careplan.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 护士实体类
 */
@Entity
@Table(name = "zcmu_nurse")
public class Nurse {
    @Id
    private String nurseId;

    private String nurseName;

    private String password;

    private String nurseCode;

    private String sex;

    private String wardcode;

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNurseCode() {
        return nurseCode;
    }

    public void setNurseCode(String nurseCode) {
        this.nurseCode = nurseCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWardcode() {
        return wardcode;
    }

    public void setWardcode(String wardcode) {
        this.wardcode = wardcode;
    }
}
