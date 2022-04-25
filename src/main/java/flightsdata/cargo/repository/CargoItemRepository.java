package flightsdata.cargo.repository;

import flightsdata.cargo.CargoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for CargoItems
 * @author AleksandraRumińska
 */
@Repository
public interface CargoItemRepository extends JpaRepository<CargoItem, String> {

    List<CargoItem> findAll();
}
