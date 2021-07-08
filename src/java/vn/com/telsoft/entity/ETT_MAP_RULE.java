/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.entity;

/**
 *
 * @author xuanb
 */
public class ETT_MAP_RULE {
    String RULE_ID;
    String GROUP_LEVEL_ID;
    String DETAIL_LEVEL_ID;
    String CL_ID;
    String LIST_GROUP_USER_ID;
    String REPORT_NAME;
    String STATUS;
    
    String GROUP_LEVEL_DES;
    String DETAIL_LEVEL_DES;

    public ETT_MAP_RULE() {
    }

    public String getGROUP_LEVEL_ID() {
        return GROUP_LEVEL_ID;
    }

    public void setGROUP_LEVEL_ID(String GROUP_LEVEL_ID) {
        this.GROUP_LEVEL_ID = GROUP_LEVEL_ID;
    }

    public String getRULE_ID() {
        return RULE_ID;
    }

    public void setRULE_ID(String RULE_ID) {
        this.RULE_ID = RULE_ID;
    }

    public String getDETAIL_LEVEL_ID() {
        return DETAIL_LEVEL_ID;
    }

    public void setDETAIL_LEVEL_ID(String DETAIL_LEVEL_ID) {
        this.DETAIL_LEVEL_ID = DETAIL_LEVEL_ID;
    }

    public String getCL_ID() {
        return CL_ID;
    }

    public void setCL_ID(String CL_ID) {
        this.CL_ID = CL_ID;
    }

    public String getLIST_GROUP_USER_ID() {
        return LIST_GROUP_USER_ID;
    }

    public void setLIST_GROUP_USER_ID(String GROUP_USER_ID) {
        this.LIST_GROUP_USER_ID = GROUP_USER_ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }

    public String getGROUP_LEVEL_DES() {
        return GROUP_LEVEL_DES;
    }

    public void setGROUP_LEVEL_DES(String GROUP_LEVEL_DES) {
        this.GROUP_LEVEL_DES = GROUP_LEVEL_DES;
    }

    public String getDETAIL_LEVEL_DES() {
        return DETAIL_LEVEL_DES;
    }

    public void setDETAIL_LEVEL_DES(String DETAIL_LEVEL_DES) {
        this.DETAIL_LEVEL_DES = DETAIL_LEVEL_DES;
    }
    
    
    
}
