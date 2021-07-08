/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.model;

import com.faplib.lib.SystemLogger;
import com.faplib.lib.admin.data.AMDataPreprocessor;
import com.faplib.util.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_KTDV_D30;
import vn.com.telsoft.util.DatabaseUtil;

/**
 *
 * @author xuanb
 */
public class KTDV_D30_Model extends AMDataPreprocessor implements Serializable {

    public List<ETT_KTDV_D30> getData(String strUSER_NAME) throws Exception {
        List<ETT_KTDV_D30> lstETT_KTDV_D30 = new ArrayList<>();

        String strSQL = " SELECT a.ktdv_id, to_char(a.create_date,'dd/mm/yyyy') create_date, "
                + "        b.group_level_id, "
                + "        a.detail_id,b.group_description, c.detail_level_description, "
                + "        a.d5, a.e5, a.f5, a.g5, a.h5, a.i5, a.j5, "
                + "        a.k5, a.l5, a.m5, a.n5, a.o5, a.p5, a.q5, a.r5, a.s5, a.t5, a.u5, "
                + "        a.v5 "
                + "   FROM   ktdv_d30 a, group_level b, detail_level c "
                + "  WHERE       a.detail_id = c.detail_id "
                + "          AND b.group_level_id = c.group_level_id "
                + "          AND UPPER (a.user_create) = UPPER (?) "
                + "          AND a.create_date >= TRUNC (SYSDATE) - 1 "
                + "          AND a.report_status = '0' "
                + "  ORDER BY group_level_id ";

        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strUSER_NAME);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_KTDV_D30 tmp = new ETT_KTDV_D30();
                tmp.setID(mRs.getInt("ktdv_id"));
                tmp.setCREATE_DATE(mRs.getString("create_date"));
                tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                tmp.setGROUP_DES(mRs.getString("group_description"));
                tmp.setDELTAIL_DES(mRs.getString("detail_level_description"));

                tmp.setD5(StringUtil.nvl(mRs.getString("d5"), ""));
                tmp.setE5(StringUtil.nvl(mRs.getString("e5"), ""));
                tmp.setF5(StringUtil.nvl(mRs.getString("f5"), ""));
                tmp.setG5(StringUtil.nvl(mRs.getString("g5"), ""));
                tmp.setH5(StringUtil.nvl(mRs.getString("h5"), ""));
                tmp.setI5(StringUtil.nvl(mRs.getString("i5"), ""));
                tmp.setJ5(StringUtil.nvl(mRs.getString("j5"), ""));
                tmp.setK5(StringUtil.nvl(mRs.getString("k5"), ""));
                tmp.setL5(StringUtil.nvl(mRs.getString("l5"), ""));
                tmp.setM5(StringUtil.nvl(mRs.getString("m5"), ""));
                tmp.setN5(StringUtil.nvl(mRs.getString("n5"), ""));
                tmp.setO5(StringUtil.nvl(mRs.getString("o5"), ""));
                tmp.setP5(StringUtil.nvl(mRs.getString("p5"), ""));
                tmp.setQ5(StringUtil.nvl(mRs.getString("q5"), ""));
                tmp.setR5(StringUtil.nvl(mRs.getString("r5"), ""));
                tmp.setS5(StringUtil.nvl(mRs.getString("s5"), ""));
                tmp.setT5(StringUtil.nvl(mRs.getString("t5"), ""));
                tmp.setU5(StringUtil.nvl(mRs.getString("u5"), ""));
                tmp.setV5(StringUtil.nvl(mRs.getString("v5"), ""));

