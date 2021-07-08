/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import com.faplib.lib.ClientMessage;
import com.faplib.lib.SystemLogger;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.TelsoftException;
import com.faplib.lib.util.DataUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vn.com.telsoft.entity.ETT_DETAIL_LEVEL;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_MAP_RULE;
import vn.com.telsoft.entity.ETT_REPORT_INFO;
import vn.com.telsoft.model.KB_GROUP_LEVEL_Model;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public class KB_GROUP_LEVEL_Controller extends TSFuncTemplate implements Serializable {

    /**
     * Creates a new instance of KB_GROUP_LEVEL_Controller
     */
    private ETT_GROUP_LEVEL mipGroupLevel;
    private ETT_DETAIL_LEVEL mipDetailLevel;
    private List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
    private List<ETT_DETAIL_LEVEL> mlvtDataSearch;
    private ETT_DETAIL_LEVEL mRowSelect;
    private ETT_GROUP_LEVEL mRowGroupLevelSelect;
    private String mstrReportName;
    private List<ETT_REPORT_INFO> lListReport = new ArrayList();

    private String mstrActionGroupLevel = "";

    private String mSelectedReport = "";
    private List<ETT_MAP_RULE> lListMapRuleInfo;

    public KB_GROUP_LEVEL_Controller() {
        try {
            mipDetailLevel = new ETT_DETAIL_LEVEL();
            mipGroupLevel = new ETT_GROUP_LEVEL();
            lListReport = DataUtil.getData(KB_GROUP_LEVEL_Model.class, "getListReport");
            if (lListReport.size() > 0) {
                mSelectedReport = lListReport.get(0).getREPORT_NAME();
                getListGroupLevel();
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void getListGroupLevel() {
        try {
            lGroupLevel = (List<ETT_GROUP_LEVEL>) DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "getListGroupLevel", mSelectedReport);
            if (lGroupLevel.size() > 0) {
                mipGroupLevel = mRowGroupLevelSelect = lGroupLevel.get(0);
                searchDetailLevel();
            } else {
                mlvtDataSearch = null;
                mipDetailLevel = null;
            }
        } catch (TelsoftException ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void searchDetailLevel() {
        try {
            mlvtDataSearch = null;
            mipDetailLevel = null;
            if (mRowGroupLevelSelect != null) {
                mlvtDataSearch = (List<ETT_DETAIL_LEVEL>) DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "searchDetailLevel", mRowGroupLevelSelect);
                if (mlvtDataSearch.size() > 0) {
                    mipDetailLevel = mRowSelect = mlvtDataSearch.get(0);
                }
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public boolean isSelectedRow() {
        return (null != mRowSelect);
    }

    public void onRowSelect() {
        if (mRowSelect != null) {
            mipDetailLevel = mRowSelect;
        }
    }

    @Override
    public void changeStateAdd() {
        super.changeStateAdd();
        mipDetailLevel = new ETT_DETAIL_LEVEL();
    }

    @Override
    public void handCancel() {
        super.handCancel();
        onRowSelect();
    }

    public void handOKGroupLevel() {
        if (mstrActionGroupLevel.equals("ADD")) {
            try {
                mipGroupLevel.setREPORT_NAME(mSelectedReport);
                DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "addGroupLevel", mipGroupLevel);
                //Load lai Du lieu
                getListGroupLevel();
                ClientMessage.logAdd();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ADD, ex.getLocalizedMessage());
            }
        } else if (mstrActionGroupLevel.equals("EDIT")) {
            try {
                DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "editGroupLevel", mipGroupLevel);
                //Load lai Du lieu
                getListGroupLevel();
                ClientMessage.logUpdate();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.UPDATE, ex.getLocalizedMessage());
            }
        }
    }

    public void changeStateAddGroupLevel() {
        mstrActionGroupLevel = "ADD";
        mipGroupLevel = new ETT_GROUP_LEVEL();
    }

    public void changeStateEditGroupLevel() {
        mstrActionGroupLevel = "EDIT";
        mipGroupLevel = mRowGroupLevelSelect;
    }

    @Override
    public void handOK() {
        if (isADD || isCOPY) {
            try {
                if (!getPermission("I")) {
                    return;
                }
                mipDetailLevel.setGROUP_LEVEL_ID(mRowGroupLevelSelect.getGROUP_LEVEL_ID());
                DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "add", mipDetailLevel);
                //Load lai Du lieu
                searchDetailLevel();
//                isADD = false;
//                isCOPY = false;
//                isDISABLE = true;
                mipDetailLevel = new ETT_DETAIL_LEVEL();
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
                DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "edit", mipDetailLevel);
                //Load lai Du lieu
                searchDetailLevel();

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

                DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "delete", mRowSelect.getDETAIL_ID());
                //Load lai Du lieu
                searchDetailLevel();

                isDELETE = false;
                isDISABLE = true;
                ClientMessage.logDelete();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.DELETE, ex.toString());
            }
        }
    }

    public void handDeleteGroupLevel() {
        try {
            DataUtil.performAction(KB_GROUP_LEVEL_Model.class, "deleteGroupLevel", mRowGroupLevelSelect.getGROUP_LEVEL_ID());
            //Load lai Du lieu
            lGroupLevel = DataUtil.getData(KB_GROUP_LEVEL_Model.class, "getListGroupLevel");
            ClientMessage.logDelete();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.DELETE, ex.toString());
        }
    }

    public ETT_DETAIL_LEVEL getIpDetailLevel() {
        return mipDetailLevel;
    }

    public void setIpDetailLevel(ETT_DETAIL_LEVEL mipDetailLevel) {
        this.mipDetailLevel = mipDetailLevel;
    }

    public List<ETT_GROUP_LEVEL> getGroupLevel() {
        return lGroupLevel;
    }

    public void setGroupLevel(List<ETT_GROUP_LEVEL> lGroupLevel) {
        this.lGroupLevel = lGroupLevel;
    }

    public List<ETT_DETAIL_LEVEL> getDataSearch() {
        return mlvtDataSearch;
    }

    public void setDataSearch(List<ETT_DETAIL_LEVEL> mlvtDataSearch) {
        this.mlvtDataSearch = mlvtDataSearch;
    }

    public ETT_DETAIL_LEVEL getRowSelect() {
        return mRowSelect;
    }

    public void setRowSelect(ETT_DETAIL_LEVEL mRowSelect) {
        this.mRowSelect = mRowSelect;
    }

    public String getReportName() {
        return mstrReportName;
    }

    public void setReportName(String mstrReportName) {
        this.mstrReportName = mstrReportName;
    }

    public ETT_GROUP_LEVEL getRowGroupLevelSelect() {
        return mRowGroupLevelSelect;
    }

    public void setRowGroupLevelSelect(ETT_GROUP_LEVEL mRowGroupLevelSelect) {
        this.mRowGroupLevelSelect = mRowGroupLevelSelect;
    }

    public ETT_GROUP_LEVEL getIpGroupLevel() {
        return mipGroupLevel;
    }

    public void setIpGroupLevel(ETT_GROUP_LEVEL mipGroupLevel) {
        this.mipGroupLevel = mipGroupLevel;
    }

    public String getSelectedReport() {
        return mSelectedReport;
    }

    public void setSelectedReport(String mSelectedReport) {
        this.mSelectedReport = mSelectedReport;
    }

    public List<ETT_REPORT_INFO> getListReport() {
        return lListReport;
    }

    public void setListReport(List<ETT_REPORT_INFO> lListReport) {
        this.lListReport = lListReport;
    }

}
