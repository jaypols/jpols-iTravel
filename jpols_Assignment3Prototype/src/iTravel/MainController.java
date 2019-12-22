/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iTravel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Pols
 */
public class MainController implements Initializable {

    private Connection conn;
    private EmployeeAdapter employee;
    private FlightsAdapter flight;

    @FXML
    private MenuBar mainMenu;

    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void addEmployee() throws Exception {
        employee = new EmployeeAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddEmployeeForm.fxml"));
        Parent addEmployee = (Parent) fxmlLoader.load();
        AddEmployeeController addEmployeeController = (AddEmployeeController) fxmlLoader.getController();
        addEmployeeController.setModel(employee);

        Scene scene = new Scene(addEmployee);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Add New Employee");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void resetDB() {
        try {
            // create Employee model
            employee = new EmployeeAdapter(conn, true);
            displayAlert("Employee table has been created");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create Flights model
            flight = new FlightsAdapter(conn, true);
            displayAlert("Flights table has been reset");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    @FXML
    public void deleteEmployee() throws Exception {
        employee = new EmployeeAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteEmployee.fxml"));
        Parent addEmployee = (Parent) fxmlLoader.load();
        DeleteEmployeeController deleteEmployeeController = (DeleteEmployeeController) fxmlLoader.getController();
        deleteEmployeeController.setModel(employee);

        Scene scene = new Scene(addEmployee);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Delete Employee");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void updateEmployee() throws Exception {
        employee = new EmployeeAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateEmployeeForm.fxml"));
        Parent updateEmployee = (Parent) fxmlLoader.load();
        UpdateEmployeeController updateEmployeeController = (UpdateEmployeeController) fxmlLoader.getController();
        updateEmployeeController.setModel(employee);

        Scene scene = new Scene(updateEmployee);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Update Employee");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void addFlight() throws Exception {
        flight = new FlightsAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFlightForm.fxml"));
        Parent addFlight = (Parent) fxmlLoader.load();
        AddFlightController addFlightController = (AddFlightController) fxmlLoader.getController();
        addFlightController.setModel(flight);

        Scene scene = new Scene(addFlight);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Add Flight");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void deleteFlight() throws Exception { // I TALKED TO TA ABOUT THIS ISSUE THAT IS NOT FIXED AND IT IS THE EXACT SAME AS ABOVE
        flight = new FlightsAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteFlightForm.fxml"));
        Parent deleteFlight = (Parent) fxmlLoader.load();
        DeleteFlightController deleteFlightController = (DeleteFlightController) fxmlLoader.getController();
        deleteFlightController.setModel(flight);

        Scene scene = new Scene(deleteFlight); // I TALKED TO TA ABOUT THIS ISSUE THAT IS NOT FIXED SINCE IT
                                                // IS THE EXACT SAME AS ABOVE
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
        stage.setTitle("Remove Flight");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:TeamDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);
            employee = new EmployeeAdapter(conn,true);
            flight = new FlightsAdapter(conn, true);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }

    }
}






