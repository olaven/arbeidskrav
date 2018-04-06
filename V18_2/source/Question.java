
/**
 * Question
 * Has registered data for a questoin and wether 
 * it is answered correctly or not. 
 * Answeres are stored in lowercase 
 */
public class Question {
    private String text;
    private String answer;
    private String imagePath;
    private boolean correct;

    /**
     * This is the constructor I intend to use 
     */
    public Question(String text, String answer, String imagePath) {
        this(text, answer, imagePath, false);
    }

    /**
     * this constructor may come in handy at some point (NOTE: Remove if not)
     */
    private Question(String text, String answer, String imagePath, boolean correct) {
        setText(text);
        setAnswer(answer.toLowerCase());
        setImagePath(imagePath);
        setCorrect(correct); //this option may come in handy. Remove if not 
    }

    //get 
    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean getCorrect() {
        return correct;
    }

    //set
    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

}