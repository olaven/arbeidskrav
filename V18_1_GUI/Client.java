import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.layout.GridPane; 

import javafx.scene.control.TextField;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.text.Font; 
import javafx.event.ActionEvent;
import javafx.geometry.Insets;

import java.util.HashMap; 
/**
 * The Client gives a visual interface to the user 
 */
public class Client extends Application{

    MeterArchive archive = new MeterArchive(); 

    private int width = 600;
    private int height = 400;

    HashMap<String, Scene> scenes; 
    private Scene mainScene; 
    private Scene addScene; 

    TextField textInput; 

    public static void main(String[] args) {
        launch(args); 
    }
    
    public Client(){
        scenes = new HashMap<String, Scene>(); 
        mainScene = getMainScene(); 
        addScene = getAddScene(); 
        //add scenes to hashmap 
        scenes.put("mainScene", mainScene); 
        scenes.put("addScene", addScene); 
    }
    
    @Override
    public void start(Stage stage){

        //main scene is the first scene to boot 
        Scene scene = scenes.get("mainScene"); 
        stage.setScene(scene); 
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
     * Use scrollpane for displaying
     */
    public void displaymeters(ActionEvent event){

    }


    /**
     * Builds and returns main scene 
     */
    private Scene getMainScene(){        
        //labels
        Label heading = new Label("Meter Archive");
        heading.setMinWidth((width / 3) / 2);
        heading.setMinHeight((height / 3) / 2);  
        heading.setFont(new Font(height / 18)); 

        Label feedback = new Label("Feedback"); 
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

        return (new Scene(gridPane, getWidth(), getHeight())); 
    }
    
    /**
     * builds and returns the scene for adding 
     */
    public Scene getAddScene(){
        GridPane layout = new GridPane(); 

        TextField test = new TextField("Test, adding"); 
        layout.add(test, 0, 0); 

        return new Scene(layout, getWidth(), getHeight()); 
    }
    
    public int getHeight(){
        return height; 
    }
    public int getWidth(){
        return width; 
    }
    public void setHeight(int height){
        this.height = height; 
    }
    public void setWidth(int width){
        this.width = width; 
    }
}
