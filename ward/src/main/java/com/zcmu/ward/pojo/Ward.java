package com.zcmu.ward.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 病区实体类
 */
@Entity
@Table(name = "ward")
public class Ward {
    @Id
    private String wardcode;

    private String bedno;

    private String parentid;

    private String status;

    public String getWardcode() {
        return wardcode;
    }

    public void setWardcode(String wardcode) {
        this.wardcode = wardcode;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
