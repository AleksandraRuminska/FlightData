let airportCode = "";
let date = "";

window.addEventListener('load', () => {
    const airForm = document.getElementById('airForm');
    airForm.addEventListener('submit', event => setVariables(event));
    if(airportCode!=="" && date!==""){
        fetchAirportInfo();
    }
});

/**
 * Function setting the parameters that will be later used for fetching data
 *
 * @param event - event of clicking the submit button
 */
function setVariables(event){
    event.preventDefault();
    airportCode =  document.getElementById('airportCode').value
    date =  document.getElementById('date').value
    fetchAirportInfo();
}

let info;

/**
 * Function fetching the data from the backend with the GET method
 *
 */
function fetchAirportInfo() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            info = JSON.parse(this.responseText);
            displayInfo(info);
        }
    };

    xhttp.open("GET", "http://localhost:8800" + '/api/flights/' +airportCode+'?date=' + date , true);
    xhttp.send();
}

/**
 * Function assigning the data from the backend to the specified elements in html
 *
 * @param {{flightsDeparting: int, flightsArriving:int, baggagePiecesArriving:int, baggagePiecesDeparting:int}} info
 */
function displayInfo(info) {
    document.getElementById("departures").innerHTML = info.flightsDeparting.toString();
    document.getElementById("arrivals").innerHTML = info.flightsArriving.toString();
    document.getElementById("baggageDep").innerHTML = info.baggagePiecesDeparting.toString();
    document.getElementById("baggageArr").innerHTML = info.baggagePiecesArriving.toString();


}



