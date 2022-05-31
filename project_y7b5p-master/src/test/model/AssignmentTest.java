package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Assignment class

public class AssignmentTest {
    private Assignment testAssignment1;
    private Assignment testAssignment2;
    private Assignment testAssignment3;
    private Assignment testAssignment4;

    @BeforeEach
    void runBefore() {
        testAssignment1 = new Assignment("Project Deliverable 1", "CPSC 210", 20211015, 20211014);
        testAssignment2 = new Assignment("Project Deliverable 1", "CPSC 210", 2021101, 2021101);
        testAssignment3 = new Assignment("Project Deliverable 1", "CPSC 210", 202110, 202110);
        testAssignment4 = new Assignment("Project Deliverable 1", "CPSC 210", 2021, 2021);
    }

    @Test
    void testConstructors() {
        assertEquals("Project Deliverable 1", testAssignment1.getTitle());
        assertEquals("CPSC 210", testAssignment1.getSubject());
        assertEquals(20211015, testAssignment1.getDueDate());
        assertEquals(20211014, testAssignment1.getExpectedCompletionDate());

    }

    @Test
    void testSetCompletionStatus() {
        assertEquals("Incomplete", testAssignment1.getCompletionStatus());
        testAssignment1.setCompletionStatus("Submitted");
        assertEquals("Submitted", testAssignment1.getCompletionStatus());
    }

    @Test
    void testSetCompletion() {
        assertEquals("Incomplete", testAssignment1.getCompletionStatus());
        testAssignment1.setCompletionStatus("Submitted");
        assertEquals("Submitted", testAssignment1.getCompletionStatus());
    }

    @Test
    void testGetExpectedCompletionEight() {
        testAssignment1.extractCompletionDates();

        assertEquals(testAssignment1.getExpectedCompletionYear(),"2021");
        assertEquals(testAssignment1.getExpectedCompletionMonth(),"10");
        assertEquals(testAssignment1.getExpectedCompletionDay(),"14");
    }

    @Test
    void testGetDueDateEight() {
        testAssignment1.extractDueDates();

        assertEquals(testAssignment1.getDueDateYear(),"2021");
        assertEquals(testAssignment1.getDueDateMonth(),"10");
        assertEquals(testAssignment1.getDueDateDay(),"15");
    }

    @Test
    void testGetExpectedCompletionSeven() {
        testAssignment2.extractCompletionDates();

        assertEquals(testAssignment2.getExpectedCompletionYear(),"2021");
        assertEquals(testAssignment2.getExpectedCompletionMonth(),"10");
        assertEquals(testAssignment2.getExpectedCompletionDay(),"1");
    }

    @Test
    void testGetDueDateSeven() {
        testAssignment2.extractDueDates();

        assertEquals(testAssignment2.getDueDateYear(),"2021");
        assertEquals(testAssignment2.getDueDateMonth(),"10");
        assertEquals(testAssignment2.getDueDateDay(),"1");
    }

    @Test
    void testGetExpectedCompletionSix() {
        testAssignment3.extractCompletionDates();

        assertEquals(testAssignment3.getExpectedCompletionYear(),"2021");
        assertEquals(testAssignment3.getExpectedCompletionMonth(),"10");
        assertEquals(testAssignment3.getExpectedCompletionDay(),"xx");
    }

    @Test
    void testGetDueDateSix() {
        testAssignment3.extractDueDates();

        assertEquals(testAssignment3.getDueDateYear(),"2021");
        assertEquals(testAssignment3.getDueDateMonth(),"10");
        assertEquals(testAssignment3.getDueDateDay(),"xx");
    }

    @Test
    void testGetExpectedCompletionFour() {
        testAssignment4.extractCompletionDates();

        assertEquals(testAssignment4.getExpectedCompletionYear(),"2021");
        assertEquals(testAssignment4.getExpectedCompletionMonth(),"xx");
        assertEquals(testAssignment4.getExpectedCompletionDay(),"xx");
    }

    @Test
    void testGetDueDateFour() {
        testAssignment4.extractDueDates();

        assertEquals(testAssignment4.getDueDateYear(),"2021");
        assertEquals(testAssignment4.getDueDateMonth(),"xx");
        assertEquals(testAssignment4.getDueDateDay(),"xx");
    }

}