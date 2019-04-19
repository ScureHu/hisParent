package com.zcmu.patient.pojo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 患者表
 */
@Entity
@Table(name = "zcmu_patient")
public class Patient implements Serializable {

    @Id
    private String uuid;
    //病区号
    private String wardcode;
    //病床号
    private String bedNo;
    //患者姓名
    private String name;
    //患者性别
    private String sex;
    //患者生日
    private Date birthday;
    //年龄
    private Integer age;
    //护理等级
    private String nursingLevel;
    //诊断
    private String diagnosis;
    //主治医生
    private String attnDoctor;
    //入院时间
    private Date inAdmitTime;
    //出院时间
    private Date outAdmitTime;
    //身份证号
    private String idNo;
    //家庭住址
    private String homeAddress;
    //联系人姓名
    private String contactName;
    //联系人电话
    private String contactPhone;
    //出院状态
    private String status;
    //身高
    private String height;
    //体重
    private String weight;
    //部门编号
    private String depetCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWardcode() {
        return wardcode;
    }

    public void setWardcode(String wardcode) {
        this.wardcode = wardcode;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNursingLevel() {
        return nursingLevel;
    }

    public void setNursingLevel(String nursingLevel) {
        this.nursingLevel = nursingLevel;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAttnDoctor() {
        return attnDoctor;
    }

    public void setAttnDoctor(String attnDoctor) {
        this.attnDoctor = attnDoctor;
    }

    public Date getInAdmitTime() {
        return inAdmitTime;
    }

    public void setInAdmitTime(Date inAdmitTime) {
        this.inAdmitTime = inAdmitTime;
    }

    public Date getOutAdmitTime() {
        return outAdmitTime;
    }

    public void setOutAdmitTime(Date outAdmitTime) {
        this.outAdmitTime = outAdmitTime;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDepetCode() {
        return depetCode;
    }

    public void setDepetCode(String depetCode) {
        this.depetCode = depetCode;
    }
}
