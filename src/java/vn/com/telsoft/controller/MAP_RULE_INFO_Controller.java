/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import vn.com.telsoft.entity.ETT_MAP_RULE;
import vn.com.telsoft.entity.ETT_COLUMN_DETAIL;
import com.faplib.lib.ClientMessage;
import com.faplib.lib.SystemLogger;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.TelsoftException;
import com.faplib.lib.util.DataUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vn.com.telsoft.entity.ETT_DETAIL_LEVEL;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_REPORT_INFO;
import vn.com.telsoft.model.MAP_RULE_INFO_Model;

/**
 *
 * @author xuanb
 */
@ManagedBean
@ViewScoped
public class MAP_RULE_INFO_Controller extends TSFuncTemplate implements Serializable {

    /**
     * Creates a new instance of KB_GROUP_LEVEL_Controller
     */
    private List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
    private List<ETT_DETAIL_LEVEL> lDetailLevel = new ArrayList<>();
    private List<ETT_REPORT_INFO> lListReport = new ArrayList();
    private List<ETT_COLUMN_DETAIL> lColumnDetail = new ArrayList();

    private List<ETT_MAP_RULE> lListMapRuleInfo;
    private ETT_MAP_RULE mRowMapRuleSelect;
    private ETT_COLUMN_DETAIL mRowColumnSelect;

    private ETT_MAP_RULE mInputRuleInfo;
    private ETT_COLUMN_DETAIL mInputColumnDetail;

    private String mSelectedReport = "";

    private String mstrActionColumnDetail = "";

