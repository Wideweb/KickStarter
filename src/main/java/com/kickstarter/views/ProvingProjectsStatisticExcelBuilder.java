package com.kickstarter.views;

import com.kickstarter.logic.domain.Project;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class ProvingProjectsStatisticExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Project> projects = (List<Project>) model.get("projectList");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Some Sheet");
        sheet.setDefaultColumnWidth(15);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        CellStyle successStyle = workbook.createCellStyle();
        Font successFont = workbook.createFont();
        successStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        successStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        successFont.setColor(HSSFColor.WHITE.index);
        successStyle.setFont(successFont);

        CellStyle failStyle = workbook.createCellStyle();
        Font failFont = workbook.createFont();
        failStyle.setFillForegroundColor(HSSFColor.RED.index);
        failStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        failFont.setColor(HSSFColor.WHITE.index);
        failStyle.setFont(successFont);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Title");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Description");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Funding Duration");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Funding Goal");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Approved");
        header.getCell(4).setCellStyle(style);

        int rowCount = 1;

        for (Project project : projects) {
            HSSFRow row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(project.getName());

            row.createCell(1).setCellValue(project.getDescription());

            row.createCell(2).setCellValue(project.getFundingDuration() + " Days");

            row.createCell(3).setCellValue(project.getFundingGoal() + "$");

            if (project.getApproved()) {
                row.createCell(4).setCellValue("YES");
                row.getCell(4).setCellStyle(successStyle);
            } else {
                row.createCell(4).setCellValue("NO");
                row.getCell(4).setCellStyle(failStyle);
            }


            rowCount++;
        }
    }
}
