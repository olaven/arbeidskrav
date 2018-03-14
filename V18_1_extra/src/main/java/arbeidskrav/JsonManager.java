package arbeidskrav;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

/**
 * This class handles JSON-related methods. 
 * Is application specific for now, and may not be 
 * generally applicable. 
 */
public class JsonManager {
    /**
     * Check if String is valid Json
     * @param string - the String to be checked 
     */
    public boolean isValidJson(String string) {
        try {
            JSONObject obj = new JSONObject(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * Turns a java object to a json string and returns it 
     * @param object - the object to JSON/Stringify  
     */
    public String objectToJson(Object object) {
        JSONObject jsonObject = new JSONObject(object);
        String jsonString = jsonObject.toString();
        return jsonString;
    }
    /**
     * Adds object in array under specified key TODO: NOT DONE!
     */
    public void addUnderKey(Object obj, String key){
        //key exists: 
        
    }
    /**
     * Returns an ArrayList of all objects in a JSON
     * Returns Object, so remember to cast back to 
     * appropriate type 
     * @param json - the json String to pull from 
     * @param key  - the specifying json key 
     */
    public ArrayList<Object> getFromJsonArray(String json, String key) {
        if (isValidJson(json))
            //get a jsonObject 
            try {
                ArrayList<Object> arrayList = new ArrayList<Object>();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = (JSONArray) jsonObject.get(key); //exception has already been thrown if this is not true 
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(jsonArray.get(i));
                }
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        else
            return null;
    }
    /**
     * removed an object from a Json Array with specified key 
     * Returns String without the object
     * @param object - the object to be removed 
     * @param json   - the json to remove from 
     * @param key    - the key of the object to be removed 
     */
    public String removeFromJsonArray(Object object, String json, String key){
        //get all from key as ArrayList. remove wished. set key to new list 
        ArrayList<Object> objects = getFromJsonArray(json, key); 
        for(Object obj : objects){
            if(obj.equals(object)){
                objects.remove(obj); 
            }
        }
        JSONObject jsonObject = new JSONObject(objects); 
        System.out.println(jsonObject.toString()); 
        return jsonObject.toString();
    }
}