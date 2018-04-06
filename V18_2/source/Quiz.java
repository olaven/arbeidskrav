import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * Quiz
 * Handles logic of a quiz 
 * Presents questions in a serial way 
 */
public class Quiz {

    private String title;

    private ArrayList<Question> questions;
    //index of current question 
    private int currentQuestionIndex;

    /**
     * constructor 
     * @param title title of the quiz 
     */
    public Quiz(String title) {
        this(title, 0);
    }

    /**
    * constructor 
    * @param title title of the quiz 
    * @param currentQuestionIndex the index of first question (default 0)
    */
    public Quiz(String title, int currentQuestionIndex) {
        questions = new ArrayList<Question>();
        setTitle(title);
        setCurrentQuestionIndex(currentQuestionIndex);
    }

    /**
     * Validate answer, assign true or false to .getCorrect()
     * @param submittedAnswer the answer being handled 
     */
    public void handleAnswer(String submittedAnswer) {
        Question question = questions.get(getCurrentQuestionIndex());
        submittedAnswer = submittedAnswer.toLowerCase();
        String correctAnswer = question.getAnswer();

        if (submittedAnswer.equals(correctAnswer)) {
            question.setCorrect(true);
        } else {
            question.setCorrect(false);
        }
    }

    /**
     * Change question to the next one 
     * Returns true if more questions, false if not 
     */
    public boolean nextQuestion() {
        //if no exception, there is a new question 
        try {
            questions.get(getCurrentQuestionIndex() + 1);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        setCurrentQuestionIndex(getCurrentQuestionIndex() + 1);
        return true;
    }

    /**
     * Adds a question if not a duplicate text + answer combination
     * return true if successful, false if not
     * (could use .getQuestions.add(), but that is messy)
     * @param question the question to be added 
     */
    public boolean add(Question question){
        if(questions.contains(question))
            return false; 
        questions.add(question); 
        return true; 
    }

    //get 
    public String getTitle() {
        return title;
    }

    public Question getCurrentQuestion() {
        return questions.get(getCurrentQuestionIndex());
    }

    private int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getQuestionCount() {
        return questions.size();
    }

    public ArrayList<Question> getQuestions(){
        return questions; 
    }

    //set
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }
}