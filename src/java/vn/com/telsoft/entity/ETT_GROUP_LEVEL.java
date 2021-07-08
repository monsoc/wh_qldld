/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.entity;

import java.io.Serializable;

/**
 *
 * @author xuanb
 */
public class ETT_GROUP_LEVEL implements Serializable{
    private int GROUP_LEVEL_ID;
    private String GROUP_DES;
    private String GROUP_CODE;
    private String REPORT_NAME;

    public ETT_GROUP_LEVEL(Integer GROUP_LEVEL_ID, String GROUP_DES) {
        this.GROUP_LEVEL_ID = GROUP_LEVEL_ID;
        this.GROUP_DES = GROUP_DES;
    }

    public ETT_GROUP_LEVEL() {
    }

    public int getGROUP_LEVEL_ID() {
        return GROUP_LEVEL_ID;
    }

    public void setGROUP_LEVEL_ID(int GROUP_LEVEL_ID) {
        this.GROUP_LEVEL_ID = GROUP_LEVEL_ID;
    }

    public String getGROUP_DES() {
        return GROUP_DES;
    }

    public void setGROUP_DES(String GROUP_DES) {
        this.GROUP_DES = GROUP_DES;
    }

    public String getGROUP_CODE() {
        return GROUP_CODE;
    }

    public void setGROUP_CODE(String GROUP_CODE) {
        this.GROUP_CODE = GROUP_CODE;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }
    
    
    
}
