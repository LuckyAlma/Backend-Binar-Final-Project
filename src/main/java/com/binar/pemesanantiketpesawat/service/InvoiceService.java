package com.binar.pemesanantiketpesawat.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public interface InvoiceService {
    JasperPrint generateInvoice(String bookingCodeResponse) throws IOException, JRException;
}
