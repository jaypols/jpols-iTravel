package iTravel;

import javafx.beans.property.*;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**

 */
public class Flights {
    private final IntegerProperty flightNumber;
    private final ObjectProperty<LocalDate> departureDate;
    private final ObjectProperty<LocalDate> arrivalDate;
    private final StringProperty origin;
    private final StringProperty destination;
    private final FloatProperty fare;


    public Flights(int flightNum, LocalDate fDate, LocalDate fArrival, String fOrigin, String fDestination, float fFare) {
        this.flightNumber = new SimpleIntegerProperty(flightNum);
        this.departureDate = new SimpleObjectProperty<>(fDate);
        this.arrivalDate = new SimpleObjectProperty<>(fArrival);
        this.origin = new SimpleStringProperty(fOrigin);
        this.destination = new SimpleStringProperty(fDestination);
        this.fare = new SimpleFloatProperty(fFare);
    }


    public void setFlightNumber(int yourFlight) {
        flightNumber.set(yourFlight);
    }

    public IntegerProperty FlightNumberProperty() {
        return flightNumber;
    }

    public int getFlightNumber() {
        return flightNumber.get();
    }

    public void setDepartureDate (LocalDate fDate) {
        departureDate.set(fDate);
    }

    public ObjectProperty DepartureDateProperty() {
        return departureDate;
    }

    public ObjectProperty getDepartureDate() {
        return departureDate;
    }

    public void setArrivalDate (LocalDate fArrival) {
        departureDate.set(fArrival);
    }

    public ObjectProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public ObjectProperty getArrivalDate() {
        return arrivalDate;
    }

    public void setOrigin(String yourOrigin) {
        origin.set(yourOrigin);
    }

    public StringProperty originProperty() {
        return origin;
    }

    public String getOrigin() {
        return origin.get();
    }

    public void setDestination(String yourDestination) {
        destination.set(yourDestination);
    }

    public StringProperty desitinationProperty() {
        return destination;
    }

    public String getDestination() {
        return destination.get();
    }

    public void setFare(float yourFare) {
        fare.set(yourFare);
    }

    public FloatProperty fareProperty() {
        return fare;
    }

    public float getFare() {
        return fare.get();
    }

}