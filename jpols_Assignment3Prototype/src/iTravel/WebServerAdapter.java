package iTravel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class WebServerAdapter {

    Connection connection;

    public WebServerAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                stmt.execute("DROP TABLE WebServer");
            } catch (SQLException ex) {
            } finally {

                stmt.execute("CREATE TABLE WebServer("
                        +"updateAvailable INT ," //BOOLEAN, 1 REPRESENTS TRUE AND 0 REPRESENTS FALSE
                        + "patchNumber INT , "
                        + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {

        this.insertWebServer(0,1234);
        this.insertWebServer(1,443);

    }

    public void insertWebServer(int updateAvail, int  patchNum) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO WebServer (updateAvailable, patchNumber)"
                + "VALUES (" + updateAvail + " , " + patchNum);
    }

    public void removeWebServer(int updateAvail, int patchNum) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM WebServer"+"WHERE updateAvailable = "+(updateAvail)+"WHERE patchNumber="+(patchNum));

    }

    public void updateWebServer(int newUpdate, int oldUpdate, int newPatchNum, int oldPatchNum) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("UPDATE WebServer SET updateAvailable = " + (newUpdate) +"WHERE updaateAvailable = " + oldUpdate);
        stmt.executeUpdate("UPDATE WebServer SET patchNumber = " + (newPatchNum) +"WHERE patchNumber = " + oldPatchNum);
    }





}

