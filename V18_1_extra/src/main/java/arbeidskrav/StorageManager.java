package arbeidskrav; 

import java.io.Reader; 
import java.io.BufferedReader; 
import java.io.Writer; 
import java.io.BufferedWriter; 
import java.io.IOException; 

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList; 

/**
 * This class handles everything about storing data for this application
 * It provides methods for: 
 * -fetching data - .fetch
 * -removing data - .remove
 * -adding data   - .add
 * -alter data    - .alter  
 */
public class StorageManager{
    private String path; 
    private String jsonKey; 

    /**
     * default constructor 
     * @param path    - file you wish to manage 
     * @param jsonKey - key in JSON-file 
     */
    public StorageManager(String path){
        setPath(path); 
    }

    /**
     * Remove specified object from data
     * Returns true if successful, false if not 
     * @param obj Object to be removed 
     */
    public boolean reamove 

    //set-methods 
    private void setPath(String path){
        this.path = path; 
    }
}