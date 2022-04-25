# FlightData
Full Stack Spring Boot Application with the use of JPA, H2 database and AJAX calculating and displaying statistics about flights. 

The files flights.json and cargo.json were generated for the need of this application with the use of the generator: https://www.json-generator.com/. The details about the database are located in the application.properties file in the resources directory.

Backend is located in the java directory whereas the javascript, html and css files are in the resources directory together with the .json files, examples of requests on the backend and necessary png, downloaded from open source page.

## Starting the application 
To properly start the application, the FlightsApplication class must be run first.
Then run the frontend of the application. In the IntelliJ IDEA you need to run it by starting the "dev" in file package.json file in the resources directory and choose one of the addresses. You need to have npm installed.

## Getting the response
The application allows for finding:

1. For requested FlightNumber and date in the form "yyyy-MM-ddTHH:mm:ss", so e.g. "2019-07-21T03:04:44":
  * Total Weight of Cargo
  * Total Weight of Baggage
  * Total Weight Overall
2. For requested Airport IATA code and date in the form "yyyy-MM-ddTHH:mm:ss", so e.g. "2019-07-21T03:04:44":
  * Number of Arrivals to the airport
  * Number of departures from the airport
  * Number of baggage pieces arriving to the airport
  * Number of baggage pieces departing from the airport
