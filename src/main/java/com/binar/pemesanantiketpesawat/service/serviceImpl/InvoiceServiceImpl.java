package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.InvoiceModel;
import com.binar.pemesanantiketpesawat.dto.InvoiceModelRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.repository.AirportRepository;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import com.binar.pemesanantiketpesawat.service.InvoiceService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public JasperPrint generateInvoice(String codeBookingRequest) throws JRException, IOException {

        System.out.println(codeBookingRequest);

        Booking bookingResponse = bookingRepository.findBookingByBookingCode(codeBookingRequest);

        Airline airlineResponse = airlineService.searchByAirlineCode(bookingResponse.getAirlineCode());

        Integer baggage = airlineResponse.getFlightClass().get(0).getAirlineBaggage();

        InvoiceModelRequest invoiceTempData = new InvoiceModelRequest(codeBookingRequest, "" + bookingResponse.getDepartureDate(),//
                airlineResponse.getAirlineName(),//
                airlineResponse.getAirlineCode(),//
                bookingResponse.getFlightClass(),//
                "" + bookingResponse.getDepartureTime(),//
                "" + bookingResponse.getArrivalTime(),//
                "" + airportRepository.findByAirportLocation(bookingResponse.getDepartureAirport()).getAirportName(), //
                "" + airportRepository.findByAirportLocation(bookingResponse.getArrivalAirport()).getAirportName(), //
                bookingResponse.getDepartureAirport(),//
                bookingResponse.getArrivalAirport(),//
                bookingResponse.getDepartureGate(),//
                bookingResponse.getArrivalGate(), //
                bookingResponse.getLongFlight1()//
        );

        Collection<InvoiceModel> modelCollections = new ArrayList<>();
        for (Passenger passenger : bookingResponse.getPassengers()) {
            modelCollections.add(new InvoiceModel(passenger.getTitle(), passenger.getFullName(), "dewasa", codeBookingRequest, bookingResponse.getAirlineCode(), "Baggage " + baggage + " Kg"));
        }


        String invoicePath = "classpath:FlightInvoice.jrxml";
        InputStream reportFile = ResourceUtils.getURL(invoicePath).openStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(modelCollections);

        Map<String, Object> param = new HashMap<>();
        param.put("bookingCode", invoiceTempData.getBookingCode());
        param.put("flightDate", invoiceTempData.getFlightDate());
        param.put("airlineName", invoiceTempData.getAirlineName());
        param.put("airlineCode", invoiceTempData.getAirlineCode());
        param.put("flightClass", invoiceTempData.getFlightClass());
        param.put("departureTime", invoiceTempData.getDepartureTime());
        param.put("arrivalTime", invoiceTempData.getArrivalTime());
        param.put("departureAirport", invoiceTempData.getDepartureAirport());
        param.put("arrivalAirport", invoiceTempData.getArrivalAirport());
        param.put("departureCity", invoiceTempData.getDepartureCity());
        param.put("arrivalCity", invoiceTempData.getArrivalCity());
        param.put("departureGate", invoiceTempData.getDepartureGate());
        param.put("arrivalGate", invoiceTempData.getArrivalGate());
        param.put("longFlight", invoiceTempData.getLongFlight());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
        return jasperPrint;
    }
}
