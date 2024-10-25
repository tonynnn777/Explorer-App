package model;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a collection of favorite locations
public class Favorites {
    private HashSet<Location> favoriteLocations;

    //EFFECTS: Constructs a new instance with an empty list of favorite locations
    public Favorites() {
        favoriteLocations = new HashSet<>();
    }

    //REQUIRES: loc is not null
    //MODIFIES: this
    //EFFECTS: adds loc location to the list if not already in the list
    public void addLocation(Location loc) {
        if (!contains(loc)) {
            favoriteLocations.add(loc);
        }
    }

    //REQUIRES: loc is not null
    //MODIFIES: this
    //EFFECTS: removes loc location to the list if already in the list
    public void removeLocation(Location loc) {
        favoriteLocations.remove(loc);
    }

    //EFFECTS: returns current list of favorite locations
    public HashSet<Location> getFavorites() {
        return favoriteLocations;
    }

    //EFFECTS: returns the location if given name matches
    public Location getLocationByName(String n) {
        for (Location l : favoriteLocations) {
            if (l.getName().equals(n)) {
                return l;
            }
        }
        return null;
    }
    
    //EFFECTS: get size of the favorite list
    public int getSize() {
        return favoriteLocations.size();
    }

    //REQUIRES: loc is not null
    //EFFECTS: returns true if a location is already in the list
    public boolean contains(Location loc) {
        for (Location l : favoriteLocations) {
            if (l.equals(loc)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns locations in this favorite list as a JSON array
    private JSONArray favoriteSToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Location loc : this.getFavorites()) {
            jsonArray.put(loc.locationToJson(loc));
        }

        return jsonArray;
    }
}