    public MAP_RULE_INFO_Controller() {
        try {
            mInputRuleInfo = new ETT_MAP_RULE();
            mInputColumnDetail = new ETT_COLUMN_DETAIL();

//            mlvtDataSearch = (List<ETT_DETAIL_LEVEL>) DataUtil.performAction(MAP_RULE_INFO_Model.class, "getData", mstrReportName);
            lListReport = DataUtil.getData(MAP_RULE_INFO_Model.class, "getListReport");
            if (lListReport.size() > 0) {
                mSelectedReport = lListReport.get(0).getREPORT_NAME();
                lGroupLevel = (List<ETT_GROUP_LEVEL>) DataUtil.performAction(MAP_RULE_INFO_Model.class, "getListGroupLevel", mSelectedReport);
                lColumnDetail = (List<ETT_COLUMN_DETAIL>) DataUtil.performAction(MAP_RULE_INFO_Model.class, "getColumnDetail", mSelectedReport);
                if (lColumnDetail.size() > 0) {
                    mInputColumnDetail = mRowColumnSelect = lColumnDetail.get(0);
                    searchInfoMapRule();
                }
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public boolean isSelectedRow() {
        return (null != mRowMapRuleSelect);
    }

    public void onRowSelect() {
        if (mRowMapRuleSelect != null) {
            try {
                mInputRuleInfo = mRowMapRuleSelect;
                lDetailLevel = (List<ETT_DETAIL_LEVEL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,"getListDetailLevel", mInputRuleInfo.getGROUP_LEVEL_ID());
            } catch (TelsoftException ex) {
                Logger.getLogger(MAP_RULE_INFO_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void changeStateAdd() {
        super.changeStateAdd();
        mInputRuleInfo = new ETT_MAP_RULE();
    }

    @Override
    public void handCancel() {
        super.handCancel();
        //Load lai Du lieu
        if (mRowColumnSelect != null) {
            try {
                lListMapRuleInfo = (List<ETT_MAP_RULE>) DataUtil.performAction(MAP_RULE_INFO_Model.class, "searchInfoMapRule", mSelectedReport, mRowColumnSelect.getCL_ID());
                if (lListMapRuleInfo.size() > 0) {
                    mInputRuleInfo = lListMapRuleInfo.get(0);
                }
            } catch (TelsoftException ex) {
                Logger.getLogger(MAP_RULE_INFO_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        onRowSelect();
    }

    public void handOK_ColumnDetail() {
        if (mstrActionColumnDetail.equals("ADD")) {
            try {
                DataUtil.performAction(MAP_RULE_INFO_Model.class,
                         "addColumnDetail", mInputColumnDetail, mSelectedReport);
                //Load lai Du lieu
                lColumnDetail = (List<ETT_COLUMN_DETAIL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                         "getColumnDetail", mSelectedReport);
                ClientMessage.logAdd();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ADD, ex.getLocalizedMessage());
            }
        } else if (mstrActionColumnDetail.equals("EDIT")) {
            try {
                DataUtil.performAction(MAP_RULE_INFO_Model.class,
                         "editColumnDetail", mInputColumnDetail);
                //Load lai Du lieu
                lColumnDetail = (List<ETT_COLUMN_DETAIL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                         "getColumnDetail", mSelectedReport);
                ClientMessage.logUpdate();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.UPDATE, ex.getLocalizedMessage());
            }
        }
    }

    public void getListColumnDetail() {
        try {
            lColumnDetail = (List<ETT_COLUMN_DETAIL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                     "getColumnDetail", mSelectedReport);
            if (lColumnDetail.size() > 0) {
                mRowColumnSelect = mInputColumnDetail = lColumnDetail.get(0);
            }
            lGroupLevel = (List<ETT_GROUP_LEVEL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                     "getListGroupLevel", mSelectedReport);
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void getListGroupLevel() {
        try {
            lGroupLevel = (List<ETT_GROUP_LEVEL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,"getListGroupLevel", mSelectedReport);

        } catch (TelsoftException ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void getListDetailLevel() {
        try {
            lDetailLevel = (List<ETT_DETAIL_LEVEL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,"getListDetailLevel", mInputRuleInfo.getGROUP_LEVEL_ID());
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void searchInfoMapRule() {
        try {
            lListMapRuleInfo = null;
            if (mRowColumnSelect != null) {
                lListMapRuleInfo = (List<ETT_MAP_RULE>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                         "searchInfoMapRule", mSelectedReport, mRowColumnSelect.getCL_ID());
                if (lListMapRuleInfo.size() > 0) {
                    mRowMapRuleSelect = mInputRuleInfo = lListMapRuleInfo.get(0);
                    getListDetailLevel();
                }
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ex.toString());
        }
    }

    public void changeStateAddColumnDetail() {
        mstrActionColumnDetail = "ADD";
        mInputColumnDetail = new ETT_COLUMN_DETAIL();
    }

    public void changeStateEditColumnDetail() {
        mstrActionColumnDetail = "EDIT";
        mInputColumnDetail = mRowColumnSelect;
    }

    @Override
    public void handOK() {
        if (isADD || isCOPY) {
            try {
                if (!getPermission("I")) {
                    return;
                }
                mInputRuleInfo.setREPORT_NAME(mSelectedReport);
                mInputRuleInfo.setCL_ID(mRowColumnSelect.getCL_ID());
                DataUtil.performAction(MAP_RULE_INFO_Model.class,
                         "add", mInputRuleInfo);
//                isADD = false;
//                isCOPY = false;
//                isDISABLE = true;
//                mInputRuleInfo = new ETT_MAP_RULE();
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
                DataUtil.performAction(MAP_RULE_INFO_Model.class,"edit", mInputRuleInfo);
                //Load lai Du lieu
                if (mRowColumnSelect != null) {
                    lListMapRuleInfo = (List<ETT_MAP_RULE>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                             "searchInfoMapRule", mSelectedReport, mRowColumnSelect.getCL_ID());
                    if (lListMapRuleInfo.size() > 0) {
                        mInputRuleInfo = lListMapRuleInfo.get(0);
                    }
                }
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

//                DataUtil.performAction(MAP_RULE_INFO_Model.class, "delete", mRowSelect.getDETAIL_ID());
                //Load lai Du lieu
//                searchData();
                isDELETE = false;
                isDISABLE = true;
                ClientMessage.logDelete();
            } catch (Exception ex) {
                SystemLogger.getLogger().error(ex);
                ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.DELETE, ex.toString());
            }
        }
    }

    public void handDeleteColumnDetail() {
        try {
            DataUtil.performAction(MAP_RULE_INFO_Model.class,
                     "deleteColumnDetail", mRowColumnSelect.getCL_ID());
            //Load lai Du lieu
            lColumnDetail = (List<ETT_COLUMN_DETAIL>) DataUtil.performAction(MAP_RULE_INFO_Model.class,
                     "getColumnDetail", mSelectedReport);
            ClientMessage.logDelete();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.DELETE, ex.toString());
        }
    }

    public List<ETT_GROUP_LEVEL> getGroupLevel() {
        return lGroupLevel;
    }

    public void setGroupLevel(List<ETT_GROUP_LEVEL> lGroupLevel) {
        this.lGroupLevel = lGroupLevel;
    }

    public ETT_MAP_RULE getRowMapRuleSelect() {
        return mRowMapRuleSelect;
    }

    public void setRowMapRuleSelect(ETT_MAP_RULE mRowMapRuleSelect) {
        this.mRowMapRuleSelect = mRowMapRuleSelect;
    }

    public List<ETT_REPORT_INFO> getListReport() {
        return lListReport;
    }

    public void setListReport(List<ETT_REPORT_INFO> lListReport) {
        this.lListReport = lListReport;
    }

    public ETT_COLUMN_DETAIL getRowColumnSelect() {
        return mRowColumnSelect;
    }

    public void setRowColumnSelect(ETT_COLUMN_DETAIL mRowColumnSelect) {
        this.mRowColumnSelect = mRowColumnSelect;
    }

    public String getSelectedReport() {
        return mSelectedReport;
    }

    public void setSelectedReport(String mSelectedReport) {
        this.mSelectedReport = mSelectedReport;
    }

    public List<ETT_COLUMN_DETAIL> getColumnDetail() {
        return lColumnDetail;
    }

    public void setColumnDetail(List<ETT_COLUMN_DETAIL> lColumnDetail) {
        this.lColumnDetail = lColumnDetail;
    }

    public ETT_MAP_RULE getInputRuleInfo() {
        return mInputRuleInfo;
    }

    public void setInputRuleInfo(ETT_MAP_RULE mInputRuleInfo) {
        this.mInputRuleInfo = mInputRuleInfo;
    }

    public ETT_COLUMN_DETAIL getInputColumnDetail() {
        return mInputColumnDetail;
    }

    public void setInputColumnDetail(ETT_COLUMN_DETAIL mInputColumnDetail) {
        this.mInputColumnDetail = mInputColumnDetail;
    }

    public List<ETT_DETAIL_LEVEL> getDetailLevel() {
        return lDetailLevel;
    }

    public void setDetailLevel(List<ETT_DETAIL_LEVEL> lDetailLevel) {
        this.lDetailLevel = lDetailLevel;
    }

    public List<ETT_MAP_RULE> getListMapRuleInfo() {
        return lListMapRuleInfo;
    }

    public void setListMapRuleInfo(List<ETT_MAP_RULE> lListMapRuleInfo) {
        this.lListMapRuleInfo = lListMapRuleInfo;
    }

    public String getActionColumnDetail() {
        return mstrActionColumnDetail;
    }

    public void setActionColumnDetail(String mstrActionColumnDetail) {
        this.mstrActionColumnDetail = mstrActionColumnDetail;
    }

}
