package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FavoritesTest {
    private Favorites favoriteList;
    private Location location1;
    private Location location2;
    private Country testCountry;

    @BeforeEach
    void setUp() {
        favoriteList = new Favorites();
        testCountry = new Country("USA");
        location1 = new Location("Hawaii", testCountry, 5);
        location2 = new Location("New York", testCountry, 4);
    }

    @Test
    void testConstructor() {
        assertEquals(0, favoriteList.getFavorites().size());
    }

    @Test
    void testAddOneLocation() {
        favoriteList.addLocation(location1);
        assertTrue(favoriteList.contains(location1));
        assertEquals(1, favoriteList.getFavorites().size());

    }

    @Test
    void testAddMultipleLocations() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        assertTrue(favoriteList.contains(location1));
        assertTrue(favoriteList.contains(location2));
        assertEquals(2, favoriteList.getFavorites().size());
    }

    @Test
    void testRemoveOneLocation() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        favoriteList.removeLocation(location1);
        assertFalse(favoriteList.contains(location1));
        assertTrue(favoriteList.contains(location2));
        assertEquals(1, favoriteList.getFavorites().size());
    }

    @Test
    void testRemoveMultipleLocations() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        favoriteList.removeLocation(location1);
        favoriteList.removeLocation(location2);
        assertFalse(favoriteList.contains(location1));
        assertFalse(favoriteList.contains(location2));
        assertEquals(0, favoriteList.getFavorites().size());
    }

    @Test
    void testRemoveNonExistentLocation() {
        favoriteList.addLocation(location1);
        favoriteList.removeLocation(location2);
        assertTrue(favoriteList.contains(location1));
        assertEquals(1, favoriteList.getFavorites().size());
    }

    @Test
    void testAddDuplicateLocation() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location1);
        assertEquals(1, favoriteList.getFavorites().size());
    }

    @Test
    void testGetLocationByName() {
        favoriteList.addLocation(location1);
        favoriteList.addLocation(location2);
        assertEquals(location1, favoriteList.getFavoriteByName("Hawaii"));
        assertEquals(null, favoriteList.getFavoriteByName("Washington DC"));


    }

}

