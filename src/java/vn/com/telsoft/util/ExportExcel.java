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
            cell.setCellValue("GIẢI QUYẾT KHIẾU NẠI KỶ LUẬT ĐẢNG VIÊN CỦA CẤP ỦY CÁC CẤP");
            setMerge(sheet, 0, 0, 0, 20, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(19);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 19, 20, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Đảng viên khiếu nại kỷ luật đảng");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số phải giải quyết");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("Đã giải quyết xong");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Cấp giải quyết khiếu nại");
            setMerge(sheet, 2, 2, 4, 9, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Nội dung khiếu nại");
            setMerge(sheet, 2, 2, 10, 12, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 13, 20, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(4);
            cell.setCellValue("BCHTW, Bộ Chính trị, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("BTV tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huyện uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("BTV huyện uỷ và tương đương ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //
            cell = row.createCell(10);
            cell.setCellValue("Nội dung vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Nguyên tắc, thủ tục, quy trình, thẩm quyền");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            //

            cell = row.createCell(13);
            cell.setCellValue("Giữ nguyên hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Thay đổi hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(15);
            cell.setCellValue("Trong đó");
            setMerge(sheet, 3, 3, 15, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(18);
            cell.setCellValue("Nguyên nhân thay đổi hình thức kỷ luật");
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
            cell.setCellValue("Tăng hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Giảm hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Xóa hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(18);
            cell.setCellValue("Vận dụng phương hướng, phương châm THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(19);
            cell.setCellValue("Thẩm tra, xác minh");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(20);
            cell.setCellValue("Khác");
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
            cell.setCellValue("GIẢI QUYẾT KHIẾU NẠI KỶ LUẬT TỔ CHỨC ĐẢNG CỦA CẤP ỦY CÁC CẤP");
            setMerge(sheet, 0, 0, 0, 21, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(20);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 20, 21, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức Đảng khiếu nại kỷ luật đảng");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số phải giải quyết");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("Đã giải quyết xong");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Cấp giải quyết khiếu nại");
            setMerge(sheet, 2, 2, 4, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Nội dung khiếu nại");
            setMerge(sheet, 2, 2, 11, 13, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 14, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(4);
            cell.setCellValue("BCHTW");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Bộ Chính trị");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("BTV tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Huyện uỷ và tương đương ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(10);
            cell.setCellValue("BTV huyện uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //
            cell = row.createCell(11);
            cell.setCellValue("Nội dung vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Nguyên tắc, thủ tục, quy trình, thẩm quyền");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            //

            cell = row.createCell(14);
            cell.setCellValue("Giữ nguyên hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Thay đổi hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Trong đó");
            setMerge(sheet, 3, 3, 16, 18, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(19);
            cell.setCellValue("Nguyên nhân thay đổi hình thức kỷ luật");
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
            cell.setCellValue("Tăng hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Giảm hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Xóa hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(19);
            cell.setCellValue("Vận dụng phương hướng, phương châm THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(20);
            cell.setCellValue("Thẩm tra, xác minh");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(21);
            cell.setCellValue("Khác");
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
            cell.setCellValue("KIỂM TRA ĐẢNG VIÊN KHI CÓ DẤU HIỆU VI PHẠM CỦA ỦY BAN KIỂM TRA CÁC CẤP VÀ CHI BỘ");
            setMerge(sheet, 0, 0, 0, 21, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(20);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 20, 21, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Đảng viên được kiểm tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số đảng viên được kiểm tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 7, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Nội dung kiểm tra");
            setMerge(sheet, 2, 2, 8, 18, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 19, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huyện uỷ và tương đương ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT đảng uỷ cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Chi bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(8);
            cell.setCellValue("Việc chấp hành nguyên tắc tập trung dân chủ, quy chế làm việc, chế độ công tác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Việc giữ gìn phẩm chất đạo đức, lối sống");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(10);
            cell.setCellValue("Việc giữ gìn đoàn kết nội bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Những điều đảng viên không được làm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Chính sách dân số- kế hoạch hóa gia đình");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Tham nhũng, cố ý làm trái");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            //

            cell = row.createCell(14);
            cell.setCellValue("Thiếu trách nhiệm, buông lỏng lãnh đạo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Đất đai, tài nguyên, khoáng sản");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Tài chính, ngân hàng, đầu tư, xây dựng cơ bản");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(17);
            cell.setCellValue("Kê khai tài sản, thu nhập");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(18);
            cell.setCellValue("Khác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            /////
            cell = row.createCell(19);
            cell.setCellValue("Có vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(20);
            cell.setCellValue("Trong đó");
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
            cell.setCellValue("Phải thi hành kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Đã thi hành kỷ luật");
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
            cell.setCellValue(" KIỂM TRA TỔ CHỨC ĐẢNG KHI CÓ DẤU HIỆU VI PHẠM CỦA ỦY BAN KIỂM TRA CÁC CẤP");
            setMerge(sheet, 0, 0, 0, 16, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(15);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 15, 16, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức đảng được kiểm tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số tổ chức đảng được kiểm tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Nội dung kiểm tra");
            setMerge(sheet, 2, 2, 7, 13, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 14, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huyện uỷ và tương đương ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT đảng uỷ cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ///
            cell = row.createCell(7);
            cell.setCellValue("Việc chấp hành nghị quyết, chỉ thị, quy định, kết luận của Đảng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(8);
            cell.setCellValue("Việc chấp hành chính sách, pháp luật của Nhà nước");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Việc thực hiện quy chế làm việc, nguyên tắc tập trung dân chủ, đoàn kết nội bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(10);
            cell.setCellValue("Công tác cán bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Thực hành tiết kiệm, chống lãng phí");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Giải quyết khiếu nại, tố cáo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Khác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            /////
            cell = row.createCell(14);
            cell.setCellValue("Có vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(15);
            cell.setCellValue("Trong đó");
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
            cell.setCellValue("Phải thi hành kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Đã thi hành kỷ luật");
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
            cell.setCellValue("KIỂM TRA ĐẢNG VIÊN THEO ĐIỀU 30 ĐIỀU LỆ ĐẢNG");
            setMerge(sheet, 0, 0, 0, 21, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(20);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 20, 21, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Đảng viên được kiểm tra");
            sheet.autoSizeColumn(1);

            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số đảng viên được kiểm tra");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 11, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Nội dung kiểm tra");
            setMerge(sheet, 2, 2, 12, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 17, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(3);
            cell.setCellValue("BCHTW, BCT, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Các CQ tham mưu của TW");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Tỉnh ủy, BTV tỉnh uỷ và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Các CQ tham mưu của cấp ủy tỉnh và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huyện ủy, BTV HU và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Các CQ tham mưu của  cấp ủy huyện và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở, BTV Đảng ủy cơ sở");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Đảng ủy bộ phận");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi bộ");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Việc chấp hành quy chế làm việc, nguyên tắc tập trung dân chủ");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Việc giữ gìn phẩm chất đạo đức, lối sống");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Việc thực hiện chức trách nhiệm vụ được giao");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Việc thực hiện những điều đảng viên không được làm");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Khác");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Thực hiện tốt");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Thực hiện chưa tốt");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Trong đó");
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
            cell.setCellValue("Có khuyết điểm, vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Phải thi hành kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Đã thi hành kỷ luật");
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
            cell.setCellValue("KIỂM TRA TỔ CHỨC ĐẢNG CẤP DƯỚI THEO ĐIỀU 30 ĐIỀU LỆ ĐẢNG");
            setMerge(sheet, 0, 0, 0, 22, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(21);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 21, 22, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức đảng được kiểm tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số tổ chức đảng được kiểm tra");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Nội dung kiểm tra");
            setMerge(sheet, 2, 2, 11, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 18, 22, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(3);
            cell.setCellValue("BCHTW, BCT, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Các CQ tham mưu của TW");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Tỉnh ủy, BTV tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Các CQ tham mưu của cấp ủy tỉnh và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huyện ủy, BTV HU và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Các CQ tham mưu của  cấp ủy huyện và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở, BTV Đảng ủy cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Đảng ủy bộ phận");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Việc chấp hành nghị quyết, chỉ thị, quy định, kết luận của Đảng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Việc chấp hành chính sách, pháp luật của Nhà nước");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Việc thực hiện quy chế làm việc, nguyên tắc tập trung dân chủ, đoàn kết nội bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Công tác cán bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Thực hành tiết kiệm, chống lãng phí");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Giải quyết khiếu nại, tố cáo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Khác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Thực hiện tốt");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Thực hiện chưa tốt");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Trong đó");
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
            cell.setCellValue("Có khuyết điểm, vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Phải thi hành kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(22);
            cell.setCellValue("Đã thi hành kỷ luật");
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
            cell.setCellValue("GIÁM SÁT ĐẢNG VIÊN THEO ĐIỀU 30 ĐIỀU LỆ ĐẢNG");
            setMerge(sheet, 0, 0, 0, 22, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(21);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 21, 22, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Đảng viên được giám sát");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số đảng viên được giám sát");
            setMerge(sheet, 2, 3, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp giám sát");
            setMerge(sheet, 2, 2, 3, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Nội dung");
            setMerge(sheet, 2, 2, 11, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Kết quả");
            setMerge(sheet, 2, 2, 18, 22, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);

            cell = row.createCell(3);
            cell.setCellValue("BCH TW, Bộ Chính trị, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("CQ tham mưu của Trung ương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Tỉnh ủy, BTV tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("CQ tham mưu của cấp ủy tỉnh và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huyện ủy, BTV HU và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("CQ tham mưu của  cấp ủy huyện và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Đảng ủy bộ phận");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(12);
            cell.setCellValue("Việc chấp hành nghị quyết, chỉ thị, quy định, kết luận của Đảng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Việc chấp hành chính sách, pháp luật của Nhà nước");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Việc chấp hành quy chế làm việc, nguyên tắc tập trung dân chủ, chế độ công tác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Việc giữ gìn phẩm chất đạo đức, lối sống");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Việc thực hiện chức trách nhiệm vụ được giao");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Những điều đảng viên không được làm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("kê khai tài sản thu nhập");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Khác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(20);
            cell.setCellValue("Số đảng viên thực hiện tốt");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Số đảng viên phát hiện có DHVP");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(22);
            cell.setCellValue("Số đảng viên chuyển kiểm tra khi có dấu hiệu vi phạm");
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
            cell.setCellValue("KIỂM TRA VIỆC THI HÀNH KỶ LUẬT CỦA TỔ CHỨC ĐẢNG CẤP DƯỚI CỦA ỦY BAN KIỂM TRA CÁC CẤP");
            setMerge(sheet, 0, 0, 0, 22, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(21);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 21, 22, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức đảng được kiểm tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số tổ chức đảng được kiểm tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Số ĐV bị THKL trong mốc thời gian kiểm tra");
            setMerge(sheet, 2, 2, 7, 8, true);
            formatCell(workbook, cell, false, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(9);
            cell.setCellValue("Đảng viên bị THKL do từng cấp quản lý");
            setMerge(sheet, 2, 2, 9, 12, true);
            formatCell(workbook, cell, false, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(13);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 13, 18, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Kiến nghị sau kiểm tra");
            setMerge(sheet, 2, 2, 19, 22, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);

            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);
            setMerge(sheet, 2, 3, 2, 2, true);

            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huyện uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT đảng uỷ cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            //

            cell = row.createCell(7);
            cell.setCellValue("Tổng số");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Số có khiếu nại sau khi bị THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Cấp tỉnh và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Cấp huyện và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Cấp cơ sở và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Chi bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            ///
            
            cell = row.createCell(13);
            cell.setCellValue("Số tổ chức đảng làm tốt công tác THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Số tổ chức đảng chưa làm tốt công tác THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Số đảng viên bị thi hành kỷ luật không đúng thẩm quyền");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Số đảng viên bị thi hành kỷ luật không đúng nguyên tắc, thủ tục");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Số đảng viên có vi phạm đến mức phải THKL nhưng không THKL");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Số đảng viên bị THKL oan, sai");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ///
            
            cell = row.createCell(19);
            cell.setCellValue("Không thay đổi hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Tăng hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Giảm hình thức kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(22);
            cell.setCellValue("Xóa kỷ luật");
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
            cell.setCellValue("THI HÀNH KỶ LUẬT ĐẢNG VIÊN CỦA CẤP ỦY CÁC CẤP VÀ CHI BỘ");
            setMerge(sheet, 0, 0, 0, 27, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(26);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 26, 27, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Đảng viên bị thi hành kỷ luật");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số đảng viên bị THKL");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Hình thức kỷ luật");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(7);
            cell.setCellValue("Đình chỉ sinh hoạt đảng");
            setMerge(sheet, 2, 4, 7, 7, true);
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Cấp thi hành kỷ luật");
            setMerge(sheet, 2, 2, 8, 12, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
                    
            cell = row.createCell(13);
            cell.setCellValue("Nội dung vi phạm");
            setMerge(sheet, 2, 2, 13, 23, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER); 

            cell = row.createCell(24);
            cell.setCellValue("Xử lý pháp luật");
            setMerge(sheet, 2, 2, 24, 26, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(27);
            cell.setCellValue("Xử lý hành chính");
//            setMerge(sheet, 2, 4, 27, 27, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            cell = row.createCell(3);
            cell.setCellValue("Khiển trách");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Cảnh cáo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Cách chức");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Khai trừ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("BCHTW, Bộ Chính trị, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Tỉnh uỷ, BTV tỉnh uỷ và tương đương ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Huyện uỷ, BTV HU và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Đảng uỷ cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            //
            cell = row.createCell(13);
            cell.setCellValue("Nguyên tắc tập trung dân chủ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(14);
            cell.setCellValue("Phẩm chất đạo đức, lối sống");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Đoàn kết nội bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Những điều đảng viên không được làm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Chính sách dân số- kế hoạch hóa gia đình");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Tham nhũng, cố ý làm trái");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Thiếu trách nhiệm, buông lỏng lãnh đạo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Đất đai, tài nguyên, khoáng sản");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(21);
            cell.setCellValue("Tài chính, ngân hàng, đầu tư, XDCB");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(22);
            cell.setCellValue("Kê khai tài sản thu nhập");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(23);
            cell.setCellValue("Khác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(24);
            cell.setCellValue("Số lượng");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(25);
            cell.setCellValue("Trong đó");
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
            cell.setCellValue("Bị phạt tù (kể cả án treo)");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(26);
            cell.setCellValue("Hình thức khác");
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
            cell.setCellValue("GIÁM SÁT TỔ CHỨC ĐẢNG THEO ĐIỀU 30 ĐIỀU LỆ ĐẢNG");
            setMerge(sheet, 0, 0, 0, 20, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(19);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 19, 20, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức đảng được giám sát");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số tổ chức đảng được giám sát");
            setMerge(sheet, 2, 3, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp giám sát");
            setMerge(sheet, 2, 2, 3, 10, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Nội dung");
            setMerge(sheet, 2, 2, 11, 17, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Kết quả");
            setMerge(sheet, 2, 2, 18, 20, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);

            cell = row.createCell(3);
            cell.setCellValue("BCH Trung ương, Bộ Chính trị, BBT");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("CQ tham mưu của Trung ương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Tỉnh ủy, BTV tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("CQ tham mưu của cấp ủy tỉnh và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huyện ủy, BTV HU và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("CQ tham mưu của  cấp ủy huyện và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở, BTV Đảng ủy cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Đảng ủy bộ phận");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(11);
            cell.setCellValue("Việc chấp hành nghị quyết, chỉ thị, quy định, kết luận của Đảng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Việc chấp hành chính sách, pháp luật của Nhà nước");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Việc thực hiện quy chế làm việc, nguyên tắc tập trung dân chủ, đoàn kết nội bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Công tác cán bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Thực hành tiết kiệm, chống lãng phí");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Giải quyết khiếu nại, tố cáo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(17);
            cell.setCellValue("Khác");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(18);
            cell.setCellValue("Số tổ chức đảng thực hiện tốt");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Số tổ chức đảng được phát hiện có dấu hiệu vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Số tổ chức đảng chuyển kiểm tra khi có dấu hiệu vi phạm ");
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
            cell.setCellValue("KIỂM TRA TỔ CHỨC ĐẢNG CẤP DƯỚI THỰC HIỆN NHIỆM VỤ KIỂM TRA, GIÁM SÁT CỦA ỦY BAN KIỂM TRA CÁC CẤP");
            setMerge(sheet, 0, 0, 0, 12, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(11);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 11, 12, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức đảng được kiểm tra");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số tổ chức đảng được kiểm tra");
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 6, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Nội dung kiểm tra");
            setMerge(sheet, 2, 2, 7, 8, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 9, 12, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);
            setMerge(sheet, 2, 3, 2, 2, true);

            cell = row.createCell(3);
            cell.setCellValue("UBKT Trung ương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("UBKT tỉnh uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("UBKT huyện uỷ và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("UBKT đảng uỷ cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            ///

            cell = row.createCell(7);
            cell.setCellValue("Việc xây dựng và thực hiện chương trình, kế hoạch kiểm tra, giám sát");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Việc lãnh đạo và chỉ đạo thực hiện nhiệm vụ kiểm tra, giám sát");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            ///

            cell = row.createCell(9);
            cell.setCellValue("Số tổ chức đảng có chương trình, kế hoạch kiểm tra, giám sát");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Số tổ chức đảng thực hiện tốt nhiệm vụ kiểm tra, giám sát");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(11);
            cell.setCellValue("Số tổ chức đảng làm tốt việc lãnh đạo, chỉ đạo công tác kiểm tra, giám sát");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Số tổ chức đảng chưa làm tốt việc lãnh đạo, chỉ đạo và thực hiện nhiệm vụ kiểm tra, giám sát");
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
            cell.setCellValue("THI HÀNH KỶ LUẬT TỔ CHỨC ĐẢNG CỦA CẤP ỦY CÁC CẤP");
            setMerge(sheet, 0, 0, 0, 16, false);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(15);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 15, 16, false);
            formatCell(workbook, cell, false, false, false, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, false);

            // Row 2			
            row = sheet.createRow(rowNum++);

            cell = row.createCell(0);
            cell.setCellValue("Số TT");
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Tổ chức đảng bị thi hành kỷ luật");
            sheet.autoSizeColumn(1);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số tổ chức đảng bị thi hành kỷ luật");
            setMerge(sheet, 2, 3, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Hình thức kỷ luật");
            setMerge(sheet, 2, 2, 3, 5, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Cấp thi hành kỷ luật");
            setMerge(sheet, 2, 2, 6, 9, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Nội dung vi phạm");
            setMerge(sheet, 2, 2, 10, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //////// Row 3
            row = sheet.createRow(rowNum++);
            
            setMerge(sheet, 2, 3, 0, 0, true);
            setMerge(sheet, 2, 3, 1, 1, true);

            cell = row.createCell(3);
            cell.setCellValue("Khiển trách");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Cảnh cáo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Giải tán");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            ///
            cell = row.createCell(6);
            cell.setCellValue("BCH TW, Bộ Chính trị, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Tỉnh ủy, BTV tỉnh ủy và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Huyện ủy, BTV huyện ủy và tương đương");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            //
            cell = row.createCell(10);
            cell.setCellValue("Nghị quyết, chỉ thị, quy định, kết luận của Đảng");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            ////
            cell = row.createCell(11);
            cell.setCellValue("Chính sách, pháp luật của Nhà nước");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Quy chế làm việc, nguyên tắc tập trung dân chủ, đoàn kết nội bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Công tác cán bộ");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Thực hành tiết kiệm, chống lãng phí");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Giải quyết khiếu nại, tố cáo");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            
            cell = row.createCell(16);
            cell.setCellValue("Khác");
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
            cell.setCellValue("KIỂM TRA ĐẢNG VIÊN THEO ĐIỀU 30 ĐIỀU LỆ ĐẢNG");
            setMerge(sheet, 0, 0, 0, 21, false);
            // Row 1
            row = sheet.createRow(rowNum++);
            cell = row.createCell(17);
            cell.setCellValue("(ĐVT: Đảng viên)");
            setMerge(sheet, 1, 1, 17, 21, false);
            // Row 2			
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Số TT");

            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(1);
            cell.setCellValue("Đảng viên được kiểm tra");
            sheet.autoSizeColumn(1);

            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(2);
            cell.setCellValue("Tổng số đảng viên được kiểm tra");
            setMerge(sheet, 2, 4, 2, 2, true);
            formatCell(workbook, cell, true, true, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(3);
            cell.setCellValue("Cấp kiểm tra");
            setMerge(sheet, 2, 2, 3, 11, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Nội dung kiểm tra");
            setMerge(sheet, 2, 2, 12, 16, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Kết luận");
            setMerge(sheet, 2, 2, 17, 21, true);
            formatCell(workbook, cell, false, false, true, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            // Row 3
            row = sheet.createRow(rowNum++);

            cell = row.createCell(3);
            cell.setCellValue("BCHTW, BCT, Ban Bí thư");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(4);
            cell.setCellValue("Các CQ tham mưu của TW");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(5);
            cell.setCellValue("Tỉnh ủy, BTV tỉnh uỷ và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(6);
            cell.setCellValue("Các CQ tham mưu của cấp ủy tỉnh và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(7);
            cell.setCellValue("Huyện ủy, BTV HU và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(8);
            cell.setCellValue("Các CQ tham mưu của  cấp ủy huyện và tương đương");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(9);
            cell.setCellValue("Đảng ủy cơ sở, BTV Đảng ủy cơ sở");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(10);
            cell.setCellValue("Đảng ủy bộ phận");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(11);
            cell.setCellValue("Chi bộ");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(12);
            cell.setCellValue("Việc chấp hành quy chế làm việc, nguyên tắc tập trung dân chủ");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(13);
            cell.setCellValue("Việc giữ gìn phẩm chất đạo đức, lối sống");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(14);
            cell.setCellValue("Việc thực hiện chức trách nhiệm vụ được giao");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(15);
            cell.setCellValue("Việc thực hiện những điều đảng viên không được làm");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(16);
            cell.setCellValue("Khác");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(17);
            cell.setCellValue("Thực hiện tốt");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(18);
            cell.setCellValue("Thực hiện chưa tốt");

            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(19);
            cell.setCellValue("Trong đó");
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
            cell.setCellValue("Có khuyết điểm, vi phạm");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(20);
            cell.setCellValue("Phải thi hành kỷ luật");
            formatCell(workbook, cell, true, true, false, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

            cell = row.createCell(21);
            cell.setCellValue("Đã thi hành kỷ luật");
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
