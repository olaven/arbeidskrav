import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1: run demo: " + "\n2: exit");
            //get input 
            int input = scanner.nextInt();
            if (input == 1) {
                client.demo(); 
                break; 
            }
            else System.out.println("Not valid input :/"); 
        }
    }

    MeterArchive archive;

    public Client() {
        archive = new MeterArchive();
    }

    /**
     * Run a demo of the assignment 
     * NOTE: This is not an elegant implementation, but 
     * then again, that is not really the point of this method. 
     */
    public void demo() {
        System.out.println("Hi^^ Lets get this demo started!"); 
        System.out.println("filling with demo data.. "); 
        populate();
        sleep(1000); 
        System.out.println("In 4 seconds, I will print all data!"); 
        sleep(4000); 
        archive.print();    
        sleep(3000); 
        System.out.println(); 
        System.out.println("TID123 is located at H125.\nI want to move it.."); 
        sleep(2500); 
        System.out.println("Moving TID132..");
        sleep(100);  
        if(archive.move("TID132", "H126")){
            System.out.println("Moved!");//will not run
        } else {
            System.out.println("This did not work.."); 
            sleep(1000); 
            System.out.println("Oh my, a typo! TID123! not TID132.."); 
            sleep(1000); 
            System.out.println("Let's try again, shall we?"); 
            sleep(3500); 
        }
        if(archive.move("TID123", "H126")){
            System.out.println("We moved it this time!"); 
            sleep(1500); 
            System.out.println("Lets look at it\n"); 
            sleep(1500); 
            Meter movedMeter = archive.fetch("TID123"); 
            if(movedMeter != null) System.out.println(movedMeter.toString()); 
            else System.out.println("This was not supposed to happen..");//should not run 
        }
        sleep(2000); 
        System.out.println("Now, let's suppose this item stopped working.."); 
        sleep(2000); 
        System.out.println("Let's change the operational-field");
        sleep(2000); 
        boolean meterAltered = archive.alterOperational("TID123");  
        if(meterAltered) System.out.println(archive.fetch("TID123").toString()); 
        else System.out.println("This was not supposed tot happen.."); 
        sleep(2000); 
        System.out.println("It was now changed using .alterOperational. Do you see it?"); 
        sleep(4000); 
        System.out.println("\nOkay, our caretaker did not manage to repair this meter."); 
        sleep(2000); 
        System.out.println("Lets remove TID132"); 
        sleep(2000); 
        if (!archive.remove("TID132")) System.out.println("This did not work.. Oh, the same typo!");
        sleep(1000); 
        if(archive.remove("TID123")) System.out.println("It worked now!"); 
        sleep(2000); 
        System.out.println("Okay, let's look at all the registered meters"); 
        sleep(2500); 
        archive.print(); 
        sleep(2500); 
        System.out.println("As you can see, the element is now gone."); 
        sleep(3000); 
        System.out.println("We got a new one, sincethe old one stopped working."); 
        sleep(2000);
        System.out.println(
            "\n\n\n\nI want to add the following: \n"
            +"max temperature: 20\n"
            +"min temperature: -25\n"
            +"identification: TID123 (same as old one)\n"
            +"location: H126 (same as old one)\n"
            +"operational: yes (hopefully..)"
        ); 
        sleep(4000); 
        archive.add(new Thermometer(20, -25, "TID123", "H126", true)); 
        System.out.println("Let's see if that worked."); 
        sleep(3000); 
        archive.print(); 
        sleep(6000); 
        System.out.println("See! It is added!"); 
    }

    /**
     * populates the archive with some mock values 
     */
    private void populate() {
        archive.add(new Weight(0.01, 20.0, "WID123", "H123", true));
        archive.add(new Clock(.25, "CID123", "H124", true));
        archive.add(new Thermometer(-20.5, 34.0, "TID123", "H125", true));
    }

    /**
     * makes the program pause. Used for demo
     * @param milliseconds - the pause duration in milliseconds
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}