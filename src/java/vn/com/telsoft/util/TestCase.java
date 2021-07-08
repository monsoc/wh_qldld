/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xuanb
 */
public class TestCase {

    public static void main(String[] arsg) {
        String strSQL = " SELECT (SELECT name FROM am_group WHERE GROUP_ID = p_group_id ),"
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

        strSQL = strSQL.replace("p_group_id", "19"); //
        strSQL = strSQL.replace("p_capkb", "1");
        strSQL = strSQL.replace("p_year", "2021");
        strSQL = strSQL.replace("p_quarter", "1");
        System.out.println(strSQL.replace("p_year", "2021"));

    }
}
