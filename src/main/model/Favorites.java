package model;

import java.util.HashSet;

// Represents a collection of favorite locations
public class Favorites {
    //EFFECTS: Constructs a new instance with an empty list of favorite locations
    public Favorites(){

    }
    //MODIFIES: this
    //EFFECTS: adds loc location to the list if not already in the list
    public void addLocation(){

    }
    //MODIFIES: this
    //EFFECTS: removes loc location to the list if already in the list
    public void removeLocation(){

    }
    //EFFECTS: returns current list of favorite locations
    public HashSet<Location> getFavorites(){
        return null;
    }
    //EFFECTS: returns true if a location is already in the list
    public boolean contains(){
        return false;
    }
    //EFFECTS: returns the location if given name matches
    public Location getFavoriteByName(){
        return null;
    }

}
