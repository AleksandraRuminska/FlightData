package flightsdata.controller;


import flightsdata.cargo.service.CargoItemService;
import flightsdata.flights.service.FlightService;
import flightsdata.response.GetAirportData;
import flightsdata.response.GetFlightData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller with the HTTP methods
 * @author AleksandraRumińska
 */
@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = {"http://localhost:8800", "http://172.23.112.1:8883", "http://172.18.224.1:8883", "http://192.168.1.40:8883", "http://127.0.0.1:8883"}, methods = RequestMethod.GET)
public class FlightController {
    private FlightService flightService;
    private CargoItemService cargoItemService;

    /**
     * Constructor of the controller
     * @param flightService - service of Flight
     * @param cargoItemService - service of CargoItem
     */
    @Autowired
    public FlightController(FlightService flightService, CargoItemService cargoItemService){
        this.flightService = flightService;
        this.cargoItemService = cargoItemService;
    }

    /**
     * Get method for the data about flight's cargo
     *
     * @param flightNumber - the number of the flight
     * @param date - data
     * @return - GetFlightData instance with statistics
     */
    @GetMapping
    public GetFlightData getFlightStats(@RequestParam(value = "flightNumber", required = true) String flightNumber, @RequestParam(value = "date", required = true) String date) {
        GetFlightData data = new GetFlightData(0,0,0);

        if(date.isEmpty()){
            return null;
        }

        data = data.CollectStatsFlight(flightNumber, LocalDateTime.parse(date), flightService);
        return data;
    }

    /**
     * Get method for the data about airport's arrivals and departures
     *
     * @param airportCode - unique airport three letter code
     * @param date - date
     * @return - GetAirportData instance with statistics
     */
    @GetMapping("{airportCode}")
    public GetAirportData getAirportStats(@PathVariable("airportCode") String airportCode, @RequestParam(value = "date", required = true) String date) {
        GetAirportData data = new GetAirportData(0,0,0,0);
        if(date.isEmpty()){
            return null;
        }
        data = data.CollectStatsAirport(airportCode, LocalDateTime.parse(date), flightService);
        return data;
    }
}
