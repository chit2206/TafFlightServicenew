package com.tekarch.TafFlightService.Services;

import com.tekarch.TafFlightService.Models.FlightDTO;
import com.tekarch.TafFlightService.Services.Interface.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class FlightServiceImpl  implements FlightService{
    @Autowired
    private final RestTemplate restTemplate;

    @Value("${flights.ms.url}")
    private String dbServiceUrl;

    public FlightServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        try {

            return Arrays.asList(restTemplate.getForObject(dbServiceUrl, FlightDTO[].class));
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching flights: " + e.getMessage(), e);
        }
    }

    @Override
    public FlightDTO getFlightById(Long flightId) {
        try {
            String url = dbServiceUrl +  flightId;
            return restTemplate.getForObject(url, FlightDTO.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching flight by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) {
        try {
            String url = dbServiceUrl ;
            return restTemplate.postForObject(url, flightDTO, FlightDTO.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error adding flight: " + e.getMessage(), e);
        }
    }

    @Override
    public FlightDTO updateFlight(Long flightId, FlightDTO flightDTO) {
        try {
            String url = dbServiceUrl  + flightId;
            restTemplate.put(url, flightDTO);
            return getFlightById(flightId);
        } catch (RestClientException e) {
            throw new RuntimeException("Error updating flight: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFlight(Long flightId) {
        try {
            String url = dbServiceUrl+ flightId;
            restTemplate.delete(url);
        } catch (RestClientException e) {
            throw new RuntimeException("Error deleting flight: " + e.getMessage(), e);
        }
    }
}