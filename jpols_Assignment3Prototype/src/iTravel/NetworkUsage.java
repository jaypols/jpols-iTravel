package iTravel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NetworkUsage {


    private final IntegerProperty userCapacity;
    private final IntegerProperty currentUsers;

    public NetworkUsage(int num, int cap) {
        this.userCapacity = new SimpleIntegerProperty(num);
        this.currentUsers= new SimpleIntegerProperty(cap);
    }


    public void setUserCapacity(int num) {
        userCapacity.set(num);
    }

    public int getUserCapacity() {
        return userCapacity.get();
    }


    public void setCurrentUsers(int num) {
        currentUsers.set(num);
    }

    public int getCurrentUsers() {
        return currentUsers.get();
    }
}