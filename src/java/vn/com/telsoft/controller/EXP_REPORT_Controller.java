/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import com.faplib.admin.security.User;
import com.faplib.lib.ClientMessage;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.TelsoftException;
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
import vn.com.telsoft.model.EXP_REPORT_Model;
import vn.com.telsoft.model.QL_DLD_Model;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public class EXP_REPORT_Controller extends TSFuncTemplate implements Serializable {

    private List<Vector> lListReportName = new ArrayList<>();
    private String mstrREPORT_NAME = "";
    private int miUSER_ID;
    private int miQUARTER;
    private int miYEAR;
    private int miGroupUserLevel;
    
    private boolean  bExportSub = false;

    /**
     * Creates a new instance of EXP_REPORT_Controller
     */
    public EXP_REPORT_Controller() {
        try {
            lListReportName = (List<Vector>) DataUtil.getData(EXP_REPORT_Model.class, "getListReportName");
            miUSER_ID = Integer.parseInt(User.getUserLogged().getUserId());
            String strGroupUserLevel = (String) DataUtil.performAction(QL_DLD_Model.class, "getGroupUserLevel", miUSER_ID);
            miGroupUserLevel = Integer.parseInt(strGroupUserLevel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public DefaultStreamedContent exportExcelReport() {
        SimpleDateFormat fmtDate = new SimpleDateFormat("dd/MM/yyyy");
        DefaultStreamedContent dfs = null;
        int iEXPORT_SUB = 0; // 0: Export cấp hiện tại| 1: Export bao gồm cả cấp con
        try {
            if(bExportSub){
                iEXPORT_SUB = 1;
            }
            switch (mstrREPORT_NAME) {
                case "KTDV_D30":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportKTDV_D30", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "KTDV_D30_TC":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportKTDV_D30_TC", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "GSDV_CAPUY":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportGSDV_CAPUY", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "GSTCD_CAPUY":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportGSTCD_CAPUY", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "THKL_DV_CAPUY":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportTHKL_DV_CAPUY", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "THKL_TCD_CAPUY":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportTHKL_TCD_CAPUY", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "GQKNKL_DV_CAPUY":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportGQKNKL_DV_CAPUY", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "GQKNKL_TCD_CAPUY":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportGQKNKL_TCD_CAPUY", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "KTDHVPDV":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportKTDHVPDV", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "KTDHVPTC":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportKTDHVPTC", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "KT_THNVKT":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportKT_THNVKT", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "KT_THKL":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportKT_THKL", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "GSDV_UBKT":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportGSDV_UBKT", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "GSTCD_UBKT":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportGSTCD_UBKT", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                case "THKL_DV_UBKT_CACCAP":
                    dfs = (DefaultStreamedContent) DataUtil.performAction(EXP_REPORT_Model.class, "exportTHKL_DV_UBKT_CACCAP", miGroupUserLevel, miYEAR, miQUARTER,mstrREPORT_NAME, iEXPORT_SUB);
                    if (dfs == null) {
                        ClientMessage.log("Không có tồn tại dữ liệu thỏa mãn điều kiện");
                    }   break;
                default:
                    break;
            } //
        } catch (TelsoftException ex) {
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



}
