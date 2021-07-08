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
public class ETT_COLUMN_DETAIL {
    String CL_CODE;
    String CL_NAME;
    String REPORT_NAME;
    String CL_ID;

    public ETT_COLUMN_DETAIL() {
    }

    public ETT_COLUMN_DETAIL(String CL_CODE, String CL_NAME, String REPORT_NAME, String CL_ID) {
        this.CL_CODE = CL_CODE;
        this.CL_NAME = CL_NAME;
        this.REPORT_NAME = REPORT_NAME;
        this.CL_ID = CL_ID;
    }

    
    
    public String getCL_CODE() {
        return CL_CODE;
    }

    public void setCL_CODE(String CL_CODE) {
        this.CL_CODE = CL_CODE;
    }

    public String getCL_NAME() {
        return CL_NAME;
    }

    public void setCL_NAME(String CL_NAME) {
        this.CL_NAME = CL_NAME;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }

    public String getCL_ID() {
        return CL_ID;
    }

    public void setCL_ID(String CL_ID) {
        this.CL_ID = CL_ID;
    }
    
    
}
