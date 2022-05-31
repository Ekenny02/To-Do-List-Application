package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for HomeworkList class

public class HomeworkListTest {
    private HomeworkList testHomeworkList1;
    private HomeworkList testHomeworkList2;
    private Assignment assignment1;
    private Assignment assignment2;
    private Assignment assignment3;

    @BeforeEach
    void runBefore() {
        testHomeworkList1 = new HomeworkList("test1");
        assignment1 = new Assignment("Project Deliverable 1", "CPSC 210", 20211015, 20211014);
        assignment2 = new Assignment("Project Deliverable 2", "CPSC 210", 20211029, 20211025);
        assignment3 = new Assignment("Project Deliverable 3", "CPSC 210", 20211209, 20211215);

        testHomeworkList2 = new HomeworkList("test2");
    }

    @Test
    void testAddMultipleAssignments() {
        testHomeworkList1.addAssignment(assignment1);
        testHomeworkList1.addAssignment(assignment2);
        assertEquals(testHomeworkList1.getAssignment(0), assignment1);
        assertEquals(testHomeworkList1.getAssignment(1), assignment2);
    }

    @Test
    void testAddAssignmentLaterCompletionDate() {
        testHomeworkList1.addAssignment(assignment3);
        testHomeworkList1.addAssignment(assignment2);
        testHomeworkList1.addAssignment(assignment1);
        assertEquals(testHomeworkList1.getAssignment(0), assignment1);
        assertEquals(testHomeworkList1.getAssignment(1), assignment2);
        assertEquals(testHomeworkList1.getAssignment(2), assignment3);
    }

    @Test
    void testRemoveAssignmentOne() {
        testHomeworkList1.addAssignment(assignment1);
        testHomeworkList1.addAssignment(assignment2);
        assertEquals(testHomeworkList1.getAssignment(0), assignment1);
        assertEquals(testHomeworkList1.getAssignment(1), assignment2);
        testHomeworkList1.removeAssignment(3);
        assertEquals(testHomeworkList1.getAssignment(0), assignment1);
        assertEquals(testHomeworkList1.getAssignment(1), assignment2);
        testHomeworkList1.removeAssignment(1);
        assertEquals(testHomeworkList1.getAssignment(0), assignment2);
    }

    @Test
    void testSubmitAssignment() {
        testHomeworkList1.addAssignment(assignment1);
        testHomeworkList1.addAssignment(assignment2);
        assertEquals(testHomeworkList1.getAssignment(0), assignment1);
        assertEquals(testHomeworkList1.getAssignment(1), assignment2);
        assertEquals(assignment1.getCompletionStatus(), "Incomplete");
        assertEquals(assignment2.getCompletionStatus(), "Incomplete");
        testHomeworkList1.submitAssignment(3);
        assertEquals(testHomeworkList1.getAssignment(0), assignment1);
        assertEquals(testHomeworkList1.getAssignment(1), assignment2);
        assertEquals(assignment1.getCompletionStatus(), "Incomplete");
        assertEquals(assignment2.getCompletionStatus(), "Incomplete");
        testHomeworkList1.submitAssignment(1);
        assertEquals(testHomeworkList1.getAssignment(0), assignment2);
        assertEquals(assignment1.getCompletionStatus(), "Submitted");
        assertEquals(assignment2.getCompletionStatus(), "Incomplete");
    }

    @Test
    void testIncompleteListSize() {
        assertEquals(testHomeworkList1.incompleteListSize(), 0);
        testHomeworkList1.addAssignment(assignment1);
        assertEquals(testHomeworkList1.incompleteListSize(), 1);
        testHomeworkList1.addAssignment(assignment2);
        assertEquals(testHomeworkList1.incompleteListSize(), 2);
    }

    @Test
    void testSubmittedListSize() {
        assertEquals(testHomeworkList1.submittedListSize(), 0);
        testHomeworkList1.addAssignment(assignment1);
        assertEquals(testHomeworkList1.submittedListSize(), 0);
        testHomeworkList1.addAssignment(assignment2);
        assertEquals(testHomeworkList1.submittedListSize(), 0);
        testHomeworkList1.submitAssignment(1);
        assertEquals(testHomeworkList1.submittedListSize(), 1);
        testHomeworkList1.submitAssignment(1);
        assertEquals(testHomeworkList1.submittedListSize(), 2);
    }

    @Test
    void testSubmittedList() {
        testHomeworkList1.addAssignment(assignment1);
        testHomeworkList1.addAssignment(assignment2);
        assertEquals(testHomeworkList1.getIncompleteHomeworkList().get(0), assignment1);
        assertEquals(testHomeworkList1.getIncompleteHomeworkList().get(1), assignment2);
        testHomeworkList1.submitAssignment(2);
        assertEquals(testHomeworkList1.getSubmittedHomeworkList().get(0), assignment2);
        testHomeworkList1.submitAssignment(1);
        assertEquals(testHomeworkList1.getSubmittedHomeworkList().get(0), assignment1);
        assertEquals(testHomeworkList1.getSubmittedHomeworkList().get(1), assignment2);
    }

    @Test
    void testSetIncompleteHomeworkList() {
        testHomeworkList1.addAssignment(assignment1);
        testHomeworkList1.addAssignment(assignment2);

        testHomeworkList2.setIncompleteHomeworkList(testHomeworkList1.getIncompleteHomeworkList());

        assertEquals(testHomeworkList2.getIncompleteHomeworkList().get(0), assignment1);
        assertEquals(testHomeworkList2.getIncompleteHomeworkList().get(1), assignment2);
    }

    @Test
    void testSetSubmittedHomeworkList() {
        testHomeworkList1.addAssignment(assignment1);
        testHomeworkList1.addAssignment(assignment2);
        testHomeworkList1.submitAssignment(1);

        testHomeworkList2.setSubmittedHomeworkList(testHomeworkList1.getIncompleteHomeworkList());

        assertEquals(testHomeworkList2.getSubmittedHomeworkList().get(0), assignment2);
    }
}
