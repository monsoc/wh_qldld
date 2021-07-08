/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.model;

import com.faplib.applet.util.StringUtil;
import com.faplib.lib.SystemLogger;
import com.faplib.lib.admin.data.AMDataPreprocessor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.com.telsoft.entity.ETT_COLUMN_DETAIL;
import vn.com.telsoft.entity.ETT_DETAIL_LEVEL;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_MAP_RULE;
import vn.com.telsoft.entity.ETT_REPORT_INFO;

/**
 *
 * @author xuanb
 */
public class MAP_RULE_INFO_Model extends AMDataPreprocessor implements Serializable {

    public List<ETT_DETAIL_LEVEL> getData(ETT_GROUP_LEVEL objETT_GROUP_LEVEL) throws Exception {
        List<ETT_DETAIL_LEVEL> lstETT_DETAIL_LEVEL = new ArrayList<>();

        String strSQL = "   SELECT detail_id, "
                + "          group_level_id, "
                + "          detail_level_code, "
                + "          detail_level_description, "
                + "          qldv_id, "
                + "          par_id, "
                + "          block_input "
                + "     FROM detail_level a "
                + "    WHERE a.group_level_id = ? "
                + " ORDER BY detail_id, detail_level_code ";

        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, objETT_GROUP_LEVEL.getGROUP_LEVEL_ID());
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_DETAIL_LEVEL tmp = new ETT_DETAIL_LEVEL();
                tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                tmp.setDETAIL_LEVEL_CODE(mRs.getString("detail_level_code"));
                tmp.setDETAIL_DES(mRs.getString("detail_level_description"));
                tmp.setQLDV_ID(mRs.getString("qldv_id"));
                tmp.setPAR_ID(mRs.getString("par_id"));
                tmp.setBLOCK_INPUT(mRs.getString("block_input"));
                lstETT_DETAIL_LEVEL.add(tmp);
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lstETT_DETAIL_LEVEL;
    }

    public List<ETT_GROUP_LEVEL> getListGroupLevel(String strREPORT_NAME) throws Exception {
        List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
        try {
            open();
            String strSQL = "   SELECT group_level_id, "
                    + "          group_code, "
                    + "          group_description, "
                    + "          report_name "
                    + "     FROM group_level "
                    + "     WHERE report_name = ? "
                    + " ORDER BY report_name, group_level_id ";
            try {
                mStmt = mConnection.prepareStatement(strSQL);
                mStmt.setString(1, strREPORT_NAME);
                mRs = mStmt.executeQuery();
                while (mRs.next()) {
                    ETT_GROUP_LEVEL tmp = new ETT_GROUP_LEVEL();
                    tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                    tmp.setGROUP_CODE(mRs.getString("group_code"));
                    tmp.setGROUP_DES(StringUtil.nvl(mRs.getString("group_description"), ""));
                    tmp.setREPORT_NAME(mRs.getString("report_name"));
                    lGroupLevel.add(tmp);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                close(mConnection, mStmt, mRs);
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lGroupLevel;
    }

    public List<ETT_MAP_RULE> searchInfoMapRule(String strREPORT_NAME, String strCL_ID) throws Exception {
        List<ETT_MAP_RULE> lMapRuleInfo = new ArrayList<>();
        String strSQL = "   SELECT b.group_description, "
                + "          c.detail_level_description, "
                + "          c.group_level_id, "
                + "          c.detail_id, "
                + "          a.rule_id, "
                + "          a.cl_id, "
                + "          a.report_name, "
                + "          a.list_group_user_id, "
                + "          a.status "
                + "     FROM map_info_rule a, group_level b, detail_level c "
                + "    WHERE     a.detail_level_id = c.detail_id "
                + "          AND c.group_level_id = b.group_level_id "
                + "          AND a.report_name = ? "
                + "          AND a.cl_id = ? "
                + " ORDER BY a.detail_level_id ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strREPORT_NAME);
            mStmt.setString(2, strCL_ID);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_MAP_RULE objRuleInfo = new ETT_MAP_RULE();
                objRuleInfo.setGROUP_LEVEL_DES(mRs.getString("group_description"));
                objRuleInfo.setGROUP_LEVEL_ID(mRs.getString("group_level_id"));
                objRuleInfo.setDETAIL_LEVEL_DES(mRs.getString("detail_level_description"));
                objRuleInfo.setDETAIL_LEVEL_ID(mRs.getString("detail_id"));
                objRuleInfo.setCL_ID(mRs.getString("cl_id"));
                objRuleInfo.setRULE_ID(mRs.getString("rule_id"));
                objRuleInfo.setLIST_GROUP_USER_ID(mRs.getString("list_group_user_id"));
                objRuleInfo.setSTATUS(mRs.getString("status"));
                objRuleInfo.setREPORT_NAME(mRs.getString("report_name"));
                lMapRuleInfo.add(objRuleInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lMapRuleInfo;
    }

    public List<ETT_COLUMN_DETAIL> getColumnDetail(String strREPORT_NAME) throws Exception {
        List<ETT_COLUMN_DETAIL> lColumnDetail = new ArrayList<>();

        String strSQL = " SELECT cl_id, cl_code, cl_name, report_name  "
                + " FROM report_column "
                + "   WHERE report_name = ? "
                + " ORDER BY cl_id ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strREPORT_NAME);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_COLUMN_DETAIL objColumn = new ETT_COLUMN_DETAIL();
                objColumn.setCL_ID(mRs.getString("cl_id"));
                objColumn.setCL_CODE(mRs.getString("cl_code"));
                objColumn.setCL_NAME(mRs.getString("cl_name"));
                objColumn.setREPORT_NAME(mRs.getString("report_name"));
                lColumnDetail.add(objColumn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lColumnDetail;
    }

    public List<ETT_REPORT_INFO> getListReport() throws Exception {
        List<ETT_REPORT_INFO> lstReport = new ArrayList<>();
        String strSQL = "SELECT report_name, description FROM list_report";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_REPORT_INFO objReport = new ETT_REPORT_INFO();
                objReport.setREPORT_NAME(mRs.getString("report_name"));
                objReport.setREPORT_DESCRIPTION(mRs.getString("description"));
                lstReport.add(objReport);
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lstReport;
    }

    public List<ETT_DETAIL_LEVEL> getListDetailLevel(String strGROUP_LEVEL_ID) throws Exception {
        List<ETT_DETAIL_LEVEL> lDetailLevel = new ArrayList<>();

        String strSQL = " SELECT   detail_id, group_level_id, detail_level_description "
                + "   FROM   detail_level "
                + "  WHERE   group_level_id = ? "
                + "  ORDER BY detail_id ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strGROUP_LEVEL_ID);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_DETAIL_LEVEL tmp = new ETT_DETAIL_LEVEL();
                tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                tmp.setDETAIL_DES(StringUtil.nvl(mRs.getString("detail_level_description"), ""));
                lDetailLevel.add(tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lDetailLevel;
    }

    public void add(ETT_MAP_RULE objETT_MAP_RULE) throws Exception {
        String strSQL = " INSERT INTO map_info_rule (rule_id, "
                + "                            detail_level_id, "
                + "                            cl_id, "
                + "                            list_group_user_id, "
                + "                            report_name, "
                + "                            status) "
                + "      VALUES ( (SELECT NVL (MAX (rule_id) + 1, 1) FROM map_info_rule),?, ?,?,?,?) ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_MAP_RULE.getDETAIL_LEVEL_ID());
            mStmt.setString(2, objETT_MAP_RULE.getCL_ID());
            mStmt.setString(3, objETT_MAP_RULE.getLIST_GROUP_USER_ID());
            mStmt.setString(4, objETT_MAP_RULE.getREPORT_NAME());
            mStmt.setString(5, objETT_MAP_RULE.getSTATUS());
            mStmt.executeUpdate();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mStmt, mConnection);
        }
    }

    public void addColumnDetail(ETT_COLUMN_DETAIL objETT_COLUMN_DETAIL, String strREPORT_NAME) throws Exception {
        String strSQL = " INSERT INTO report_column (cl_id, "
                + "                            cl_code, "
                + "                            cl_name, "
                + "                            report_name) "
                + "      VALUES ((SELECT NVL (MAX (cl_id) + 1, 1) FROM report_column),?,?,?) ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_COLUMN_DETAIL.getCL_CODE());
            mStmt.setString(2, objETT_COLUMN_DETAIL.getCL_NAME());
            mStmt.setString(3, strREPORT_NAME);
            mStmt.executeUpdate();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mStmt, mConnection);
        }
    }

    public void edit(ETT_MAP_RULE objETT_MAP_RULE) throws Exception {
        String strSQL = " UPDATE map_info_rule  "
                + "     SET detail_level_id = ?, list_group_user_id = ?, status = ?  "
                + " WHERE rule_id = ? ";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_MAP_RULE.getDETAIL_LEVEL_ID());
            mStmt.setString(2, objETT_MAP_RULE.getLIST_GROUP_USER_ID());
            mStmt.setString(3, objETT_MAP_RULE.getSTATUS());
            mStmt.setString(4, objETT_MAP_RULE.getRULE_ID());
            mStmt.executeUpdate();
            mConnection.commit();
        } catch (Exception ex) {
            mConnection.rollback();
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            mConnection.setAutoCommit(true);
            close(mStmt, mConnection);
        }
    }

    public void editColumnDetail(ETT_COLUMN_DETAIL objETT_COLUMN_DETAIL) throws Exception {
        String strSQL = "UPDATE report_column SET cl_code = ?, cl_name = ? WHERE cl_id = ?";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_COLUMN_DETAIL.getCL_CODE());
            mStmt.setString(2, objETT_COLUMN_DETAIL.getCL_NAME());
            mStmt.setString(3, objETT_COLUMN_DETAIL.getCL_ID());
            mStmt.executeUpdate();
            mConnection.commit();
        } catch (Exception ex) {
            mConnection.rollback();
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            mConnection.setAutoCommit(true);
            close(mStmt, mConnection);
        }
    }

    public void delete(Integer iDetailID) throws Exception {
        String strSQL = " DELETE FROM detail_level WHERE detail_id = ?";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iDetailID);
            mStmt.executeUpdate();
            mConnection.commit();
        } catch (Exception ex) {
            mConnection.rollback();
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            mConnection.setAutoCommit(true);
            close(mStmt, mConnection);
        }
    }

    public void deleteColumnDetail(Integer iCL_ID) throws Exception {
        String strSQL = " DELETE FROM report_column WHERE cl_id = ?";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iCL_ID);
            mStmt.executeUpdate();
            mConnection.commit();
        } catch (Exception ex) {
            mConnection.rollback();
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            mConnection.setAutoCommit(true);
            close(mStmt, mConnection);
        }
    }
}
