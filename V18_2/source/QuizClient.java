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
 * Displays an answerable quiz to the user 
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

    //Local-class-fields 
    private QuizesManager quizesManager; 
    private Quiz selectedQuiz; 

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

        //Local-class-fields 
        quizesManager = new QuizesManager(); 
        selectedQuiz = quizesManager.getQuiz("Capital Quiz!"); //Default quiz for now 
    }
    /**
     * Runs on start -> required by Application 
     */
    public void start(Stage stage) {

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

        Scene scene = new Scene(vBox, width, height);
        scene.getStylesheets().add(mainStyleSheetPath);
        stage.setScene(scene);
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
            submitButton.setDisable(false);
            start(new Stage());
        }
    }

    /**
     * Deactivates "submit button"
     * Displays a new window to the user -> offers restarting the game 
     */
    private void displayEndOptions() {

        submitButton.setDisable(true);

        quitButton = new Button("Quit");
        quitButton.setOnAction(this::buttonPress);

        restartButton = new Button("restart");
        restartButton.setOnAction(this::buttonPress);

        HBox endLayout = new HBox();
        endLayout.getChildren().addAll(quitButton, restartButton);

        Scene endScene = new Scene(endLayout, width / 4, height / 4);
        endScene.getStylesheets().add(mainStyleSheetPath);

        Stage endStage = new Stage();
        endStage.setTitle("End of quiz");
        endStage.setScene(endScene);
        endStage.show();
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
}