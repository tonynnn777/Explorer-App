package model;

import org.json.JSONObject;

// This class represents a location with a name, country of origin, and a rating
public class Location {
    private String name;
    private Country country;
    private int rating;

    //REQUIRES: name is not empty or null &&
    //          0 <= rating <= 5
    //EFFECTS: Construct a location with name, country, and a number rating
    public Location(String name, Country c, int rating) {
        this.name = name;
        this.country = c;
        this.rating = rating;
    }

    //EFFECTS: returns location's name
    public String getName() {
        return name;
    }

    //EFFECTS: returns location's country of origin
    public Country getCountry() {
        return country; 
    }
    
    //EFFECTS: returns location's rating
    public int getRating() {
        return rating;
    }

    //EFFECTS: returns location's name and rating as readable strings
    public String toString() {
        return name + " (Rating: " + rating + ")";
    }
    
    //REQUIRES: newRating must be from 0 to 5
    //MODIFIES: this
    //EFFECTS: sets the new rating of location to newRating
    public void setRating(int newRating) {
        rating = newRating;
    }

    //EFFECTS: returns location as a JSON object
    public JSONObject locationToJson(Location loc) {
        JSONObject json = new JSONObject();
        json.put("name", loc.getName());
        json.put("country", loc.getCountry().getName());
        json.put("rating", loc.getRating());
        return json;
    }
}
