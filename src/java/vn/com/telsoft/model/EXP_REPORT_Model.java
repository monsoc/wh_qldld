/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.model;

import com.faplib.lib.admin.data.AMDataPreprocessor;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import oracle.jdbc.OracleTypes;
import org.primefaces.model.DefaultStreamedContent;
import vn.com.telsoft.util.ExportExcel;

/**
 *
 * @author xuanb
 */
public class EXP_REPORT_Model extends AMDataPreprocessor implements Serializable {

    public DefaultStreamedContent exportKTDV_D30(int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_KTDV_D30(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5",
                                                  "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5"};
                return exp.exportKTDV_D30("KTDV_D30", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

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

    public DefaultStreamedContent exportKTDV_D30_TC (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_KTDV_D30_TC(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5",
                                                   "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5", "w5"};
                return exp.exportKTDV_D30_TC("KTDV_D30_TC", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }
    
public DefaultStreamedContent exportGSDV_CAPUY (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_GSDV_CAPUY(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                  "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5", "w5"};
                return exp.exportGSDV_CAPUY("GSDV_CAPUY", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }


public DefaultStreamedContent exportKT_THKL (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_KT_THKL(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                  "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5", "w5"};
                return exp.exportKT_THKL("KT_THKL", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportTHKL_DV_CAPUY (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_THKL_DV_CAPUY(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                   "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5", "w5","x5","y5","z5","aa5","ab5"};
                return exp.exportTHKL_DV_CAPUY("THKL_DV_CAPUY", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportTHKL_TCD_CAPUY (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_THKL_TCD_CAPUY(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                   "n5", "o5", "p5", "q5"};
                return exp.exportTHKL_TCD_CAPUY("THKL_TCD_CAPUY", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportGSTCD_CAPUY (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_GSTCD_CAPUY(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                   "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5"};
                return exp.exportGSTCD_CAPUY("GSTCD_CAPUY", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }


        
public DefaultStreamedContent exportKT_THNVKT (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_KT_THNVKT(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5"};
                return exp.exportKT_THNVKT("KT_THNVKT", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportGQKNKL_DV_CAPUY (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_GQKNKL_DV_CAPUY(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5",
                                                   "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5"};
                return exp.exportGQKNKL_DV_CAPUY("GQKNKL_DV_CAPUY", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportGQKNKL_TCD_CAPUY (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_GQKNKL_TCD_CAPUY(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5",
                                                   "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5"};
                return exp.exportGQKNKL_TCD_CAPUY("GQKNKL_TCD_CAPUY", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportKTDHVPDV (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_KTDHVPDV(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5",
                                                   "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5"};
                return exp.exportKTDHVPDV("KTDHVPDV", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportKTDHVPTC (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_KTDHVPTC(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5",
                                                   "m5", "n5", "o5", "p5", "q5"};
                return exp.exportKTDHVPTC("KTDHVPTC", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportGSDV_UBKT (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_gsdv_ubkt(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                   "n5", "o5", "p5", "q5", "r5"};
                return exp.exportTHKL_TCD_CAPUY("GSDV_UBKT", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportGSTCD_UBKT (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_gstcd_ubkt(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                   "n5", "o5", "p5", "q5", "r5"};
                return exp.exportTHKL_TCD_CAPUY("GSTCD_UBKT", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }

public DefaultStreamedContent exportTHKL_DV_UBKT_CACCAP (int iGroupUserLevel,  int iYEAR, int iQUARTER, String strREPORT_NAME, int iEXPORT_SUB) throws Exception {
        String runSP = "{ call exp_thkl_dv_ubkt_caccap(?,?,?,?,?,?,?) }";
        ExportExcel exp = new ExportExcel();
        CallableStatement cs = null;
        ResultSet rsExport = null;
        try {
            open();
            cs = mConnection.prepareCall(runSP);
            cs.setInt(1, iGroupUserLevel);
            cs.setInt(2, iYEAR);
            cs.setInt(3, iQUARTER);
            cs.setString(4, strREPORT_NAME);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.INTEGER);
            cs.setInt(7, iEXPORT_SUB);
            cs.execute();
            int iCountRow = (int) cs.getObject(6);
            if (iCountRow > 0) {
                rsExport = (ResultSet) cs.getObject(5);
                String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", 
                                                   "n5", "o5", "p5", "q5", "r5"};
                return exp.exportTHKL_TCD_CAPUY("THKL_DV_UBKT_CACCAP", rsExport, arrHeader);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, cs, rsExport);
        }
        return null;
    }
//

    public DefaultStreamedContent exportExcleKTDV_D30_BK(String strREPORT_DATE) throws Exception {
        String strSQL = " /* Formatted on 13-May-2021 07:49:58 (QP5 v5.254) */ "
                + "   SELECT * "
                + "     FROM (SELECT b.group_code stt, "
                + "                  b.group_description dvdkt, "
                + "                  NULL c5, "
                + "                  NULL d5, "
                + "                  NULL e5, "
                + "                  NULL f5, "
                + "                  NULL g5, "
                + "                  NULL h5, "
                + "                  NULL i5, "
                + "                  NULL j5, "
                + "                  NULL k5, "
                + "                  NULL l5, "
                + "                  NULL m5, "
                + "                  NULL n5, "
                + "                  NULL o5, "
                + "                  NULL p5, "
                + "                  NULL q5, "
                + "                  NULL r5, "
                + "                  NULL s5, "
                + "                  NULL t5, "
                + "                  NULL u5, "
                + "                  NULL v5, "
                + "                  0 ord "
                + "             FROM group_level b "
                + "            WHERE report_name = 'KTDV_D30' AND group_code = 'I' "
                + "           UNION ALL "
                + "           SELECT aa.detail_level_code stt, "
                + "                  aa.detail_level_description dvdkt, "
                + "                  case when aa.detail_level_code = 4 then a.j5+a.k5+a.l5 else a.c5 end c5, "
                + "                  a.d5, "
                + "                  a.e5, "
                + "                  a.f5, "
                + "                  a.g5, "
                + "                  a.h5, "
                + "                  a.i5, "
                + "                  a.j5, "
                + "                  a.k5, "
                + "                  a.l5, "
                + "                  a.m5, "
                + "                  a.n5, "
                + "                  a.o5, "
                + "                  a.p5, "
                + "                  a.q5, "
                + "                  a.r5, "
                + "                  a.s5, "
                + "                  a.t5, "
                + "                  a.u5, "
                + "                  a.v5, "
                + "                  1 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'I' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id "
                + "           UNION ALL "
                + "           SELECT NULL stt, "
                + "                  'Cộng (1+2+3+4)' dvdkt, "
                + "                  SUM (a.c5) c5, "
                + "                  SUM (a.d5) d5, "
                + "                  SUM (a.e5) e5, "
                + "                  SUM (a.f5) f5, "
                + "                  SUM (a.g5) g5, "
                + "                  SUM (a.h5) h5, "
                + "                  SUM (a.i5) i5, "
                + "                  SUM (a.j5) j5, "
                + "                  SUM (a.k5) k5, "
                + "                  SUM (a.l5) l5, "
                + "                  SUM (a.m5) m5, "
                + "                  SUM (a.n5) n5, "
                + "                  SUM (a.o5) o5, "
                + "                  SUM (a.p5) p5, "
                + "                  SUM (a.q5) q5, "
                + "                  SUM (a.r5) r5, "
                + "                  SUM (a.s5) s5, "
                + "                  SUM (a.t5) t5, "
                + "                  SUM (a.u5) u5, "
                + "                  SUM (a.v5) v5, "
                + "                  2 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'I' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id "
                + "           UNION ALL "
                + "           SELECT b.group_code stt, "
                + "                  b.group_description dvdkt, "
                + "                  NULL c5, "
                + "                  NULL d5, "
                + "                  NULL e5, "
                + "                  NULL f5, "
                + "                  NULL g5, "
                + "                  NULL h5, "
                + "                  NULL i5, "
                + "                  NULL j5, "
                + "                  NULL k5, "
                + "                  NULL l5, "
                + "                  NULL m5, "
                + "                  NULL n5, "
                + "                  NULL o5, "
                + "                  NULL p5, "
                + "                  NULL q5, "
                + "                  NULL r5, "
                + "                  NULL s5, "
                + "                  NULL t5, "
                + "                  NULL u5, "
                + "                  NULL v5, "
                + "                  3 ord "
                + "             FROM group_level b "
                + "            WHERE report_name = 'KTDV_D30' AND group_code = 'II' "
                + "           UNION ALL "
                + "           SELECT aa.detail_level_code stt, "
                + "                  aa.detail_level_description dvdkt, "
                + "                  case when aa.detail_level_code = 8 then a.h5+a.i5+a.j5  "
                + "                       when aa.detail_level_code = 9 then a.j5+a.k5+a.l5 else a.c5 end c5, "
                + "                  a.d5, "
                + "                  a.e5, "
                + "                  a.f5, "
                + "                  a.g5, "
                + "                  a.h5, "
                + "                  a.i5, "
                + "                  a.j5, "
                + "                  a.k5, "
                + "                  a.l5, "
                + "                  a.m5, "
                + "                  a.n5, "
                + "                  a.o5, "
                + "                  a.p5, "
                + "                  a.q5, "
                + "                  a.r5, "
                + "                  a.s5, "
                + "                  a.t5, "
                + "                  a.u5, "
                + "                  a.v5, "
                + "                  4 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'II' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id "
                + "           UNION ALL "
                + "           SELECT NULL stt, "
                + "                  'Cộng (5+6+7+8+9)' dvdkt, "
                + "                  SUM (a.c5) c5, "
                + "                  SUM (a.d5) d5, "
                + "                  SUM (a.e5) e5, "
                + "                  SUM (a.f5) f5, "
                + "                  SUM (a.g5) g5, "
                + "                  SUM (a.h5) h5, "
                + "                  SUM (a.i5) i5, "
                + "                  SUM (a.j5) j5, "
                + "                  SUM (a.k5) k5, "
                + "                  SUM (a.l5) l5, "
                + "                  SUM (a.m5) m5, "
                + "                  SUM (a.n5) n5, "
                + "                  SUM (a.o5) o5, "
                + "                  SUM (a.p5) p5, "
                + "                  SUM (a.q5) q5, "
                + "                  SUM (a.r5) r5, "
                + "                  SUM (a.s5) s5, "
                + "                  SUM (a.t5) t5, "
                + "                  SUM (a.u5) u5, "
                + "                  SUM (a.v5) v5, "
                + "                  5 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'II' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id "
                + "           UNION ALL "
                + "           SELECT b.group_code stt, "
                + "                  b.group_description dvdkt, "
                + "                  NULL c5, "
                + "                  NULL d5, "
                + "                  NULL e5, "
                + "                  NULL f5, "
                + "                  NULL g5, "
                + "                  NULL h5, "
                + "                  NULL i5, "
                + "                  NULL j5, "
                + "                  NULL k5, "
                + "                  NULL l5, "
                + "                  NULL m5, "
                + "                  NULL n5, "
                + "                  NULL o5, "
                + "                  NULL p5, "
                + "                  NULL q5, "
                + "                  NULL r5, "
                + "                  NULL s5, "
                + "                  NULL t5, "
                + "                  NULL u5, "
                + "                  NULL v5, "
                + "                  6 ord "
                + "             FROM group_level b "
                + "            WHERE report_name = 'KTDV_D30' AND group_code = 'III' "
                + "           UNION ALL "
                + "           SELECT aa.detail_level_code stt, "
                + "                  aa.detail_level_description dvdkt, "
                + "                  case when aa.detail_level_code = 14 then a.h5+a.i5+a.j5+a.k5+a.l5 else a.c5 end c5, "
                + "                  a.d5, "
                + "                  a.e5, "
                + "                  a.f5, "
                + "                  a.g5, "
                + "                  a.h5, "
                + "                  a.i5, "
                + "                  a.j5, "
                + "                  a.k5, "
                + "                  a.l5, "
                + "                  a.m5, "
                + "                  a.n5, "
                + "                  a.o5, "
                + "                  a.p5, "
                + "                  a.q5, "
                + "                  a.r5, "
                + "                  a.s5, "
                + "                  a.t5, "
                + "                  a.u5, "
                + "                  a.v5, "
                + "                  7 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'III' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id "
                + "           UNION ALL "
                + "           SELECT NULL stt, "
                + "                  'Cộng (10+11+12+13+14+15)' dvdkt, "
                + "                  SUM (a.c5) c5, "
                + "                  SUM (a.d5) d5, "
                + "                  SUM (a.e5) e5, "
                + "                  SUM (a.f5) f5, "
                + "                  SUM (a.g5) g5, "
                + "                  SUM (a.h5) h5, "
                + "                  SUM (a.i5) i5, "
                + "                  SUM (a.j5) j5, "
                + "                  SUM (a.k5) k5, "
                + "                  SUM (a.l5) l5, "
                + "                  SUM (a.m5) m5, "
                + "                  SUM (a.n5) n5, "
                + "                  SUM (a.o5) o5, "
                + "                  SUM (a.p5) p5, "
                + "                  SUM (a.q5) q5, "
                + "                  SUM (a.r5) r5, "
                + "                  SUM (a.s5) s5, "
                + "                  SUM (a.t5) t5, "
                + "                  SUM (a.u5) u5, "
                + "                  SUM (a.v5) v5, "
                + "                  8 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'III' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id "
                + "           UNION ALL "
                + "           SELECT b.group_code stt, "
                + "                  b.group_description dvdkt, "
                + "                  NULL c5, "
                + "                  NULL d5, "
                + "                  NULL e5, "
                + "                  NULL f5, "
                + "                  NULL g5, "
                + "                  NULL h5, "
                + "                  NULL i5, "
                + "                  NULL j5, "
                + "                  NULL k5, "
                + "                  NULL l5, "
                + "                  NULL m5, "
                + "                  NULL n5, "
                + "                  NULL o5, "
                + "                  NULL p5, "
                + "                  NULL q5, "
                + "                  NULL r5, "
                + "                  NULL s5, "
                + "                  NULL t5, "
                + "                  NULL u5, "
                + "                  NULL v5, "
                + "                  9 ord "
                + "             FROM group_level b "
                + "            WHERE report_name = 'KTDV_D30' AND group_code = 'IV' "
                + "           UNION ALL "
                + "           SELECT aa.detail_level_code stt, "
                + "                  aa.detail_level_description dvdkt, "
                + "                  a.c5, "
                + "                  a.d5, "
                + "                  a.e5, "
                + "                  a.f5, "
                + "                  a.g5, "
                + "                  a.h5, "
                + "                  a.i5, "
                + "                  a.j5, "
                + "                  a.k5, "
                + "                  a.l5, "
                + "                  a.m5, "
                + "                  a.n5, "
                + "                  a.o5, "
                + "                  a.p5, "
                + "                  a.q5, "
                + "                  a.r5, "
                + "                  a.s5, "
                + "                  a.t5, "
                + "                  a.u5, "
                + "                  a.v5, "
                + "                  10 ord "
                + "             FROM report_qldld a "
                + "                  RIGHT JOIN "
                + "                  (SELECT detail_id, "
                + "                          b.detail_level_code, "
                + "                          b.detail_level_description "
                + "                     FROM detail_level b, group_level c "
                + "                    WHERE     b.group_level_id = c.group_level_id "
                + "                          AND c.group_code = 'IV' "
                + "                          AND c.report_name = 'KTDV_D30') aa "
                + "                      ON a.detail_id = aa.detail_id) "
                + " ORDER BY ord, stt ";
        ExportExcel exp = new ExportExcel();
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5"};
            return exp.exportKTDV_D30("KTDV_D30", mRs, arrHeader);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(mConnection, mStmt, mRs);
        }
        return null;
    }
}
