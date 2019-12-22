package iTravel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class NetworkUsageAdapter {

    Connection connection;

    public NetworkUsageAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                stmt.execute("DROP TABLE NetworkUsage");
            } catch (SQLException ex) {
            } finally {

                stmt.execute("CREATE TABLE NetworkUsage ("
                        + "userCapacity INT, "
                        + "currentUsers INT, "
                        + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {
        this.insertNetworkUsage(900,278);
        this.insertNetworkUsage(200, 129);

    }

    public void insertNetworkUsage(int userCapacity, int currentUsers) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO NetworkUsage (userCapacity, currentUsers)"
                + "VALUES (" + currentUsers + " , " + userCapacity);
    }

    public void removeNetworkUsage(int userCapacity, int currentUsers) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM NetworkUsage"+"WHERE currentUsers="+(currentUsers)+"WHERE userCapcity="+(userCapacity));

    }

    public void updateNetworkUsage(int newUserCapacity, int newCurrentUsers, int currentUsers, int userCapacity) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("UPDATE NetworkUsage SET currentUsers = " + (newCurrentUsers) +"WHERE NetworkNumber="+ currentUsers);
        stmt.executeUpdate("UPDATE NetworkUsage SET networkCapacity = " + (newUserCapacity) +"WHERE NetworkNumber="+ userCapacity);
    }





}
