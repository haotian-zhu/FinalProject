package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.convert.convertImage;
//implements abstract class Initializable to initialize the project
public class Controller implements Initializable {
    //Create necessary widgets of the UI
    @FXML
    public Button upload;
    @FXML
    public Button convert;
    @FXML
    public Button preview;
    @FXML
    public ListView listview1;
    @FXML
    public Label myLabel;
    @FXML
    public ChoiceBox<String> format;
    @FXML
    public TilePane tilePane;

    //Create a list to allow the user to select
    //preferred format  via choiceBox
    public String[] list = {"jpg", "png", "bmp", "gif"};

    //Create a list of image path to loop through
    public List<String> pathList = new ArrayList<>();

    //Creat a list of image name so that in convert.java
    //we can properly set directory for converted image
    public List<String> nameList = new ArrayList<>();

    //override initialize(URL, ResourceBundle)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        format.getItems().addAll(list);
        //Make a reference to getFormat() and link it
        //to node of format
        format.setOnAction(this::getFormat);

    }

    //upload images via FileChooser, limit number of file to 12
    public void uploadImage(ActionEvent event) {
        System.out.println("Please don't upload more than 12 images.");
        FileChooser fc = new FileChooser();
        List<File> selectedImage = fc.showOpenMultipleDialog(null);

        if (selectedImage != null) {
            for (File file : selectedImage) {
                Image myImage = new Image("file:" + file.getAbsolutePath());

                //Add image name & path to the lists so we can loop through
                pathList.add(file.getAbsolutePath());
                nameList.add(file.getName());

                //show image properties in ListView.
                listview1.getItems().add("File name: " + file.getName());
                listview1.getItems().add("Path: " + file.getAbsolutePath());
                listview1.getItems().add("dimension: " + myImage.getHeight() + "x" + myImage.getWidth());
            }
        } else {

            //prevent the user from entering invalid path
            System.out.println("Invalid file input");
        }
    }

    //inform the user which format he/she has chosen via the label
    public void getFormat(ActionEvent event) {
        String myFormat = format.getValue();
        myLabel.setText("Convert image to: ." + myFormat + " format");
    }

    //allow the user to preview image thumbnails
    public void getPreview(ActionEvent event) {

        //Check if the user has selected image from FileChooser
        if (!pathList.isEmpty()){

        //Loop through the paths and create (100X100)thumbnails
        for (int i = 0; i < pathList.size(); i++) {
            Image myImage = new Image("file:" + pathList.get(i));
            ImageView imageview = new ImageView();
            imageview.setFitWidth(100);
            imageview.setFitHeight(100);
            imageview.setImage(myImage);

            //present thumbnails in the tilePane
            tilePane.getChildren().add(imageview);

            //set gap between each thumbnails
            tilePane.setHgap(5);
            tilePane.setVgap(5);
        }
        }else{
            //Remind the user to upload image
            System.out.println("Please upload image");
        }
    }

    //Convert images to a selected format
    public void Convert(ActionEvent event) {
        //check if the user has selected a format
        if (format.getValue()!= null){
            for (int i = 0; i < pathList.size(); i++) {
                //convertImage is written and imported from convert.java,
                // so that we can keep the controller clean
                convertImage(pathList.get(i),nameList.get(i), format.getValue());
            }
            System.out.println("Successfully downloaded!");

            //clear the list for next input from FileChooser
            pathList.clear();
            nameList.clear();

        }else{

            //Remind the user that he should select a format before convert
            System.out.println("Please choose a format");
        }
    }

}





