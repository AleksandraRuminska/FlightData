package flightsdata.initialize;

import flightsdata.cargo.CargoItem;
import flightsdata.cargo.service.CargoItemService;
import flightsdata.flights.Flight;
import flightsdata.flights.service.FlightService;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class reading the JSON files and extracting data
 * @author AleksandraRumińska
 */
@NoArgsConstructor
public class ReadFileJSON {

    /**
     * Method parsing the JSON files
     *
     * @param flights - array of all flights to be populated
     * @param cargoItemService - service of CargoItem
     * @param flightService - service of Flight
     * @throws IOException - input/output exception
     * @throws ParseException - exception while parsing JSON files
     */
    public void parseFunction(List<Flight> flights, CargoItemService cargoItemService, FlightService flightService) throws IOException, ParseException {
        JSONParser parserFlight = new JSONParser();

        Path resources = Paths.get("src","main","resources");
        String absolutePath = resources.toFile().getAbsolutePath();

        FileReader fReader = new FileReader(absolutePath+"/flights.json");
        JSONArray flightsArray = (JSONArray) parserFlight.parse(fReader);

        flightsArray.forEach(flight -> parseFlight((JSONObject)flight, flights, flightService));

        JSONParser parserCargo = new JSONParser();

        FileReader f2Reader = new FileReader(absolutePath + "/cargo.json");
        JSONArray cargoArray = (JSONArray) parserCargo.parse(f2Reader);

        ArrayList<CargoItem> cargoList = new ArrayList<>();

        cargoArray.forEach(cargo -> parserCargo((JSONObject)cargo, cargoList));

        for(CargoItem cargo : cargoList){
            Flight fl = new Flight();
            fl = fl.findFlight(flights, cargo.getIdTable());

            CargoItem item = CargoItem.builder()
                    .flightId(fl)
                    .id(cargo.getId())
                    .weight(cargo.getWeight())
                    .weightUnit(cargo.getWeightUnit())
                    .type(cargo.getType())
                    .pieces(cargo.getPieces())
                    .build();
            cargoItemService.create(item);
        }


    }

    /**
     * Method extracting data about flights
     *
     * @param flight - JSON object in the file
     * @param flights - array of flights to be populated
     * @param flightService - service of FLight
     */
    public static void parseFlight(JSONObject flight, List<Flight> flights, FlightService flightService){
        String flightNumber = ((JSONObject) flight).get("flightNumber").toString();
        String departureAirportIATACode = ((JSONObject) flight).get("departureAirportIATACode").toString();
        String arrivalAirportIATACode = ((JSONObject) flight).get("arrivalAirportIATACode").toString();
        String date = ((JSONObject) flight).get("departureDate").toString();
        LocalDateTime departureDate = LocalDateTime.parse(date.substring(0,19));
        int flightId = Integer.parseInt(((JSONObject) flight).get("flightId").toString());

        Flight flightAfter = new Flight(flightId, flightNumber, departureAirportIATACode, arrivalAirportIATACode, departureDate, null);
        flights.add(flightAfter);
        Flight item = Flight.builder()
                .flightId(flightId)
                .flightNumber(flightNumber)
                .arrivalAirportIATACode(arrivalAirportIATACode)
                .departureAirportIATACode(departureAirportIATACode)
                .departureDate(departureDate)
                .build();
        flightService.create(item);
    }


    /**
     * Method extracting data about cargo
     *
     * @param cargo - JSON object in the file
     * @param cargoList - list of all cargo to be populated
     */
    public static void parserCargo(JSONObject cargo, List<CargoItem> cargoList){
        int flightId = Integer.parseInt(((JSONObject) cargo).get("flightId").toString());

        JSONArray baggage = (JSONArray) cargo.get("baggage");
        for(Object elem: baggage){
            JSONObject obj = (JSONObject) elem;
            int id = Integer.parseInt(obj.get("id").toString());
            int weight = Integer.parseInt(obj.get("weight").toString());
            String weightUnit = obj.get("weightUnit").toString();
            int pieces = Integer.parseInt(obj.get("pieces").toString());
            cargoList.add(new CargoItem(flightId, null, id, weight, weightUnit, "baggage", pieces));
        }
        JSONArray cargoCargo = (JSONArray) cargo.get("cargo");
        for(Object elem: cargoCargo){
            JSONObject obj = (JSONObject) elem;
            int id = Integer.parseInt(obj.get("id").toString());
            int weight = Integer.parseInt(obj.get("weight").toString());
            String weightUnit = obj.get("weightUnit").toString();
            int pieces = Integer.parseInt(obj.get("pieces").toString());
            cargoList.add(new CargoItem(flightId, null, id, weight, weightUnit, "cargo", pieces));
        }



    }

}
