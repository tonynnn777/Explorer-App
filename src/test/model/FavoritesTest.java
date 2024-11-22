package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;


public class FavoritesTest {
    private Favorites favoriteList;
    private Location location1;
    private Location location2;
    private Country testCountry;

    @BeforeEach
    void setUp() {
        favoriteList = new Favorites();
        testCountry = new Country("USA");
        location1 = new Location("Hawaii", testCountry, 5, "images/hawaii.jpeg");
        location2 = new Location("New York", testCountry, 4, "images/nyc.jpeg");
    }

    @Test
    void testConstructor() {
        assertEquals(0, favoriteList.getSize());
    }

    @Test
    void testAddOneLocation() {
        favoriteList.addLocation(location1);
        assertTrue(favoriteList.contains(location1));
        assertEquals(1, favoriteList.getSize());

    }

    @Test
    void testAddMultipleLocations() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        assertTrue(favoriteList.contains(location1));
        assertTrue(favoriteList.contains(location2));
        assertEquals(2, favoriteList.getSize());
    }

    @Test
    void testRemoveOneLocation() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        favoriteList.removeLocation(location1);
        assertFalse(favoriteList.contains(location1));
        assertTrue(favoriteList.contains(location2));
        assertEquals(1, favoriteList.getSize());
    }

    @Test
    void testRemoveMultipleLocations() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        favoriteList.removeLocation(location1);
        favoriteList.removeLocation(location2);
        assertFalse(favoriteList.contains(location1));
        assertFalse(favoriteList.contains(location2));
        assertEquals(0, favoriteList.getSize());
    }

    @Test
    void testRemoveNonExistentLocation() {
        favoriteList.addLocation(location1);
        favoriteList.removeLocation(location2);
        assertTrue(favoriteList.contains(location1));
        assertEquals(1, favoriteList.getSize());
    }

    @Test
    void testAddDuplicateLocation() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location1);
        assertEquals(1, favoriteList.getSize());
    }

    @Test
    void testGetLocationByName() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        assertEquals(location1, favoriteList.getLocationByName("Hawaii"));
        assertEquals(null, favoriteList.getLocationByName("Washington DC"));


    }

    @Test
    void testGetFavorites() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        HashSet<Location> testFavovites = favoriteList.getFavorites();
        assertEquals(2, testFavovites.size());
        assertTrue(testFavovites.contains(location1));
    }
}

