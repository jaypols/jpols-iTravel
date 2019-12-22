package iTravel;

import javafx.beans.property.*;

/**
 *
 * @author Pols
 */
public class FrequentFlierProgram {
    private final FloatProperty accumulatedFare;
    private final IntegerProperty customerID;
    private final IntegerProperty totalFlightsAttended;
    private final BooleanProperty discountApplied;


    public FrequentFlierProgram(float accFare, int custID, int totFlightAtt, boolean discApp) {
        this.accumulatedFare = new SimpleFloatProperty(accFare);
        this.customerID = new SimpleIntegerProperty(custID);
        this.totalFlightsAttended = new SimpleIntegerProperty(totFlightAtt);
        this.discountApplied = new SimpleBooleanProperty(discApp);
    }


    public void setAccumulatedFare(Float yourFare) {
        accumulatedFare.set(yourFare);
    }

    public FloatProperty accumulatedFareProperty() {
        return accumulatedFare;
    }

    public float getAccumulatedFare () {
        return accumulatedFare.get();
    }

    public void setCustomerID(int custID) {
        customerID.set(custID);
    }

    public IntegerProperty customerIDPrperty() {
        return customerID;
    }

    public int getCustomerID() {
        return customerID.get();
    }

    public void setTotalFlightsAttended(int totFlights) {
        customerID.set(totFlights);
    }

    public IntegerProperty totalFlightsAttendedProperty() {
        return totalFlightsAttended;
    }

    public int getTotalFlightsAttended() {
        return totalFlightsAttended.get();
    }

    public void setDiscountApplied(boolean discApp) {
        discountApplied.set(discApp);
    }

    public BooleanProperty discountAppliedProperty() {
        return discountApplied;
    }

    public boolean getDiscoutApplied() {
        return discountApplied.get();
    }
}