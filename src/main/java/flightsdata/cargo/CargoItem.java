package flightsdata.cargo;

import flightsdata.flights.Flight;
import lombok.*;

import javax.persistence.*;

/**
 * The CargoItem class containing the baggage or cargo items from a specified by flightId flight
 * @author AleksandraRumińska
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "cargoItems")
public class CargoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTable;
    @ManyToOne()
    @JoinColumn(name="flightId")
    private Flight flightId;
    private int id;
    private int weight;
    private String weightUnit;
    private String type;
    private int pieces;
}
