package persistence;

import model.Assignment;
import model.HomeworkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Tests for JsonWriter Class
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HomeworkList hwl = new HomeworkList("My Homework List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHomeworkList() {
        try {
            HomeworkList hwl = new HomeworkList("My Homework List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHomeworkList.json");
            writer.open();
            writer.write(hwl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHomeworkList.json");
            hwl = reader.read();
            assertEquals("My Homework List", hwl.getListTitle());
            assertEquals(0, hwl.incompleteListSize());
            assertEquals(0, hwl.submittedListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHomeworkList() {
        try {
            HomeworkList hwl = new HomeworkList("My Homework List");
            hwl.addAssignment(new Assignment("1","1",1,1));
            hwl.addAssignment(new Assignment("2","2",2,2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHomeworkList.json");
            writer.open();
            writer.write(hwl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHomeworkList.json");
            hwl = reader.read();
            assertEquals("My Homework List", hwl.getListTitle());
            List<Assignment> incompleteAssignments = hwl.getIncompleteHomeworkList();
            List<Assignment> submittedAssignments = hwl.getSubmittedHomeworkList();
            assertEquals(2, incompleteAssignments.size());
            assertEquals(0, submittedAssignments.size());
            checkAssignment("1", "1", 1,1, incompleteAssignments.get(0));
            checkAssignment("2", "2", 2,2, incompleteAssignments.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}