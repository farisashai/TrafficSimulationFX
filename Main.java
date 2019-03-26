package TRAFFICSIM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("PVPHS Student Traffic Simulation");
        primaryStage.setResizable(false);
        Controller c = new Controller(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
        loader.setController(c);
        AnchorPane p = null;
        try {
            p = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(p));
        primaryStage.show();
    }
}
