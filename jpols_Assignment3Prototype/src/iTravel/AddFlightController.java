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

public class AddFlightController implements Initializable {

    @FXML
    Button cancelBtn;

    @FXML
    TextField flightNumTxt;

    @FXML
    TextField fareTxt;
    @FXML
    TextField originTxt;
    @FXML
    TextField destinationTxt;
    final ObservableList<String> data = FXCollections.observableArrayList();
    private FlightsAdapter flightsAdapter;

    public void setModel(FlightsAdapter flight){
        flightsAdapter=flight;
    }


    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
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
    public void save(){
        try{
            flightsAdapter.insertFlight(Integer.parseInt(flightNumTxt.getText()), originTxt.getText(),destinationTxt.getText(),Integer.valueOf(fareTxt.getText()));
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }







    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
