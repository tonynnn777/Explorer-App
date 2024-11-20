package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import model.Favorites;

public class GUIApp extends JFrame {
    private HashMap<String, Favorites> favoriteListsMap;

    // Constructor
    public GUIApp() {
        super("World Explorer Application");

        // Initialize favorite lists map
        favoriteListsMap = new HashMap<>();

        // Set up main frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Add panels
        add(createControlPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true); // Show the window
    }

    // Creates a control panel with buttons
    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Add buttons
        JButton createButton = new JButton("Create A New List");
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton browseButton = new JButton("Browse Locations");
        JButton viewButton = new JButton("View Favorite Lists");

        // Add ActionListeners (placeholders for now)
        loadButton.addActionListener(e -> loadFavorites());
        saveButton.addActionListener(e -> saveFavorites());
        browseButton.addActionListener(e -> browseLocations());
        viewButton.addActionListener(e -> viewFavorites());
        createButton.addActionListener(e -> createList());

        // Add buttons to the panel
        panel.add(createButton);
        panel.add(viewButton);
        panel.add(browseButton);
        panel.add(saveButton);
        panel.add(loadButton);

        return panel;
    }

    // Creates a main panel with a text area
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Placeholder text area
        JTextArea textArea = new JTextArea("Welcome to the World Explorer App, an app to to help plan your next summer vacation!");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    // Placeholder methods for button actions
    private void loadFavorites() {
        System.out.println("Load Favorites clicked");
    }

    private void saveFavorites() {
        System.out.println("Save Favorites clicked");
    }

    private void browseLocations() {
        System.out.println("Browse Locations clicked");
    }

    private void viewFavorites() {
        System.out.println("View Favorites clicked");
    }

    private void createList() {
        System.out.println("Create List clicked");
    }

    // Main method
    public static void main(String[] args) {
        new GUIApp();
    }
}
