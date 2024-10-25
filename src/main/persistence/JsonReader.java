package persistence;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Country;
import model.Favorites;
import model.Location;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// represents a reader that reads Favorites from JSON data stored in file 
public class JsonReader {
    private String source;

    //EFFECTS: contructs reader to read from source file
    public JsonReader(String source) {

    }

    //EFFECTS: reads favorite lists from file and returns it as a HashMap<String, Favorites>
    //         throws IOException if an error occurs reading data from file
    public HashMap<String, Favorites> read() {
        HashMap<String, Favorites> stub = new HashMap<>();
        return stub;
    }

    // EFFECTS: reads source file as string and returns it
    //          throws IOException if error occurs while reading file
    private String readFile(String source) {
        return "";
    }

    //EFFECTS: parses through favorite lists from JSON object and return it as a HashMap
    private HashMap<String, Favorites> parseFavorites(JSONObject jsonObject) {
        HashMap<String, Favorites> stub = new HashMap<>();
        return stub;
    }

    // EFFECTS: parses a Favorites list from JSON array and returns it as a Favorites object
    private Favorites parseFavorites(JSONArray jsonArray) {
        Favorites fav = new Favorites();
        return fav;
    }

    //EFFECTS: parses a location from JSON object and returns it
    private Location parseLocation(JSONObject jsonObject) {
        Location loc = new Location("Test", new Country("Canada"), 1);
        return loc;
    }
}