                lstETT_KTDV_D30.add(tmp);
            }

        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lstETT_KTDV_D30;
    }

    public List<ETT_GROUP_LEVEL> getListGroupLevel() throws Exception {
        List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
        try {
            open();
            lGroupLevel = DatabaseUtil.getListGroupLevel(mConnection, "KTDV_D30");
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lGroupLevel;
    }

    public void add(ETT_KTDV_D30 objData, String strUserLogin) throws Exception {
        String strKTDV_ID = "";
        String strSQL = " INSERT INTO  ktdv_d30(ktdv_id, create_date, user_create, detail_id, d5, e5, "
                + "                      f5, g5, h5, i5, j5, k5, l5, m5, n5, o5, p5, "
                + "                      q5, r5, s5, t5, u5, v5, month_report, group_user, "
                + "                      report_status)  "
                + " VALUES (?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,null,null,'0') ";

        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            strKTDV_ID = DatabaseUtil.getSequenceValue(mConnection, "SQL_KTDV_D30");
            mStmt.setString(1, strKTDV_ID);
            mStmt.setString(2, strUserLogin);
            mStmt.setInt(3, objData.getDETAIL_ID());
            mStmt.setString(4, objData.getD5());
            mStmt.setString(5, objData.getE5());
            mStmt.setString(6, objData.getF5());
            mStmt.setString(7, objData.getG5());
            mStmt.setString(8, objData.getH5());
            mStmt.setString(9, objData.getI5());
            mStmt.setString(10, objData.getJ5());
            mStmt.setString(11, objData.getK5());
            mStmt.setString(12, objData.getL5());
            mStmt.setString(13, objData.getM5());
            mStmt.setString(14, objData.getN5());
            mStmt.setString(15, objData.getO5());
            mStmt.setString(16, objData.getP5());
            mStmt.setString(17, objData.getQ5());
            mStmt.setString(18, objData.getR5());
            mStmt.setString(19, objData.getS5());
            mStmt.setString(20, objData.getT5());
            mStmt.setString(21, objData.getU5());
            mStmt.setString(22, objData.getV5());
            mStmt.executeUpdate();
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mStmt, mConnection);
        }
    }

    public void edit(ETT_KTDV_D30 objData) throws Exception {
        String strSQL = " UPDATE ktdv_d30 SET detail_id=?,d5=?,e5=?,f5=?,g5=?,h5=?,i5=?,j5=?, "
                + "                     k5=?,l5=?,m5=?,n5=?,o5=?,p5=?,q5=?,r5=?,s5=?,t5=?,u5=?,v5=? "
                + " WHERE ktdv_id = ?  ";
        try {
            open();
            mConnection.setAutoCommit(false);
            List listChange = logBeforeUpdate("ktdv_d30", "ktdv_id = " + objData.getID());
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, objData.getDETAIL_ID());
            mStmt.setString(2, objData.getD5());
            mStmt.setString(3, objData.getE5());
            mStmt.setString(4, objData.getF5());
            mStmt.setString(5, objData.getG5());
            mStmt.setString(6, objData.getH5());
            mStmt.setString(7, objData.getI5());
            mStmt.setString(8, objData.getJ5());
            mStmt.setString(9, objData.getK5());
            mStmt.setString(10, objData.getL5());
            mStmt.setString(11, objData.getM5());
            mStmt.setString(12, objData.getN5());
            mStmt.setString(13, objData.getO5());
            mStmt.setString(14, objData.getP5());
            mStmt.setString(15, objData.getQ5());
            mStmt.setString(16, objData.getR5());
            mStmt.setString(17, objData.getS5());
            mStmt.setString(18, objData.getT5());
            mStmt.setString(19, objData.getU5());
            mStmt.setString(20, objData.getV5());
            mStmt.setInt(21, objData.getID());
            mStmt.execute();
            logAfterUpdate(listChange);
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

    public void delete(Integer iKTDV_ID) throws Exception {
        String strSQL = " DELETE FROM ktdv_d30 WHERE ktdv_id = ?";
        try {
            open();
            mConnection.setAutoCommit(false);
            logBeforeDelete("ktdv_d30", "ktdv_id = "+iKTDV_ID);
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iKTDV_ID);
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
