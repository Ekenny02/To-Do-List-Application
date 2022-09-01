package persistence;

import model.HomeworkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


// Tests for JsonReader Class
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HomeworkList hwl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHomeworkList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHomeworkList.json");
        try {
            HomeworkList hwl = reader.read();
            assertEquals("My Homework List", hwl.getListTitle());
            assertEquals(0, hwl.incompleteListSize());
            assertEquals(0, hwl.submittedListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHomeworkListOnlyIncomplete() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHomeworkListOnlyIncomplete.json");
        try {
            HomeworkList hwl = reader.read();
            assertEquals("My Homework List", hwl.getListTitle());
            assertEquals(2, hwl.incompleteListSize());
            checkAssignment("1", "1",1,1,
                    hwl.getIncompleteHomeworkList().get(0));
            checkAssignment("2", "2",2,2,
                    hwl.getIncompleteHomeworkList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderGeneralHomeworkListIncompleteAndSubmitted() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHomeworkListIncompleteAndSubmitted.json");
        try {
            HomeworkList hwl = reader.read();
            assertEquals("My Homework List", hwl.getListTitle());
            assertEquals(2, hwl.incompleteListSize());
            checkAssignment("1", "1",1,1,
                    hwl.getIncompleteHomeworkList().get(0));
            checkAssignment("2", "2",2,2,
                    hwl.getIncompleteHomeworkList().get(1));
            assertEquals(2, hwl.submittedListSize());
            checkAssignment("3", "3",3,3,
                    hwl.getSubmittedHomeworkList().get(0));
            checkAssignment("4", "4",4,4,
                    hwl.getSubmittedHomeworkList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
