package com.zcmu.order.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 接收前端传入要拆分的患者 和 拆分的药物
 */
public class SplitOrderVO implements Serializable {
    private List<String> patientIds;

    private List<String> drugUseIds;

    public List<String> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<String> patientIds) {
        this.patientIds = patientIds;
    }

    public List<String> getDrugUseIds() {
        return drugUseIds;
    }

    public void setDrugUseIds(List<String> drugUseIds) {
        this.drugUseIds = drugUseIds;
    }
}
