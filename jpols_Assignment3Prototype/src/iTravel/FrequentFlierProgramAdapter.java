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
public class FrequentFlierProgramAdapter {

    Connection connection;

    public FrequentFlierProgramAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE FrequentFlierProgram");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Employees
                stmt.execute("CREATE TABLE FrequentFlierProgram("
                        + "AccumulatedFare FLOAT, "
                        + "CustomerID INT, "
                        + "TotalFlightsAttended INT, "
                        + "DiscountApplied INT, " // 1 represents true for boolean and 0 represents false
                        + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {
        // Add a Frequent Flier
        this.insertFrequentFlier((float)24.3,334, 100,1);
    }

    public void insertFrequentFlier(float AccFare, int custID, int totalFlightsAtt, int discApp) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO FrequentFlierProgram (AccumulatedFare, CustomerID, TotalFlightsAttended, DiscountApplied) VALUES ('" + AccFare + "','" + custID + "','" + totalFlightsAtt + "','" + discApp + "')");
    }

    public void deleteFrequentFlier(int custID) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM FrequentFlierProgram");
        rs.next();
        stmt.executeUpdate("DELETE FROM FrequentFlierProgram "
                + "WHERE CustomerID ='"+ custID +"'");
    }

    // Get all Customer ID
    public ObservableList<FrequentFlierProgram> getCustomerID() throws SQLException {
        ObservableList<FrequentFlierProgram> frequentFlier = FXCollections.observableArrayList();

        ResultSet result;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM FrequentFlierProgram ";
        result = stmt.executeQuery(sqlStatement);

        while(result.next())
        {
            frequentFlier.add(new FrequentFlierProgram(result.getFloat(1),result.getInt(2),result.getInt(3),result.getBoolean(4)));
        }

        return frequentFlier;
    }



}
