package persistence;

import java.io.*;
import java.util.HashMap;
import org.json.JSONObject;
import model.Favorites;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a writer that writes JSON representation of Favorites list to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {

    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of favorite lists map to file
    public void write(HashMap<String, Favorites> favoriteListsMap) {
    }

    // EFFECTS: converts favorite lists to JSON object representation
    private JSONObject favoriteListsToJson(HashMap<String, Favorites> favoriteListsMap) {
        JSONObject json = new JSONObject();
        return json;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }
}

