package com.tekarch.TafFlightService.Services;

import com.tekarch.TafFlightService.Models.FlightDTO;
import com.tekarch.TafFlightService.Services.Interface.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private  RestTemplate restTemplate;

    @Value("${flights.ms.url}")
    private String dbServiceUrl;

//    public FlightServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @Override
    public List<FlightDTO> getAllFlights() {
        try {
            ResponseEntity<FlightDTO[]> response = restTemplate.getForEntity(dbServiceUrl, FlightDTO[].class);
            return Arrays.asList(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching flights: " + e.getMessage(), e);
        }
    }
    public List<FlightDTO> retrieveFlights() {
        try {
            String url = dbServiceUrl ;
            ResponseEntity<List<FlightDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<FlightDTO>>() {
                    }
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to retrieve Flight: " + response.getStatusCode());
            }

            return response.getBody();
        } catch (RestClientException e) {
            System.err.println("Error fetching users: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        List<FlightDTO> flights = retrieveFlights();
        return flights.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));





    }


    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) {
        try {

            return restTemplate.postForObject(dbServiceUrl, flightDTO, FlightDTO.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error adding flight: " + e.getMessage(), e);
        }
    }

    @Override
    public FlightDTO updateFlight(Long flightId, FlightDTO flightDTO) {
        try {
            String url = dbServiceUrl + flightId;
            restTemplate.put(url, flightDTO);
            return getFlightById(flightId);
        } catch (RestClientException e) {
            throw new RuntimeException("Error updating flight: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFlight(Long flightId) {
        try {
            String url = dbServiceUrl + flightId;
            restTemplate.delete(url);
        } catch (RestClientException e) {
            throw new RuntimeException("Error deleting flight: " + e.getMessage(), e);
        }
    }
}