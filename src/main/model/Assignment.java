package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an assignment with a title,subject,due date,date of expected completion,and completion status
public class Assignment implements Writable {

    private String title;                 //Assignment title
    private String subject;               //Assignment subject
    private int dueDate;                  //Assignment due-date
    private int expectedCompletionDate;   //Date you plan on completing the Assignment
    private String completionStatus;      //Whether assignment is incomplete or submitted

    private String expectedCompletionYear;
    private String expectedCompletionMonth;
    private String expectedCompletionDay;
    private String dueDateYear;
    private String dueDateMonth;
    private String dueDateDay;

    /*
     * REQUIRES: assignmentTitle and assignmentSubject have a non-zero length,
     * assignmentDueDate and assignmentExpectedCompletionDate are 8 digit positive integers of format YYYY/MM/DD
     * EFFECTS: Assignment title is set to assignmentTitle;
     *          Assignment subject is set to assignmentSubject;
     *          Assignment due-date is set to assignmentDueDate;
     *          Date you plan on completing the Assignment is set to assignmentExpectedCompletionDate
     *          Completion status of Assignment is set to "Incomplete"
     */
    public Assignment(String title, String subject, int dueDate,
                      int expectedCompletionDate) {

        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.expectedCompletionDate = expectedCompletionDate;
        completionStatus = "Incomplete";
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public int getDueDate() {
        return dueDate;
    }

    public int getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String s) {
        completionStatus = s;
    }

    public String getExpectedCompletionYear() {
        return expectedCompletionYear;
    }

    public String getExpectedCompletionMonth() {
        return expectedCompletionMonth;
    }

    public String getExpectedCompletionDay() {
        return expectedCompletionDay;
    }

    public String getDueDateYear() {
        return dueDateYear;
    }

    public String getDueDateMonth() {
        return dueDateMonth;
    }

    public String getDueDateDay() {
        return dueDateDay;
    }

    /*
     * REQUIRES: completionDateLength > 0
     * MODIFIES: this
     * EFFECTS: separates 8 digit int completionDate into Year (first 4 digits), Month (5th and 6th digits),
     * Day (7th and 8th digits);
     * "xx" is produced if there are no values either Month or Date
     */
    public void extractCompletionDates() {

        String expectedCompletionDateString = Integer.toString(getExpectedCompletionDate());
        int completionDateLength = expectedCompletionDateString.length();

        if (completionDateLength <= 4) {
            expectedCompletionYear = expectedCompletionDateString.substring(0, completionDateLength);
            expectedCompletionMonth = "xx";
            expectedCompletionDay = "xx";
        } else if (completionDateLength <= 6) {
            expectedCompletionYear = expectedCompletionDateString.substring(0, 4);
            expectedCompletionMonth = expectedCompletionDateString.substring(4, completionDateLength);
            expectedCompletionDay = "xx";
        } else if (completionDateLength < 8) {
            expectedCompletionYear = expectedCompletionDateString.substring(0, 4);
            expectedCompletionMonth = expectedCompletionDateString.substring(4, 6);
            expectedCompletionDay = expectedCompletionDateString.substring(6, completionDateLength);
        } else {
            expectedCompletionYear = expectedCompletionDateString.substring(0, 4);
            expectedCompletionMonth = expectedCompletionDateString.substring(4, 6);
            expectedCompletionDay = expectedCompletionDateString.substring(6, 8);
        }
    }

    /*
     * REQUIRES: dueDateLength > 0
     * MODIFIES: this
     * EFFECTS: separates 8 digit int dueDate into Year (first 4 digits), Month (5th and 6th digits),
     * Day (7th and 8th digits)
     * "xx" is produced if there are no values either Month or Date
     */
    public void extractDueDates() {

        String dueDateString = Integer.toString(getDueDate());
        int dueDateLength = dueDateString.length();

        if (dueDateLength <= 4) {
            dueDateYear = dueDateString.substring(0, dueDateLength);
            dueDateMonth = "xx";
            dueDateDay = "xx";
        } else if (dueDateLength <= 6) {
            dueDateYear = dueDateString.substring(0, 4);
            dueDateMonth = dueDateString.substring(4, dueDateLength);
            dueDateDay = "xx";
        } else if (dueDateLength < 8) {
            dueDateYear = dueDateString.substring(0, 4);
            dueDateMonth = dueDateString.substring(4, 6);
            dueDateDay = dueDateString.substring(6, dueDateLength);
        } else {
            dueDateYear = dueDateString.substring(0, 4);
            dueDateMonth = dueDateString.substring(4, 6);
            dueDateDay = dueDateString.substring(6, 8);
        }
    }

    // Converts assignment to JSON Object
    //**Method modeled after JSONSerializationDemo**
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Subject", subject);
        json.put("Due-Date", dueDate);
        json.put("Expected Completion Date", expectedCompletionDate);
        return json;
    }
}


