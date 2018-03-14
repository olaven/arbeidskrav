package arbeidskrav;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException; 
/**
 * this class provides a simple interface for 
 * reading from- and writing to files.
 * NOTE: Exceptions are handled in this class. Not sure if that is the 
 * best way to do it, but it seems more logical to me, as using the 
 * class wil become less of a hassle for the end user.  
 */
public class FileManager {
    /**
     * returns content from a file 
     * @param path file location 
     */
    public String getContent(String path){
        FileReader fileReader = null; 
        String text = ""; 
        try {
            fileReader = new FileReader(path); 
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                text += line;
                line = reader.readLine();
            }
            reader.close(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return text; 
    }

    /**
     * replces content of file 
     * @param path location of file 
     * @param content content to replace with 
     */
    public void replaceContent(String path, String content) {
        FileWriter fileWriter = null; 
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        try {
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns true if file exists 
     * @param path location of file 
     */
    public boolean fileExists(String path) {
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {
            return true;
        }
        return false;
    }
}