package model;

import java.util.HashSet;

// Represents a collection of favorite locations
public class Favorites {
    //EFFECTS: Constructs a new instance with an empty list of favorite locations
    public Favorites(){

    }
    //REQUIRES: loc is not null
    //MODIFIES: this
    //EFFECTS: adds loc location to the list if not already in the list
    public void addLocation(Location loc){

    }
    //REQUIRES: loc is not null
    //MODIFIES: this
    //EFFECTS: removes loc location to the list if already in the list
    public void removeLocation(Location loc){

    }
    //EFFECTS: returns current list of favorite locations
    public HashSet<Location> getFavorites(){
        return null;
    }
    //EFFECTS: returns the location if given name matches
    public Location getFavoriteByName(){
        return null;
    }
    //REQUIRES: loc is not null
    //EFFECTS: returns true if a location is already in the list
    public boolean contains(Location loc){
        return false;
    }

}
