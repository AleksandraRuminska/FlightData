package flightsdata.flights.service;

import flightsdata.flights.Flight;
import flightsdata.flights.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for Flight
 * @author AleksandraRumińska
 */
@Service
public class FlightService {
    private final FlightRepository repository;

    /**
     * FlightService constructor
     * @param repository - repository of Flight
     */
    @Autowired
    public FlightService(FlightRepository repository){
        this.repository = repository;
    }

    /**
     * Method for finding all Flights in the repository
     * @return - list of Flights
     */
    public List<Flight> findAllFlights(){
        return repository.findAll();
    }

    /**
     * Method to add new Flight to the repository
     * @param flights - Flight to be added to repository
     * @return - Flight
     */
    @Transactional
    public Flight create(Flight flights){
        return repository.save(flights);
    }

}
