// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.Country;
import model.Favorites;
import model.Location;

import java.util.HashMap;


public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFavMap() {
        try {
            HashMap<String, Favorites> favoriteListsMap = new HashMap<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFavMap.json");
            writer.open();
            writer.write(favoriteListsMap);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFavMap.json");
            HashMap<String, Favorites> readFavoriteLists = reader.read();
            assertEquals(0, readFavoriteLists.size()); // Ensure it reads back as empty
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFavMap() {
        try {
            HashMap<String, Favorites> favoriteListsMap = new HashMap<>();
            Country country = new Country("Canada");
            Favorites favorites = new Favorites();
            favorites.addLocation(new Location("Banff", country, 5));
            favorites.addLocation(new Location("Toronto", country, 4));
            favoriteListsMap.put("Test Fav List One", favorites);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFavMap.json");
            writer.open();
            writer.write(favoriteListsMap);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFavMap.json");
            HashMap<String, Favorites> readFavoriteLists = reader.read();
            assertEquals(1, readFavoriteLists.size());
            assertTrue(readFavoriteLists.containsKey("Test Fav List One"));

            Favorites readFavorites = readFavoriteLists.get("Test Fav List One");
            assertEquals(2, readFavorites.getFavorites().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
