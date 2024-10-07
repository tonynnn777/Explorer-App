package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CountryTest {
    private Country testCountry;

    @BeforeEach
    void setUp() {
        testCountry = new Country("USA");
    }

    @Test
    void testConstructor() {
        assertEquals("USA", testCountry.getName());
    }
}
