import java.io.File; 

/**
 * Question
 * Has registered data for a questoin and wether 
 * it is answered correctly or not. 
 * Answeres are stored in lowercase 
 */
public class Question {
    private String text; 
    private String answer; 
    private File image;
    private boolean correct; 

    //This is the constructor I intend to use 
    public Question(String text, String answer, File image) {
        this(text, answer, image, false);
    }
    //this constructor may come in handy at some point NOTE: remove if not
    public Question(String text, String answer, File image, boolean correct){
        setText(text); 
        setAnswer(answer.toLowerCase()); 
        setImage(image); 
        setCorrect(correct); //this option may come in handy. Remove if not 
    }

    //get 
    public String getText(){return text;}
    public String getAnswer(){return answer;}
    public File getImage(){return image;}
    public boolean getCorrect(){return correct;}

    //set
    public void setText(String text){
        if(text.length() > 0)
            this.text = text; 
    }
    public void setAnswer(String answer){
        if(answer.length() > 0)
            this.answer = answer; 
    }
    public void setImage(File image){
        this.image = image; 
    }
    public void setCorrect(boolean correct){
        this.correct = correct; 
    }
    
}