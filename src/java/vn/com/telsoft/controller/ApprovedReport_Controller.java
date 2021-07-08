/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import com.faplib.admin.security.User;
import com.faplib.lib.ClientMessage;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.util.DataUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vn.com.telsoft.entity.ETT_APPROVED;
import vn.com.telsoft.model.ApprovedReport_Model;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public class ApprovedReport_Controller extends TSFuncTemplate implements Serializable {

    private List<Vector> lListGroupUser = new ArrayList<>();
    private int miUSER_ID;
    private int miQUARTER;
    private int miYEAR;
    private int miGroupUser;
    private int miCapBC;
    private boolean bDisplayTable = false;
    private boolean bDisplayButtonApproved = false;

    private List<ETT_APPROVED> mlDataSearch = new ArrayList<>();

    private boolean bExportSub = false;

    /**
     * Creates a new instance of EXP_REPORT_Controller
     */
    public ApprovedReport_Controller() {
        try {
            miUSER_ID = Integer.parseInt(User.getUserLogged().getUserId());
            String[] arrInfoUser = (String[]) DataUtil.performAction(ApprovedReport_Model.class, "getInfoUser", miUSER_ID);
            if (arrInfoUser.length >= 2) {
                miGroupUser = Integer.parseInt(arrInfoUser[0]);
                miCapBC = Integer.parseInt(arrInfoUser[1]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void searchInfoReport() {
        try {
            bDisplayButtonApproved = false;
            mlDataSearch = (List<ETT_APPROVED>) DataUtil.performAction(ApprovedReport_Model.class, "searchInfoReport", miCapBC, miGroupUser, miYEAR, miQUARTER);
            if (mlDataSearch.size() <= 0) {
                bDisplayTable = false;
                ClientMessage.log("Không có dữ liệu thỏa mãn");
            } else {
                bDisplayTable = true;
                // Duyet hien thi Button chot so lieu
                for (int i = 0; i < mlDataSearch.size(); i++) {
                    ETT_APPROVED tmp = mlDataSearch.get(i);
                    if (tmp.getTT_CHOTKB().equals("0")) {
                        bDisplayButtonApproved = true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void approvedReport() {
        try {
            DataUtil.performAction(ApprovedReport_Model.class, "approvedReport", miCapBC, miGroupUser, miUSER_ID, miYEAR, miQUARTER);
            searchInfoReport();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void handOK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public List<Vector> getListGroupUser() {
        return lListGroupUser;
    }

    public void setListGroupUser(List<Vector> lListGroupUser) {
        this.lListGroupUser = lListGroupUser;
    }

    public List<ETT_APPROVED> getDataSearch() {
        return mlDataSearch;
    }

    public void setDataSearch(List<ETT_APPROVED> mlDataSearch) {
        this.mlDataSearch = mlDataSearch;
    }

    public boolean isDisplayTable() {
        return bDisplayTable;
    }

    public void setDisplayTable(boolean bDisplayTable) {
        this.bDisplayTable = bDisplayTable;
    }

    public boolean isDisplayButtonApproved() {
        return bDisplayButtonApproved;
    }

    public void setDisplayButtonApproved(boolean bDisplayButtonApproved) {
        this.bDisplayButtonApproved = bDisplayButtonApproved;
    }

}
