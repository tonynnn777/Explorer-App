package persistence;

import java.util.HashMap;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.nio.charset.StandardCharsets;

import org.json.*;

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
        this.source = source;
    }

    //EFFECTS: reads favorite lists from file and returns it as a HashMap<String, Favorites>
    //         throws IOException if an error occurs reading data from file
    public HashMap<String, Favorites> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFavorites(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    //          throws IOException if error occurs while reading file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses through favorite lists from JSON object and return it as a HashMap
    private HashMap<String, Favorites> parseFavorites(JSONObject jsonObject) {
        HashMap<String, Favorites> favoriteListsMap = new HashMap<>();
        
        for (String listName : jsonObject.keySet()) {
            JSONArray jsonArray = jsonObject.getJSONArray(listName);
            Favorites favorites = parseFavorites(jsonArray);
            favoriteListsMap.put(listName, favorites);
        }

        return favoriteListsMap;
    }

    // EFFECTS: parses a Favorites list from JSON array and returns it as a Favorites object
    private Favorites parseFavorites(JSONArray jsonArray) {
        Favorites fav = new Favorites();

        for (Object obj : jsonArray) {
            JSONObject locationJson = (JSONObject) obj;
            Location location = parseLocation(locationJson);
            fav.addLocation(location);
        }
        return fav;
    }

    //EFFECTS: parses a location from JSON object and returns it
    private Location parseLocation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String countryName = jsonObject.getString("country");
        int rating = jsonObject.getInt("rating");

        Country country = new Country(countryName);
        return new Location(name, country, rating);
    }
}
