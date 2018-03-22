public class Clock extends Meter {
    private double minInterval;

    //Default values 
    private final double DEFAULT_MIN_INTERVAL = 0.5;

    /**
     * Constructs a clock with default values 
     */
    public Clock() {
        super();
        setMinInterval(DEFAULT_MIN_INTERVAL);
    }

    /**
     * @param minInterval    - minimum interval this clock measures (sec)
     * @param identification - unique identifier for this device 
     * @param location       - where (typically in what shelf) may this be located
     * @param operational    - wether this device is working or not 
    */
    public Clock(double minInterval, String identification, String location, boolean operational) {
        super(identification, location, operational);
        setMinInterval(minInterval);
    }

    /**
     * returns a String with the objects current state 
    */
    @Override
    public String toString() {
        return "" + "\nmin interval: " + getMinInterval() + super.toString();
    }

    //set-methods
    public void setMinInterval(double minInterval) {
        this.minInterval = minInterval;
    }

    //get-methods
    public double getMinInterval() {
        return minInterval;
    }
}