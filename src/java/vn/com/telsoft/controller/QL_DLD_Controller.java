/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import com.faplib.admin.security.User;
import com.faplib.lib.ClientMessage;
import com.faplib.lib.SystemLogger;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.config.Config;
import com.faplib.lib.util.DataUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import vn.com.telsoft.entity.ETT_QLDLD;
import vn.com.telsoft.model.QL_DLD_Model;
import vn.com.telsoft.util.AppUtil;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public final class QL_DLD_Controller extends TSFuncTemplate implements Serializable {

    /**
     * Creates a new instance of KTDV_D30_Controller
     */
    private List<ETT_QLDLD> mListParamTable;
    private int miUSER_ID;
    private String mstrGroupUserLevel = "";
    private String mstrREPORT_NAME = "";
    private Map mMapRuleInfo = new HashMap();

    private boolean bCheckDisplayButtonSave = false;
    private boolean bCheckDisplayButtonEdit = false;
    private boolean bCheckDisableButtonEdit = true;

    private boolean bDisplayTableParam = false;
    private boolean bEditTableParam = false;

    private boolean bIsEditAction = false;

    private boolean bDisplayInput = false;

    private int miQUARTER;
    private int miYEAR;

    private boolean bEditDialog = false;
    private String mstrMessageDialog = "";

    public QL_DLD_Controller() {
        try {
            mstrREPORT_NAME = Config.getCurrentPath().replaceAll("/client/", "");
            miUSER_ID = Integer.parseInt(User.getUserLogged().getUserId());
            mstrGroupUserLevel = (String) DataUtil.performAction(QL_DLD_Model.class, "getGroupUserLevel", miUSER_ID);
            mMapRuleInfo = (Map) DataUtil.performAction(QL_DLD_Model.class, "getMapRuleInfo", mstrREPORT_NAME);
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void saveInfo() {
        try {
            for (ETT_QLDLD objQLDLD : mListParamTable) {
                // Check ban tin co khai bao 
                if (objQLDLD.getDETAIL_ID() > 0) {
                    if (objQLDLD.CheckUpdateValue()) {
                        DataUtil.performAction(QL_DLD_Model.class, "saveInfoReport", objQLDLD, miUSER_ID, mstrREPORT_NAME, miYEAR, miQUARTER);
                    }
                }
            }
            ClientMessage.log("Cập nhật biểu mẫu thành công");
        } catch (Exception ex) {
            Logger.getLogger(QL_DLD_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionReport(String strAction) {
        try {
            if (strAction.equals("SEARCH")) {
                // Check du lieu DB
                bCheckDisplayButtonSave = false;
                bEditTableParam = false;
                mListParamTable = null;
                mListParamTable = (List<ETT_QLDLD>) DataUtil.performAction(QL_DLD_Model.class, "searchInfo", miUSER_ID, mstrREPORT_NAME, miYEAR, miQUARTER);
                if (mListParamTable == null) {
                    bDisplayTableParam = false;
                    bCheckDisplayButtonEdit = false;
                    ClientMessage.log("Không có dữ liệu thỏa mãn");
                } else {
                    bCheckDisplayButtonEdit = true;
                    bDisplayTableParam = true;
                    String strExistInfo = (String) DataUtil.performAction(QL_DLD_Model.class, "checkExistInfo", miUSER_ID, mstrREPORT_NAME, miYEAR, miQUARTER);
                    if (strExistInfo.equals("0")) {
                        // Chua duyệt ==> duoc phep sua
                        bCheckDisableButtonEdit = false;
                    } else {
                        // Da duyet, khong dc phep sua
                        bCheckDisableButtonEdit = true;

                    }

                }
                bDisplayInput = false;
            } else if (strAction.equals("ADD")) {
                // Kiem tra da khai bao chua
                String strExistInfo = (String) DataUtil.performAction(QL_DLD_Model.class, "checkExistInfo", miUSER_ID, mstrREPORT_NAME, miYEAR, miQUARTER);
                if (!strExistInfo.equals("")) {
                    // Da khai bao ==> bat dialog thong bao
                    RequestContext.getCurrentInstance().execute("PF('dlMessageAddInfo').show()");
                    if (strExistInfo.equals("0")) {
                        // Chua chot so lieu => duoc phep sua
                        bEditDialog = true;
                        mstrMessageDialog = "Đã khai báo số liệu cho Quý được chọn";

                    } else {
                        // Da cho so lieu => khong duoc phep sua
                        bEditDialog = false;
                        mstrMessageDialog = "Đã khai báo số liệu cho Quý được chọn. Số liệu đã được chốt, không được phép sửa đổi";
                    }
                } else {
                    // Chua khai bao ==> gen table moi cho phep khai bao
                    mListParamTable = (List<ETT_QLDLD>) DataUtil.performAction(QL_DLD_Model.class, "getParameTable", mstrREPORT_NAME);
                    // Cho phep khai bao
                    bCheckDisplayButtonSave = true;
                    bDisplayTableParam = true;
                    bEditTableParam = true;
                    bCheckDisplayButtonEdit = false;
                    bIsEditAction = false;
                    bDisplayInput = false;
                }
            } else if (strAction.equals("EDIT")) {
                bEditTableParam = true;
                bCheckDisplayButtonSave = true;
                bCheckDisplayButtonEdit = false;
                bIsEditAction = true;
                bDisplayInput = true;
            } else if (strAction.equals("SAVE")) {
                try {
                    DataUtil.performAction(QL_DLD_Model.class, "saveInfoReport", mListParamTable, miUSER_ID, mstrREPORT_NAME, miYEAR, miQUARTER, bIsEditAction);
                } catch (Exception ex) {
                    ClientMessage.log("Cập nhật biểu mẫu không thành công");
                    Logger.getLogger(QL_DLD_Controller.class.getName()).log(Level.SEVERE, null, ex);
                    throw new Exception(ex.toString());
                }
                ClientMessage.log("Cập nhật biểu mẫu thành công");
                // Cho phep khai bao
                bCheckDisplayButtonSave = false;
                bEditTableParam = false;
                bCheckDisplayButtonEdit = false;
                bDisplayInput = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean isCheckDisabledCell(int iDetailID, String strColumnName) {
        String strKEY = strColumnName + "_" + iDetailID;
        if (mMapRuleInfo.containsKey(strKEY)) {
            String strListUser = mMapRuleInfo.get(strKEY).toString();
            return AppUtil.isMember(mstrGroupUserLevel, strListUser, ",");
        }
        return false;
    }

    public List<ETT_QLDLD> getListParamTable() {
        return mListParamTable;
    }

    public void setListParamTable(List<ETT_QLDLD> mlvtDataSearch) {
        this.mListParamTable = mlvtDataSearch;
    }

    public boolean isCheckDisplayButtonSave() {
        return bCheckDisplayButtonSave;
    }

    public void setCheckDisplayButtonSave(boolean bCheckDisplayButtonSave) {
        this.bCheckDisplayButtonSave = bCheckDisplayButtonSave;
    }

    public boolean isDisplayTableParam() {
        return bDisplayTableParam;
    }

    public void setDisplayTableParam(boolean bDisplayTableParam) {
        this.bDisplayTableParam = bDisplayTableParam;
    }

    public int getQUARTER() {
        return miQUARTER;
    }

    public void setQUARTER(int miQUARTER) {
        this.miQUARTER = miQUARTER;
    }

    public int getYEAR() {
        return miYEAR;
    }

    public void setYEAR(int miYEAR) {
        this.miYEAR = miYEAR;
    }

    public boolean isCheckDisplayButtonEdit() {
        return bCheckDisplayButtonEdit;
    }

    public void setCheckDisplayButtonEdit(boolean bCheckDisplayButtonEdit) {
        this.bCheckDisplayButtonEdit = bCheckDisplayButtonEdit;
    }

    public boolean isEditTableParam() {
        return bEditTableParam;
    }

    public void setEditTableParam(boolean bEditTableParam) {
        this.bEditTableParam = bEditTableParam;
    }

    public boolean isDisplayInput() {
        return bDisplayInput;
    }

    public void setDisplayInput(boolean bDisplayInput) {
        this.bDisplayInput = bDisplayInput;
    }

    public boolean isEditDialog() {
        return bEditDialog;
    }

    public void setEditDialog(boolean bEditDialog) {
        this.bEditDialog = bEditDialog;
    }

    public String getMessageDialog() {
        return mstrMessageDialog;
    }

    public void setMessageDialog(String strMessageDialog) {
        this.mstrMessageDialog = strMessageDialog;
    }

    public boolean isCheckDisableButtonEdit() {
        return bCheckDisableButtonEdit;
    }

    public void setCheckDisableButtonEdit(boolean bCheckDisableButtonEdit) {
        this.bCheckDisableButtonEdit = bCheckDisableButtonEdit;
    }

    @Override
    public void handOK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
