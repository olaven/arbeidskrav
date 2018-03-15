package arbeidskrav;

import java.util.ArrayList;

/**
 * WRITE SOMETHING HERE (and in basic)
 */
public class MeterArchive {
    /**
     * Notes on implementing StorageManager 
     * 
     * StorageManager does not care for permissions 
     * -> it adds everything and removed everything, if 
     * asked to. Specific rule have to be handled 
     * in this class. 
     * 
     * Meters are no longer stored in this class, but 
     * in the file. THus, methods in StorageManager 
     * should be used for accessing them. 
     */

    private final String PATH_TO_ARCHIVE = "./src/main/java/arbeidskrav/data/meterArchive.json"; 
    private final String JSON_KEY = "meters"; 
    private StorageManager storageManager; 

    public MeterArchive() {
        storageManager = new StorageManager(PATH_TO_ARCHIVE, JSON_KEY);
    }

    /**
     * Adds a new meter to the archive
     * Returns true if successfull, false if not  
     * @param meter - the meter to be added 
     */
    public boolean add(Meter meter) {
        if (fetch(meter.getIdentification()) == null) {
            storageManager.add(meter);
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
        storageManager.remove(meter);
        return true;
    }

    /**
     * Returns specified meter, if in the archive 
     */
    public Meter fetch(String identification) {
        ArrayList<Meter> meters = castArrayListToMeters(storageManager.fetchAllData()); 

        for (Meter meter : meters) {
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
        ArrayList<Meter> meters = castArrayListToMeters(storageManager.fetchAllData()); 
        for (Meter meter : meters) {
            System.out.println(meter.toString());
        }
    }
    /**
     * I can do this because only meters are added in this applciation 
     * (hopefully)
     */
    private ArrayList<Meter> castArrayListToMeters(ArrayList<Object> list){
        for(Object o : list){
            o = storageManager.convertJSONObjectToObject(o, Meter.class); 
            o.getClass();
        }
        return new ArrayList<Meter>();
        //return (ArrayList<Meter>)(ArrayList<?>) list; 
    }
}
