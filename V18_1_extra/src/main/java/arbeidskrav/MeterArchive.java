package arbeidskrav;

import java.util.ArrayList;

public class MeterArchive {

    private ArrayList<Meter> meters;
    private FileManager fileManager = new FileManager();
    private JsonManager jsonManager = new JsonManager();

    private String PATH_TO_ARCHIVE = "./src/main/java/arbeidskrav/data/meterArchive.json";
    private String ARCHIVE_METER_KEY = "meters"; 

    public MeterArchive() {
        meters = getMeters();
    }

    /**
     * Adds a new meter to the archive
     * Returns true if successfull, false if not  
     * @param meter - the meter to be added 
     */
    public boolean add(Meter meter) {
        //TODO: if content is valid JSON, add a new Meter. If not, replace content with valid json of Meter 
        if (fetch(meter.getIdentification()) == null) {
            meters.add(meter);
            return true;
        }
        return false;
    }

    /**
     * Removes first occurence of a meter based on its identification
     * Returns true if removed, false if not found 
     * @param identification - identification of the string to be removed 
     */
    public boolean remove(String identification) {
        Meter meter = fetch(identification);
        if (meter == null)
            return false;
        meters.remove(meter);
        /**
         * IMPLEMENTING: use JSON file 
         */
        String without = jsonManager.removeFromJsonArray(meter, fileManager.getContent(PATH_TO_ARCHIVE), ARCHIVE_METER_KEY); 
        fileManager.replaceContent(PATH_TO_ARCHIVE, without); 
        return true;
    }

    /**
     * Returns specified meter, if in the archive 
     */
    public Meter fetch(String identification) {
        for (Meter meter : getMeters()) {
            if (meter.getIdentification() == identification) {
                return meter;
            }
        }
        return null;
    }

    /**
     * Modifies the meters location 
     * Returns true if altered, false if not found 
     * @param identification - identification of meter to be altered 
     * @param newLocation    - new location of the meter 
     */
    public boolean move(String identification, String newLocation) {
        Meter meter = fetch(identification);
        if (meter == null)
            return false;
        //the meter was fetched, starting to alter 
        meter.setLocation(newLocation);
        return true;
    }

    /**
     * Modifies the meters operational status 
     * Returns true if altered, false if not found 
     * @param identification - identification of meter to be altered  
     */
    public boolean alterOperational(String identification) {
        Meter meter = fetch(identification);
        if (meter == null)
            return false;
        //meter.setOperational(!meter.getOperational());//mer logisk å endre?
        meter.setOperational(false);
        return true;
    }

    /**
     * Lists all registered items. 
     * Not explicitly part of the task, but 
     * handy for testing and visualizing 
     */
    public void print() {
        for (Meter meter : meters) {
            System.out.println(meter.toString());
        }
    }

    /**
     * returns true if the archive has valid JSON in it
     */
    public boolean validArchive() {
        return jsonManager.isValidJson(fileManager.getContent(PATH_TO_ARCHIVE));
    }

    /**
     * returns the meters registered as 
     * objects in an ArrayList 
     */
    private ArrayList<Meter> getMeters() {
        String archive = fileManager.getContent(PATH_TO_ARCHIVE); 
        ArrayList<Object> objectList = jsonManager.getFromJsonArray(archive, ARCHIVE_METER_KEY); 
        ArrayList<Meter> meterList = new ArrayList<Meter>(); 
        
        try {
            for(Object meter : objectList){
                meterList.add((Meter) meter); 
            }
        } catch (Exception e) {
            System.out.println(
                "Somehow, something other than meters have been stored"
                +"\nMeters is emptied of the non-meter content"
            ); 
            return new ArrayList<Meter>(); 
        }

        return meterList; 
    }
}
