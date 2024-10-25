package persistence;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of favorite lists map to file
    public void write(HashMap<String, Favorites> favoriteListsMap) {
        JSONObject json = favoriteListsToJson(favoriteListsMap);
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: converts favorite lists map to JSON object representation
    private JSONObject favoriteListsToJson(HashMap<String, Favorites> favoriteListsMap) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Favorites> entry : favoriteListsMap.entrySet()) {
            json.put(entry.getKey(), entry.getValue().favoritesToJson());
        }
        return json;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

