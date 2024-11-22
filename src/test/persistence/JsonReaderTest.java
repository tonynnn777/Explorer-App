// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import model.Favorites;
import model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;


public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HashMap<String, Favorites> favMap = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFavoriteListsMap() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFavMap.json");
        try {
            HashMap<String, Favorites> favMap = reader.read();
            assertTrue(favMap.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFavoriteListsMap() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFavMap.json");
        try {
            HashMap<String, Favorites> favMap = reader.read();
            assertEquals(2, favMap.size());
            assertTrue(favMap.containsKey("Test Fav List One"));
            assertTrue(favMap.containsKey("Test Fav List Two"));

            Favorites favListOne = favMap.get("Test Fav List One");
            assertEquals(2, favListOne.getFavorites().size());

            Location toronto = favListOne.getLocationByName("Toronto");
            assertNotNull(toronto);
            assertEquals("Toronto", toronto.getName());
            assertEquals("Canada", toronto.getCountry().getName());
            assertEquals(4, toronto.getRating());
            assertEquals("images/toronto.jpeg", toronto.getImagePath());

            Favorites favListTwo = favMap.get("Test Fav List Two");
            assertEquals(2, favListTwo.getFavorites().size());

            Location losAngeles = favListTwo.getLocationByName("Los Angeles");
            assertNotNull(losAngeles);
            assertEquals("Los Angeles", losAngeles.getName());
            assertEquals("USA", losAngeles.getCountry().getName());
            assertEquals(3, losAngeles.getRating());
            assertEquals("images/la.jpeg", losAngeles.getImagePath());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
