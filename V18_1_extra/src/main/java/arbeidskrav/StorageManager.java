package arbeidskrav;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * This class handles everything about storing data for this application
 * It provides methods for: 
 * -fetching all data - .fetchAll
 *  * -adding data       - .add
 * -removing data     - .remove
 * -alter data        - .alter  
 */
public class StorageManager {

    /**
     * Noteas about refactoring: 
     * .add, .remove and .alter are very similar
     * (private) .getAllData is ugly. Could probably be improved.
     */

    private String path;
    private String key;
    private File file;

    /**
     * default constructor 
     * @param path    - file you wish to manage 
     * @param jsonKey - key in JSON-file 
     */
    public StorageManager(String path, String jsonKey) {
        setPath(path);
        setJsonKey(jsonKey);
        setFile(new File(path));
    }

    /**
     * Returns objects from storage as ArrayList 
     */
    public ArrayList<Object> fetchAllData() {
        /*
            1. get all data from current archive 
            2. turn into array 
            3. add each item in array to allData
            4  return allData
        */
        ArrayList<Object> allData = new ArrayList<Object>();

        String data = getData();

        JSONArray jsonArray;

        try {
            jsonArray = (JSONArray) new JSONObject(data).get(getKey());
            for (int i = 0; i < jsonArray.length(); i++) {
                allData.add(jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allData;
    }

    /**
     * Add specified boject to data
     * @param object - Object to be removed
     */
    public void add(Object object) {//TODO: IMPLEMENT
        /*
            1. get all data from current archive as ArrayList
            2. add new object to ArrayList
            3 turn back to json-string 
            4.replace old content 
        */
        ArrayList<Object> list = fetchAllData();

        list.add(object);
        String jsonFromList = new Gson().toJson(list);
        replaceData("{\"" + getKey() + "\":" + jsonFromList + "}");
    }

    /**
     * Remove specified object from data
     * @param object - Object to be removed 
     */
    public void remove(Object object) {//TODO: IMPLEMENT
        /*
            1. get all data from current archive as ArrayList
            2. remove object from array 
            3. turn back to json-string 
            4.replace old content 
        */
        ArrayList<Object> list = fetchAllData();
        list.remove(object);
        String jsonFromList = new Gson().toJson(list);
        replaceData("{\"" + getKey() + "\":" + jsonFromList + "}");
    }

    /**
     * Replace object with a new one 
     * @param oldObject - Object to be replaced 
     * @param newObject - Object to replace
     */
    public void alter(Object oldObject, Object newObject) {//TODO: IMPLEMENT
        /*
            1. get all data from current archive 
            2. turn into array 
            3. remove old
            4. add new 
            5 turn back to json-string 
            6.replace old content 
        */
        ArrayList<Object> list = fetchAllData(); 
        list.remove(oldObject);  
        list.add(newObject); 
        String jsonFromList = new Gson().toJson(list);
        replaceData("{\"" + getKey() + "\":" + jsonFromList + "}");
    }

    /**
     * Gets all content from file
     * Returns null if failure 
     */
    private String getData() {
        String text = "";
        try {
            FileReader fr = new FileReader(getFile());
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                text += line;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (isValidJson(text))
            return text;
        else
            return "{'" + getKey() + "':[]}";
    }

    /**
     * Replaces content in file 
     * @param data - new data
     */
    private void replaceData(String data) {
        try {
            FileWriter fw = new FileWriter(getFile());
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns true if supplied string is valid JSON 
     * @param json String to be evaluated
     */
    private boolean isValidJson(String json) {
        try {
            JSONObject o = new JSONObject(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    //set-methods 
    private void setPath(String path) {
        this.path = path;
    }

    private void setJsonKey(String key) {
        this.key = key;
    }

    private void setFile(File file) {
        this.file = file;
    }

    //get-methods 
    private String getPath() {
        return path;
    }

    private String getKey() {
        return key;
    }

    private File getFile() {
        return file;
    }
}