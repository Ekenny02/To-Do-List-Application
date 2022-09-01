package persistence;

import model.Assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

// checks JSON Object title, subject, due-date, and expected completion date match respective assignment
public class JsonTest {
    protected void checkAssignment(String title, String subject, int dueDate,int expectedCompletionDate,
                                   Assignment assignment) {
        assertEquals(title, assignment.getTitle());
        assertEquals(subject, assignment.getSubject());
        assertEquals(dueDate, assignment.getDueDate());
        assertEquals(expectedCompletionDate, assignment.getExpectedCompletionDate());
    }
}

