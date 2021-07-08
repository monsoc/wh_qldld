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
import vn.com.telsoft.entity.ETT_DETAIL_LEVEL;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_REPORT_INFO;

/**
 *
 * @author xuanb
 */
public class KB_GROUP_LEVEL_Model extends AMDataPreprocessor implements Serializable {

    public List<ETT_DETAIL_LEVEL> searchDetailLevel(ETT_GROUP_LEVEL objETT_GROUP_LEVEL) throws Exception {
        List<ETT_DETAIL_LEVEL> lstETT_DETAIL_LEVEL = new ArrayList<>();

        String strSQL = "   SELECT detail_id, "
                + "          group_level_id, "
                + "          detail_level_code, "
                + "          detail_level_description, "
                + "          rule_info "
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
                tmp.setRULE_INFO(mRs.getString("rule_info"));
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

    public void add(ETT_DETAIL_LEVEL objETT_DETAIL_LEVEL ) throws Exception {
        String strSQL = " INSERT INTO detail_level (detail_id, "+
                        "                           group_level_id, "+
                        "                           detail_level_code, "+
                        "                           detail_level_description,"+
                        "                           rule_info) "+
                        "      VALUES ( (SELECT MAX (detail_id) +1 FROM detail_level),?,?,?,?) ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_DETAIL_LEVEL.getGROUP_LEVEL_ID().toString());
            mStmt.setString(2, objETT_DETAIL_LEVEL.getDETAIL_LEVEL_CODE());
            mStmt.setString(3, objETT_DETAIL_LEVEL.getDETAIL_DES());
            mStmt.setString(4, objETT_DETAIL_LEVEL.getRULE_INFO());
            mStmt.executeUpdate();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mStmt, mConnection);
        }
    }
    
    public void addGroupLevel(ETT_GROUP_LEVEL objETT_GROUP_LEVEL) throws Exception {
        String strSQL = " INSERT INTO group_level (group_level_id, "+
                        "                          group_code, "+
                        "                          group_description, "+
                        "                          report_name) "+
                        "      VALUES ( (SELECT MAX (group_level_id) + 1 FROM group_level), "+
                        "              ?,?,?) ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_GROUP_LEVEL.getGROUP_CODE());
            mStmt.setString(2, objETT_GROUP_LEVEL.getGROUP_DES());
            mStmt.setString(3, objETT_GROUP_LEVEL.getREPORT_NAME());
            mStmt.executeUpdate();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mStmt, mConnection);
        }
    }

    public void edit(ETT_DETAIL_LEVEL objETT_DETAIL_LEVEL) throws Exception {
        String strSQL = " UPDATE detail_level "+
                        "    SET detail_level_code = ?, detail_level_description = ?, rule_info = ? "+
                        "  WHERE detail_id = ? ";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_DETAIL_LEVEL.getDETAIL_LEVEL_CODE());
            mStmt.setString(2, objETT_DETAIL_LEVEL.getDETAIL_DES());
            mStmt.setString(3, objETT_DETAIL_LEVEL.getRULE_INFO());
            mStmt.setInt(4, objETT_DETAIL_LEVEL.getDETAIL_ID());
            mStmt.execute();
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
    
    public void editGroupLevel(ETT_GROUP_LEVEL objETT_GROUP_LEVEL) throws Exception {
        String strSQL = " UPDATE group_level SET group_code = ?, group_description=?, report_name = ? WHERE group_level_id = ? ";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, objETT_GROUP_LEVEL.getGROUP_CODE());
            mStmt.setString(2, objETT_GROUP_LEVEL.getGROUP_DES());
            mStmt.setString(3, objETT_GROUP_LEVEL.getREPORT_NAME());
            mStmt.setInt(4, objETT_GROUP_LEVEL.getGROUP_LEVEL_ID());
            mStmt.executeUpdate();
            mConnection.commit();
            System.out.println("Edit Group Sucss: " +  objETT_GROUP_LEVEL.getGROUP_CODE());
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
    
    public void deleteGroupLevel(int iGroupLevelID) throws Exception {
        String strSQL = " DELETE FROM group_level WHERE group_level_id = ?";
        try {
            open();
            mConnection.setAutoCommit(false);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iGroupLevelID);
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
    
public List<ETT_REPORT_INFO> getListReport() throws Exception {
        List<ETT_REPORT_INFO> lstReport = new ArrayList<>();
        String strSQL = "SELECT report_name, description FROM list_report ORDER BY report_id";
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
}
