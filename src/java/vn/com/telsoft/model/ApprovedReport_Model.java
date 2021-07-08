/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.model;

import com.faplib.lib.SystemLogger;
import com.faplib.lib.admin.data.AMDataPreprocessor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.com.telsoft.entity.ETT_APPROVED;

/**
 *
 * @author xuanb
 */
public class ApprovedReport_Model extends AMDataPreprocessor implements Serializable {

    public String[] getInfoUser(Integer iUSERID) throws Exception {
        String strGroupUserLevel = "";
        String strSQL = " SELECT DISTINCT b.GROUP_ID, a.qldv_id"
                + "   FROM am_user a, am_group_user b"
                + "  WHERE a.user_id = b.user_id AND a.user_id = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iUSERID);
            mRs = mStmt.executeQuery();
            if (mRs != null && mRs.next()) {
                return new String[]{mRs.getString("GROUP_ID"), mRs.getString("qldv_id")};
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return new String[]{};
    }

    public List<ETT_APPROVED> searchInfoReport(int iCapBC, int iGroupUser, int iYear, int iQuarter) throws Exception {
        List<ETT_APPROVED> lResult = new ArrayList<>();

        String strSQL = " SELECT (SELECT name FROM am_group WHERE GROUP_ID = p_group_id ) name,"
                + " 	   sl_bm_phai_kb (p_capkb) sl_bmpkb,"
                + "        sl_bm_da_kb (p_group_id, p_year, p_quarter) sl_bmdkb,"
                + "        (sl_bm_phai_kb (p_capkb) - sl_bm_da_kb (p_group_id, p_year, p_quarter)) sl_bmckb,"
                + "        ds_bm_chua_kb (p_capkb, p_group_id, p_year, p_quarter) ds_bmckb,"
                + "        tt_chot_kb (p_capkb, p_group_id, p_year, p_quarter) ttchotkb"
                + "   FROM DUAL"
                + " UNION ALL"
                + " SELECT aa.name,"
                + "        sl_bm_phai_kb (aa.qldv_id) sl_bmpkb,"
                + "        sl_bm_da_kb (aa.GROUP_ID, p_year, p_quarter) sl_bmdkb,"
                + "        (sl_bm_phai_kb (aa.qldv_id) - sl_bm_da_kb (aa.GROUP_ID, p_year, p_quarter)) sl_bmckb,"
                + "        ds_bm_chua_kb (aa.qldv_id, aa.GROUP_ID, p_year, p_quarter) ds_bmckb,"
                + "        tt_chot_kb (aa.qldv_id, aa.GROUP_ID, p_year, p_quarter) ttchotkb"
                + "   FROM (SELECT DISTINCT b.GROUP_ID, b.name, c.qldv_id"
                + "           FROM am_group_user a, am_group b, am_user c"
                + "          WHERE a.GROUP_ID = b.GROUP_ID AND a.user_id = c.user_id AND b.parent_id = p_group_id) aa";

        strSQL = strSQL.replace("p_capkb", String.valueOf(iCapBC));
        strSQL = strSQL.replace("p_group_id", String.valueOf(iGroupUser));
        strSQL = strSQL.replace("p_year", String.valueOf(iYear));
        strSQL = strSQL.replace("p_quarter", String.valueOf(iQuarter));
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_APPROVED tmp = new ETT_APPROVED();
                tmp.setDONVI(mRs.getString("name"));
                tmp.setSL_BMPKB(mRs.getInt("sl_bmpkb"));
                tmp.setSL_BMDKB(mRs.getInt("sl_bmdkb"));
                tmp.setSL_BMCKB(mRs.getInt("sl_bmckb"));
                tmp.setDS_BMCKB(mRs.getString("ds_bmckb"));
                tmp.setTT_CHOTKB(mRs.getString("ttchotkb"));
                lResult.add(tmp);
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ex.printStackTrace();
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lResult;
    }

    public void approvedReport(int iCapBC, int iGroupUser, int iUserID, int iYear, int iQuarter) throws Exception {
        String strSQL_UPDATE = " UPDATE report_qldld"
                + "    SET report_status = '1'"
                + "  WHERE     user_id IN (SELECT user_id"
                + "                          FROM am_group_user"
                + "                         WHERE GROUP_ID = ?)"
                + "        AND year = ? "
                + "        AND quarter = ? ";

        String strSQL_INSERT = " INSERT INTO info_approved (approved_date,"
                + "                            year,"
                + "                            quarter,"
                + "                            app_level,"
                + "                            user_id)"
                + "      VALUES (SYSDATE,?,?,?,?)";
        try {
            open();
            mConnection.setAutoCommit(false);
            // Update
            mStmt = mConnection.prepareStatement(strSQL_UPDATE);
            mStmt.setInt(1, iGroupUser);
            mStmt.setInt(2, iYear);
            mStmt.setInt(3, iQuarter);
            mStmt.executeUpdate();

            mStmt.close();
            // Insert
            mStmt = mConnection.prepareStatement(strSQL_INSERT);
            mStmt.setInt(1, iYear);
            mStmt.setInt(2, iQuarter);
            mStmt.setInt(3, iCapBC);
            mStmt.setInt(4, iUserID);
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
