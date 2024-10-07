package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocationTest {
    private Location testLocation;
    private Country testCountry;

    @BeforeEach
    void setUp() {
        testCountry = new Country("USA");
        testLocation = new Location("Hawaii", testCountry, 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Hawaii", testLocation.getName());
        assertEquals(testCountry, testLocation.getCountry());
        assertEquals(5, testLocation.getRating());
    }

    @Test
    void testSetRating() {
        assertEquals(5, testLocation.getRating());
        testLocation.setRating(3);
        assertEquals(3, testLocation.getRating());
    }

    @Test
    void testToString() {
        assertEquals((testLocation.getName() + " (Rating: " + testLocation.getRating() + ")"), testLocation.toString());
    }

}
