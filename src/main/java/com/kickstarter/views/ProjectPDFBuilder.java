package com.kickstarter.views;

import java.net.URL;
import java.util.Formatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kickstarter.logic.domain.Reward;
import com.kickstarter.models.ProjectModel;
import com.kickstarter.models.RewardModel;


public class ProjectPDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ProjectModel project = (ProjectModel) model.get("project");

        // Header

        URL logoUrl = this.getClass().getClassLoader().getResource("images/logo.png");
        if (logoUrl != null) {
            Image logo = Image.getInstance(logoUrl);
            doc.add(logo);
        } else {
            doc.add(new Paragraph("KICKSTARTER"));
        }

        // Body
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 60.0f, Font.BOLD, BaseColor.BLACK);
        Paragraph title = new Paragraph(project.getName(), titleFont);
        doc.add(title);
        Font mainInfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.ITALIC, BaseColor.BLACK);
        Formatter f = new Formatter();
        Paragraph mainInfo = new Paragraph(
                f.format("%s backers.\n" +
                        "%s$ pledged of %s$ total\n" +
                        "%s days to go",
                        project.getBackers(), project.getPledged(),
                        project.getFundingGoal(), project.getDaysToGo()).toString(),
                mainInfoFont);
        doc.add(mainInfo);
        Font descriptionFont = new Font(Font.FontFamily.TIMES_ROMAN, 15.0f, Font.NORMAL, BaseColor.BLACK);
        Paragraph description = new Paragraph(project.getDescription(), descriptionFont);
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
        cell.setPhrase(new Phrase("Reward", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        for (RewardModel reward: project.getRewards()){
            table.addCell(reward.getDescription());
            table.addCell(reward.getAmount().toString() + "$");
        }

        doc.add(table);

        // Footer
        Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL, BaseColor.BLACK);
        Paragraph footer = new Paragraph("©KickStarter", footerFont);
        footer.setSpacingBefore(50);
        doc.add(footer);
    }

}