package iTravel;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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

// import the required libraries
public class DeleteFlightController implements Initializable {
    @FXML
    ComboBox<String> flightnumBox;
    @FXML
    ComboBox<String> destinationBox;
    @FXML
    ComboBox<String> originBox;
    @FXML
    ComboBox<String> fare;
    @FXML
    Button closeBtn;

    final ObservableList<String> data = FXCollections.observableArrayList();
    final ObservableList<String> data1 = FXCollections.observableArrayList();
    final ObservableList<String> data2 = FXCollections.observableArrayList();
    final ObservableList<String> data3 = FXCollections.observableArrayList();

    private FlightsAdapter flightsAdapter;

    public void setModel(FlightsAdapter flight){
        flightsAdapter = flight;
        buildComboBoxData();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
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
    @FXML
    public void save() {
        try {

            flightsAdapter.removeFlight(Integer.parseInt(flightnumBox.getValue()));
        } catch (SQLException ex) {
            displayAlert("ERRORll: " + ex.getMessage());
        }

        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void buildComboBoxData() {
        try {
            data.addAll(flightsAdapter.getFlightsNamesList());
            data1.addAll(flightsAdapter.getFlightsOriginList());
            data2.addAll(flightsAdapter.getFlightFaresList());
            data3.addAll(flightsAdapter.getFlightsDestinationList());
        } catch (SQLException ex) {
            displayAlert("comb box error: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flightnumBox.setItems(data);
        destinationBox.setItems(data3);
        originBox.setItems(data1);
        fare.setItems(data2);
    }
}