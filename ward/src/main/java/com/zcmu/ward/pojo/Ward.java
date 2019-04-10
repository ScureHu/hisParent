package com.zcmu.ward.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 病区实体类
 */
@Entity
@Table(name = "zcmu_ward")
public class Ward {
    @Id
    private String wardcode;

    private Integer bedSum;

    private String wardName;

    public String getWardcode() {
        return wardcode;
    }

    public void setWardcode(String wardcode) {
        this.wardcode = wardcode;
    }

    public Integer getBedSum() {
        return bedSum;
    }

    public void setBedSum(Integer bedSum) {
        this.bedSum = bedSum;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
