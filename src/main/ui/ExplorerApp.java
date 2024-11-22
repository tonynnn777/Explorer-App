// reference from TellerApp and JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Country;
import model.Favorites;
import model.Location;
import persistence.JsonReader;
import persistence.JsonWriter;


// World Explorer Application
public class ExplorerApp {
    private HashMap<String, Favorites> favoriteListsMap;
    private HashMap<Country, List<Location>> locationsByCountry;
    private Favorites activeFavoriteList;
    private Scanner input;
    private static final String JSON_STORE = "./data/favMap.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private static final Country COUNTRY_USA = new Country("USA");
    private static final Country COUNTRY_CANADA = new Country("Canada");
    private static final Country COUNTRY_FRANCE = new Country("France");
    private static final Country COUNTRY_AUSTRALIA = new Country("Australia");
    private static final Country COUNTRY_THAILAND = new Country("Thailand");
    

    //EFFECTS: runs the Explorer App
    public ExplorerApp() {
        System.out.println("Welcome to the World Explorer App, an app to to help plan your next summer vacation!");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runExplorer();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runExplorer() {
        boolean keepGoing = true;
        String command = null;

        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
            
        }
        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command according to input
    //         if command is null or invalid print invalid command
    private void processCommand(String command) {
        if (command.equals("b")) {
            browseLocations();
        } else if (command.equals("c")) {
            createFavoriteList();
        } else if (command.equals("v")) {
            viewFavoriteLists();
        } else if (command.equals("s")) {
            saveFavMap();
        } else if (command.equals("l")) {
            loadFavMap();
        } else {
            System.out.println("Invalid command...");
        }
    }
    
    //MODIFIES: this, Favorites, Location
    //EFFECTS: initiallizes lists and locations
    private void init() {
        favoriteListsMap = new HashMap<>();
        locationsByCountry = new HashMap<>();
        activeFavoriteList = new Favorites();

        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        iniLocations();
    }

    //MODIFIES: Locations
    //EFFECTS: initializes locations 
    private void iniLocations() {
        locationsByCountry.put(COUNTRY_USA, Arrays.asList(
            new Location("New York City", COUNTRY_USA, 5, "images/nyc.jpeg"),
            new Location("Los Angeles", COUNTRY_USA, 4, "images/la.jpeg"),
            new Location("Chicago", COUNTRY_USA, 4, "images/chicago.jpeg")
        ));
        locationsByCountry.put(COUNTRY_CANADA, Arrays.asList(
            new Location("Toronto", COUNTRY_CANADA, 5, "images/toronto.jpeg"),
            new Location("Vancouver", COUNTRY_CANADA, 4, "images/vancouver.jpeg"),
            new Location("Montreal", COUNTRY_CANADA, 4, "images/montreal.jpeg")
        ));
        locationsByCountry.put(COUNTRY_FRANCE, Arrays.asList(
            new Location("Paris", COUNTRY_FRANCE, 5, "images/paris.jpeg"),
            new Location("Nice", COUNTRY_FRANCE, 4, "images/nice.jpeg"),
            new Location("Lyon", COUNTRY_FRANCE, 3, "images/lyon.jpeg")
        ));
        locationsByCountry.put(COUNTRY_AUSTRALIA, Arrays.asList(
            new Location("Melbourne", COUNTRY_AUSTRALIA, 4, "images/melbourne.jpeg"),
            new Location("Brisbane", COUNTRY_AUSTRALIA, 4, "images/brisbane.jpeg")
        ));
        locationsByCountry.put(COUNTRY_THAILAND, Arrays.asList(
            new Location("Bangkok", COUNTRY_THAILAND, 5, "images/bangkok.jpeg"),
            new Location("Phuket", COUNTRY_THAILAND, 3, "images/phuket.jpeg")
        ));
    }

    //EFFECTS: displays menu options for the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> browse locations");
        System.out.println("\tc -> create a new favorite list");
        System.out.println("\tv -> view favorite lists");
        System.out.println("\ts -> save all favorite lists");
        System.out.println("\tl -> load all favorite lists");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: prints out countries for user to view
    private void printCountries() {
        System.out.println("\nSelect a country to browse:");
        System.out.println("\t1 -> USA");
        System.out.println("\t2 -> Canada");
        System.out.println("\t3 -> France");
        System.out.println("\t4 -> Australia");
        System.out.println("\t5 -> Thailand");
    }

    //MODIFIES: this
    //EFFECTS: Lets the user browse locations by countries
    //         takes user input and show locations in that country
    //         prints invalid selection if input is not a number 1-5
    private void browseLocations() {
        printCountries();
        String command = input.next();
        switch (command) {
            case "1":
                showLocationByCountry(COUNTRY_USA);
                break;
            case "2":
                showLocationByCountry(COUNTRY_CANADA);
                break;
            case "3":
                showLocationByCountry(COUNTRY_FRANCE);
                break;
            case "4":
                showLocationByCountry(COUNTRY_AUSTRALIA);
                break;
            case "5":
                showLocationByCountry(COUNTRY_THAILAND);
                break;
            default:
                System.out.println("Invalid selection. Returning to menu...");
        }
    }
    
    //REQUIRES: c is not null
    //EFFECTS: shows location by selected country,
    //         and prompts the user to add to their favorite list
    private void showLocationByCountry(Country c) {
        printLocationsByCountry(c);
        addToFavoritesPrompt();
    }

    //MODIFIES: this
    //EFFECTS: asks if user wants to add a location to their favorite list
    //         and add it if yes
    //         otherwise print out something
    private void addToFavoritesPrompt() {
        System.out.println("Would you like to add a location to your favorite list? (yes/no)");
        String response = input.next().toLowerCase();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the name of the location you want to add to your favorite list:");
            String locationName = input.next();
            addLocationToFavorites(locationName);
        } else {
            System.out.println("You chose not to add a location to your favorites.");
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if there is an active favorite list or no lists created,
    //         add a location to active favorite list if name matches locName,
    //         tells the user location not found if no match.
    private void addLocationToFavorites(String locName) {
        // Check if there is an active favorite list or no lists created
        if (activeFavoriteList == null || favoriteListsMap.isEmpty()) {
            System.out.println("You have not created any favorite lists yet. Please create one!");
            return;
        }
        for (List<Location> locations : locationsByCountry.values()) {
            for (Location l : locations) {
                if ((l.getName().equals(locName))) {
                    activeFavoriteList.addLocation(l);
                    System.out.println(locName + " has been added to your list called " 
                                    + getLocationNameByLocation(favoriteListsMap, activeFavoriteList)
                                    + " ! ! !");
                    return;
                }
            }
        }
        System.out.println("Location " + locName + " not found in the available locations.");
    }

    //REQUIRES: map not empty, f is not null
    //EFFECTS: returns corresponding title of a favorite list by searching through the hashmap
    //         returns null if no match
    private String getLocationNameByLocation(HashMap<String, Favorites> map, Favorites f) {
        for (Map.Entry<String, Favorites> pair : map.entrySet()) { //search through each pairs of key/value
            if (pair.getValue().equals(f)) {
                return pair.getKey();
            }
        }
        return null; // if no match favorites is found
    }

    //REQUIRES: c is not null
    //EFFECTS: prints out locations that are in country c
    private void printLocationsByCountry(Country c) {
        List<Location> locations = locationsByCountry.get(c);
        if (locations != null) {
            System.out.println("\n Locations in " + c.getName() + ": ");
            for (Location l : locations) {
                System.out.println("\t " + l.getName() + "(Rating: " + l.getRating() + ")");
            }
        } else {
            System.out.println("No locations in that Country. Sorry!");
        }
    }

    // MODIFIES: this, Favorites
    // EFFECTS: creates a new favorite list with the provided name, adds it to the collection of favorite lists,
    //          and sets it as the active favorite list
    private void createFavoriteList() {
        System.out.println("Enter the name of the new favorite list: ");
        String listName = input.next();
        Favorites newFavorites = new Favorites();
        favoriteListsMap.put(listName, newFavorites);
        activeFavoriteList = newFavorites;
        System.out.println("Favorite list " + listName + " is created!");
    }

    // MODIFIES: this, Location, Favorites
    // EFFECTS: if there exists a favorite list,
    //          displays the names of all the favorite lists to the user,
    //          allows the user to select one to view by name, shows the locations in the active list,
    //          gives user option to remove a location and rate a location
    private void viewFavoriteLists() {
        if (favoriteListsMap.isEmpty()) {
            System.out.println("No lists to view.");
            return;
        }

        System.out.println("Favorite Lists: ");
        for (String listName : favoriteListsMap.keySet()) {
            System.out.println("\t " + listName);
        }
        System.out.println("Select a list to view: ");
        String selectedListName = input.next();
        Favorites selectedList = favoriteListsMap.get(selectedListName);
        
        if (selectedList != null) {
            activeFavoriteList = selectedList;
            System.out.println(getLocationNameByLocation(favoriteListsMap, activeFavoriteList)
                                                         + " is the current active list.");
            System.out.println("Locations in this favorite list: " + selectedList.getFavorites().toString());
        } else {
            System.out.println("Favorite list " + selectedListName + " does not exist.");
        }
        if (activeFavoriteList.getSize() != 0) {
            removeLocationPrompt();
            rateLocationPrompt();
        }
    }

    //MODIFIES: this, Favorites
    //EFFECTS: prompts the user if they want to remove a location from their list,
    //         remove a location from list if user choose to do so
    private void removeLocationPrompt() {
        System.out.println("Would you like to remove a location from your favorite list? (yes/no)");
        String response = input.next();
    
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the name of the location you want to remove:");
            String locationToRemove = input.next();
            removeLocationFromFavorites(activeFavoriteList.getLocationByName(locationToRemove));
        } else {
            System.out.println("No location removed from your favorite list.");
        }
    }

    //MODIFIES: this, Favorites
    //EFFECTS: remove a location from an active favorite list
    //         if location is null, print not found in list
    private void removeLocationFromFavorites(Location loc) {
        if (loc == null) {
            System.out.println("Location not found in your favorite list.");
            return;
        }
        activeFavoriteList.removeLocation(loc);
        System.out.println(loc.getName() + " has been removed from your favorite list.");
    }

    //MODIFIES: this, Location, Favorites
    //EFFECTS: let user change the rating of a location in their active favorite list
    //         by prompting and let user input a location in their active list
    //         doesn't run if active favorite list is empty
    private void rateLocationPrompt() {
        if (activeFavoriteList.getSize() == 0) {
            return;
        }
        System.out.println("Would you like to rate a location from your favorite list? (yes/no)"); 
        System.out.println("Note: this WILL change the location's rating!");
        String response = input.next();
    
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the name of the location you want to rate:");
            String locationToRate = input.next();
            rateLocationFromFavorites(activeFavoriteList.getLocationByName(locationToRate));
        } else {
            System.out.println("Ratings remain the same.");
        }
    }

    //MODIFIES: this, Location, Favorites
    //EFFECTS: if location is not null,
    //         changes the actual rating of a location from favorite list
    //         from 0 to 5 inclusive
    private void rateLocationFromFavorites(Location loc) {
        if (loc != null) {
            int oldRating = loc.getRating();
            System.out.println("Enter rating (0 to 5): ");
            int newRating = input.nextInt();
            if (newRating >= 0 && newRating <= 5) {
                loc.setRating(newRating);
                System.out.println("The rating of " + loc.getName() + " has been changed from " 
                                    + oldRating + " to " + loc.getRating());
            } else {
                System.out.println("Invalid rating.");
            }
        } else {
            System.out.println("No such Location found.");
        }
    } 

    // EFFECTS: saves the favMap to file
    private void saveFavMap() {
        try {
            jsonWriter.open();
            jsonWriter.write(favoriteListsMap);
            jsonWriter.close();
            System.out.println("Saved all lists to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads favMap from file
    private void loadFavMap() {
        try {
            favoriteListsMap = jsonReader.read();
            System.out.println("Loaded all lists from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
