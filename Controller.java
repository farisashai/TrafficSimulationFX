package TRAFFICSIM;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
public class Controller implements Initializable {
    private LinkedList<Student> students;
    private boolean genderColorOn = true;
    private int currentPeriod;
    private boolean paused = false;
    @FXML private AnchorPane pane;
    @FXML private Label period;
    @FXML private Button next, previous, pause, evacuate, settings;
    @FXML private ToggleButton genderToggle;
    @FXML private Label stairA, stairB, stairC, stairD, stairE, stairF, stairG, stairH, stairI, stairJ;
    private Label[] stairs;
    public Controller(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }
    @Override public void initialize(URL location, ResourceBundle resources) {
        Database.initialize();
        stairs = new Label[] {stairA, stairB, stairC, stairD, stairE, stairF, stairG, stairH, stairI, stairJ};
        AtomicBoolean aa = new AtomicBoolean();
        period.setText("Period: " + currentPeriod);
        genderToggle.setOnMouseClicked(event -> {
            genderColorOn = !genderColorOn;
            for (Student s: students)
                s.setGenderColorEnabled(genderColorOn);
        });
        next.setOnAction(event -> {
            if (currentPeriod < 6) {
                boolean isMoving = false;
                for (Student s: students) {
                    if (s.isMoving()) {
                        isMoving = true;
                        break;
                    }
                }
                if (!isMoving) {
                    currentPeriod++;
                    Database.resetStairCounters();
                    for (Student s : students)
                        s.nextPeriod();
                    period.setText("Period: " + currentPeriod);

                    List<Staircase> list = Database.getStaircaseList();
                    for (Staircase s: list)
                        stairs[list.indexOf(s)].setText(""+s.getStudentCounter());
                }
            }
        });
        previous.setOnAction(event -> {
            if (currentPeriod > 0) {
                boolean isMoving = false;
                for (Student s : students) {
                    if (s.isMoving()) {
                        isMoving = true;
                        break;
                    }
                }
                if (!isMoving) {
                    currentPeriod--;
                    Database.resetStairCounters();
                    for (Student s : students)
                        s.previousPeriod();
                    period.setText("Period: " + currentPeriod);
                    List<Staircase> list = Database.getStaircaseList();
                    for (Staircase s: list)
                        stairs[list.indexOf(s)].setText(""+s.getStudentCounter());
                }
            }
        });
        pause.setOnAction(event -> {
            if (paused) {
                for (Student s: students) {
                    if (s.getCurrentAnimation() != null)
                        s.getCurrentAnimation().play();
                }
                pause.setText("Pause");
            } else {
                for (Student s: students) {
                    if (s.isMoving())
                        s.getCurrentAnimation().pause();
                }
                pause.setText("Play");
            }
            paused = !paused;
        });
        settings.setOnAction(event -> {
            Stage settingsStage = new Stage();
            try {
                SettingsController controller = new SettingsController(currentPeriod,(Stage)next.getScene().getWindow());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
                loader.setController(controller);
                AnchorPane pane = loader.load();
                settingsStage.setScene(new Scene(pane));
            } catch (IOException e) {
                e.printStackTrace();
            }
            settingsStage.show();
        });
        evacuate.setOnAction(event -> {
            aa.set(true);
            for (Student s1: students) {
                if (s1.isMoving()) {
                    aa.set(false);
                    break;
                }
                if (s1.getCurrentClass() != null && s1.getCurrentClass().equals(Database.getRoom("EVACUATION"))) {
                    aa.set(false);
                    break;
                }
            }
            if (aa.get()) {
                Database.resetStairCounters();
                for (Student s : students)
                    if (s.getCurrentClass() != null)
                        s.goToRoom(Database.getRoom("EVACUATION"));
                List<Staircase> list = Database.getStaircaseList();
                for (Staircase s: list)
                    stairs[list.indexOf(s)].setText(""+s.getStudentCounter());
            }
        });
        students = new LinkedList<>();
        for (int i = 0; i < Data.studentPopulation; i++)
            students.add(new Student(Math.random() < 0.5,Data.studentRadius, 1+ (Math.random() * ((Data.speedRange/100))),Database.generateSchedule(),currentPeriod));
        pane.getChildren().addAll(students);
        genderToggle.setSelected(genderColorOn);
        for (Student s: students)
            s.setGenderColorEnabled(genderColorOn);
    }
}
