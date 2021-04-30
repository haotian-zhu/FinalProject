package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//inherit the application class
public class Main extends Application {

    //start the primary stage and handle IOExceptions.
    //load the fxml file
    //set the window's dimension and title
    //show the stage
    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("ImageTool");
        primaryStage.setScene(new Scene(root, 640, 485));
        primaryStage.show();


    }

    //launch the whole thing
    public static void main(String[] args) {
        launch(args);
    }
}
