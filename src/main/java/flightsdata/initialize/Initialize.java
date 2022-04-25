package flightsdata.initialize;

import flightsdata.cargo.CargoItem;
import flightsdata.cargo.service.CargoItemService;
import flightsdata.flights.Flight;
import flightsdata.flights.service.FlightService;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to initialize the Flights and CargoItems; populating the database
 * @author AleksandraRumińska
 */
@Component
public class Initialize {
    private FlightService flightService;
    private CargoItemService cargoItemService;

    /**
     * Constructor of Initialize class
     * @param flightService - service of Flight
     * @param cargoItemService - service of CargoItem
     */
    public Initialize(FlightService flightService, CargoItemService cargoItemService){
        this.flightService = flightService;
        this.cargoItemService = cargoItemService;
    }

    /**
     * Method init that populates database
     *
     * @throws IOException - input/output exception
     * @throws ParseException - exception while parsing JSON files
     */
    @PostConstruct
    public void init() throws IOException, ParseException {
        List<Flight> flights = new ArrayList<>();

        ReadFileJSON reader = new ReadFileJSON();
        reader.parseFunction(flights, cargoItemService, flightService);

    }
}
