package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightsAdapter {

    Connection connection;

    public FlightsAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                stmt.execute("DROP TABLE Flights");
            } catch (SQLException ex) {
            } finally {
                // Create the table of Matches
                stmt.execute("CREATE TABLE Flights("
                        + "flightNumber INT NOT NULL, "
                        + "origin CHAR(25) NOT NULL , "
                        + "destination CHAR(25) NOT NULL , "
                        + "fare FLOAT NOT NULL "
                        + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {
        this.insertFlight(1, "Toronto", "Miami", 542);
        this.insertFlight(2,"Vancouver","London",740);

    }

    public void insertFlight(int num, String start, String end, float num2) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Flights (flightNumber, origin, destination, fare)"
                + "VALUES (" + num + " , '" + start + "' , '" + end + "',  " + num2 + ")");
    }

    public void removeFlight(int fNum) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Flights "
                    + "WHERE flightNumber =" + fNum + "");
    }

    public ObservableList<String> getFlightsNamesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;
        Statement stmt = connection.createStatement();

        String sqlStatement = "SELECT * FROM Flights";

        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            String myString = Integer.toString(rs.getInt("flightNumber"));
            list.add(myString);

        }
        return list;
    }

    public ObservableList<String> getFlightFaresList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stmt = connection.createStatement();


        String sqlStatement = "SELECT * FROM Flights";

        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            String myString = Float.toString(rs.getFloat("fare"));
            list.add(myString);

        }
        return list;
    }

    public ObservableList<String> getFlightsOriginList() throws SQLException {
        ObservableList<java.lang.String> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stmt = connection.createStatement();


        String sqlStatement = "SELECT * FROM Flights";

        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            String myString = rs.getString("origin");
            list.add(myString);

        }
        return list;

    }

    public ObservableList<String> getFlightsDestinationList() throws SQLException {
        ObservableList<java.lang.String> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stmt = connection.createStatement();


        String sqlStatement = "SELECT * FROM Flights";

        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            String myString = rs.getString("destination");
            list.add(myString);

        }
        return list;

    }

}
