package iTravel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class ApplicationServerAdapter {

    Connection connection;

    public ApplicationServerAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                stmt.execute("DROP TABLE ApplicationServer");
            } catch (SQLException ex) {
            } finally {

                stmt.execute("CREATE TABLE ApplicationServer("
                        +"updateAvailable INT ," //BOOLEAN, 1 REPRESENTS TRUE AND 0 REPRESENTS FALSE
                        + "patchNumber INT , "
                        + ")");
                populateSamples();
            }
        }
    }

    private void populateSamples() throws SQLException {

        this.insertApplicationServer(0,1234);
        this.insertApplicationServer(1,443);

    }

    public void insertApplicationServer(int updateAvail, int  patchNum) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO ApplicationServer (updateAvailable, patchNumber)"
                + "VALUES (" + updateAvail + " , " + patchNum);
    }

    public void removeApplicationServer(int updateAvail, int patchNum) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM ApplicationServer"+"WHERE updateAvailable = "+(updateAvail)+"WHERE patchNumber="+(patchNum));

    }

    public void updateApplicationServer(int newUpdate, int oldUpdate, int newPatchNum, int oldPatchNum) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("UPDATE ApplicationServer SET updateAvailable = " + (newUpdate) +"WHERE updaateAvailable = " + oldUpdate);
        stmt.executeUpdate("UPDATE ApplicationServer SET patchNumber = " + (newPatchNum) +"WHERE patchNumber = " + oldPatchNum);
    }





}