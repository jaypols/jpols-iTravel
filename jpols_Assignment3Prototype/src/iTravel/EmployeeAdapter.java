
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
public class EmployeeAdapter {

    Connection connection;

    public EmployeeAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Employee");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Employees
                stmt.execute("CREATE TABLE Employee("
                        + "EmployeeName CHAR(50) NOT NULL, "
                        + "EmployeeID INT NOT NULL, "
                        + "EmployeePosition CHAR(50) NOT NULL "
                        + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {
        // Add an Employee
        this.insertEmployee("Jayson","Business Manager",12345);
    }

    public void insertEmployee(String eName, String ePosition, int eID) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Employee (EmployeeName, EmployeeID, EmployeePosition) VALUES ('" + eName + "'," + eID + ",'" + ePosition + "')");
    }

    public void deleteEmployee(String eName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM Employee");
        rs.next();
        stmt.executeUpdate("DELETE FROM Employee "
                + "WHERE EmployeeName = '"+ eName +"'");
    }

    public void updateEmployee(String newEName, String NewEPosition, int newEID, String OldEName) throws SQLException
    {

        Statement stmt = connection.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM Employee");
        rs.next();

        stmt.executeUpdate("UPDATE Employee SET EmployeeName = '" + newEName + "' WHERE EmployeeName = '" + OldEName + "'");
        stmt.executeUpdate("UPDATE Employee SET EmployeeID = " + newEID + " WHERE EmployeeName = '" + OldEName + "'");
        stmt.executeUpdate("UPDATE Employee SET EmployeePosition = '" + NewEPosition + "' WHERE EmployeeName = '" + OldEName + "'");

    }

    // Get all Employees
    public ObservableList<Employee> getEmployeeList() throws SQLException {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        ResultSet result;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Employee ";
        result = stmt.executeQuery(sqlStatement);

        while(result.next())
        {
            employeeList.add(new Employee(result.getString(1), result.getInt(2),
                    result.getString(3)));
        }

        return employeeList;
    }

    // Get a String list of employees to populate the ComboBox
    public ObservableList<String> getEmployeeNamesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Employee";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // Loop the entire rows of rs and set the string values of list
        while(rs.next())
        {
            list.add(new String(rs.getString("EmployeeName")));
        }

        return list;
    }



}
