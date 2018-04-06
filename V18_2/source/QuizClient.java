import java.util.ArrayList; 

import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.event.ActionEvent;

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
    private String mainStyleSheetPath = "file:../stylesheets/main.css";

    //GUI-fields 
    private Label headerLabel;
    private ImageView imageView;
    private Label questionLabel;
    private TextField inputField;
    private Button submitButton;
    private Label statusField;

    private Button quitButton;
    private Button restartButton;

    //My-class-fields 
    private QuizesManager quizesManager; 
    private Quiz selectedQuiz; 

    //Inner-class-fields 
    SceneFactory sceneFactory; 

    //Scenes 
    Scene quizScene; 
    Scene chooseScene; 
    Scene endScene;

    public QuizClient() {
        //Non-GUI-fields 
        width = 750;
        height = 500;
        imageWidth = width / 3;
        imageHeight = height / 3;
        correctCounter = 0;

        //GUI-fields 
        headerLabel = new Label();
        imageView = new ImageView();
        questionLabel = new Label();
        inputField = new TextField();
        submitButton = new Button("submit");
        statusField = new Label();

        //My-class-fields 
        quizesManager = new QuizesManager(); 
        selectedQuiz = quizesManager.getQuiz("Capital Quiz!"); //Default quiz for now 

        //Inner-class-fields 
        sceneFactory = new SceneFactory(); 

        //Scenes 
        quizScene = sceneFactory.quizScene(); 
        chooseScene = sceneFactory.chooseScene(); 
        endScene = sceneFactory.endScene(); 
    }
    /**
     * Runs on start -> required by Application 
     */
    public void start(Stage stage) {
        stage.setScene(quizScene);
        stage.setTitle("Quiz!");
        stage.show();
    }

    /**
     * Updates data in relevant GUI-fields
     * -> image 
     * -> question
     * -> status 
     */
    public void updateDisplayedQuestion() {
        Question question = getCurrentQuestion();
        //NOT ADDED QUESTION TEXT
        imageView.setImage(new Image(question.getImagePath(), imageWidth, imageWidth, false, false));
        questionLabel.setText(question.getText());
        statusField.setText(getStatus());
    }

    

    /**
     * Deactivates "submit button" and opens a stage 
     * with endScene as scene 
     */
    private void displayEndOptions() {

        toggleButton(submitButton); 

        //create a new stage end set endScene as its scene 
        Stage stage = new Stage(); 
        stage.setTitle("Quiz over"); 
        stage.setScene(endScene); 
        stage.show(); 
    }

    /**
     * Toggles wether the specified button is toggled or not 
     */
    private void toggleButton(Button button){
        button.setDisable(!button.isDisabled()); 
    }

    //get 
    private Question getCurrentQuestion() {
        return selectedQuiz.getCurrentQuestion();
    }

    public int getCorrectCounter() {
        return correctCounter;
    }

    public String getStatus() {
        return (getCorrectCounter() + "/" + selectedQuiz.getQuestionCount()).toString();
    }

    //set
    public void setCorrectCounter(int correctCounter) {
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
            System.out.println("chooseScene not implemented yet!"); 
            return new Scene(new VBox()); //placeholder 
        }
        /**
         * Where questions are answered.
         */
        public Scene quizScene(){
            headerLabel.setFont(new Font(100));
            headerLabel.setText(selectedQuiz.getTitle());

            imageView.setImage(new Image(getCurrentQuestion().getImagePath(), imageWidth, imageHeight, false, false));
            
            questionLabel.setText(getCurrentQuestion().getText());

            inputField.setPromptText("enter answer");

            submitButton.setOnAction(this::buttonPress);
            submitButton.setDefaultButton(true);

            statusField.setText(getStatus());

            VBox vBox = new VBox();
            vBox.getChildren().addAll(headerLabel, imageView, questionLabel, inputField, submitButton, statusField);

            return new Scene(vBox, width, height);
        }
        /**
         * The menu where the user can choose different quizes.
         */
        public Scene endScene(){
            quitButton = new Button("Quit");
            quitButton.setOnAction(this::buttonPress);

            restartButton = new Button("restart");
            restartButton.setOnAction(this::buttonPress);

            HBox endLayout = new HBox();
            endLayout.getChildren().addAll(quitButton, restartButton);

            Scene scene = new Scene(endLayout, width / 4, height / 4);
            scene.getStylesheets().add(mainStyleSheetPath);

            return scene; 
        }

        /**
     * Handles events from buttonpresses
     * @param event the ActionEvent of the press 
     */
        public void buttonPress(ActionEvent event) {
            if (event.getSource().equals(submitButton)) {
                //check if the question is correct 
                String submittedAnswer = inputField.getText();
                selectedQuiz.handleAnswer(submittedAnswer);
                Question question = getCurrentQuestion();

                //check if the question turned out correct 
                if (question.getCorrect())
                    setCorrectCounter(getCorrectCounter() + 1);
                //if there is a new question
                if (selectedQuiz.nextQuestion()) {
                    updateDisplayedQuestion();
                } else {
                    displayEndOptions();                     
                }
            }
            if (event.getSource().equals(quitButton)) {
                Platform.exit();
            }
            if (event.getSource().equals(restartButton)) {
                selectedQuiz.setCurrentQuestionIndex(0);
                setCorrectCounter(0);
                toggleButton(submitButton); 
                start(new Stage());
            }
        }
    }
}