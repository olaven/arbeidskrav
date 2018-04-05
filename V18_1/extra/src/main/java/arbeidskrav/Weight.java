package arbeidskrav;

public class Weight extends Meter {
    private double minWeight;
    private double maxWeight;

    private static double DEFAULT_MINWEIGHT = 0.0;
    private static double DEFAULT_MAXWEIGHT = 200.0;

    /**
     * Constructs a weight with default values 
    */
    public Weight() {
        super();
        setMinWeight(DEFAULT_MINWEIGHT);
        setMaxWeight(DEFAULT_MAXWEIGHT);
    }

    /**
     * @param maxWeight      - maximum weight this weight can measure (gram)
     * @param minWeight      - minimum weight this weight can measure (gram)
     * @param identification - unique identifier for this device 
     * @param location       - where (typically in what shelf) may this be located
     * @param operational    - wether this device is working or not 
    */
    public Weight(double minWeight, double maxWeight, String identification, String location, boolean operational) {
        super(identification, location, operational);
        setMinWeight(minWeight);
        setMaxWeight(minWeight);
    }

    /**
     * returns a String with the objects current state 
    */
    @Override
    public String toString() {
        return "" + "\nmin weight: " + getMinWeight() + "\nmax weight: " + getMaxWeight() + super.toString();
    }

    //set-methods 
    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    //get-methods 
    public double getMinWeight() {
        return minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}