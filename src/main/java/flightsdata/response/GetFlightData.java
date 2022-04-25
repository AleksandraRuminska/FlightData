package flightsdata.response;

import flightsdata.flights.Flight;
import flightsdata.flights.service.FlightService;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class with response for data about flight's cargo
 * @author AleksandraRumińska
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GetFlightData {
    private int totalCargoWeight;
    private int totalBaggageWeight;
    private int totalWeight;

    /**
     * Method to collect the needed statistics about the flight
     *
     * @param flightNumber - number of the flight
     * @param date - date
     * @param flightService - service of the Flight
     * @return - - instance of GetFlightData with all statistics
     */
    public GetFlightData CollectStatsFlight(String flightNumber, LocalDateTime date, FlightService flightService) {
        List<Flight> flights = flightService.findAllFlights();

        int cargoWeight = 0;
        int baggageWeight = 0;
        int totalWeight = 0;
        GetFlightData data = new GetFlightData(cargoWeight, baggageWeight, totalWeight);
        for(Flight flight : flights){
            if(flight.getFlightNumber().equals(flightNumber) && flight.getDepartureDate().isEqual(date)){
                flight.getCargo().forEach(baggage ->{
                    if(baggage.getType().equals("baggage")){
                        data.setTotalBaggageWeight(data.getTotalBaggageWeight() + baggage.getWeight());
                    }else if(baggage.getType().equals("cargo")){
                        data.setTotalCargoWeight(data.getTotalCargoWeight() + baggage.getWeight());
                    }
                });
                break;
            }


        }
        data.setTotalWeight(data.getTotalBaggageWeight()+data.getTotalCargoWeight());
        return data;
    }

}
