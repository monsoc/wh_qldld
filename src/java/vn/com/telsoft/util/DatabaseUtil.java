/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.util;

import com.faplib.applet.util.StringUtil;
import com.faplib.lib.admin.data.AMDataPreprocessor;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vn.com.telsoft.entity.ETT_DETAIL_LEVEL;
import vn.com.telsoft.entity.ETT_GROUP_LEVEL;

/**
 *
 * @author xuanb
 */
public class DatabaseUtil extends AMDataPreprocessor implements Serializable {

    public static List<ETT_GROUP_LEVEL> getListGroupLevel(Connection cnn, String strREPORT_NAME) throws Exception {
        List<ETT_GROUP_LEVEL> lGroupLevel = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String strSQL = "   SELECT   group_level_id, group_description "
                + "     FROM   group_level "
                + "    WHERE   report_name = ? "
                + " ORDER BY   group_level_id ";
        try {
            stmt = cnn.prepareStatement(strSQL);
            stmt.setString(1, strREPORT_NAME);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ETT_GROUP_LEVEL tmp = new ETT_GROUP_LEVEL();
                tmp.setGROUP_LEVEL_ID(rs.getInt("group_level_id"));
                tmp.setGROUP_DES(StringUtil.nvl(rs.getString("group_description"), ""));
                lGroupLevel.add(tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
        }
        return lGroupLevel;
    }

    

    public static String getSequenceValue(Connection cn, String sequenceName)
            throws Exception {
        String strSQL = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(strSQL);
            rs.next();
            String strReturn = rs.getString(1);
            rs.close();
            stmt.close();
            return strReturn;
        } catch (Exception e) {
            if ((e.getMessage() != null) && (e.getMessage().startsWith("ORA-02289"))) {
                Statement stmt = cn.createStatement();
                stmt.executeUpdate("CREATE SEQUENCE " + sequenceName + " START WITH " + String.valueOf(1 + 1));
                stmt.close();
                return String.valueOf(1);
            }
            throw e;
        }
    }
}
