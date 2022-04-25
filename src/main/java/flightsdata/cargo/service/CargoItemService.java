package flightsdata.cargo.service;

import flightsdata.cargo.CargoItem;
import flightsdata.cargo.repository.CargoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for CargoItem
 * @author AleksandraRumińska
 */
@Service
public class CargoItemService {
    private final CargoItemRepository repository;

    /**
     * CargoItemService constructor
     * @param repository - repository of CargoItem
     */
    @Autowired
    public CargoItemService(CargoItemRepository repository){
        this.repository = repository;
    }


    /**
     * Method for finding all CargoItems in the repository
     * @return - list of CargoItems
     */
    public List<CargoItem> findAll(){
        return repository.findAll();
    }

    /**
     * Method to add new CargoItem to the repository
     * @param cargo - CargoItem to be added to repository
     * @return - CargoItem
     */
    @Transactional
    public CargoItem create(CargoItem cargo){
        return repository.save(cargo);
    }
}
