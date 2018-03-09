/**
 * This class defines a thermomenter. 
 * Extends Meter. 
 */
public class Thermometer extends Meter 
{
    private double minTemp; 
    private double maxTemp; 
    
    //Default values
    private static final double DEFAULT_MINTEMP = -25.0; 
    private static final double DEFAULT_MAXTEMP = 45.0; 
    
    
    /**
     * Constructs a thermometer with default values
     */
    public Thermometer()
    {
        super(); 
        setMinTemp(DEFAULT_MINTEMP); 
        setMaxTemp(DEFAULT_MAXTEMP);  
    }
    
    /**
     * @param maxTemp        - maximum temperature this thermometer can measure (c)
     * @param minTemp        - minimum temperature this thermometer can measure (c) 
     * @param identification - unique identifier for this device 
     * @param location       - where (typically in what shelf) may this be located
     * @param operational    - wether this device is working or not 
    */
    public Thermometer(double minTemp, double maxTemp, String identification, String location, boolean operational)
    {
        super(identification, location, operational); 
        setMinTemp(minTemp); 
        setMaxTemp(maxTemp); 
    }
    
    /**
     * returns a String with the objects current state 
    */
    @Override 
    public String toString()
    {
        return "" 
        + "\nmin temp: " + getMinTemp()
        + "\nmax temp: " + getMaxTemp()
        + super.toString(); 
    }
    //set-methods 
    public void setMinTemp(double minTemp)
    {
        this.minTemp = minTemp; 
    }
    public void setMaxTemp(double maxTemp)
    {
        this.maxTemp = maxTemp; 
    }
    
    //getMethods
    public double getMinTemp()
    {
        return minTemp; 
    }
    public double getMaxTemp()
    {
        return maxTemp; 
    }
}