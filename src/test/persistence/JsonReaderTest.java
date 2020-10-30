package persistence;

import model.BudgetSystem;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BudgetSystem budgetSystem = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadEmptyBudgetSystem() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBudgetSystem.json");
        try {
            BudgetSystem emptyBudgetSystem = reader.read();
            assertEquals("Empty BS", emptyBudgetSystem.getName());
            assertEquals(0, emptyBudgetSystem.getCategories().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBudgetSystem() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBudgetSystem.json");
        try {
            BudgetSystem actualBudgetSystem = reader.read();
            assertEquals(budgetSystem.getName(), actualBudgetSystem.getName());
            assertEquals(budgetSystem.getName(), budgetSystem.getName());
            checkCategories(budgetSystem.getCategories(), actualBudgetSystem.getCategories());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Couldn't read from file");
        }
    }
}
