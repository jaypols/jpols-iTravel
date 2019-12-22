package iTravel;

import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pols
 */
public class DektopAccount {
    private final StringProperty username;
    private final StringProperty password;


    public DektopAccount(String newUsername, String newPassword) {
        this.username = new SimpleStringProperty(newUsername);
        this.password = new SimpleStringProperty(newPassword);
    }


    public void setUsername(String yourUsername) {
        username.set(yourUsername);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setPassword(String yourPassword) {
        password.set(yourPassword);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }
}