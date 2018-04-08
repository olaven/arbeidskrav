import java.util.ArrayList; 

import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.Pane; 
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView; 
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent; 

import javafx.geometry.Insets; 
import javax.management.openmbean.OpenDataException;

/**
 * QuizClient
 * Displays an answerable quiz to the user.
 * 
 * I have chosen to have all fields under QuizClient even if they 
 * are only used by inner classes. The idea behind this is that I 
 * would rather have everything similar grouped together than having 
 * some GUI-elements that group nicely together being spread all over 
 * the place. 
 * 
 * I am considering ot split this into two classes, as it is becoming very big 
 * */
public class QuizClient extends Application {
    //Non-GUI-fields 
    private int width;
    private int height;
    private int imageWidth;
    private int imageHeight;
    private int correctCounter;
    private ArrayList<Stage> openStages;
    private String mainStyleSheetPath = "file:../stylesheets/main.css";

    //GUI-fields 
    //-chooseScene 
    Label chooseHeader; 
    ListView<Label> quizNamesView;
    //-quizScene 
    private Label headerLabel;
    private ImageView imageView;
    private Label questionLabel;
    private TextField inputField;
    private Button submitButton;
    private Label statusField;
    //-endScene
    private Button quitButton;
    private Button restartButton;
    

    //My-class-fields 
    private QuizesManager quizesManager; 
    private Quiz selectedQuiz; 

    //Inner-class-fields 
    SceneFactory sceneFactory; 



    public QuizClient() {
        //Non-GUI-fields 
        width = 750;
        height = 500;
        imageWidth = width / 3;
        imageHeight = height / 3;
        correctCounter = 0;
        openStages = new ArrayList<Stage>(); 

        //GUI-fields 
        //-chooseScene 
        chooseHeader = new Label("Choose Quiz"); 
        quizNamesView = new ListView<Label>();

        //-quizScene 
        headerLabel = new Label();
        imageView = new ImageView();
        questionLabel = new Label();
        inputField = new TextField();
        submitButton = new Button("submit");
        statusField = new Label();
        //-endScene 
        quitButton = new Button("Quit");
        restartButton = new Button("Restart"); 

        //My-class-fields 
        quizesManager = new QuizesManager(); 
        selectedQuiz = quizesManager.getQuiz("Capital Quiz!"); //Default quiz for now 

        //Inner-class-fields 
        sceneFactory = new SceneFactory(); 

    }
    /**
     * Runs on start -> required by Application 
     */
    public void start(Stage stage) {
        displayChooseScene(); 
    }

    /**
     * Updates data in relevant GUI-fields
     * -> image 
     * -> question
     */
    public void updateDisplayedQuestion() {
        Question question = getCurrentQuestion();
        //NOT ADDED QUESTION TEXT
        imageView.setImage(new Image(question.getImagePath(), imageWidth, imageWidth, false, false));
        questionLabel.setText(question.getText());
    }

    /**
     * Updates status (x/total)
     * @param correct positive or negative feedback 
     */
    public void updateDisplayedStatus(boolean correct){
        String color = (correct ? "green" : "red"); 
        statusField.setStyle("-fx-text-fill: " + color + ";");
        statusField.setText(getStatus()); 
    }

    /**
     * 
     */
    private void displayChooseScene() {
        displayScene(sceneFactory.chooseScene(), "Choose scene."); 
    }
    
    /**
     * 
     */
    private void displayQuizScene() {

        if(submitButton.isDisabled())
            toggleButton(submitButton);  

        displayScene(sceneFactory.quizScene(), "Quiz!"); 
    }

    /**
     * Deactivates "submit button" and opens a stage 
     * with endScene as scene 
     */
    private void displayEndScene() {

        toggleButton(submitButton); 
        displayScene(sceneFactory.endScene(), "Quiz over."); 
    }

    /**
     * Displays specified scene in stage, with specified title. 
     * Container method -> reused in .display*SPECIFICSCENE*() above 
     */
    private void displayScene(Scene scene, String title){
        Stage stage = new Stage(); 
        stage.setScene(scene); 
        stage.setTitle(title); 
        stage.show(); 

        openStages.add(stage); 
    }

    /**
     * Closes all stages that are open
     * -> contained in openStages 
     */
    public void closeAllOpenStages(){
        for(Stage stage : getOpenStages()){
            stage.close(); 
        }
    }

    /**
     * Toggles wether the specified button is toggled or not 
     */
    private void toggleButton(Button button){
        button.setDisable(!button.isDisabled()); 
    }

    //get 
    public Quiz getSelectedQuiz(){
        return selectedQuiz;
    }

    private Question getCurrentQuestion() {
        return selectedQuiz.getCurrentQuestion();
    }

    private int getCorrectCounter() {
        return correctCounter;
    }

    private String getStatus() {
        return (getCorrectCounter() + "/" + selectedQuiz.getQuestionCount()).toString();
    }

    private ArrayList<Stage> getOpenStages(){
        return openStages; 
    }

