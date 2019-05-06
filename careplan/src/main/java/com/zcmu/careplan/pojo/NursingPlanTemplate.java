package com.zcmu.careplan.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "zcmu_nursing_plan_template")
public class NursingPlanTemplate implements Serializable {

    @Id
    private String id;

    private String name;

    private String isValid;

    private String content;

    private String target;

    private String nursingMeasure;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNursingMeasure() {
        return nursingMeasure;
    }

    public void setNursingMeasure(String nursingMeasure) {
        this.nursingMeasure = nursingMeasure;
    }
}
