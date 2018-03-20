package arbeidskrav; 

import junit.framework.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testAdd() {
        MeterArchive archive = new MeterArchive(); 
        Meter t1 = new Thermometer(20, 20, "SAMEID", "PLACEMENT1", true); 
        Meter t2 = new Thermometer(20, 20, "SAMEID", "PLACEMENT2", true);
        //adding the first meter -> does not exist 
        Assert.assertEquals(archive.add(t1), true);
        //adding a meter ID that already exists
        Assert.assertEquals(archive.add(t2), false);
    }

}
