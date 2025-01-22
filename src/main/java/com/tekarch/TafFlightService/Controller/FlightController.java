package com.tekarch.TafFlightService.Controller;


import com.tekarch.TafFlightService.Models.FlightDTO;
import com.tekarch.TafFlightService.Services.Interface.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {


    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        List<FlightDTO> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long flightId) {
        FlightDTO flight = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }

    @PostMapping
    public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {
        FlightDTO newFlight = flightService.addFlight(flightDTO);
        return ResponseEntity.ok(newFlight);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long flightId, @RequestBody FlightDTO flightDTO) {
        FlightDTO updatedFlight = flightService.updateFlight(flightId, flightDTO);
        return ResponseEntity.ok(updatedFlight);
    }
    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return ResponseEntity.ok("Flight deleted successfully.");
    }

}
