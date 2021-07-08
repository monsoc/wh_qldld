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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;
import vn.com.telsoft.entity.ETT_QLDLD;

/**
 *
 * @author xuanb
 */
public class QL_DLD_Model extends AMDataPreprocessor implements Serializable {

    public List<ETT_QLDLD> searchInfo(int iUSERID, String strREPORT_NAME, int iYEAR, int iQUARTER) throws Exception {
        List<ETT_QLDLD> lstETT_QLDLD = new ArrayList<>();
        String runSP = "{ call search_info_report(?,?,?,?,?,?) }";
        CallableStatement cs = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iUSERID);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.execute();
            mRs = null;
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                mRs = (ResultSet) cs.getObject(5);
                String strTMP = "";
                while (mRs.next()) {
                    ETT_QLDLD tmp = new ETT_QLDLD();
                    String strGROUP_LEVEL_ID = mRs.getString("group_level_id");
                    if (!strTMP.equals(strGROUP_LEVEL_ID)) {
                        //Tao row groupLevel
                        tmp.setDETAIL_DES(mRs.getString("group_description"));
                        tmp.setDETAIL_ID(0);
                        lstETT_QLDLD.add(tmp);
                        // Fill row detail
                        tmp = new ETT_QLDLD();
                        tmp.setREPORT_ID(mRs.getInt("report_id"));
                        tmp.setCREATE_DATE(mRs.getString("create_date"));
                        tmp.setUSER_ID(mRs.getInt("user_id"));
                        tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                        tmp.setGROUP_DES(mRs.getString("group_description"));
                        tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                        tmp.setDETAIL_DES(mRs.getString("detail_level_description"));
                        tmp.setC5(StringUtil.nvl(mRs.getString("c5"), ""));
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
                        tmp.setW5(StringUtil.nvl(mRs.getString("w5"), ""));
                        tmp.setX5(StringUtil.nvl(mRs.getString("x5"), ""));
                        tmp.setY5(StringUtil.nvl(mRs.getString("y5"), ""));
                        tmp.setZ5(StringUtil.nvl(mRs.getString("z5"), ""));
                        tmp.setAA5(StringUtil.nvl(mRs.getString("aa5"), ""));
                        tmp.setAB5(StringUtil.nvl(mRs.getString("ab5"), ""));
                        tmp.setAC5(StringUtil.nvl(mRs.getString("ac5"), ""));
                        tmp.setAD5(StringUtil.nvl(mRs.getString("ad5"), ""));
                        tmp.setAE5(StringUtil.nvl(mRs.getString("ae5"), ""));
                        tmp.setAF5(StringUtil.nvl(mRs.getString("af5"), ""));
                        tmp.setAG5(StringUtil.nvl(mRs.getString("ag5"), ""));
                        tmp.setAH5(StringUtil.nvl(mRs.getString("ah5"), ""));
                        tmp.setAI5(StringUtil.nvl(mRs.getString("ai5"), ""));
                        lstETT_QLDLD.add(tmp);
                        strTMP = strGROUP_LEVEL_ID;
                    } else {
                        tmp.setREPORT_ID(mRs.getInt("report_id"));
                        tmp.setCREATE_DATE(mRs.getString("create_date"));
                        tmp.setUSER_ID(mRs.getInt("user_id"));
                        tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                        tmp.setGROUP_DES(mRs.getString("group_description"));
                        tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                        tmp.setDETAIL_DES(mRs.getString("detail_level_description"));
                        tmp.setC5(StringUtil.nvl(mRs.getString("c5"), ""));
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
                        tmp.setW5(StringUtil.nvl(mRs.getString("w5"), ""));
                        tmp.setX5(StringUtil.nvl(mRs.getString("x5"), ""));
                        tmp.setY5(StringUtil.nvl(mRs.getString("y5"), ""));
                        tmp.setZ5(StringUtil.nvl(mRs.getString("z5"), ""));
                        tmp.setAA5(StringUtil.nvl(mRs.getString("aa5"), ""));
                        tmp.setAB5(StringUtil.nvl(mRs.getString("ab5"), ""));
                        tmp.setAC5(StringUtil.nvl(mRs.getString("ac5"), ""));
                        tmp.setAD5(StringUtil.nvl(mRs.getString("ad5"), ""));
                        tmp.setAE5(StringUtil.nvl(mRs.getString("ae5"), ""));
                        tmp.setAF5(StringUtil.nvl(mRs.getString("af5"), ""));
                        tmp.setAG5(StringUtil.nvl(mRs.getString("ag5"), ""));
                        tmp.setAH5(StringUtil.nvl(mRs.getString("ah5"), ""));
                        tmp.setAI5(StringUtil.nvl(mRs.getString("ai5"), ""));
                        lstETT_QLDLD.add(tmp);
                    }
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, cs, mRs);
        }
        return lstETT_QLDLD;
    }

    public String checkExistInfo(int iUSERID, String strREPORT_NAME, int iYEAR, int iQUARTER) {
        String strSQL = " SELECT distinct report_status "
                + "   FROM report_qldld"
                + "  WHERE user_id = ? AND year = ? AND quarter = ? AND report_name = ? ";
        String strREPORT_STATUS = "";

        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iUSERID);
            mStmt.setInt(2, iYEAR);
            mStmt.setInt(3, iQUARTER);
            mStmt.setString(4, strREPORT_NAME);
            mRs = mStmt.executeQuery();
            if (mRs != null && mRs.next()) {
                strREPORT_STATUS = mRs.getString("report_status");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                close(mConnection, mStmt, mRs);
            } catch (SQLException ex) {
                Logger.getLogger(QL_DLD_Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return strREPORT_STATUS;
    }

    public void saveInfoReport(List<ETT_QLDLD> mListParamTable, int iUSER_ID, String strREPORT_NAME, int iYEAR, int iQUARTER, boolean bIsEditAction) throws Exception {
        String strDELETE = " DELETE FROM report_qldld "
                + "   WHERE user_id = ? AND year = ? "
                + "         AND quarter = ? AND report_name = ?";
        try {
            open();
            if (bIsEditAction) {
                // Delete du lieu cu
                mConnection.setAutoCommit(false);
                mStmt = mConnection.prepareStatement(strDELETE);
                mStmt.setInt(1, iUSER_ID);
                mStmt.setInt(2, iYEAR);
                mStmt.setInt(3, iQUARTER);
                mStmt.setString(4, strREPORT_NAME);
                mStmt.executeUpdate();
                // Cap nhat du lieu moi
                for (ETT_QLDLD objQLDLD : mListParamTable) {
                    // Check ban tin co khai bao 
                    if (objQLDLD.getDETAIL_ID() > 0) {
                        if (objQLDLD.CheckUpdateValue()) {
                            insertInfo(mConnection, objQLDLD, iUSER_ID, iYEAR, iQUARTER, strREPORT_NAME);
                        }
                    }
                }
                mConnection.commit();
            } else {
                // Cap nhat du lieu moi
                for (ETT_QLDLD objQLDLD : mListParamTable) {
                    // Check ban tin co khai bao 
                    if (objQLDLD.getDETAIL_ID() > 0) {
                        if (objQLDLD.CheckUpdateValue()) {
                            insertInfo(mConnection, objQLDLD, iUSER_ID, iYEAR, iQUARTER, strREPORT_NAME);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            mConnection.rollback();
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mStmt, mConnection);
        }
    }

    public void insertInfo(Connection cnn, ETT_QLDLD objData, int iUSER_ID, int iYEAR, int iQUARTER, String strREPORT_NAME) throws SQLException, Exception {
        String strSQL = "   INSERT INTO report_qldld (report_id, create_date, user_id, detail_id,"
                + "        year, quarter, report_name,  "
                + "        c5, d5, e5, f5, g5, h5, i5, j5, k5, l5, m5, n5, "
                + "        o5, p5, q5, r5, s5, t5, u5, v5, w5, x5, y5, "
                + "        z5, aa5, ab5, ac5, ad5, ae5, af5, ag5, ah5, ai5) "
                + "   VALUES ( "
                + "        seq_report_qldld.nextval, sysdate, ?,?,"
                + "        ?,?,?,"
                + "        ?,?,?,?,?,?,?,?,?,?,?,?, "
                + "        ?,?,?,?,?,?,?,?,?,?,?, "
                + "        ?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = cnn.prepareStatement(strSQL);
            stmt.setInt(1, iUSER_ID);
            stmt.setInt(2, objData.getDETAIL_ID());
            stmt.setInt(3, iYEAR);
            stmt.setInt(4, iQUARTER);
            stmt.setString(5, strREPORT_NAME);
            stmt.setString(6, objData.getC5());
            stmt.setString(7, objData.getD5());
            stmt.setString(8, objData.getE5());
            stmt.setString(9, objData.getF5());
            stmt.setString(10, objData.getG5());
            stmt.setString(11, objData.getH5());
            stmt.setString(12, objData.getI5());
            stmt.setString(13, objData.getJ5());
            stmt.setString(14, objData.getK5());
            stmt.setString(15, objData.getL5());
            stmt.setString(16, objData.getM5());
            stmt.setString(17, objData.getN5());
            stmt.setString(18, objData.getO5());
            stmt.setString(19, objData.getP5());
            stmt.setString(20, objData.getQ5());
            stmt.setString(21, objData.getR5());
            stmt.setString(22, objData.getS5());
            stmt.setString(23, objData.getT5());
            stmt.setString(24, objData.getU5());
            stmt.setString(25, objData.getV5());
            stmt.setString(26, objData.getW5());
            stmt.setString(27, objData.getX5());
            stmt.setString(28, objData.getY5());
            stmt.setString(29, objData.getZ5());
            stmt.setString(30, objData.getAA5());
            stmt.setString(31, objData.getAB5());
            stmt.setString(32, objData.getAC5());
            stmt.setString(33, objData.getAD5());
            stmt.setString(34, objData.getAE5());
            stmt.setString(35, objData.getAF5());
            stmt.setString(36, objData.getAG5());
            stmt.setString(37, objData.getAH5());
            stmt.setString(38, objData.getAI5());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.toString());
        } finally {
            close(stmt);
        }
    }

    public List<ETT_GROUP_LEVEL> getListGroupLevel(String strREPORT_NAME, String strGroupUserLevel) throws Exception {
        List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
        String strSQL = "   SELECT DISTINCT a.group_level_id, group_description "
                + "     FROM group_level a, detail_level b "
                + "    WHERE     a.group_level_id = b.group_level_id "
                + "          AND INSTR (qldv_id, ?) > 0 "
                + "          AND report_name = ? "
                + " ORDER BY group_level_id ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strGroupUserLevel);
            mStmt.setString(2, strREPORT_NAME);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ETT_GROUP_LEVEL tmp = new ETT_GROUP_LEVEL();
                tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                tmp.setGROUP_DES(StringUtil.nvl(mRs.getString("group_description"), ""));
                lGroupLevel.add(tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mRs);
            close(mStmt, mConnection);
        }
        return lGroupLevel;
    }

    public Map getMapRuleInfo(String strREPORT_NAME) throws Exception {
        Map mRuleInfo = new HashMap();
        String strSQL = " SELECT detail_id, rule_info "
                + "   FROM detail_level a, group_level b "
                + "  WHERE a.group_level_id = b.group_level_id AND b.report_name = ? ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strREPORT_NAME);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                String strDETAIL_ID = mRs.getString("detail_id");
                String strRuleInfo = StringUtil.nvl(mRs.getString("rule_info"), "");
                if (!strRuleInfo.equals("")) {
                    String[] arrCell = strRuleInfo.split("\\|");
                    for (int i = 0; i < arrCell.length; i++) {
                        String strTmpCell = arrCell[i];
                        String[] arrRuleMap = strTmpCell.split(":");
                        if (arrRuleMap.length >= 2) {
                            String strKEY = arrRuleMap[0] + "_" + strDETAIL_ID;
                            mRuleInfo.put(strKEY, arrRuleMap[1]);
                        }
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mRs);
            close(mStmt, mConnection);
        }
        return mRuleInfo;
    }

    public String getGroupUserLevel(Integer iUSERID) throws Exception {
        String strGroupUserLevel = "";
        String strSQL = " SELECT qldv_id FROM am_user WHERE user_id = ? ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iUSERID);
            mRs = mStmt.executeQuery();
            if (mRs != null && mRs.next()) {
                return StringUtil.nvl(mRs.getString("qldv_id"), "1");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return strGroupUserLevel;
    }

    public List<ETT_QLDLD> getParameTable(String strREPORT_NAME) throws Exception {
        List<ETT_QLDLD> lstETT_QLDLD = new ArrayList<>();

        String strSQL = "   SELECT a.group_level_id, "
                + "          b.detail_id, "
                + "          a.group_description, "
                + "          b.detail_level_description "
                + "     FROM group_level a, detail_level b "
                + "    WHERE a.group_level_id = b.group_level_id AND a.report_name = ? "
                + " ORDER BY a.group_level_id, b.detail_id ";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, strREPORT_NAME);
            mRs = mStmt.executeQuery();
            String strTMP = "";
            while (mRs.next()) {
                ETT_QLDLD tmp = new ETT_QLDLD();
                String strGROUP_LEVEL_ID = mRs.getString("group_level_id");
                if (!strTMP.equals(strGROUP_LEVEL_ID)) {
                    //Tao row groupLevel
                    tmp.setDETAIL_DES(mRs.getString("group_description"));
                    tmp.setDETAIL_ID(0);
                    lstETT_QLDLD.add(tmp);
                    // Fill row detail
                    tmp = new ETT_QLDLD();
                    tmp.setREPORT_ID(mRs.getInt("detail_id"));
                    tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                    tmp.setDETAIL_DES(mRs.getString("detail_level_description"));
                    lstETT_QLDLD.add(tmp);
                    strTMP = strGROUP_LEVEL_ID;
                } else {
                    tmp.setREPORT_ID(mRs.getInt("detail_id"));
                    tmp.setDETAIL_ID(mRs.getInt("detail_id"));
                    tmp.setGROUP_DES(mRs.getString("group_description"));
                    tmp.setGROUP_LEVEL_ID(mRs.getInt("group_level_id"));
                    tmp.setDETAIL_DES(mRs.getString("detail_level_description"));
                    lstETT_QLDLD.add(tmp);
                }
            }
        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            throw new Exception(ex.toString());
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return lstETT_QLDLD;
    }
}
