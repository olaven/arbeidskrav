public abstract class Meter 
{
    private String identification; 
    private String location; 
    private boolean operational; 
    
    //Default values
    private static final String DEFAULT_IDENTIFICATION = "unspecified"; 
    private static final String DEFAULT_LOCATION = "unspecified"; 
    private static final boolean DEFAULT_OPERATIONAL = false; 
    
    /**
     * Constructs a meter with default values 
     */
    public Meter()
    {
        this(DEFAULT_IDENTIFICATION, DEFAULT_LOCATION, DEFAULT_OPERATIONAL); 
    }
    /**
     * @param identification - unique identifier for this device 
     * @param location       - where (typically in what shelf) may this be located
     * @param operational    - wether this device is working or not 
    */
    public Meter(String identification, String location, boolean operational)
    {
        setIdentification(identification); 
        setLocation(location); 
        setOperational(operational); 
    }
    
    /**
     * Checks wether or not two objects are of the same class 
    */
    public boolean equals(Object o)
    {
        if(o == this)
            return true; 
        if(!(getClass() == o.getClass()))
            return false; 
        if(o.toString() == this.toString()) 
            return true; 
        return false; 
    }
    
    /**
     * Returns a String with the objects current state
    */
    public String toString()
    {
        return "" 
        + "\nidentification: " + getIdentification()
        + "\nlocation: " + getLocation()
        + "\noperational: " + (getOperational() ? "yes" : "no"); 
    } 
    
    //set-methods 
    public void setIdentification(String identification)
    {
        this.identification = identification; 
    }
    public void setLocation(String location)
    {
        this.location = location; 
    }
    public void setOperational(boolean operational)
    {
        this.operational = operational; 
    }
    
    //get-methods 
    public String getIdentification()
    {
        return identification; 
    }
    public String getLocation()
    {
        return location; 
    }
    public boolean getOperational()
    {
        return operational; 
    }
}