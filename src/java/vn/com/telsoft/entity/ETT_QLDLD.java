/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.entity;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class ETT_QLDLD implements Serializable {

    private Integer REPORT_ID;
    private String CREATE_DATE;
    private Integer USER_ID;
    private Integer DETAIL_ID;
    private Integer YEAR;
    private Integer QUARTER;
    private Integer REPORT_STATUS;
    private String REPORT_NAME;

    private String C5;
    private String D5;
    private String E5;
    private String F5;
    private String G5;
    private String H5;
    private String I5;
    private String J5;
    private String K5;
    private String L5;
    private String M5;
    private String N5;
    private String O5;
    private String P5;
    private String Q5;
    private String R5;
    private String S5;
    private String T5;
    private String U5;
    private String V5;
    private String W5;
    private String X5;
    private String Y5;
    private String Z5;
    private String AA5;
    private String AB5;
    private String AC5;
    private String AD5;
    private String AE5;
    private String AF5;
    private String AG5;
    private String AH5;
    private String AI5;

    private Integer GROUP_LEVEL_ID;
    private String GROUP_DES;
    private String DETAIL_DES;

    public ETT_QLDLD() {
    }

    public ETT_QLDLD(String GROUP_DES, String DETAIL_DES, Integer REPORT_ID) {
        this.GROUP_DES = GROUP_DES;
        this.DETAIL_DES = DETAIL_DES;
        this.REPORT_ID = REPORT_ID;
    }


    public boolean CheckUpdateValue() {
        if ( (C5 != null && !C5.equals("")) || (D5 != null && !D5.equals("")) || (E5 != null && !E5.equals("") ) || (F5 != null && !F5.equals(""))
                || (G5 != null && !G5.equals("")) || (H5 != null && !H5.equals("")) || (I5 != null && !I5.equals(""))
                || (K5 != null && !K5.equals("")) || (L5 != null && !L5.equals("")) || (M5 != null && !M5.equals("")) 
                || (N5 != null && !N5.equals("")) || (O5 != null && !O5.equals("")) || (P5 != null && !P5.equals("")) 
                || (Q5 != null && !Q5.equals("")) || (R5 != null && !R5.equals("")) || (S5 != null && !S5.equals(""))
                || (T5 != null && !T5.equals("")) || (U5 != null && !U5.equals("")) || (V5 != null && !V5.equals(""))
                || (W5 != null && !W5.equals("")) || (X5 != null && !X5.equals("")) || (Y5 != null && !Y5.equals(""))
                || (Z5 != null && !Z5.equals("")) || (AA5!= null && !AA5.equals(""))|| (AB5!= null && !AB5.equals(""))
                || (AC5!= null && !AC5.equals(""))|| (AD5!= null && !AD5.equals(""))|| (AE5!= null && !AE5.equals(""))
                || (AF5!= null && !AF5.equals(""))|| (AG5!= null && !AG5.equals(""))|| (AH5!= null && !AH5.equals(""))
                || (AI5!= null && !AI5.equals(""))) {
            return true;
        }
        return false;
    }

    public String getC5() {
        return C5;
    }

    public void setC5(String C5) {
        this.C5 = C5;
    }

    public String getD5() {
        return D5;
    }

    public void setD5(String D5) {
        this.D5 = D5;
    }

    public String getE5() {
        return E5;
    }

    public void setE5(String E5) {
        this.E5 = E5;
    }

    public String getF5() {
        return F5;
    }

    public void setF5(String F5) {
        this.F5 = F5;
    }

    public String getG5() {
        return G5;
    }

    public void setG5(String G5) {
        this.G5 = G5;
    }

    public String getH5() {
        return H5;
    }

    public void setH5(String H5) {
        this.H5 = H5;
    }

    public String getI5() {
        return I5;
    }

    public void setI5(String I5) {
        this.I5 = I5;
    }

    public String getJ5() {
        return J5;
    }

    public void setJ5(String J5) {
        this.J5 = J5;
    }

    public String getK5() {
        return K5;
    }

    public void setK5(String K5) {
        this.K5 = K5;
    }

    public String getL5() {
        return L5;
    }

    public void setL5(String L5) {
        this.L5 = L5;
    }

    public String getM5() {
        return M5;
    }

    public void setM5(String M5) {
        this.M5 = M5;
    }

    public String getN5() {
        return N5;
    }

    public void setN5(String N5) {
        this.N5 = N5;
    }

    public String getO5() {
        return O5;
    }

    public void setO5(String O5) {
        this.O5 = O5;
    }

    public String getP5() {
        return P5;
    }

    public void setP5(String P5) {
        this.P5 = P5;
    }

    public String getQ5() {
        return Q5;
    }

    public void setQ5(String Q5) {
        this.Q5 = Q5;
    }

    public String getR5() {
        return R5;
    }

    public void setR5(String R5) {
        this.R5 = R5;
    }

    public String getS5() {
        return S5;
    }

    public void setS5(String S5) {
        this.S5 = S5;
    }

    public String getT5() {
        return T5;
    }

    public void setT5(String T5) {
        this.T5 = T5;
    }

    public String getU5() {
        return U5;
    }

    public void setU5(String U5) {
        this.U5 = U5;
    }

    public String getV5() {
        return V5;
    }

    public void setV5(String V5) {
        this.V5 = V5;
    }

    public String getW5() {
        return W5;
    }

    public void setW5(String W5) {
        this.W5 = W5;
    }

    public String getX5() {
        return X5;
    }

    public void setX5(String X5) {
        this.X5 = X5;
    }

    public String getY5() {
        return Y5;
    }

    public void setY5(String Y5) {
        this.Y5 = Y5;
    }

    public String getZ5() {
        return Z5;
    }

    public void setZ5(String Z5) {
        this.Z5 = Z5;
    }

    public String getAA5() {
        return AA5;
    }

    public void setAA5(String AA5) {
        this.AA5 = AA5;
    }

    public String getAB5() {
        return AB5;
    }

    public void setAB5(String AB5) {
        this.AB5 = AB5;
    }

    public String getAC5() {
        return AC5;
    }

    public void setAC5(String AC5) {
        this.AC5 = AC5;
    }

    public String getAD5() {
        return AD5;
    }

    public void setAD5(String AD5) {
        this.AD5 = AD5;
    }

    public String getAE5() {
        return AE5;
    }

    public void setAE5(String AE5) {
        this.AE5 = AE5;
    }

    public String getAF5() {
        return AF5;
    }

    public void setAF5(String AF5) {
        this.AF5 = AF5;
    }

    public String getAG5() {
        return AG5;
    }

    public void setAG5(String AG5) {
        this.AG5 = AG5;
    }

    public String getAH5() {
        return AH5;
    }

    public void setAH5(String AH5) {
        this.AH5 = AH5;
    }

    public String getAI5() {
        return AI5;
    }

    public void setAI5(String AI5) {
        this.AI5 = AI5;
    }

    

    public Integer getREPORT_ID() {
        return REPORT_ID;
    }

    public void setREPORT_ID(Integer REPORT_ID) {
        this.REPORT_ID = REPORT_ID;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public Integer getDETAIL_ID() {
        return DETAIL_ID;
    }

    public void setDETAIL_ID(Integer DETAIL_ID) {
        this.DETAIL_ID = DETAIL_ID;
    }

    public Integer getYEAR() {
        return YEAR;
    }

    public void setYEAR(Integer YEAR) {
        this.YEAR = YEAR;
    }

    public Integer getQUARTER() {
        return QUARTER;
    }

    public void setQUARTER(Integer QUARTER) {
        this.QUARTER = QUARTER;
    }

    public Integer getREPORT_STATUS() {
        return REPORT_STATUS;
    }

    public void setREPORT_STATUS(Integer REPORT_STATUS) {
        this.REPORT_STATUS = REPORT_STATUS;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }


    public Integer getGROUP_LEVEL_ID() {
        return GROUP_LEVEL_ID;
    }

    public void setGROUP_LEVEL_ID(Integer GROUP_LEVEL_ID) {
        this.GROUP_LEVEL_ID = GROUP_LEVEL_ID;
    }

    public String getGROUP_DES() {
        return GROUP_DES;
    }

    public void setGROUP_DES(String GROUP_DES) {
        this.GROUP_DES = GROUP_DES;
    }

    public String getDETAIL_DES() {
        return DETAIL_DES;
    }

    public void setDETAIL_DES(String DETAIL_DES) {
        this.DETAIL_DES = DETAIL_DES;
    }

    

}
