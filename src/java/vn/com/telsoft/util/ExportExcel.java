/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.util;

/**
 *
 * @author xuanb
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FontScheme;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import oracle.jdbc.OracleTypes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;

public class ExportExcel {

    public void exportExcelMultilSheet(String fileName, ResultSet rs, String[] arrHeader, String strColumnSplitSheet) {
        try {
            String strOutFileName = fileName.substring(0, fileName.length() - 4) + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xls";
            XSSFWorkbook workbook = new XSSFWorkbook();
            String strTMP = "";
            int rowNum = 0;
            XSSFSheet sheet = null;
            while (rs.next()) {
                String strNewSheet = rs.getString(strColumnSplitSheet);
                if (!strNewSheet.equals(strTMP)) {
                    strTMP = strNewSheet;
                    rowNum = 0;
                    sheet = workbook.createSheet(strNewSheet);
                    Row row = sheet.createRow(rowNum++);
                    int colNum = 0;
                    for (int i = 0; i < arrHeader.length; i++) {
                        Cell cell = row.createCell(colNum++);
                        cell.setCellValue(arrHeader[i].toString());
                    }
                    colNum = 0;
                    row = sheet.createRow(rowNum++);
                    for (int i = 0; i < arrHeader.length; i++) {
                        Cell cell = row.createCell(colNum++);
                        cell.setCellValue(rs.getString(arrHeader[i]));
                    }
                } else {
                    Row row = sheet.createRow(rowNum++);
                    int colNum = 0;
                    for (int i = 0; i < arrHeader.length; i++) {
                        Cell cell = row.createCell(colNum++);
                        cell.setCellValue(rs.getString(arrHeader[i]));
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream(strOutFileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

public DefaultStreamedContent exportGQKNKL_DV_CAPUY(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("GI???I QUY???T KHI???U N???I K??? LU???T ?????NG VI??N C???A C???P ???Y C??C C???P");
            setMerge(sheet, 0, 0, 0, 20, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(19);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 19, 20, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("?????ng vi??n khi???u n???i k??? lu???t ?????ng");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ph???i gi???i quy???t");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("???? gi???i quy???t xong");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C???p gi???i quy???t khi???u n???i");
            setMerge(sheet, 2, 2, 4, 9, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("N???i dung khi???u n???i");
            setMerge(sheet, 2, 2, 10, 12, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 13, 20, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(4);
            cell.setCellValue("BCHTW, B??? Ch??nh tr???, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("T???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("BTV t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huy???n u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("BTV huy???n u??? v?? t????ng ??????ng ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //
            cell = row.createCell(10);
            cell.setCellValue("N???i dung vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("H??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Nguy??n t???c, th??? t???c, quy tr??nh, th???m quy???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            //

            cell = row.createCell(13);
            cell.setCellValue("Gi??? nguy??n h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Thay ?????i h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(15);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 15, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(18);
            cell.setCellValue("Nguy??n nh??n thay ?????i h??nh th???c k??? lu???t");
            setMerge(sheet, 3, 3, 18, 20, true);
            formatCell(workbook, cell, false, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 2, 4, 2, 2, true);
            setMerge(sheet, 2, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);


            cell = row.createCell(15);
            cell.setCellValue("T??ng h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Gi???m h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("X??a h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(18);
            cell.setCellValue("V???n d???ng ph????ng h?????ng, ph????ng ch??m THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(19);
            cell.setCellValue("Th???m tra, x??c minh");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(20);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 20; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II") ||
                        rs.getString(arrHeader[0]).equals("III"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


public DefaultStreamedContent exportGQKNKL_TCD_CAPUY(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("GI???I QUY???T KHI???U N???I K??? LU???T T??? CH???C ?????NG C???A C???P ???Y C??C C???P");
            setMerge(sheet, 0, 0, 0, 21, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(20);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 20, 21, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng khi???u n???i k??? lu???t ?????ng");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ph???i gi???i quy???t");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("???? gi???i quy???t xong");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C???p gi???i quy???t khi???u n???i");
            setMerge(sheet, 2, 2, 4, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("N???i dung khi???u n???i");
            setMerge(sheet, 2, 2, 11, 13, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 14, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(4);
            cell.setCellValue("BCHTW");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("B??? Ch??nh tr???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("T???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("BTV t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Huy???n u??? v?? t????ng ??????ng ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(10);
            cell.setCellValue("BTV huy???n u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //
            cell = row.createCell(11);
            cell.setCellValue("N???i dung vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("H??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Nguy??n t???c, th??? t???c, quy tr??nh, th???m quy???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            //

            cell = row.createCell(14);
            cell.setCellValue("Gi??? nguy??n h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Thay ?????i h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 16, 18, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(19);
            cell.setCellValue("Nguy??n nh??n thay ?????i h??nh th???c k??? lu???t");
            setMerge(sheet, 3, 3, 19, 21, true);
            formatCell(workbook, cell, false, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 2, 4, 2, 2, true);
            setMerge(sheet, 2, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);
            setMerge(sheet, 3, 4, 15, 15, true);


            cell = row.createCell(16);
            cell.setCellValue("T??ng h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Gi???m h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("X??a h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(19);
            cell.setCellValue("V???n d???ng ph????ng h?????ng, ph????ng ch??m THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(20);
            cell.setCellValue("Th???m tra, x??c minh");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(21);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 21; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


public DefaultStreamedContent exportKTDHVPDV(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("KI???M TRA ?????NG VI??N KHI C?? D???U HI???U VI PH???M C???A ???Y BAN KI???M TRA C??C C???P V?? CHI B???");
            setMerge(sheet, 0, 0, 0, 21, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(20);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 20, 21, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("?????ng vi??n ???????c ki???m tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ?????ng vi??n ???????c ki???m tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 7, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("N???i dung ki???m tra");
            setMerge(sheet, 2, 2, 8, 18, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 19, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huy???n u??? v?? t????ng ??????ng ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT ?????ng u??? c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Chi b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(8);
            cell.setCellValue("Vi???c ch???p h??nh nguy??n t???c t???p trung d??n ch???, quy ch??? l??m vi???c, ch??? ????? c??ng t??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Vi???c gi??? g??n ph???m ch???t ?????o ?????c, l???i s???ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(10);
            cell.setCellValue("Vi???c gi??? g??n ??o??n k???t n???i b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Nh???ng ??i???u ?????ng vi??n kh??ng ???????c l??m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Ch??nh s??ch d??n s???- k??? ho???ch h??a gia ????nh");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Tham nh??ng, c??? ?? l??m tr??i");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            //

            cell = row.createCell(14);
            cell.setCellValue("Thi???u tr??ch nhi???m, bu??ng l???ng l??nh ?????o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("?????t ??ai, t??i nguy??n, kho??ng s???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("T??i ch??nh, ng??n h??ng, ?????u t??, x??y d???ng c?? b???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(17);
            cell.setCellValue("K?? khai t??i s???n, thu nh???p");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(18);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            /////
            cell = row.createCell(19);
            cell.setCellValue("C?? vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(20);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 20, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 2, 4, 2, 2, true);
            
            setMerge(sheet, 3, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);
            setMerge(sheet, 3, 4, 15, 15, true);
            setMerge(sheet, 3, 4, 16, 16, true);
            setMerge(sheet, 3, 4, 17, 17, true);
            setMerge(sheet, 3, 4, 18, 18, true);
            setMerge(sheet, 3, 4, 19, 19, true);


            cell = row.createCell(20);
            cell.setCellValue("Ph???i thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("???? thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 21; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && 
                        (rs.getString(arrHeader[0]).equals("I") ||
                         rs.getString(arrHeader[0]).equals("II")||
                        rs.getString(arrHeader[0]).equals("III")||
                        rs.getString(arrHeader[0]).equals("IV")
                        )) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

public DefaultStreamedContent exportKTDHVPTC(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(" KI???M TRA T??? CH???C ?????NG KHI C?? D???U HI???U VI PH???M C???A ???Y BAN KI???M TRA C??C C???P");
            setMerge(sheet, 0, 0, 0, 16, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(15);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 15, 16, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng ???????c ki???m tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? t??? ch???c ?????ng ???????c ki???m tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("N???i dung ki???m tra");
            setMerge(sheet, 2, 2, 7, 13, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 14, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huy???n u??? v?? t????ng ??????ng ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT ?????ng u??? c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ///
            cell = row.createCell(7);
            cell.setCellValue("Vi???c ch???p h??nh ngh??? quy???t, ch??? th???, quy ?????nh, k???t lu???n c???a ?????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(8);
            cell.setCellValue("Vi???c ch???p h??nh ch??nh s??ch, ph??p lu???t c???a Nh?? n?????c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Vi???c th???c hi???n quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???, ??o??n k???t n???i b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(10);
            cell.setCellValue("C??ng t??c c??n b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Th???c h??nh ti???t ki???m, ch???ng l??ng ph??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Gi???i quy???t khi???u n???i, t??? c??o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            /////
            cell = row.createCell(14);
            cell.setCellValue("C?? vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(15);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 15, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 2, 4, 2, 2, true);
            
            setMerge(sheet, 3, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);


            cell = row.createCell(15);
            cell.setCellValue("Ph???i thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("???? thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 16; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && 
                        (rs.getString(arrHeader[0]).equals("I") ||
                         rs.getString(arrHeader[0]).equals("II")
                        )) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void formatCell(XSSFWorkbook workbook, Cell cell, boolean bRotateUp, boolean bWrapText, boolean bBoldText, boolean bForegroundColor) {
        XSSFCellStyle cs = workbook.createCellStyle();

        if (bRotateUp) {
            cs.setRotation((short) 90);              // set text rotation
            cs.getStyleXf().setApplyAlignment(true); // <<< Important
        }
        cs.setWrapText(bWrapText); //Set wordwrap

        XSSFFont font = workbook.createFont();
        font.setBold(bBoldText);
        cs.setFont(font);

        if (bForegroundColor) {
            cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        // Boder
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        cell.setCellStyle(cs);
    }

    public void formatCell(XSSFWorkbook workbook, Cell cell, boolean bRotateUp, boolean bWrapText, boolean bBoldText) {
        XSSFCellStyle cs = workbook.createCellStyle();

        if (bRotateUp) {
            cs.setRotation((short) 90);              // set text rotation
            cs.getStyleXf().setApplyAlignment(true); // <<< Important
        }
        cs.setWrapText(bWrapText); //Set wordwrap

        XSSFFont font = workbook.createFont();
        font.setBold(bBoldText);
        cs.setFont(font);

        // Boder
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        cell.setCellStyle(cs);
    }

    public void formatCell(XSSFWorkbook workbook, Cell cell, boolean bRotateUp, boolean bWrapText, boolean bBoldText, HorizontalAlignment hrz, VerticalAlignment vtc) {
        XSSFCellStyle cs = workbook.createCellStyle();

        if (bRotateUp) {
            cs.setRotation((short) 90);              // set text rotation
            cs.getStyleXf().setApplyAlignment(true); // <<< Important
        }
        cs.setWrapText(bWrapText); //Set wordwrap

        XSSFFont font = workbook.createFont();
        font.setBold(bBoldText);
        cs.setFont(font);

        // Boder
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        cell.setCellStyle(cs);

        // 
        cs.setAlignment(hrz);
        cs.setVerticalAlignment(vtc);
    }

    public void formatCell(XSSFWorkbook workbook, Cell cell, boolean bRotateUp, boolean bWrapText,
            boolean bBoldText, HorizontalAlignment hrz, VerticalAlignment vtc, boolean bBorder) {
        XSSFCellStyle cs = workbook.createCellStyle();

        if (bRotateUp) {
            cs.setRotation((short) 90);              // set text rotation
            cs.getStyleXf().setApplyAlignment(true); // <<< Important
        }
        cs.setWrapText(bWrapText); //Set wordwrap

        XSSFFont font = workbook.createFont();
        font.setBold(bBoldText);
        cs.setFont(font);

        if (bBorder) {
            // Boder
            cs.setBorderTop(BorderStyle.THIN);
            cs.setBorderRight(BorderStyle.THIN);
            cs.setBorderBottom(BorderStyle.THIN);
            cs.setBorderLeft(BorderStyle.THIN);
        }
        // 
        cs.setAlignment(hrz);
        cs.setVerticalAlignment(vtc);

        cell.setCellStyle(cs);
    }

    public static XSSFCellStyle getDefaultStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 8);
        font.setFontName("Times New Roma");
        font.setBold(true);
        ((XSSFFont) font).setFamily(3);
        ((XSSFFont) font).setScheme(FontScheme.NONE);
        cs.setFont(font);
        return cs;
    }

    public DefaultStreamedContent exportKTDV_D30(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("KI???M TRA ?????NG VI??N THEO ??I???U 30 ??I???U L??? ?????NG");
            setMerge(sheet, 0, 0, 0, 21, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(20);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 20, 21, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("?????ng vi??n ???????c ki???m tra");
            sheet.autoSizeColumn(1);

            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ?????ng vi??n ???????c ki???m tra");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 11, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("N???i dung ki???m tra");
            setMerge(sheet, 2, 2, 12, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 17, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(3);
            cell.setCellValue("BCHTW, BCT, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C??c CQ tham m??u c???a TW");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("T???nh ???y, BTV t???nh u??? v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("C??c CQ tham m??u c???a c???p ???y t???nh v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huy???n ???y, BTV HU v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("C??c CQ tham m??u c???a  c???p ???y huy???n v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???, BTV ?????ng ???y c?? s???");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("?????ng ???y b??? ph???n");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi b???");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Vi???c ch???p h??nh quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Vi???c gi??? g??n ph???m ch???t ?????o ?????c, l???i s???ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Vi???c th???c hi???n ch???c tr??ch nhi???m v??? ???????c giao");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Vi???c th???c hi???n nh???ng ??i???u ?????ng vi??n kh??ng ???????c l??m");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Kh??c");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Th???c hi???n t???t");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Th???c hi???n ch??a t???t");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 19, 21, true);
            formatCell(workbook, cell, false, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 3, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);
            setMerge(sheet, 3, 4, 15, 15, true);
            setMerge(sheet, 3, 4, 16, 16, true);
            setMerge(sheet, 3, 4, 17, 17, true);
            setMerge(sheet, 3, 4, 18, 18, true);

            cell = row.createCell(19);
            cell.setCellValue("C?? khuy???t ??i???m, vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Ph???i thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("???? thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i < 22; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II") ||
                        rs.getString(arrHeader[0]).equals("III")||
                        rs.getString(arrHeader[0]).equals("IV"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public DefaultStreamedContent exportKTDV_D30_TC(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("KI???M TRA T??? CH???C ?????NG C???P D?????I THEO ??I???U 30 ??I???U L??? ?????NG");
            setMerge(sheet, 0, 0, 0, 22, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(21);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 21, 22, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng ???????c ki???m tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? t??? ch???c ?????ng ???????c ki???m tra");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("N???i dung ki???m tra");
            setMerge(sheet, 2, 2, 11, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 18, 22, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(3);
            cell.setCellValue("BCHTW, BCT, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C??c CQ tham m??u c???a TW");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("T???nh ???y, BTV t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("C??c CQ tham m??u c???a c???p ???y t???nh v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huy???n ???y, BTV HU v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("C??c CQ tham m??u c???a  c???p ???y huy???n v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???, BTV ?????ng ???y c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("?????ng ???y b??? ph???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Vi???c ch???p h??nh ngh??? quy???t, ch??? th???, quy ?????nh, k???t lu???n c???a ?????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Vi???c ch???p h??nh ch??nh s??ch, ph??p lu???t c???a Nh?? n?????c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Vi???c th???c hi???n quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???, ??o??n k???t n???i b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("C??ng t??c c??n b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Th???c h??nh ti???t ki???m, ch???ng l??ng ph??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Gi???i quy???t khi???u n???i, t??? c??o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Th???c hi???n t???t");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Th???c hi???n ch??a t???t");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 20, 22, true);
            formatCell(workbook, cell, false, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 3, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);
            setMerge(sheet, 3, 4, 15, 15, true);
            setMerge(sheet, 3, 4, 16, 16, true);
            setMerge(sheet, 3, 4, 17, 17, true);
            setMerge(sheet, 3, 4, 18, 18, true);
            setMerge(sheet, 3, 4, 19, 19, true);

            cell = row.createCell(20);
            cell.setCellValue("C?? khuy???t ??i???m, vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Ph???i thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(22);
            cell.setCellValue("???? thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 22; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II") ||
                        rs.getString(arrHeader[0]).equals("III"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public DefaultStreamedContent exportGSDV_CAPUY(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("GI??M S??T ?????NG VI??N THEO ??I???U 30 ??I???U L??? ?????NG");
            setMerge(sheet, 0, 0, 0, 22, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(21);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 21, 22, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("?????ng vi??n ???????c gi??m s??t");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ?????ng vi??n ???????c gi??m s??t");
            setMerge(sheet, 2, 3, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p gi??m s??t");
            setMerge(sheet, 2, 2, 3, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("N???i dung");
            setMerge(sheet, 2, 2, 11, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("K???t qu???");
            setMerge(sheet, 2, 2, 18, 22, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);

            cell = row.createCell(3);
            cell.setCellValue("BCH TW, B??? Ch??nh tr???, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("CQ tham m??u c???a Trung ????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("T???nh ???y, BTV t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("CQ tham m??u c???a c???p ???y t???nh v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huy???n ???y, BTV HU v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("CQ tham m??u c???a  c???p ???y huy???n v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("?????ng ???y b??? ph???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(12);
            cell.setCellValue("Vi???c ch???p h??nh ngh??? quy???t, ch??? th???, quy ?????nh, k???t lu???n c???a ?????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Vi???c ch???p h??nh ch??nh s??ch, ph??p lu???t c???a Nh?? n?????c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Vi???c ch???p h??nh quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???, ch??? ????? c??ng t??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Vi???c gi??? g??n ph???m ch???t ?????o ?????c, l???i s???ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Vi???c th???c hi???n ch???c tr??ch nhi???m v??? ???????c giao");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Nh???ng ??i???u ?????ng vi??n kh??ng ???????c l??m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("k?? khai t??i s???n thu nh???p");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(20);
            cell.setCellValue("S??? ?????ng vi??n th???c hi???n t???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("S??? ?????ng vi??n ph??t hi???n c?? DHVP");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(22);
            cell.setCellValue("S??? ?????ng vi??n chuy???n ki???m tra khi c?? d???u hi???u vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 22; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II") ||
                        rs.getString(arrHeader[0]).equals("III"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
public DefaultStreamedContent exportKT_THKL(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("KI???M TRA VI???C THI H??NH K??? LU???T C???A T??? CH???C ?????NG C???P D?????I C???A ???Y BAN KI???M TRA C??C C???P");
            setMerge(sheet, 0, 0, 0, 22, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(21);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 21, 22, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng ???????c ki???m tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? t??? ch???c ?????ng ???????c ki???m tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("S??? ??V b??? THKL trong m???c th???i gian ki???m tra");
            setMerge(sheet, 2, 2, 7, 8, true);
            formatCell(workbook, cell, false, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(9);
            cell.setCellValue("?????ng vi??n b??? THKL do t???ng c???p qu???n l??");
            setMerge(sheet, 2, 2, 9, 12, true);
            formatCell(workbook, cell, false, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(13);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 13, 18, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Ki???n ngh??? sau ki???m tra");
            setMerge(sheet, 2, 2, 19, 22, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);
            setMerge(sheet, 2, 3, 2, 2, true);

            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huy???n u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT ?????ng u??? c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            //

            cell = row.createCell(7);
            cell.setCellValue("T???ng s???");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("S??? c?? khi???u n???i sau khi b??? THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("C???p t???nh v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("C???p huy???n v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("C???p c?? s??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Chi b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            ///
            
            cell = row.createCell(13);
            cell.setCellValue("S??? t??? ch???c ?????ng l??m t???t c??ng t??c THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("S??? t??? ch???c ?????ng ch??a l??m t???t c??ng t??c THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("S??? ?????ng vi??n b??? thi h??nh k??? lu???t kh??ng ????ng th???m quy???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("S??? ?????ng vi??n b??? thi h??nh k??? lu???t kh??ng ????ng nguy??n t???c, th??? t???c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("S??? ?????ng vi??n c?? vi ph???m ?????n m???c ph???i THKL nh??ng kh??ng THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("S??? ?????ng vi??n b??? THKL oan, sai");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ///
            
            cell = row.createCell(19);
            cell.setCellValue("Kh??ng thay ?????i h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("T??ng h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Gi???m h??nh th???c k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(22);
            cell.setCellValue("X??a k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 22; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
    public DefaultStreamedContent exportTHKL_DV_CAPUY(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("THI H??NH K??? LU???T ?????NG VI??N C???A C???P ???Y C??C C???P V?? CHI B???");
            setMerge(sheet, 0, 0, 0, 27, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(26);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 26, 27, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("?????ng vi??n b??? thi h??nh k??? lu???t");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ?????ng vi??n b??? THKL");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("H??nh th???c k??? lu???t");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(7);
            cell.setCellValue("????nh ch??? sinh ho???t ?????ng");
            setMerge(sheet, 2, 4, 7, 7, true);
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("C???p thi h??nh k??? lu???t");
            setMerge(sheet, 2, 2, 8, 12, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(13);
            cell.setCellValue("N???i dung vi ph???m");
            setMerge(sheet, 2, 2, 13, 23, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER); 

            cell = row.createCell(24);
            cell.setCellValue("X??? l?? ph??p lu???t");
            setMerge(sheet, 2, 2, 24, 26, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(27);
            cell.setCellValue("X??? l?? h??nh ch??nh");
//            setMerge(sheet, 2, 4, 27, 27, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            cell = row.createCell(3);
            cell.setCellValue("Khi???n tr??ch");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C???nh c??o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("C??ch ch???c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Khai tr???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("BCHTW, B??? Ch??nh tr???, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("T???nh u???, BTV t???nh u??? v?? t????ng ??????ng ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Huy???n u???, BTV HU v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("?????ng u??? c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            //
            cell = row.createCell(13);
            cell.setCellValue("Nguy??n t???c t???p trung d??n ch???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(14);
            cell.setCellValue("Ph???m ch???t ?????o ?????c, l???i s???ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("??o??n k???t n???i b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Nh???ng ??i???u ?????ng vi??n kh??ng ???????c l??m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Ch??nh s??ch d??n s???- k??? ho???ch h??a gia ????nh");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Tham nh??ng, c??? ?? l??m tr??i");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Thi???u tr??ch nhi???m, bu??ng l???ng l??nh ?????o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("?????t ??ai, t??i nguy??n, kho??ng s???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(21);
            cell.setCellValue("T??i ch??nh, ng??n h??ng, ?????u t??, XDCB");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(22);
            cell.setCellValue("K?? khai t??i s???n thu nh???p");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(23);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(24);
            cell.setCellValue("S??? l?????ng");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(25);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 25, 26, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);
            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 3, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            
            
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);
            setMerge(sheet, 3, 4, 15, 15, true);
            setMerge(sheet, 3, 4, 16, 16, true);
            setMerge(sheet, 3, 4, 17, 17, true);
            setMerge(sheet, 3, 4, 18, 18, true);
            setMerge(sheet, 3, 4, 19, 19, true);
            setMerge(sheet, 3, 4, 20, 20, true);
            setMerge(sheet, 3, 4, 21, 21, true);
            setMerge(sheet, 3, 4, 22, 22, true);
            setMerge(sheet, 3, 4, 23, 23, true);
            setMerge(sheet, 3, 4, 24, 24, true);
            
//            cell = row.createCell(27);
            
            setMerge(sheet, 2, 4, 27, 27, true);

            
            cell = row.createCell(25);
            cell.setCellValue("B??? ph???t t?? (k??? c??? ??n treo)");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(26);
            cell.setCellValue("H??nh th???c kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 27; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II") ||
                        rs.getString(arrHeader[0]).equals("III"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
public DefaultStreamedContent exportGSTCD_CAPUY(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("GI??M S??T T??? CH???C ?????NG THEO ??I???U 30 ??I???U L??? ?????NG");
            setMerge(sheet, 0, 0, 0, 20, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(19);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 19, 20, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng ???????c gi??m s??t");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? t??? ch???c ?????ng ???????c gi??m s??t");
            setMerge(sheet, 2, 3, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p gi??m s??t");
            setMerge(sheet, 2, 2, 3, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("N???i dung");
            setMerge(sheet, 2, 2, 11, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("K???t qu???");
            setMerge(sheet, 2, 2, 18, 20, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);

            cell = row.createCell(3);
            cell.setCellValue("BCH Trung ????ng, B??? Ch??nh tr???, BBT");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("CQ tham m??u c???a Trung ????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("T???nh ???y, BTV t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("CQ tham m??u c???a c???p ???y t???nh v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huy???n ???y, BTV HU v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("CQ tham m??u c???a  c???p ???y huy???n v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???, BTV ?????ng ???y c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("?????ng ???y b??? ph???n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(11);
            cell.setCellValue("Vi???c ch???p h??nh ngh??? quy???t, ch??? th???, quy ?????nh, k???t lu???n c???a ?????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Vi???c ch???p h??nh ch??nh s??ch, ph??p lu???t c???a Nh?? n?????c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Vi???c th???c hi???n quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???, ??o??n k???t n???i b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("C??ng t??c c??n b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Th???c h??nh ti???t ki???m, ch???ng l??ng ph??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Gi???i quy???t khi???u n???i, t??? c??o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(17);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(18);
            cell.setCellValue("S??? t??? ch???c ?????ng th???c hi???n t???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("S??? t??? ch???c ?????ng ???????c ph??t hi???n c?? d???u hi???u vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("S??? t??? ch???c ?????ng chuy???n ki???m tra khi c?? d???u hi???u vi ph???m ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 20; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);

                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

public DefaultStreamedContent exportKT_THNVKT(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("KI???M TRA T??? CH???C ?????NG C???P D?????I TH???C HI???N NHI???M V??? KI???M TRA, GI??M S??T C???A ???Y BAN KI???M TRA C??C C???P");
            setMerge(sheet, 0, 0, 0, 12, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(11);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 11, 12, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng ???????c ki???m tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? t??? ch???c ?????ng ???????c ki???m tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("N???i dung ki???m tra");
            setMerge(sheet, 2, 2, 7, 8, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 9, 12, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);
            setMerge(sheet, 2, 3, 2, 2, true);

            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT t???nh u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huy???n u??? v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT ?????ng u??? c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            ///

            cell = row.createCell(7);
            cell.setCellValue("Vi???c x??y d???ng v?? th???c hi???n ch????ng tr??nh, k??? ho???ch ki???m tra, gi??m s??t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Vi???c l??nh ?????o v?? ch??? ?????o th???c hi???n nhi???m v??? ki???m tra, gi??m s??t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            ///

            cell = row.createCell(9);
            cell.setCellValue("S??? t??? ch???c ?????ng c?? ch????ng tr??nh, k??? ho???ch ki???m tra, gi??m s??t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("S??? t??? ch???c ?????ng th???c hi???n t???t nhi???m v??? ki???m tra, gi??m s??t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(11);
            cell.setCellValue("S??? t??? ch???c ?????ng l??m t???t vi???c l??nh ?????o, ch??? ?????o c??ng t??c ki???m tra, gi??m s??t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("S??? t??? ch???c ?????ng ch??a l??m t???t vi???c l??nh ?????o, ch??? ?????o v?? th???c hi???n nhi???m v??? ki???m tra, gi??m s??t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 12; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);

                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


public DefaultStreamedContent exportTHKL_TCD_CAPUY(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlExcel = ctx.getRealPath("/ExportExcel/") + "/";
            String strOutFileName = fileName + "_" + new java.text.SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date()) + ".xlsx";
            String strOutUrlFile = urlExcel + strOutFileName;

            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet(fileName);

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("THI H??NH K??? LU???T T??? CH???C ?????NG C???A C???P ???Y C??C C???P");
            setMerge(sheet, 0, 0, 0, 16, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(15);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 15, 16, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("S??? TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("T??? ch???c ?????ng b??? thi h??nh k??? lu???t");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? t??? ch???c ?????ng b??? thi h??nh k??? lu???t");
            setMerge(sheet, 2, 3, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("H??nh th???c k??? lu???t");
            setMerge(sheet, 2, 2, 3, 5, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("C???p thi h??nh k??? lu???t");
            setMerge(sheet, 2, 2, 6, 9, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("N???i dung vi ph???m");
            setMerge(sheet, 2, 2, 10, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);

            cell = row.createCell(3);
            cell.setCellValue("Khi???n tr??ch");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C???nh c??o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Gi???i t??n");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            ///
            cell = row.createCell(6);
            cell.setCellValue("BCH TW, B??? Ch??nh tr???, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("T???nh ???y, BTV t???nh ???y v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Huy???n ???y, BTV huy???n ???y v?? t????ng ??????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //
            cell = row.createCell(10);
            cell.setCellValue("Ngh??? quy???t, ch??? th???, quy ?????nh, k???t lu???n c???a ?????ng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(11);
            cell.setCellValue("Ch??nh s??ch, ph??p lu???t c???a Nh?? n?????c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???, ??o??n k???t n???i b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("C??ng t??c c??n b???");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Th???c h??nh ti???t ki???m, ch???ng l??ng ph??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Gi???i quy???t khi???u n???i, t??? c??o");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Kh??c");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            // Row 4
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            for (int i = 2; i <= 16; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            }

            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, false);

                    }
                }
            }

            FileOutputStream outputStream = new FileOutputStream(strOutUrlFile);
            workbook.write(outputStream);
            workbook.close();
            return downloadFile(new File(strOutUrlFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    protected void setMerge(XSSFSheet sheet, int numRow, int untilRow, int numCol, int untilCol, boolean border) {
        CellRangeAddress cellMerge = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
        sheet.addMergedRegion(cellMerge);
        if (border) {
            setBordersToMergedCells(sheet, cellMerge);
        }

    }

    protected void setBordersToMergedCells(XSSFSheet sheet, CellRangeAddress rangeAddress) {
        RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
    }

    public static void main(String[] arsg) {
        ExportExcel test = new ExportExcel();
        ResultSet rs = null;
        Connection cnn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cnn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@127.0.0.1:1521:orcl", "report_owner", "REPORT123456");
            if (cnn != null) {
                // Test call produce
                String runSP = "{ call exp_ktdv_d30(?,?,?,?,?,?) }";
                CallableStatement cs = cnn.prepareCall(runSP);
                cs.setInt(1, 3);
                cs.setInt(2, 2021);
                cs.setInt(3, 1);
                cs.setString(4, "KTDV_D30");
                cs.registerOutParameter(5, OracleTypes.CURSOR);
                cs.registerOutParameter(6, OracleTypes.INTEGER);
                cs.execute();
                int iCountRow = (int) cs.getObject(6);
                if (iCountRow > 0) {
                    ResultSet rsExport = (ResultSet) cs.getObject(5);
                    String[] arrHeader = new String[]{"stt", "dvdkt", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "k5", "l5", "m5", "n5", "o5", "p5", "q5", "r5", "s5", "t5", "u5", "v5"};
                    test.exportExcel("TestExcel", rsExport, arrHeader);
                    System.out.println("Export Excel succs");
                } else {
                    System.out.println("Khong co ban ghi thoa man");
                }
            } else {
                System.out.println("Connection fail");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void exportExcel(String fileName, ResultSet rs, String[] arrHeader) {
        try {
            String strOutFileName = "D://" + fileName + ".xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();

            int rowNum = 0;
            XSSFSheet sheet = null;
            sheet = workbook.createSheet("KTDV-D30");

            ////Header
            // Row 0
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue("KI???M TRA ?????NG VI??N THEO ??I???U 30 ??I???U L??? ?????NG");
            setMerge(sheet, 0, 0, 0, 21, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(17);
            cell.setCellValue("(??VT: ?????ng vi??n)");
            setMerge(sheet, 1, 1, 17, 21, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("S??? TT");

            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("?????ng vi??n ???????c ki???m tra");
            sheet.autoSizeColumn(1);

            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("T???ng s??? ?????ng vi??n ???????c ki???m tra");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("C???p ki???m tra");
            setMerge(sheet, 2, 2, 3, 11, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("N???i dung ki???m tra");
            setMerge(sheet, 2, 2, 12, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("K???t lu???n");
            setMerge(sheet, 2, 2, 17, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(3);
            cell.setCellValue("BCHTW, BCT, Ban B?? th??");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("C??c CQ tham m??u c???a TW");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("T???nh ???y, BTV t???nh u??? v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("C??c CQ tham m??u c???a c???p ???y t???nh v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huy???n ???y, BTV HU v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("C??c CQ tham m??u c???a  c???p ???y huy???n v?? t????ng ??????ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("?????ng ???y c?? s???, BTV ?????ng ???y c?? s???");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("?????ng ???y b??? ph???n");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi b???");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Vi???c ch???p h??nh quy ch??? l??m vi???c, nguy??n t???c t???p trung d??n ch???");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Vi???c gi??? g??n ph???m ch???t ?????o ?????c, l???i s???ng");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Vi???c th???c hi???n ch???c tr??ch nhi???m v??? ???????c giao");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Vi???c th???c hi???n nh???ng ??i???u ?????ng vi??n kh??ng ???????c l??m");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Kh??c");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Th???c hi???n t???t");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Th???c hi???n ch??a t???t");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Trong ????");
            setMerge(sheet, 3, 3, 19, 21, true);
            formatCell(workbook, cell, false, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 4
            row = sheet.createRow(rowNum++);

            //Merge
            setMerge(sheet, 2, 4, 0, 0, true);
            setMerge(sheet, 2, 4, 1, 1, true);
            setMerge(sheet, 3, 4, 3, 3, true);
            setMerge(sheet, 3, 4, 4, 4, true);
            setMerge(sheet, 3, 4, 5, 5, true);
            setMerge(sheet, 3, 4, 6, 6, true);
            setMerge(sheet, 3, 4, 7, 7, true);
            setMerge(sheet, 3, 4, 8, 8, true);
            setMerge(sheet, 3, 4, 9, 9, true);
            setMerge(sheet, 3, 4, 10, 10, true);
            setMerge(sheet, 3, 4, 11, 11, true);
            setMerge(sheet, 3, 4, 12, 12, true);
            setMerge(sheet, 3, 4, 13, 13, true);
            setMerge(sheet, 3, 4, 14, 14, true);
            setMerge(sheet, 3, 4, 15, 15, true);
            setMerge(sheet, 3, 4, 16, 16, true);
            setMerge(sheet, 3, 4, 17, 17, true);
            setMerge(sheet, 3, 4, 18, 18, true);

            cell = row.createCell(19);
            cell.setCellValue("C?? khuy???t ??i???m, vi ph???m");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Ph???i thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("???? thi h??nh k??? lu???t");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //Row5
            row = sheet.createRow(rowNum++);
            row.setRowStyle(getDefaultStyle(workbook));

            cell = row.createCell(0);
            cell.setCellValue("A");
            formatCell(workbook, cell, false, false, false);

            cell = row.createCell(1);
            cell.setCellValue("B");
            formatCell(workbook, cell, false, false, false);

            for (int i = 2; i < 22; i++) {
                cell = row.createCell(i);
                cell.setCellValue(i - 1);
                formatCell(workbook, cell, false, false, false);
            }

            //cell.setCellStyle(style);
            /// End Header
            while (rs.next()) {
                row = sheet.createRow(rowNum++);
                int colNum = 0;
                if (rs.getString(arrHeader[0]) != null && (rs.getString(arrHeader[0]).equals("I") ||
                        rs.getString(arrHeader[0]).equals("II") ||
                        rs.getString(arrHeader[0]).equals("III") ||
                        rs.getString(arrHeader[0]).equals("IV"))) {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
                        formatCell(workbook, cell, false, false, true,true);
                    }
                } else {
                    for (int i = 0; i < arrHeader.length; i++) {
                        cell = row.createCell(colNum++);
                        if (i == 0 || i == 1) {
                            cell.setCellValue(rs.getString(arrHeader[i]));
                        } else {
                            if (rs.getString(arrHeader[i]) != null) {
                                cell.setCellValue(rs.getInt(arrHeader[i]));
                            } else {
                                cell.setCellValue(rs.getString(arrHeader[i]));
                            }

                        }
//					cell.setCellValue(rs.getString(arrHeader[i]));
                        formatCell(workbook, cell, false, false, false);

                    }
                }

            }

            FileOutputStream outputStream = new FileOutputStream(strOutFileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DefaultStreamedContent downloadFile(File file) throws Exception {
        return downloadFile2(file.getName(), file.getPath());
    }

    public static DefaultStreamedContent downloadFile2(String strFileName, String strFilePath) throws Exception {
        String strContentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(strFileName);
        InputStream stream = new FileInputStream(new File(strFilePath));
        return new DefaultStreamedContent(stream, strContentType, strFileName);
    }
}
