package model;

// This class represents a location with a name, country of origin, and a rating
public class Location {
    //REQUIRES: name is not empty or null &&
    //          0 <= rating <= 5
    //EFFECTS: Construct a location with name, country, and a number rating
    public Location(String name, Country c, int rating){
        //stub
    }

    //EFFECTS: returns location's name
    public String getName(){
        return "";
    }

    //EFFECTS: returns location's country of origin
    public Country getCountry(){
        return null; 
    }
    
    //EFFECTS: returns location's rating
    public int getRating(){
        return 0;
    }
    //REQUIRES: newRating must be from 0 to 5
    //MODIFIES: this
    //EFFECTS: sets the new rating of location to newRating
    public void setRating(int newRating){

    }
}
