package flightsdata.flights.repository;

import flightsdata.flights.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Flight
 * @author AleksandraRumińska
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    List<Flight> findAll();

}

