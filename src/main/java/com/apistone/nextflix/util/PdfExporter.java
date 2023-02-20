package com.apistone.nextflix.util;

import java.awt.Color;
import java.io.*;
import java.util.Objects;
import java.util.Set;


import com.apistone.nextflix.constant.StringConstant;
import com.apistone.nextflix.dto.ChannelDto;
import com.apistone.nextflix.dto.ZipcodeDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import lombok.extern.slf4j.Slf4j;


/**
 * Project: nextflix
 * Package: com.apistone.nextflix.util
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/23/2023
 * Time: 7:52 PM
 * <p>
 * Use: Utility for exporting PDF in base64 String.
 */
@Slf4j
public class PdfExporter {
    String FONT = Objects.requireNonNull(getClass().getClassLoader().getResource(StringConstant.FONT_FILE_NAME)).getPath();

    /**
     * Get check mark symbol.
     * @return {@link com.lowagie.text.Paragraph} having check mark.
     * @throws IOException If IO exception occurs.
     */
    private Paragraph checkMark() throws IOException {
        FontSelector selector = new FontSelector();
        BaseFont base = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        selector.addFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
        selector.addFont(new Font(base, 12));
        char tickSymbol=10003;
        String text = String.valueOf(tickSymbol);
        Phrase ph = selector.process(text);
        return new Paragraph(ph);
    }

    /**
     * Get Symbol for particular package.
     * @param isChecked Particular package is available or not.
     * @param isPremiumPackage Particular package is premium package or not.
     * @return {@link com.lowagie.text.Paragraph} having symbol for particular package.
     * @throws IOException If IO exception occurs.
     */
    private Paragraph getPackageSymbol(boolean isChecked, boolean isPremiumPackage) throws IOException {
        Paragraph paragraph;
        if(isChecked && isPremiumPackage)
            paragraph = new Paragraph("*");
        else if(isChecked)
            paragraph = checkMark();
        else
            paragraph = new Paragraph("");
        return paragraph;
    }

    /**
     * Get sub heading.
     * @param zipcode zipcode of particular location.
     * @return {@link com.lowagie.text.Paragraph} having sub heading.
     */
    private Paragraph getSubHeading(int zipcode){
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
        font.setSize(10);
        font.setColor(Color.DARK_GRAY);
        Paragraph paragraph = new Paragraph(StringConstant.PDF_FILE_SUB_HEADING + zipcode, font);
        paragraph.setSpacingAfter(-8f);
        return paragraph;
    }

    /**
     * Get main heading.
     * @return {@link com.lowagie.text.Paragraph} having heading.
     */
    private Paragraph getHeading(){
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
        font.setSize(18);
        font.setColor(Color.DARK_GRAY);
        return new Paragraph(StringConstant.PDF_FILE_HEADING, font);
    }

    /**
     * Writes header to the table.
     * @param table table which header need to be passed.
     */
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
        font.setColor(Color.WHITE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(10);
        cell.setPhrase(new Phrase(StringConstant.PDF_NUMBER_COLUMN, font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(StringConstant.PDF_CHANNEL_COLUMN, font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(StringConstant.PDF_STANDARD_COLUMN, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell.setPhrase(new Phrase(StringConstant.PDF_PREFERRED_COLUMN, font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(StringConstant.PDF_BASIC_COLUMN, font));
        table.addCell(cell);
    }

    /**
     * Writes data into the table.
     * @param channels channels which need add into the table.
     * @param table table which need to write data into.
     * @throws IOException if IO exception occurs.
     */
    private void writeTableData(Set<ChannelDto> channels, PdfPTable table) throws IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        for (ChannelDto channel : channels) {
            cell.setPhrase(new Phrase(String.valueOf(channel.getChannelNumber())));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPadding(10);
            table.addCell(cell);
            cell.setPhrase(new Phrase(channel.getChannelName()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(getPackageSymbol(channel.isStandard(), channel.isPremiumPackage())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell.setPhrase(new Phrase(getPackageSymbol(channel.isPreferred(), channel.isPremiumPackage())));
            table.addCell(cell);
            cell.setPhrase(new Phrase(getPackageSymbol(channel.isBasic(), channel.isPremiumPackage())));
            table.addCell(cell);
        }
    }

    /**
     * Get base64 data of {@link java.io.ByteArrayOutputStream}.
     * @param baos  {@link java.io.ByteArrayOutputStream} which need to convert into base64.
     * @return base64 string.
     */
    private String getBase64(ByteArrayOutputStream baos) {

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        byte[] bytes = bais.readAllBytes();
        String b64 = java.util.Base64.getEncoder().encodeToString(bytes);
        log.info("getBase64 : ");
        return b64;
    }

    /**
     * Get {@link com.lowagie.text.pdf.PdfPTable} with header and all the data.
     * @param channels channels which need add into the table.
     * @return {@link com.lowagie.text.pdf.PdfPTable} with header and all the data.
     * @throws IOException if IO exception occurs.
     */
    private PdfPTable getTable(Set<ChannelDto> channels) throws IOException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(channels, table);
        return table;
    }

    /**
     * Set footer to document which is provided.
     * @param document {@link com.lowagie.text.Document} in which footer need to be added.
     * @throws IOException if any IO exception occurs.
     */
    public void setFooter(Document document) throws IOException {
        BaseFont fontCourier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
        // footers must be added before the document is opened
        HeaderFooter footer = new HeaderFooter(
                new Phrase(StringConstant.PDF_PAGE_NUMBER_PREFIX, new Font(fontCourier)), true);
        footer.setBorder(Rectangle.NO_BORDER);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);
    }

    /**
     * Get PDF in base64 String.
     * @param zipcodeDto {@link com.apistone.nextflix.dto} which need add into the table
     * @return PDF in base64 String.
     * @throws DocumentException if any document exception occurs.
     * @throws IOException if any IO exception occurs.
     */
    public String getPdfInBase64(ZipcodeDto zipcodeDto) throws DocumentException, IOException {
        Set<ChannelDto> channels = zipcodeDto.getChannels();
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String base64;
        PdfWriter.getInstance(document, baos);
        // adding footer before opening document
        setFooter(document);
        document.open();
        Paragraph subHeading = getSubHeading(zipcodeDto.getZipcode());
        Paragraph heading = getHeading();
        PdfPTable table = getTable(channels);
        document.add(subHeading);
        document.add(heading);
        document.add(table);
        document.close();
        base64 = getBase64(baos);
        return base64;
    }
}
