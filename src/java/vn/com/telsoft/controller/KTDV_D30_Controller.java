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
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vn.com.telsoft.entity.ETT_DETAIL_LEVEL;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_KTDV_D30;
import vn.com.telsoft.model.KTDV_D30_Model;
import vn.com.telsoft.model.QL_DLD_Model;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public class KTDV_D30_Controller extends TSFuncTemplate implements Serializable {

    /**
     * Creates a new instance of KTDV_D30_Controller
     */
    private List<ETT_KTDV_D30> mlvtDataSearch;
    private ETT_KTDV_D30 mRowSelect;
    private ETT_KTDV_D30 mInputKTDV;
    private Integer iSelectKTDV_ID;
    private List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
    private List<ETT_DETAIL_LEVEL> lDetailLevel = new ArrayList<>();
    private String mstrUserLogin = "";
    private String mstrGroupUserLevel = "";
    
    private String mstrREPORT_NAME = "";

    public KTDV_D30_Controller() {
        try {
            mInputKTDV = new ETT_KTDV_D30();
            lGroupLevel = DataUtil.getData(KTDV_D30_Model.class, "getListGroupLevel");
            mstrREPORT_NAME = Config.getCurrentPath().replaceAll("/client/", "");
            // Get du lieu khai bao
            mstrUserLogin = User.getUserLogged().getUserName();
            mstrGroupUserLevel = (String) DataUtil.performAction(QL_DLD_Model.class, "getGroupUserLevel", User.getUserLogged().getUserId());
            mlvtDataSearch = (List<ETT_KTDV_D30>) DataUtil.performAction(KTDV_D30_Model.class, "getData", mstrUserLogin);
            if (mlvtDataSearch.size() > 0) {
                mRowSelect = mInputKTDV = mlvtDataSearch.get(0);
                getListDetailLevel();
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void getListDetailLevel() {
        try {
            lDetailLevel = (List<ETT_DETAIL_LEVEL>) DataUtil.performAction(QL_DLD_Model.class, "getListDetailLevel", mInputKTDV.getGROUP_LEVEL_ID(), mstrGroupUserLevel);
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public boolean isCheckDisabled(String strListGroupUserLevel) {
        if (!isDISABLE) {
            String[] arrTMP = strListGroupUserLevel.split(",");
            for (int i = 0; i < arrTMP.length; i++) {
                if (mstrGroupUserLevel.equals(arrTMP[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void onRowSelect() {
        if (mRowSelect != null) {
            mInputKTDV = mRowSelect;
            getListDetailLevel();
        }
    }

    public boolean isSelectedRow() {
        return (null != mRowSelect);
    }

    public List<ETT_KTDV_D30> getDataSearch() {
        return mlvtDataSearch;
    }

    public void setDataSearch(List<ETT_KTDV_D30> mlvtDataSearch) {
        this.mlvtDataSearch = mlvtDataSearch;
    }

    public ETT_KTDV_D30 getRowSelect() {
        return mRowSelect;
    }

    public void setRowSelect(ETT_KTDV_D30 mRowSelect) {
        this.mRowSelect = mRowSelect;
    }

    public Integer getSelectKTDV_ID() {
        return iSelectKTDV_ID;
    }

    public void setSelectKTDV_ID(Integer iSelectKTDV_ID) {
        this.iSelectKTDV_ID = iSelectKTDV_ID;
    }

    public ETT_KTDV_D30 getInputKTDV() {
        return mInputKTDV;
    }

    public void setInputKTDV(ETT_KTDV_D30 mInputKTDV) {
        this.mInputKTDV = mInputKTDV;
    }

    public List<ETT_GROUP_LEVEL> getGroupLevel() {
        return lGroupLevel;
    }

    public void setGroupLevel(List<ETT_GROUP_LEVEL> lGroupLevel) {
        this.lGroupLevel = lGroupLevel;
    }

    public List<ETT_DETAIL_LEVEL> getDetailLevel() {
        return lDetailLevel;
    }

    public void setDetailLevel(List<ETT_DETAIL_LEVEL> lDetailLevel) {
        this.lDetailLevel = lDetailLevel;
    }

    @Override
    public void changeStateAdd() {
        super.changeStateAdd();
        mInputKTDV = new ETT_KTDV_D30();
    }

    @Override
    public void handCancel() {
        super.handCancel();
        onRowSelect();
    }

    @Override
    public void handOK() {
        if (isADD || isCOPY) {
            try {
                if (!getPermission("I")) {
                    return;
                }
                DataUtil.performAction(KTDV_D30_Model.class, "add", mInputKTDV, mstrUserLogin);
                //Load lai Du lieu
                mlvtDataSearch = (List<ETT_KTDV_D30>) DataUtil.performAction(KTDV_D30_Model.class, "getData", mstrUserLogin);
                isADD = false;
                isCOPY = false;
                isDISABLE = true;
                ClientMessage.logAdd();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ADD, ex.getLocalizedMessage());
            }
        } else if (isEDIT) {
            try {
                if (!getPermission("U")) {
                    return;
                }
                DataUtil.performAction(KTDV_D30_Model.class, "edit", mInputKTDV);
                //Load lai Du lieu
                mlvtDataSearch = (List<ETT_KTDV_D30>) DataUtil.performAction(KTDV_D30_Model.class, "getData", mstrUserLogin);

                isEDIT = false;
                isDISABLE = true;
                ClientMessage.logUpdate();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.UPDATE, ex.getLocalizedMessage());
            }
        }
    }

    @Override
    public void handDelete() {
        if (isDELETE) {
            try {
                if (!getPermission("D")) {
                    return;
                }

                DataUtil.performAction(KTDV_D30_Model.class, "delete", mRowSelect.getID());
                //Load lai Du lieu
                mlvtDataSearch = (List<ETT_KTDV_D30>) DataUtil.performAction(KTDV_D30_Model.class, "getData", mstrUserLogin);

                isDELETE = false;
                isDISABLE = true;
                ClientMessage.logDelete();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.DELETE, ex.toString());
            }
        }
    }

}
