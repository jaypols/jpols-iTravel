package iTravel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteEmployeeController implements Initializable {

    @FXML
    Button cancelBtn;

    @FXML
    Button saveBtn;

    @FXML
    ComboBox<String> emplo;

    private EmployeeAdapter employeeAdapter;

    // Some local variable declarations
    // The data variable is used to populate the ComboBoxes
    final ObservableList<String> data = FXCollections.observableArrayList();
    // To reference the models inside the controller
    public void setModel(EmployeeAdapter emp) {
        employeeAdapter = emp;
        buildComboBoxData();
    }

    public void buildComboBoxData() {
        try {
            data.addAll(employeeAdapter.getEmployeeNamesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void save() throws SQLException {
        employeeAdapter.deleteEmployee(emplo.getValue());
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
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

            stage.getIcons().add(new Image("file:src/iTravel/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emplo.setItems(data);
    }
}
