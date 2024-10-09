package model;

// This class represents a country in the system that has a name
public class Country {
    private String name;

    //REQUIRES: Country's name cannot be empty or null
    //EFFECTS: Construct a country of set name

    public Country(String name) {
        this.name = name;
    }
    
    //EFFECTS: Returns a country's name

    public String getName() {
        return name;
    }
}