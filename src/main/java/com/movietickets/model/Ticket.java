package com.movietickets.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.movietickets.repository.SeatRepository;

/**
 * Represents a printable movie ticket that can export to PDF.
 */
public class Ticket {
private int ticketID;
private double finalPrice;
private Reservation reservationID;
private Show showID;
private int seatID;
private int hallID;

public Ticket() {}

public Ticket(int ticketID, double finalPrice, Reservation reservationID, Show showID, int seatID, int hallID) {
    this.ticketID = ticketID;
    this.finalPrice = finalPrice;
    this.reservationID = reservationID;
    this.showID = showID;
    this.seatID = seatID;
    this.hallID = hallID;
}

/**
 * Generates a PDF file for this ticket with clean visual layout.
 * Requires iTextPDF (v5.5+).
 */
public void printToPDF() {
    String folderPath = "./data/tickets/";
    new File(folderPath).mkdirs();
    String filePath = folderPath + "ticket_" + ticketID + ".pdf";

    Document document = new Document(PageSize.A6, 20, 20, 20, 20);

    try {
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Header
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.WHITE);
        PdfPTable header = new PdfPTable(1);
        header.setWidthPercentage(100);
        PdfPCell headerCell = new PdfPCell(new Phrase("Movie Ticket", headerFont));
        headerCell.setBackgroundColor(BaseColor.BLACK);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(10f);
        headerCell.setBorder(Rectangle.NO_BORDER);
        header.addCell(headerCell);
        document.add(header);

        document.add(Chunk.NEWLINE);

        // Ticket info
        Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font infoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(5f);

        String seatLabel = String.valueOf(seatID);
        SeatRepository seatRepo = new SeatRepository();
        var seatOpt = seatRepo.findById(seatID);
        if (seatOpt.isPresent()) {
            Seat s = seatOpt.get();
            seatLabel = s.getRow() + s.getNum();
        }

        addRow(table, "Ticket ID:", String.valueOf(ticketID), labelFont, infoFont);
        addRow(table, "Movie:", showID.getMovie().getTitle(), labelFont, infoFont);
        addRow(table, "Show Date:", showID.getDate(), labelFont, infoFont);
        addRow(table, "Show Time:", showID.getStartTime(), labelFont, infoFont);
        addRow(table, "Hall:", String.valueOf(hallID), labelFont, infoFont);
        addRow(table, "Seat:", seatLabel, labelFont, infoFont);
        addRow(table, "Price:", String.format("%.2f EGP", finalPrice), labelFont, infoFont);
        addRow(table, "User:", reservationID.getUser().getEmail(), labelFont, infoFont);
        addRow(table, "Issued At:", reservationID.getReservationTime(), labelFont, infoFont);
        document.add(table);

        document.add(Chunk.NEWLINE);

        // Footer note
        Paragraph footer = new Paragraph(
                "Please arrive 15 minutes early.\nThank you for choosing popcorn code movie reservation system!",
                new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY)
        );
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);

        document.close();
        System.out.println("Ticket PDF generated successfully: " + filePath);

    } catch (DocumentException | IOException e) {
        System.err.println("Error generating PDF ticket: " + e.getMessage());
    }
}

private void addRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
    PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
    labelCell.setBorder(Rectangle.NO_BORDER);
    table.addCell(labelCell);

    PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
    valueCell.setBorder(Rectangle.NO_BORDER);
    table.addCell(valueCell);
}

// Getters / setters / equals / hashCode / toString

public int getTicketID() { return ticketID; }
public void setTicketID(int ticketID) { this.ticketID = ticketID; }
public double getFinalPrice() { return finalPrice; }
public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }
public Reservation getReservationID() { return reservationID; }
public void setReservationID(Reservation reservationID) { this.reservationID = reservationID; }
public Show getShowID() { return showID; }
public void setShowID(Show showID) { this.showID = showID; }
public int getSeatID() { return seatID; }
public void setSeatID(int seatID) { this.seatID = seatID; }
public int getHallID() { return hallID; }
public void setHallID(int hallID) { this.hallID = hallID; }

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ticket)) return false;
    Ticket ticket = (Ticket) o;
    return ticketID == ticket.ticketID;
}

@Override
public int hashCode() { return Objects.hash(ticketID); }

@Override
public String toString() {
    return "Ticket{" +
            "ticketID=" + ticketID +
            ", finalPrice=" + finalPrice +
            ", hallID=" + hallID +
            ", seatID=" + seatID +
            '}';
}
}
