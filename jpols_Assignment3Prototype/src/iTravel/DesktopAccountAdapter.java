
package iTravel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.org.apache.bcel.internal.generic.Select;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pols
 */
public class DesktopAccountAdapter {

    Connection connection;

    public DesktopAccountAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE DesktopAccount");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Employees
                stmt.execute("CREATE TABLE DesktopAccount("
                        + "DesktopUsername CHAR(15) NOT NULL PRIMARY KEY, "
                        + "DesktopPassowrd CHAR(15)" + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {
        // Add an Employee
        this.insertDesktopAccount("jpols","123abc");
    }

    public void insertDesktopAccount(String DeskUsername, String DeskPasswrod) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO DesktopAccount (DesktopUsername, DesktopPassword) VALUES ('" + DeskUsername + "','" + DeskPasswrod + "')");
    }

    public void deleteDesktopAccount(String DeskUsern) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM DesktopAccount");
        rs.next();
        stmt.executeUpdate("DELETE FROM DesktopAccount "
                + "WHERE DesktopUsername ='"+ DeskUsern +"'");
    }

    public void updateDesktopAccount(String newUsername, String newPassword, String OldUsername, String OldPassword) throws SQLException
    {

        Statement stmt = connection.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM DesktopAccount");
        rs.next();

        stmt.executeUpdate("UPDATE DesktopAccount SET DesktopUsername = " + newUsername + " WHERE Name = " + OldUsername + "");
        stmt.executeUpdate("UPDATE DesktopAccount SET DesktopPassword = " + newPassword + " WHERE Name = " + OldPassword + "");

    }

    // Get all Employees
    public ObservableList<Employee> getUsername() throws SQLException {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        ResultSet result;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM DesktopAccount ";
        result = stmt.executeQuery(sqlStatement);

        while(result.next())
        {
            employeeList.add(new Employee(result.getString(1), result.getInt(2),
                    result.getString(3)));
        }

        return employeeList;
    }

    // Get a String list of usernames to populate the ComboBox
    public ObservableList<String> getUsernameList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM DesktopAccount";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // Loop the entire rows of rs and set the string values of list
        while(rs.next())
        {
            list.add(new String(rs.getString("DesktopUsername")));
        }

        return list;
    }



}
