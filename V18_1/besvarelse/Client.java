

public class Client
{
  MeterArchive archive;
  
  public Client()
  {
      archive = new MeterArchive(); 
      mainMethod(); 
  }
  
  public void mainMethod()
  {
      populate(); 
      archive.print(); 
      sleep(10000); 
      System.out.println("after"); 
  }
  /**
   * populates the archive with some mock values 
   */
  private void populate()
  {
      archive.add(new Weight(0.01, 20.0, "WID123", "H123", true));
      archive.add(new Clock(.25, "CID123", "H124", true));
      archive.add(new Thermometer(-20.5, 34.0, "TID123", "H125", true));
  }
  /**
   * makes the program pause. Used for demo
   * @param milliseconds - the pause duration in milliseconds
   */
  private void sleep(int milliseconds)
  {
      try        
      {
          Thread.sleep(milliseconds);
      } 
      catch(InterruptedException ex) 
      {
          Thread.currentThread().interrupt();
      }
  }
}