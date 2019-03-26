package TRAFFICSIM;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class SettingsController implements Initializable {
    Stage mainStage;
    int currentPeriod;
    public SettingsController(int currentPeriod, Stage mainStage) {
        this.currentPeriod = currentPeriod;
        this.mainStage = mainStage;
    }
    @FXML private Button refresh;
    @FXML private Slider population, time, studentradius, classradius, stairwidth, period, zero, five, six;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        population.setValue(Data.studentPopulation);
        time.setValue(Data.avgTime*2);
        studentradius.setValue(Data.studentRadius);
        classradius.setValue(Data.classRadius);
        stairwidth.setValue(Data.stairRadius);
        period.setValue(currentPeriod);
        zero.setValue(Data.percentZero);
        five.setValue(Data.percentFifth);
        six.setValue(Data.percentSixth);
        refresh.setOnAction(event -> {
            mainStage.close();
            ((Stage)(refresh.getScene().getWindow())).close();
            Data.studentPopulation = (int)population.getValue();
            Data.avgTime = time.getValue()/2.;
            Data.studentRadius = studentradius.getValue();
            Data.classRadius = classradius.getValue();
            Data.stairRadius = stairwidth.getValue();
            currentPeriod = (int)period.getValue();
            Data.percentZero = zero.getValue();
            Data.percentFifth = five.getValue();
            Data.percentSixth = six.getValue();
            Controller c = new Controller(currentPeriod);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
            loader.setController(c);
            AnchorPane p = null;
            try {
                p = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        });
    }
}