package arbeidskrav;

import java.util.ArrayList;

public class MeterArchive {

    private ArrayList<Meter> meters;

    public MeterArchive() {
        meters = new ArrayList<Meter>();
    }

    /**
     * Adds a new meter to the archive
     * Returns true if successfull, false if not  
     * @param meter - the meter to be added 
     */
    public boolean add(Meter meter) {
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
        /*
        for (Meter meter : meters) {
            if (meter.getIdentification() == identification) {
                meters.remove(meter);
                return true;
            }
        }
        return false;
        */

        Meter meter = fetch(identification); 
        if (meter == null) return false; 
        meters.remove(meter); 
        return true; 
    }

    /**
     * Returns specified meter, if in the archive 
     */
    public Meter fetch(String identification) {
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
        for (Meter meter : meters) {
            System.out.println(meter.toString());
        }
    }
}
