/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import com.faplib.admin.security.User;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.util.DataUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import vn.com.telsoft.model.SEARCH_REPORT_Model;
import vn.com.telsoft.model.QL_DLD_Model;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public class SEARCH_REPORT_Controller extends TSFuncTemplate implements Serializable {

    private List<Vector> lListReportName = new ArrayList<>();
    private List<Vector> lListGroupUser = new ArrayList<>();
    private String mstrREPORT_NAME = "";
    private int miUSER_ID;
    private int miQUARTER;
    private int miYEAR;
    private int miGroupUserLevel;
    private int iGROUP_USER;

    private boolean bExportSub = false;

    /**
     * Creates a new instance of EXP_REPORT_Controller
     */
    public SEARCH_REPORT_Controller() {
        try {
            miUSER_ID = Integer.parseInt(User.getUserLogged().getUserId());
            lListReportName = (List<Vector>) DataUtil.getData(SEARCH_REPORT_Model.class, "getListReportName");
            lListGroupUser = (List<Vector>) DataUtil.performAction(SEARCH_REPORT_Model.class, "getListGroupUser", miUSER_ID);
            String strGroupUserLevel = (String) DataUtil.performAction(QL_DLD_Model.class, "getGroupUserLevel", miUSER_ID);
            miGroupUserLevel = Integer.parseInt(strGroupUserLevel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public DefaultStreamedContent searchReport() {
        SimpleDateFormat fmtDate = new SimpleDateFormat("dd/MM/yyyy");
        DefaultStreamedContent dfs = null;
        int iEXPORT_SUB = 0; // 0: Export cấp hiện tại| 1: Export bao gồm cả cấp con
        try {
        } catch (Exception ex) {
            Logger.getLogger(EXP_REPORT_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dfs;
    }

    @Override
    public void handOK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Vector> getListReportName() {
        return lListReportName;
    }

    public void setListReportName(List<Vector> lListReportName) {
        this.lListReportName = lListReportName;
    }

    public String getREPORT_NAME() {
        return mstrREPORT_NAME;
    }

    public void setREPORT_NAME(String strREPORT_NAME) {
        this.mstrREPORT_NAME = strREPORT_NAME;
    }

    public int getQUARTER() {
        return miQUARTER;
    }

    public void setQUARTER(int miQARTER) {
        this.miQUARTER = miQARTER;
    }

    public int getYEAR() {
        return miYEAR;
    }

    public void setYEAR(int miYEAR) {
        this.miYEAR = miYEAR;
    }

    public boolean isExportSub() {
        return bExportSub;
    }

    public void setExportSub(boolean bExportSub) {
        this.bExportSub = bExportSub;
    }

    public int getGROUP_USER() {
        return iGROUP_USER;
    }

    public void setGROUP_USER(int iGROUP_USER) {
        this.iGROUP_USER = iGROUP_USER;
    }

    public List<Vector> getListGroupUser() {
        return lListGroupUser;
    }

    public void setListGroupUser(List<Vector> lListGroupUser) {
        this.lListGroupUser = lListGroupUser;
    }

}
