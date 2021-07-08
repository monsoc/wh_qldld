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
public class ETT_DETAIL_LEVEL implements Serializable{
    private Integer DETAIL_ID;
    private Integer GROUP_LEVEL_ID;
    private String DETAIL_DES;
    private String PAR_ID;
    private String DETAIL_LEVEL_CODE;
    private String QLDV_ID;
    private String BLOCK_INPUT;
    private String RULE_INFO;

    public ETT_DETAIL_LEVEL() {
    }

    public ETT_DETAIL_LEVEL(Integer DETAIL_ID, Integer GROUP_LEVEL_ID, String DETAIL_DES, String PAR_ID, String DETAIL_LEVEL_CODE, String QLDV_ID, String BLOCK_INPUT) {
        this.DETAIL_ID = DETAIL_ID;
        this.GROUP_LEVEL_ID = GROUP_LEVEL_ID;
        this.DETAIL_DES = DETAIL_DES;
        this.PAR_ID = PAR_ID;
        this.DETAIL_LEVEL_CODE = DETAIL_LEVEL_CODE;
        this.QLDV_ID = QLDV_ID;
        this.BLOCK_INPUT = BLOCK_INPUT;
    }

    public String getRULE_INFO() {
        return RULE_INFO;
    }

    public void setRULE_INFO(String RULE_INFO) {
        this.RULE_INFO = RULE_INFO;
    }

    
    
    public String getBLOCK_INPUT() {
        return BLOCK_INPUT;
    }

    public void setBLOCK_INPUT(String BLOCK_INPUT) {
        this.BLOCK_INPUT = BLOCK_INPUT;
    }


    

    public Integer getDETAIL_ID() {
        return DETAIL_ID;
    }

    public void setDETAIL_ID(Integer DETAIL_ID) {
        this.DETAIL_ID = DETAIL_ID;
    }

    public Integer getGROUP_LEVEL_ID() {
        return GROUP_LEVEL_ID;
    }

    public void setGROUP_LEVEL_ID(Integer GROUP_LEVEL_ID) {
        this.GROUP_LEVEL_ID = GROUP_LEVEL_ID;
    }

    public String getDETAIL_DES() {
        return DETAIL_DES;
    }

    public void setDETAIL_DES(String DETAIL_DES) {
        this.DETAIL_DES = DETAIL_DES;
    }

    public String getPAR_ID() {
        return PAR_ID;
    }

    public void setPAR_ID(String PAR_ID) {
        this.PAR_ID = PAR_ID;
    }

    public String getDETAIL_LEVEL_CODE() {
        return DETAIL_LEVEL_CODE;
    }

    public void setDETAIL_LEVEL_CODE(String DETAIL_LEVEL_CODE) {
        this.DETAIL_LEVEL_CODE = DETAIL_LEVEL_CODE;
    }

    public String getQLDV_ID() {
        return QLDV_ID;
    }

    public void setQLDV_ID(String QLDV_ID) {
        this.QLDV_ID = QLDV_ID;
    }

    

}
