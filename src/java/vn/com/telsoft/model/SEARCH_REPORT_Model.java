/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.model;

import com.faplib.lib.admin.data.AMDataPreprocessor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author xuanb
 */
public class SEARCH_REPORT_Model extends AMDataPreprocessor implements Serializable {


    public List<Vector> getListReportName() throws Exception {
        List<Vector> lReportName = new ArrayList<>();
        String strSQL = "SELECT report_name FROM list_report ORDER BY report_id";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(mRs.getString("report_name"));
                lReportName.add(vtRow);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mStmt, mConnection);
        }
        return lReportName;
    }
    
    public List<Vector> getListGroupUser(int iUSER_ID) throws Exception {
        List<Vector> lReportName = new ArrayList<>();
        String strSQL = "   SELECT GROUP_ID, name" +
                        "     FROM (SELECT a.GROUP_ID, a.name" +
                        "             FROM am_group a" +
                        "            WHERE a.parent_id IN (SELECT a.GROUP_ID" +
                        "                                    FROM am_group a, am_user b, am_group_user c" +
                        "                                   WHERE a.GROUP_ID = c.GROUP_ID AND b.user_id = c.user_id AND b.user_id = ?)" +
                        "           UNION ALL" +
                        "           SELECT -1 GROUP_ID, a.name" +
                        "             FROM am_group a, am_user b, am_group_user c" +
                        "            WHERE a.GROUP_ID = c.GROUP_ID AND b.user_id = c.user_id AND b.user_id = ?" +
                        "           UNION ALL" +
                        "           SELECT -2 GROUP_ID, a.name || '(Bao gồm cả dữ liệu cấp dưới)'" +
                        "             FROM am_group a, am_user b, am_group_user c" +
                        "            WHERE a.GROUP_ID = c.GROUP_ID AND b.user_id = c.user_id AND b.user_id = ?)" +
                        " ORDER BY GROUP_ID" ;
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setInt(1, iUSER_ID);
            mStmt.setInt(2, iUSER_ID);
            mStmt.setInt(3, iUSER_ID);
            mRs = mStmt.executeQuery();
            System.out.println("Hello............."+ iUSER_ID);
            while (mRs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(mRs.getString("GROUP_ID"));
                vtRow.add(mRs.getString("name"));
                lReportName.add(vtRow);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mStmt, mConnection);
        }
        return lReportName;
    }
}
