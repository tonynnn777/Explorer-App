package ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Country;
import model.Favorites;
import model.Location;

// World Explorer Application
public class ExplorerApp {

    private HashMap<String, Favorites> favoriteLists;
    private HashMap<Country, List<Location>> locationsByCountry;
    private Favorites activeFavoriteList;
    private Scanner input;

    private static final Country COUNTRY_USA = new Country("USA");
    private static final Country COUNTRY_CANADA = new Country("Canada");
    private static final Country COUNTRY_FRANCE = new Country("France");
    private static final Country COUNTRY_AUSTRALIA = new Country("Australia");
    private static final Country COUNTRY_THAILAND = new Country("Thailand");
    

    //EFFECTS: runs the Explorer App
    public ExplorerApp() {
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
    
    private void processCommand(String command) {
        if (command.equals("b")) {
            browseLocations();
        } else if (command.equals("c")) {
            createFavoriteList();
        } else if (command.equals("v")) {
            viewFavoriteLists();
        } else {
            System.out.println("Invalid command...");
        }
    }
    
    //EFFECTS: initiallizes lists and locations

    private void init() {
        favoriteLists = new HashMap<>();
        locationsByCountry = new HashMap<>();
        activeFavoriteList = new Favorites();

        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        iniLocations();
    }

    //EFFECTS: initializes locations 
    
    private void iniLocations() {
        locationsByCountry.put(COUNTRY_USA, Arrays.asList(
            new Location("New York City", COUNTRY_USA, 5),
            new Location("Los Angeles", COUNTRY_USA, 4),
            new Location("Chicago", COUNTRY_USA, 4)
        ));
        locationsByCountry.put(COUNTRY_CANADA, Arrays.asList(
            new Location("Toronto", COUNTRY_CANADA, 5),
            new Location("Vancouver", COUNTRY_CANADA, 4),
            new Location("Montreal", COUNTRY_CANADA, 4)
        ));
        locationsByCountry.put(COUNTRY_FRANCE, Arrays.asList(
            new Location("Paris", COUNTRY_FRANCE, 5),
            new Location("Nice", COUNTRY_FRANCE, 4),
            new Location("Lyon", COUNTRY_FRANCE, 3)
        ));
        locationsByCountry.put(COUNTRY_AUSTRALIA, Arrays.asList(
            new Location("Melbourne", COUNTRY_AUSTRALIA, 4),
            new Location("Brisbane", COUNTRY_AUSTRALIA, 4)
        ));
        locationsByCountry.put(COUNTRY_THAILAND, Arrays.asList(
            new Location("Bangkok", COUNTRY_THAILAND, 5),
            new Location("Phuket", COUNTRY_THAILAND, 3)
        ));
    }
    //EFFECTS: displays menu options for the user

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> browse locations");
        System.out.println("\tc -> create a new favorite list");
        System.out.println("\tv -> view favorite lists");
        System.out.println("\tq -> quit");
    }
    //EFFECTS: prints out countries

    private void printCountries() {
        System.out.println("\nSelect a country to browse:");
        System.out.println("\t1 -> USA");
        System.out.println("\t2 -> Canada");
        System.out.println("\t3 -> France");
        System.out.println("\t4 -> Australia");
        System.out.println("\t5 -> Thailand");
    }
    //EFFECTS: Lets the user browse locations by countries

    private void browseLocations() {
        printCountries();
        String command = input.next();
        switch (command) {
            case "1":
                printLocationByCountry(COUNTRY_USA);
                break;
            case "2":
                printLocationByCountry(COUNTRY_CANADA);
                break;
            case "3":
                printLocationByCountry(COUNTRY_FRANCE);
                break;
            case "4":
                printLocationByCountry(COUNTRY_AUSTRALIA);
                break;
            case "5":
                printLocationByCountry(COUNTRY_THAILAND);
                break;
            default:
                System.out.println("Invalid selection.");
        }
        addToFavoritesPrompt();
    }

    //MODIFIES: this
    //EFFECTS: asks if user wants to add a location to their favorite list
    //         and add it if they says yes
    //         otherwise go back to menu
    private void addToFavoritesPrompt() {
        System.out.println("Would you like to add a location to your favorite list? (yes/no)");
        String response = input.next();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the name of the location you want to add to your favorite list:");
            String locationName = input.next();
            addLocationToFavorites(locationName);
        } else {
            System.out.println("You chose not to add a location to your favorites.");
        }
    }
    //REQUIRES: locName not empty or null
    //MODIFIES: this
    //EFFECTS: add a location by name to a favorite list
    
    private void addLocationToFavorites(String locName) {
        // Check if there is an active favorite list
        if (activeFavoriteList == null || favoriteLists.isEmpty()) {
            System.out.println("You have not created any favorite lists yet.");
            return;
        }
        for (List<Location> locations : locationsByCountry.values()) {
            for (Location l : locations) {
                if ((l.getName().equals(locName))) {
                    activeFavoriteList.addLocation(l);
                    System.out.println(locName + " has been added to your fav list!");
                    return;
                }
            }
        }
        System.out.println("Location " + locName + " not found in the available locations.");
    }
    //EFFECTS: prints out locations that are in country c

    private void printLocationByCountry(Country c) {
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

    // MODIFIES: this
    // EFFECTS: creates a new favorite list with the provided name, adds it to the collection of favorite lists,
    //          and sets it as the active favorite list
    private void createFavoriteList() {
        System.out.println("Enter the name of the new favorite list: ");
        String listName = input.next();
        Favorites newFavorites = new Favorites();
        favoriteLists.put(listName, newFavorites);
        activeFavoriteList = newFavorites;
        System.out.println("Favorite list " + listName + " is active!");
    }

    // EFFECTS: displays the names of all the favorite lists to the user and allows the user to select one to view,

    private void viewFavoriteLists() {
        if (favoriteLists.isEmpty()) {
            System.out.println("No lists to view.");
            return;
        }

        System.out.println("Favorite Lists: ");
        for (String listName : favoriteLists.keySet()) {
            System.out.println("\t " + listName);
        }
        System.out.println("Select a list to view: ");
        String selectedListName = input.next();
        
        Favorites selectedList = favoriteLists.get(selectedListName);
        if (selectedList != null) {
            activeFavoriteList = selectedList;
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
    //EFFECTS: prompts the user if they want to remove a location from their list
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

    private void removeLocationFromFavorites(Location loc) {
        if (loc == null) {
            System.out.println("Location not found in your favorite list.");
            return;
        }
        activeFavoriteList.removeLocation(loc);
        System.out.println(loc.getName() + " has been removed from your favorite list.");
    }

    //MODIFIES: this, Location
    //EFFECTS: let user change the rating of a location in their favorite list

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
    //EFFECTS: changes the actual rating of a location from favorite list

    private void rateLocationFromFavorites(Location loc) {
        int oldRating = loc.getRating();
        System.out.println("Enter rating (0 to 5): ");
        int newRating = input.nextInt();
        loc.setRating(newRating);
        System.out.println("The rating of " + loc + "has been changed from " + oldRating + " to " + loc.getRating());
    } 
}
