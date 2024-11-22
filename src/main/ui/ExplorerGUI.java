package ui;

import javax.swing.*;

import model.Country;
import model.Favorites;
import model.Location;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ExplorerGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private static final String MENU_PANEL = "Menu Panel";
    private static final String COUNTRY_PANEL = "Country Panel";
    private static final String BROWSE_PANEL = "Browse Panel";
    private HashSet<Location> allLocations;
    private Country selectedCountry;
    private HashMap<String, Favorites> favoritesMap;

    private static final String JSON_STORE = "./data/favMap.json"; // where to save, load
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final Country COUNTRY_USA = new Country("USA");
    private static final Country COUNTRY_CANADA = new Country("Canada");
    private static final Country COUNTRY_FRANCE = new Country("France");
    private static final Country COUNTRY_AUSTRALIA = new Country("Australia");
    private static final Country COUNTRY_THAILAND = new Country("Thailand");

    //EFFECTS: runs the ExplorerGUI
    public ExplorerGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        allLocations = initLocations();
        favoritesMap = new HashMap<>();
        setTitle("Explorer App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Panels
        mainPanel.add(createMenuPanel(), MENU_PANEL);
        mainPanel.add(createCountryPanel(), COUNTRY_PANEL);
        mainPanel.add(createBrowsePanel(new ArrayList<>()), BROWSE_PANEL); // Empty initially

        setVisible(true);
    }

    //MODIFIES: this, Location
    //EFFECTS: initialize the locations
    private HashSet<Location> initLocations() {
        HashSet<Location> locations = new HashSet<>();
        
        locations.add(new Location("Bangkok", COUNTRY_THAILAND, 5, "images/bangkok.jpeg"));
        locations.add(new Location("Phuket", COUNTRY_THAILAND, 3, "images/phuket.jpeg"));
        locations.add(new Location("Melbourne", COUNTRY_AUSTRALIA, 4, "images/melbourne.jpeg"));
        locations.add(new Location("Brisbane", COUNTRY_AUSTRALIA, 4, "images/brisbane.jpeg"));
        locations.add(new Location("Paris", COUNTRY_FRANCE, 5, "images/paris.jpeg"));
        locations.add(new Location("Nice", COUNTRY_FRANCE, 4, "images/nice.jpeg"));
        locations.add(new Location("Lyon", COUNTRY_FRANCE, 3, "images/lyon.jpeg"));
        locations.add(new Location("Toronto", COUNTRY_CANADA, 4, "images/toronto.jpeg"));
        locations.add(new Location("Vancouver", COUNTRY_CANADA, 4, "images/vancouver.jpeg"));
        locations.add(new Location("Montreal", COUNTRY_CANADA, 4, "images/montreal.jpeg"));
        locations.add(new Location("New York City", COUNTRY_USA, 5, "images/nyc.jpeg"));
        locations.add(new Location("Los Angeles", COUNTRY_USA, 4, "images/la.jpeg"));
        locations.add(new Location("Chicago", COUNTRY_USA, 4, "images/chicago.jpeg"));
        return locations;
    }

    //MODIFIES: this
    //EFFECTS: shows the menu panel
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new FlowLayout());

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(e -> cardLayout.show(mainPanel, COUNTRY_PANEL));
        menuPanel.add(browseButton);

        JButton createListButton = new JButton("Create List");
        createListButton.addActionListener(e -> handleCreateList());
        menuPanel.add(createListButton);

        JButton viewListButton = new JButton("View Lists");
        viewListButton.addActionListener(e -> handleViewList());
        menuPanel.add(viewListButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveFavMap());
        menuPanel.add(saveButton);
    
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadFavMap());
        menuPanel.add(loadButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        menuPanel.add(exitButton);

        return menuPanel;
    }

    //MODIFIES: this
    //EFFECTS: handles create list
    //         if list has same same, throw error message
    private void handleCreateList() {
        String listName = JOptionPane.showInputDialog(this, "Enter new list name:");
        if (listName != null && !listName.trim().isEmpty()) {
            if (!favoritesMap.containsKey(listName)) {
                favoritesMap.put(listName, new Favorites());
                JOptionPane.showMessageDialog(this, "List created: " + listName);
            } else {
                JOptionPane.showMessageDialog(this, "List already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: handles when user click on the view list button
    //         if no list, throw error message
    //         otherwise show the locations in the selected list
    private void handleViewList() {
        if (favoritesMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No lists available. Create one first.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] listNames = favoritesMap.keySet().toArray(new String[0]);
        String selectedList = (String) JOptionPane.showInputDialog(
                this,
                "Select a list to view:",
                "View List",
                JOptionPane.PLAIN_MESSAGE,
                null,
                listNames,
                listNames[0]);
        if (selectedList != null) {
            Favorites favorites = favoritesMap.get(selectedList);
            showListPanel(selectedList, favorites.getFavorites());
        }
    }

    //MODIFIES: this
    //EFFECTS: shows the locations in the selected list
    private void showListPanel(String listName, HashSet<Location> locations) {
        JPanel listPanel = new JPanel(new BorderLayout());
    
        JLabel titleLabel = new JLabel("Viewing List: " + listName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        listPanel.add(titleLabel, BorderLayout.NORTH);
    
        JPanel locationGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        for (Location location : locations) {
            locationGrid.add(createLocationPanel(location, true));
        }
        listPanel.add(locationGrid, BorderLayout.CENTER);
    
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, MENU_PANEL));
        listPanel.add(backButton, BorderLayout.SOUTH);
    
        // Add to the main panel and show it
        mainPanel.add(listPanel, "List Panel");
        cardLayout.show(mainPanel, "List Panel");
    }
    

    //MODIDIES: this
    //EFFECTS: shows the country panel
    private JPanel createCountryPanel() {
        JPanel countryPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Select a Country", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        countryPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel countryGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        Set<Country> countries = allLocations.stream()
                .map(Location::getCountry)
                .collect(Collectors.toSet());
        for (Country country : countries) {
            JButton countryButton = new JButton(country.getName());
            countryButton.addActionListener(e -> {
                selectedCountry = country;
                updateBrowsePanel();
                cardLayout.show(mainPanel, BROWSE_PANEL);
            });
            countryGrid.add(countryButton);
        }
        countryPanel.add(countryGrid, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, MENU_PANEL));
        countryPanel.add(backButton, BorderLayout.SOUTH);

        return countryPanel;
    }

    //MODIFIES: this
    //EFFECTS: shows the browsing panel showing the locations
    private JPanel createBrowsePanel(List<Location> locations) {
        JPanel browsePanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Browse Locations", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        browsePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel locationGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        for (Location location : locations) {
            locationGrid.add(createLocationPanel(location, false));
        }
        browsePanel.add(locationGrid, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Countries");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, COUNTRY_PANEL));
        browsePanel.add(backButton, BorderLayout.SOUTH);

        return browsePanel;
    }

    //MODIFIES: this
    //EFFECTS: shows the location panel
    //         if viewing list, right clicking gives options to remove a location
    //         if browsing, right clicking gives option to add to a list
    private JPanel createLocationPanel(Location location, boolean isViewingList) {
        JPanel locationPanel = new JPanel(new BorderLayout());
        locationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    
        // Image
        ImageIcon imageIcon = new ImageIcon(location.getImagePath());
        JLabel imageLabel = new JLabel(scaleImage(imageIcon, 150, 150));
        locationPanel.add(imageLabel, BorderLayout.CENTER);
    
        // Info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel("Name: " + location.getName());
        JLabel ratingLabel = new JLabel("Rating: " + location.getRating());
        infoPanel.add(nameLabel);
        infoPanel.add(ratingLabel);
    
        locationPanel.add(infoPanel, BorderLayout.SOUTH);
    
        // Add a right-click context menu
        addRightClickMenu(locationPanel, location, isViewingList);
        return locationPanel;
    }

    //MODIFIES: this
    // EFFECTS: Adds a right-click context menu to the given panel
    private void addRightClickMenu(JPanel locationPanel, Location location, boolean isViewingList) {
        JPopupMenu contextMenu = new JPopupMenu();

        if (isViewingList) {
            // Option to remove from list
            JMenuItem removeFromList = new JMenuItem("Remove from List");
            removeFromList.addActionListener(e -> removeFromList(location));
            contextMenu.add(removeFromList);
        } else {
            // Option to add to a list
            JMenuItem addToList = new JMenuItem("Add to List");
            addToList.addActionListener(e -> openAddToListDialog(location));
            contextMenu.add(addToList);
        }
        addContextMenuMouseListener(locationPanel, contextMenu);
    }

    // MODIFIES: this
    // EFFECTS: Adds a mouse listener to show the context menu on right-click
    private void addContextMenuMouseListener(JPanel locationPanel, JPopupMenu contextMenu) {
        locationPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) { // Detect right-click
                    contextMenu.show(locationPanel, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) { // Detect right-click
                    contextMenu.show(locationPanel, e.getX(), e.getY());
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: removes location from the current list
    private void removeFromList(Location location) {
        if (favoritesMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No lists available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String[] listNames = favoritesMap.keySet().toArray(new String[0]);
        String selectedList = (String) JOptionPane.showInputDialog(
                this,
                "Select a list to remove this location from:",
                "Remove from List",
                JOptionPane.PLAIN_MESSAGE,
                null,
                listNames,
                listNames[0]);
    
        removeLocationFromSelectedList(selectedList, location);
    }

    //MODIFIES: this
    //EFFECTS: remove location from selected list if it contains the location
    private void removeLocationFromSelectedList(String selectedList, Location location) {
        if (selectedList != null) {
            Favorites selectedFavorites = favoritesMap.get(selectedList);
            if (selectedFavorites.contains(location)) {
                selectedFavorites.removeLocation(location);
                JOptionPane.showMessageDialog(this, "Location removed from list: " + selectedList);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Location not found in the selected list.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //MODIFIES: this
    //EFFECTS: updates the browse panel to display only locations for the selected country
    private void updateBrowsePanel() {
        List<Location> filteredLocations = allLocations.stream()
                .filter(loc -> loc.getCountry().equals(selectedCountry))
                .collect(Collectors.toList());

        mainPanel.remove(mainPanel.getComponent(2)); // Remove old browse panel
        mainPanel.add(createBrowsePanel(filteredLocations), BROWSE_PANEL);
    }

    //MODIFIES: this
    //EFFETS: adds a location to a selected list
    private void openAddToListDialog(Location location) {
        ifNoListThrowError();

        String[] listNames = favoritesMap.keySet().toArray(new String[0]);
        String selectedList = (String) JOptionPane.showInputDialog(
                this,
                "Select a list to add this location:",
                "Add to List",
                JOptionPane.PLAIN_MESSAGE,
                null,
                listNames,
                listNames[0]);

        if (selectedList != null) {
            Favorites selectedFavorites = favoritesMap.get(selectedList);
            if (selectedFavorites.contains(location)) {
                JOptionPane.showMessageDialog(this, 
                                            "Location already in the list!",
                                            "Error", 
                                            JOptionPane.ERROR_MESSAGE);
            } else {
                selectedFavorites.addLocation(location);
                JOptionPane.showMessageDialog(this, "Location added to list: " + selectedList);
            }
        }
    }
    
    //EFFECTS: shows an error if no list has been created
    private void ifNoListThrowError() {
        if (favoritesMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                                        "No lists available. Create one first.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    //MODIFIES: this
    //EFFECTS: scales images to width and height
    private ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    //MODIFIES: this
    // EFFECTS: saves the favMap to file
    private void saveFavMap() {
        try {
            jsonWriter.open();
            jsonWriter.write(favoritesMap);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Favorites saved successfully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save favorites to " + JSON_STORE,
                             "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads favMap from file
    private void loadFavMap() {
        try {
            favoritesMap = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Favorites loaded successfully from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load favorites from " + JSON_STORE,
                             "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
