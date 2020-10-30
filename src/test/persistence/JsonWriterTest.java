package persistence;

import model.BudgetSystem;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

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
    void testWriteEmptyBudgetSystem() {
        try{
            BudgetSystem emptyBudgetSystem = new BudgetSystem("Empty BS");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBudgetSystem.json");
            writer.open();
            writer.write(emptyBudgetSystem);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBudgetSystem.json");
            emptyBudgetSystem = reader.read();
            assertEquals("Empty BS", emptyBudgetSystem.getName());
            assertEquals(0, emptyBudgetSystem.getCategories().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBudgetSystem() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBudgetSystem.json");
            writer.open();
            writer.write(budgetSystem);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBudgetSystem.json");
            BudgetSystem actualBudgetSystem = reader.read();
            assertEquals(budgetSystem.getName(), budgetSystem.getName());
            checkCategories(budgetSystem.getCategories(), actualBudgetSystem.getCategories());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
