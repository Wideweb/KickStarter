package com.kickstarter.views;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Header

        URL logoUrl = this.getClass().getClassLoader().getResource("images/logo.png");
        if (logoUrl != null) {
            Image logo = Image.getInstance(logoUrl);
            doc.add(logo);
        } else {
            doc.add(new Paragraph("KICKSTARTER"));
        }

        // Body
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 50.0f, Font.BOLD, BaseColor.BLACK);
        Paragraph title = new Paragraph("Project Name", titleFont);
        doc.add(title);
        Font descriptionFont = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLACK);
        Paragraph description = new Paragraph("Project Description", descriptionFont);
        doc.add(description);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {7.0f, 3.0f});
        table.setSpacingBefore(10);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GREEN);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Offer", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

            table.addCell("Description");
            table.addCell("Price");

        doc.add(table);

        // Footer
        Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL, BaseColor.BLACK);
        Paragraph footer = new Paragraph("©KickStarter", footerFont);
        footer.setSpacingBefore(50);
        doc.add(footer);
    }

}