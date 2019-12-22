package iTravel;

import javafx.beans.property.*;

import java.time.LocalDate;

public class WebServer {
    private final BooleanProperty updateAvailable;
    private final IntegerProperty patchNumber;
    private final ObjectProperty<LocalDate> lastUpdate;

    public WebServer(int patchNum, boolean updateAvail, LocalDate lastUpd){
        this.updateAvailable = new SimpleBooleanProperty(updateAvail);
        this.patchNumber = new SimpleIntegerProperty(patchNum);
        this.lastUpdate = new SimpleObjectProperty<>(lastUpd);
    }

    public void setUpdateAvailable(boolean newUpd){updateAvailable.set(newUpd);}
    public boolean getUpdateAvailable(){return updateAvailable.get();}

    public void setPatchNumber(int num){patchNumber.set(num);}
    public int getPatchNumber(){return patchNumber.get();}

    public void setLastUpdate(LocalDate newUpdate){lastUpdate.set(newUpdate);}
    public ObjectProperty<LocalDate> getLastUpdate(){return lastUpdate;}



}
