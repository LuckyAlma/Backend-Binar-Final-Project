package com.binar.pemesanantiketpesawat.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
@Disabled
public class InvoiceServiceImplTest {
    @Test
    public void testGenerateFlightInvoice() {
        try {
            // Load the JasperReport template file from classpath
            InputStream reportStream = getClass().getResourceAsStream("src/main/resources/FlightInvoice.jrxml");

            if (reportStream == null) {
                throw new FileNotFoundException("File not found: FlightInvoice.jrxml");
            }

            // Compile the JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Set parameter values
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("bookingCode", "ABC123");
            parameters.put("airlineName", "Aditya Airline");
            parameters.put("airlineCode", "AC123");
            parameters.put("flightClass", "Economy");
            parameters.put("flightDate", "2023-06-30");
            parameters.put("departureTime", "10:00 AM");
            parameters.put("departureCity", "City A");
            parameters.put("departureAirport", "Airport A");
            parameters.put("longFlight", "Yes");
            parameters.put("arrivalTime", "12:00 PM");
            parameters.put("arrivalCity", "City B");
            parameters.put("arrivalAirport", "Airport B");
            parameters.put("fullName", "Freddy Mercury");
            parameters.put("ticketNumber", "T123456");
            parameters.put("flightNumber", "FL123");
            parameters.put("facility", "WiFi");

            // Fill the JasperReport with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);

            // Export the JasperPrint to PDF
            String outputPath = "Desktop/file.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

            System.out.println("Flight invoice generated successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: FlightInvoice.jrxml");
            e.printStackTrace();
        } catch (JRException e) {
            System.err.println("Error loading object from InputStream");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred");
            e.printStackTrace();
        }
    }
}