    //set
    private void setSelectedQuiz(Quiz selectedQuiz){
        this.selectedQuiz = selectedQuiz; 
    }

    private void setCorrectCounter(int correctCounter) {
        if (correctCounter >= 0)
            this.correctCounter = correctCounter;
    }

    /**
     * Inner Class for building scenes. 
     * I am not sure if this is the best way to structure this, but 
     * it seems like a reasonably tidy solution for now. 
     */
    private class SceneFactory{
        /**
         * Where the user can choose different quizes.
         */
        public Scene chooseScene(){
            ArrayList<String> quizNames = quizesManager.getQuizNames(); 
            VBox vBox = new VBox(); 

            for(String quizName : quizNames){
                /* 
                    Fix to avoid duplicates on reload. 
                    Not elegant or efficient. Should get back to this. 
                */
                boolean alreadyAdded = false; 
                for(Label label : quizNamesView.getItems()){
                    if(label.getText().equals(quizName))
                        alreadyAdded = true; 
                }
                if(!alreadyAdded){
                    Label label = new Label(quizName); 
                    label.setOnMouseClicked(this::labelPressed); 
                    quizNamesView.getItems().add(label);
                }
            }

            vBox.getChildren().addAll(chooseHeader, quizNamesView); 

            return getBasicScene(vBox, width / 4, height / 4); 

        }
        /**
         * Where questions are answered.
         */
        public Scene quizScene(){
            headerLabel.setFont(new Font(50));
            headerLabel.setText(getSelectedQuiz().getTitle());
            headerLabel.setMinWidth(width / 2); 

            imageView.setImage(new Image(getCurrentQuestion().getImagePath(), imageWidth, imageHeight, false, false));
            
            questionLabel.setText(getCurrentQuestion().getText());
            questionLabel.setFont(new Font(25)); 
            questionLabel.setMinWidth(width / 3); 

            inputField.setPromptText("enter answer");

            submitButton.setOnAction(this::buttonPress);
            submitButton.setDefaultButton(true);

            statusField.setText(getStatus());
            statusField.setFont(new Font(30)); 

            GridPane gridPane = new GridPane(); 
            gridPane.setPadding(new Insets(0, width / 10, 0, width / 10)); 
            gridPane.add(headerLabel, 0, 0, 4, 1);
            gridPane.add(imageView, 0, 1, 4, 1);
            gridPane.add(questionLabel, 0, 2, 2, 1);  
            gridPane.add(inputField, 0, 3, 3, 1); 
            gridPane.add(submitButton, 3, 3, 1, 1); 
            gridPane.add(statusField, 0, 4); 


            return getBasicScene(gridPane, width, height); 

        }
        /**
         * The menu where the user can choose different quizes.
         */
        public Scene endScene(){
            quitButton.setOnAction(this::buttonPress); 
            restartButton.setOnAction(this::buttonPress);

            HBox endLayout = new HBox();
            endLayout.getChildren().addAll(quitButton, restartButton);

            return getBasicScene(endLayout, width / 4, height / 4); 
        }

        /**
         * Builds a basic scene and returns it 
         * Cleans up some of the repeated code above.
         */
        private Scene getBasicScene(Pane layout, int widht, int height){
            Scene scene = new Scene(layout, widht, height); 
            scene.getStylesheets().add(mainStyleSheetPath); 
            return scene; 
        }

        //------------------------------------------------------------
        //EVENT HANDLING 
        //------------------------------------------------------------

        /**
         * Handles events from buttonpresses
         * @param event the ActionEvent of the press 
         */
        public void buttonPress(ActionEvent event) {
            Quiz quiz = getSelectedQuiz(); 
            if (event.getSource().equals(submitButton)) {
                //check if the question is correct 
                String submittedAnswer = inputField.getText();
                inputField.clear(); 
                quiz.handleAnswer(submittedAnswer);
                Question question = getCurrentQuestion();

                //check if the question turned out correct 
                if (question.getCorrect()){
                    setCorrectCounter(getCorrectCounter() + 1);
                    updateDisplayedStatus(true);
                } else {
                    updateDisplayedStatus(false); 
                }

                //if there is a new question
                if (quiz.nextQuestion()) {
                    updateDisplayedQuestion();
                } else {
                    displayEndScene();                     
                }
            }
            if (event.getSource().equals(quitButton)) {
                Platform.exit();
            }
            if (event.getSource().equals(restartButton)) {
                closeAllOpenStages(); 
                quiz.setCurrentQuestionIndex(0);
                setCorrectCounter(0);
                toggleButton(submitButton); 
                start(new Stage());
            }
        }

         /**
         * Handles events from clicks on labels 
         * @param event the ActionEvent of the press 
         */
        public void labelPressed(MouseEvent event){
            //NOTE: Assumes that all presses are from quizNamesView, for now. 

            Label source = (Label) event.getSource(); 
            String quizName = source.getText(); 
            Quiz quiz = quizesManager.getQuiz(quizName); 

            if(quiz != null){
                setSelectedQuiz(quiz); 
                displayQuizScene(); 
            }
        }
    }
}