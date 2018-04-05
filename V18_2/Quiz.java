import java.util.ArrayList;
import java.io.File;  
/**
 * Quiz
 * Handles logic of a quiz 
 * Presents questions in a serial way 
 */
public class Quiz {
    ArrayList<Question> questions; 
    //index of current question 
    private int currentQuestionIndex; 

    public Quiz(){
        this(0); 
    }
    /**
     * @param qurrentQuestion 0 if not specified 
     */
    public Quiz(int currentQuestionIndex){
        setCurrentQuestionIndex(currentQuestionIndex);
        questions = new ArrayList<Question>();  
        addTestQuestions();
    }

    /**
     * Submit answer 
     * Continue if correct and move to next question
     */
    public void submitAnswer(String answer){
        //variables make the code more readable 
        Question question = getCurrentQuestion(); 
        String correct = question.getAnswer(); 
        answer = answer.toLowerCase(); 

        if(answer == correct){
            question.setCorrect(true);
        } else{
            question.setCorrect(false); 
        }
        nextQuestion(); 
    }
    /**
     * Change current question to next in the list 
     */
    public void nextQuestion(){
        //NOT IMPLEMENTED 
    }
    /**
     * Adds questions to the list 
     * Could be replaed with reading from a 
     * file at some point 
     */
    public void addTestQuestions(){
        questions.add(new Question("What country is this?", "Norway", new File("./images/norge.png"))); 
        questions.add(new Question("Which country is this?", "Denmark", new File("./images/danmark.png")));
        questions.add(new Question("Which country is this?", "Sweden", new File("./images/sverige.png")));
        questions.add(new Question("Which country is this??", "Netherlands", new File("./images/nederland.png")));
    }

    //get
    //only Question iteself is relevant to the client  
    private int getCurrentQuestionIndex(){
        return currentQuestionIndex; 
    }
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }
    //set 
    private void setCurrentQuestionIndex(int currentQuestion){
        if(currentQuestion > 0 /*og innenfor antall registrerte spørsmål - 1*/){
            this.currentQuestionIndex = currentQuestion; 
        } 
    }
    
}