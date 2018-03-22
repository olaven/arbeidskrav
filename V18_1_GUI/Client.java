import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.layout.GridPane; 

import javafx.scene.control.TextField;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.TextField; 
import javafx.scene.text.Font; 
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
/**
 * The Client gives a visual interface to the user 
 */
public class Client extends Application{

    MeterArchive archive = new MeterArchive(); 

    TextField textInput; 

    @Override
    public void start(Stage stage){
        //REFACTOR IN FUTURE 
        int width = 600; 
        int height = 400; 
        //labels
        Label heading = new Label("Meter Archive");
        heading.setMinWidth((width / 3) / 2);
        heading.setMinHeight((height / 3) / 2);  
        heading.setFont(new Font(height / 18)); 

        Label feedback = new Label("test"); 
        feedback.setMinWidth(width / 3);
        feedback.setMinHeight(height / 3);
        feedback.setFont(new Font(height / 18));

        //buttons 
        Button addButton = new Button("Add Meter"); 
        addButton.setMinWidth(width/3); 
        addButton.setMinHeight(height / 3);
        addButton.setOnAction(this::addMeter);

        Button removeButton = new Button("Remove Button");
        removeButton.setMinWidth(width / 3);
        removeButton.setMinHeight(height / 3);
        removeButton.setOnAction(this::removeMeter);  

        Button displayButton = new Button("Display Meters");
        displayButton.setMinWidth(width / 3);
        displayButton.setMinHeight(height / 3); 
        displayButton.setOnAction(this::displaymeters); 

        //text input 
        textInput = new TextField("enter ID here"); 
        textInput.setMaxHeight(height / 6); 

        //Adding elements to gridPane
        GridPane gridPane = new GridPane(); 
        gridPane.add(heading      , 0, 0, 2, 1); 
        gridPane.add(feedback     , 2, 0, 1, 1); 
        gridPane.add(addButton    , 0, 1, 2, 1); 
        gridPane.add(removeButton , 2, 1, 2, 1); 
        gridPane.add(displayButton, 4, 1, 2, 1);
        gridPane.add(textInput    , 2, 2, 1, 1); 

        stage.setScene(new Scene(gridPane, width, height)); 
        stage.setTitle("Meter Archive"); 
        stage.show(); 
    }

    /**
     * Adds a meter to the archive 
     */
    public void addMeter(ActionEvent event){
        //textInput.getText(); 
    }

    /**
     * Removes a meter in archive 
     */
    public void removeMeter(ActionEvent event){

    }

    /**
     * Displays meters in archive
     */
    public void displaymeters(ActionEvent event){

    }
}