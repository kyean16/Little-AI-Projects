/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Keyean
 */
public class FXMLDocumentController implements Initializable {
    
    //Global Variables
    private Desktop desktop = Desktop.getDesktop();
    private int[][] map; //2D array
    private int numCities; //Cities
    private int maxGeneration; //Max Generation
    private int numSamples; //Max num of Samples per Generation
   // private TSPTour bestTour; //Best TPSTour
    File file;
    
    @FXML //fx:id = startButton
    private Button loadButton;
    
    @FXML //fx:id = startButton
    private Button startButton;
    
     @FXML //fx:id = labelArea
    private TextArea textArea;
     
    @FXML //fx:id = genSlider
    private Slider genSlider;
    
    @FXML //fx:id = genLabel
    private Label genLabel;
    
     @FXML //fx:id = sampleSlide
    private Slider sampleSlider;
    
    @FXML //fx:id = sampleLabel
    private Label sampleLabel;
    
    @FXML //fx:id = checkLabel;
    private Label checkLabel;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //GenSlider
        genSlider.setMin(1);
        genSlider.setMax(100);
        genSlider.setValue(40);
        genSlider.setShowTickLabels(true);
        genSlider.setShowTickMarks(true);
        genSlider.setMajorTickUnit(50);
        genSlider.setMinorTickCount(5);
        genSlider.setBlockIncrement(10);
        genSlider.valueProperty().addListener(( //Listener
            ObservableValue<? extends Number> ov, Number old_val, 
            Number new_val) -> {
               genLabel.setText("Number of Generation: " +(int)Math.round(genSlider.getValue()));
        });
        //GenLabel
        genLabel.setText("Number of Generation: " +(int)Math.round(genSlider.getValue()));
        
        sampleSlider.setMin(1);
        sampleSlider.setMax(100);
        sampleSlider.setValue(20);
        sampleSlider.setShowTickLabels(true);
        sampleSlider.setShowTickMarks(true);
        sampleSlider.setMajorTickUnit(50);
        sampleSlider.setMinorTickCount(5);
        sampleSlider.setBlockIncrement(10);
        sampleSlider.valueProperty().addListener(( //Listener
            ObservableValue<? extends Number> ov, Number old_val, 
            Number new_val) -> {
               sampleLabel.setText("Samples per Generation: " +(int)Math.round(sampleSlider.getValue()));
        });
        //GenLabel
        sampleLabel.setText("Samples per Generation: " +(int)Math.round(sampleSlider.getValue()));
        //To Move to Css
        checkLabel.setText("");
        /*
        .textArea {
            -fx-focus-color: transparent; 
        */
    }    
    
    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        fileChooser.setTitle("Open Resource File");
        Stage stage = new Stage();
        file = fileChooser.showOpenDialog(stage);
        TSPReader tspReader = new TSPReader(file);
        
        if(!tspReader.isValid()) //File not valid
        {
            checkLabel.setTextFill(Color.RED);
            checkLabel.setText("File cannot be read");
            file = null;
        }
        else //File is valid
        {
            checkLabel.setTextFill(Color.GREEN);
            checkLabel.setText("Sucessfully Loaded");
            numCities = tspReader.getNumCities(); //Sets Number of Cities
            map = tspReader.getMap(); //Sets the 2-D Map
        }
    }
    
    private static void configureFileChooser(
        final FileChooser fileChooser) {      
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
    }
    
     @FXML
    private void handleStartButtonAction(ActionEvent event) {
        if(file == null)
        {
            checkLabel.setTextFill(Color.RED);
            checkLabel.setText("Missing File");
        }
        else
        {
            maxGeneration = (int)Math.round(genSlider.getValue());
            numSamples = (int)Math.round(sampleSlider.getValue());
            textArea.setText("");
            checkLabel.setText("");
            BeginSearch();
        }
    }
    
       private void BeginSearch()
    {
 	TSP genetics = new TSP(map, numCities, maxGeneration ,numSamples, textArea);
         textArea.appendText("");
    }
}
