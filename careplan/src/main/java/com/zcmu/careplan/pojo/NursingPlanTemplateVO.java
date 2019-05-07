package com.zcmu.careplan.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NursingPlanTemplateVO implements Serializable {
    private String id;

    private String name;

    private String isValid;

    private String content;

    private Date healthTime;

    public Date getHealthTime() {
        return healthTime;
    }

    public void setHealthTime(Date healthTime) {
        this.healthTime = healthTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTarget() {
        return target;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

    public List<String> getNursingMeasure() {
        return nursingMeasure;
    }

    public void setNursingMeasure(List<String> nursingMeasure) {
        this.nursingMeasure = nursingMeasure;
    }

    private List<String> target;

    private List<String> nursingMeasure;


}
