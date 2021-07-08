/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.util;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.util.ArrayUtils;

/**
 *
 * @author xuanb
 */
public class AppUtil {

    public static boolean isMember(String strCheck, String strList, String strSplit) {
        String[] strMEMBERs = StringUtils.split(strList, strSplit);
        return ArrayUtils.contains(strMEMBERs, strCheck);
    }
}
