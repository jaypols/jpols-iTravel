package iTravel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pols
 */
public class Employee {
    private final StringProperty name;
    private final IntegerProperty employeeID;
    private final StringProperty position;


    public Employee(String eName, int ID, String ePosition) {
        this.name = new SimpleStringProperty(eName);
        this.employeeID = new SimpleIntegerProperty(ID);
        this.position = new SimpleStringProperty(ePosition);
    }


    public void setName(String yourName) {
        name.set(yourName);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setEmployeeID(int ID) {
        employeeID.set(ID);
    }

    public IntegerProperty employeeIDProperty() {
        return employeeID;
    }

    public int getEmployeeID() {
        return employeeID.get();
    }

    public void setPosition(String ePosition) {
        position.set(ePosition);
    }

    public StringProperty positionProperty() {
        return position;
    }

    public String getPosition() {
        return position.get();
    }
}