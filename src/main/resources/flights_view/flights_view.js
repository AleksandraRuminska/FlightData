let flightNumber = "";
let date = "";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => setVariables(event));
    if(flightNumber!=="" && date!==""){
        fetchFlightInfo();
    }
});

/**
 * Function setting the parameters that will be later used for fetching data
 *
 * @param event - event of clicking the submit button
 */
function setVariables(event){
    event.preventDefault();
    flightNumber =  document.getElementById('flightNumber').value
    date =  document.getElementById('date').value
    fetchFlightInfo();
}

let info;

/**
 * Function fetching the data from the backend with the GET method
 *
 */
function fetchFlightInfo() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            info = JSON.parse(this.responseText);
            displayInfo(info);
        }
    };

    xhttp.open("GET", "http://localhost:8800" + '/api/flights' + '?flightNumber='+flightNumber+'&date=' + date , true);
    xhttp.send();
}

/**
 * Function assigning the data from the backend to the specified elements in html
 *
 * @param {{totalCargoWeight: int, totalBaggageWeight:int, totalWeight:int}} info
 */
function displayInfo(info) {
    document.getElementById("cargo").innerHTML = info.totalCargoWeight.toString();
    document.getElementById("baggage").innerHTML = info.totalBaggageWeight.toString();
    document.getElementById("total").innerHTML = info.totalWeight.toString();

}


