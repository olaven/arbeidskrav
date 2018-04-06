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

    public Quiz(String title){
        this(title, 0); 
    }
    public Quiz(String title, int currentQuestionIndex){
        questions = new ArrayList<Question>(); 
        setTitle(title); 
        setCurrentQuestionIndex(currentQuestionIndex); 
    }

    /**
     * Validate answer, assign true or false to .getCorrect()
     */
    public void handleAnswer(String submittedAnswer){
        Question question = questions.get(getCurrentQuestionIndex()); 
        submittedAnswer = submittedAnswer.toLowerCase(); 
        String correctAnswer = question.getAnswer();
        
        if(submittedAnswer.equals(correctAnswer)){
            question.setCorrect(true); 
        } else {
            question.setCorrect(false); 
        }
    }

    /**
     * Change question to the next one 
     * Returns true if mroe questions, false if not 
     */
    public boolean nextQuestion(){
        //if no exception, there is a new question 
        try {
            questions.get(getCurrentQuestionIndex() + 1); 
            System.out.println("there is a new question: " + getCurrentQuestionIndex()); 
        } catch (IndexOutOfBoundsException e) {
            System.out.println("no new question"); 
            return false; 
        }
        setCurrentQuestionIndex(getCurrentQuestionIndex() + 1); 
        return true;  
    }
    
    /**
     * Adds questions to the list 
     * Could be replaed with reading from a 
     * file at some point 
     */
    public void addTestQuestions(){
        questions.add(new Question("Name the capital?", "Oslo", "images/norway.png")); 
        questions.add(new Question("Name the capital?", "Copenhagen", "images/denmark.png"));
        questions.add(new Question("Name the capital?", "Stockholm", "images/sweden.png"));
        questions.add(new Question("Name the capital?", "Haag", "images/netherlands.png"));
    }
    
    //get 
    public String getTitle(){
        return title; 
    }
    public Question getCurrentQuestion(){
        return questions.get(getCurrentQuestionIndex()); 
    }
    private int getCurrentQuestionIndex(){
        return currentQuestionIndex; 
    }
    public int getQuestionCount() {
        return questions.size();
    }
    //set
    public void setTitle(String title){
        this.title = title; 
    }
    public void setCurrentQuestionIndex(int currentQuestionIndex){
        this.currentQuestionIndex = currentQuestionIndex; 
    }
}