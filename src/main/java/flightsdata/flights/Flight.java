package flightsdata.flights;

import flightsdata.cargo.CargoItem;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The Flight class containing the flight entry
 * @author AleksandraRumińska
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    private int flightId;
    private String flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private LocalDateTime departureDate;

    @OneToMany(mappedBy="flightId")
    @ToString.Exclude
    private List<CargoItem> cargo;


    public Flight findFlight(List<Flight> flights, int id){
        for(Flight fl : flights){
            if(fl.flightId == id){
                return fl;
            }
        }
        return null;
    }



}
