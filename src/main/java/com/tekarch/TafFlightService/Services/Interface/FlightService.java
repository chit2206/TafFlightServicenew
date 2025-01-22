package com.tekarch.TafFlightService.Services.Interface;


import com.tekarch.TafFlightService.Models.FlightDTO;

import java.util.List;
public interface FlightService {
    List<FlightDTO> getAllFlights();

    FlightDTO getFlightById(Long flightId);

    FlightDTO addFlight(FlightDTO flightDTO);

    FlightDTO updateFlight(Long flightId, FlightDTO flightDTO);

    void deleteFlight(Long flightId);
}