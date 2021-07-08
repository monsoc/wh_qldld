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

public class ETT_APPROVED {
    String DONVI;
    int SL_BMPKB;
    int SL_BMDKB;
    int SL_BMCKB;
    String DS_BMCKB;
    String TT_CHOTKB;

    public ETT_APPROVED() {
    }

    public ETT_APPROVED(String DONVI, int SL_BMPKB, int SL_BMDKB, int SL_BMCKB, String DS_BMCKB, String TT_CHOTKB) {
        this.DONVI = DONVI;
        this.SL_BMPKB = SL_BMPKB;
        this.SL_BMDKB = SL_BMDKB;
        this.SL_BMCKB = SL_BMCKB;
        this.DS_BMCKB = DS_BMCKB;
        this.TT_CHOTKB = TT_CHOTKB;
    }
    
    

    public String getDONVI() {
        return DONVI;
    }

    public void setDONVI(String DONVI) {
        this.DONVI = DONVI;
    }

    public int getSL_BMPKB() {
        return SL_BMPKB;
    }

    public void setSL_BMPKB(int SL_BMPKB) {
        this.SL_BMPKB = SL_BMPKB;
    }

    public int getSL_BMDKB() {
        return SL_BMDKB;
    }

    public void setSL_BMDKB(int SL_BMDKB) {
        this.SL_BMDKB = SL_BMDKB;
    }

    public int getSL_BMCKB() {
        return SL_BMCKB;
    }

    public void setSL_BMCKB(int SL_BMCKB) {
        this.SL_BMCKB = SL_BMCKB;
    }

    public String getDS_BMCKB() {
        return DS_BMCKB;
    }

    public void setDS_BMCKB(String DS_BMCKB) {
        this.DS_BMCKB = DS_BMCKB;
    }

    public String getTT_CHOTKB() {
        return TT_CHOTKB;
    }

    public void setTT_CHOTKB(String TT_CHOTKB) {
        this.TT_CHOTKB = TT_CHOTKB;
    }
    
    
    
}
