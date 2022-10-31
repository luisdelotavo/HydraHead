package se2203b.assignments1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import static se2203b.assignments1.HydraGameController.randomValue;

/*
    This is the Application class of the HydraGame
    Used to launch the GUI Application
 */

public class HydraGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //This is used to set the title and add the HydraIcon to the top of the GUI window
        //The scene is loaded in with proper size
        FXMLLoader fxmlLoader = new FXMLLoader(HydraGameApplication.class.getResource("HydraGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 850);
        stage.setTitle("Hydra Game");
        Image icon = new Image("file:src/main/resources/se2203b/assignments1/HydraIcon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();   }

    public static void main(String[] args) {
        launch();
    }
}