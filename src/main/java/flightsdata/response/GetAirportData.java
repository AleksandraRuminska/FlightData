package flightsdata.response;

import flightsdata.flights.Flight;
import flightsdata.flights.service.FlightService;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class with response for data about airport's arrivals and departures
 * @author AleksandraRumińska
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GetAirportData {
    private int flightsDeparting;
    private int flightsArriving;
    private int baggagePiecesArriving;
    private int baggagePiecesDeparting;

    /**
     * Method to collect the needed statistics about the airport
     *
     * @param airportCode - three letter code of the airport
     * @param date - date
     * @param flightService - service of the Flight
     * @return - instance of GetAirportData with all statistics
     */
    public GetAirportData CollectStatsAirport(String airportCode, LocalDateTime date, FlightService flightService){
        List<Flight> flights = flightService.findAllFlights();
        int flightsDeparting = 0;
        int flightsArriving = 0;
        int baggagePiecesArriving = 0;
        int baggagePiecesDeparting = 0;

        GetAirportData data = new GetAirportData(flightsDeparting, flightsArriving, baggagePiecesArriving, baggagePiecesDeparting);
        for(Flight flight : flights){
            if(flight.getDepartureDate().isEqual(date)){
                if(flight.getArrivalAirportIATACode().equals(airportCode)){
                    data.setFlightsArriving(data.getFlightsArriving()+1);
                    flight.getCargo().forEach(baggage -> {
                        if(baggage.getType().equals("baggage")){
                            data.setBaggagePiecesArriving(data.getBaggagePiecesArriving() + baggage.getPieces());
                        } });
                }else if(flight.getDepartureAirportIATACode().equals(airportCode)){

                    flight.getCargo().forEach(baggage ->{
                        if(baggage.getType().equals("baggage")){
                            data.setBaggagePiecesDeparting(data.getBaggagePiecesDeparting() + baggage.getPieces());
                        } });
                    data.setFlightsDeparting(data.getFlightsDeparting()+1);

                }

            }
        }

        return data;
    }
}
