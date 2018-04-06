import java.util.HashMap;
import java.util.ArrayList; 
import java.util.Collection;

/**
 * Quizes
 * Handles multiple quizes. 
 * For speed, quizes are stored as HashMap, 
 * as list: 
 *  names  -> .getQuizNames() 
 *  quizes -> .getQuizes()
 */
public class QuizesManager {
    /*
        At some point, I want the user to make his/her own quizes.
        I am trying to keep this in mind when implementing

        For now, the program adds some default quizes. 
     */
    HashMap<String, Quiz> quizes;

    public QuizesManager() {
                            //name , quiz
        quizes = new HashMap<String, Quiz>(); 
        //adding default quizes  
        addDefaultQuizes(); 
    }
    
    /**
     * Adds some default quizes. 
     * I should find (and have planned to) find some more elegant ways to to this
     */
    private void addDefaultQuizes(){
        Quiz flagQuiz = new Quiz("Capital Quiz!"); 

        flagQuiz.add(new Question("Name the capital?", "Oslo", "file:../images/flags/norway.png")); 
        flagQuiz.add(new Question("Name the capital?", "Copenhagen", "file:../images/flags/denmark.png"));
        flagQuiz.add(new Question("Name the capital?", "Stockholm", "file:../images/flags/sweden.png"));
        flagQuiz.add(new Question("Name the capital?", "Haag", "file:../images/flags/netherlands.png"));

        quizes.put(flagQuiz.getTitle(), flagQuiz); 

        Quiz tk1100Quiz = new Quiz("tk110 Quiz!");

        tk1100Quiz.add(new Question("What does DNS stand for?", "dns", "file:../images/flags/netherlands.png"));

        quizes.put(tk1100Quiz.getTitle(), tk1100Quiz);
    }

    /**
     * Adds a quiz to quizes if name is not used
     * Returns false if true if added, false if not 
     * Expected that GUI handles this 
     * @param name name of the quiz 
     * @param quiz the quiz itself 
     */
    public boolean add(String name, Quiz quiz){
        if(quizes.containsKey(name))
            return false; 
        quizes.put(name, quiz);  
        return true; 
    }

    /**
     * Adds a quiz to quizes if name is not used
     * Returns false if true if added, false if not 
     * Expected that GUI handles this 
     * @param name name of the quiz 
     */
    public boolean remove(String name){
        if(!quizes.containsKey(name))
            return false; 
        quizes.remove(name); 
        return true; 
    }

    /**
     * Returns specified quiz. 
     * Returns null if quiz does not exis. 
     * Expected that GUI handles this 
     * @param name name of the quiz 
     */
    public Quiz getQuiz(String name){
        if(quizes.containsKey(name))
            return quizes.get(name); 
        return null;
    }

    /**
     * Returns quiz names as ArrayList
     * This will make it easy to iterate for displaying GUI etc. 
     */
    public ArrayList<String> getQuizNames(){
        //return HashMapToArrayList(quizes.keySet());
        return new ArrayList<String>(quizes.keySet()); 
    }

    /**
     * Returns quizes as ArrayList
     * This will make it easy to iterate for displaying GUI etc. 
     */
    public ArrayList<Quiz> getQuizes(){
        return new ArrayList<Quiz>(quizes.values()); 
    }
}