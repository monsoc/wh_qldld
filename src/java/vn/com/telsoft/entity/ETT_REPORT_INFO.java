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
public class ETT_REPORT_INFO {
    String REPORT_NAME;
    String REPORT_DESCRIPTION;

    public ETT_REPORT_INFO(String REPORT_NAME, String REPORT_DESCRIPTION) {
        this.REPORT_NAME = REPORT_NAME;
        this.REPORT_DESCRIPTION = REPORT_DESCRIPTION;
    }

    public ETT_REPORT_INFO() {
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }

    public String getREPORT_DESCRIPTION() {
        return REPORT_DESCRIPTION;
    }

    public void setREPORT_DESCRIPTION(String REPORT_DESCRIPTION) {
        this.REPORT_DESCRIPTION = REPORT_DESCRIPTION;
    }

    
    
}
