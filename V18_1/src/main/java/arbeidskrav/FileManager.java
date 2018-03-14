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
 */
public class FileManager {
    /**
     * returns content from a file 
     * @param path file location 
     */
    public String getContent(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fileReader);
        String text = "";
        String line = reader.readLine();
        while (line != null) {
            text += line;
            line = reader.readLine();
        }
        reader.close();
        return text;
    }

    /**
     * replces content of file 
     * @param path location of file 
     * @param content content to replace with 
     */
    public void replaceContent(String path, String content) throws IOException {
        FileWriter fileWriter = null; 
        fileWriter = new FileWriter(path);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close();
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