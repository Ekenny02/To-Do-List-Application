package persistence;

import model.HomeworkList;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of HomeworkList to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    //**Method modeled after JSONSerializationDemo**
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    //**Method modeled after JSONSerializationDemo**
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of HomeworkList to file
    //**Method modeled after JSONSerializationDemo**
    public void write(HomeworkList hwl) {
        JSONObject json = hwl.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    //**Method modeled after JSONSerializationDemo**
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    //**Method modeled after JSONSerializationDemo**
    private void saveToFile(String json) {
        writer.print(json);
    }
}
