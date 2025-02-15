package com.tekarch.TafFlightService.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDTO {

    private Long id;

  //@JsonProperty("flight_number")
    private String flightNumber;


    private String departure;


    private String arrival;

   //@JsonProperty("departure_time")
    private LocalDateTime departureTime;

   //@JsonProperty("arrival_time")
    private LocalDateTime arrivalTime;


    private Double price;

 //@JsonProperty("available_seats")
    private Integer availableSeats;

   //@JsonProperty("created_at")
    private LocalDateTime createdAt ;
  //  @JsonProperty("updated_at")
    private LocalDateTime updatedAt;


}
