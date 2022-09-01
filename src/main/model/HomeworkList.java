package model;

import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Represents the lists of incomplete Assignments and submitted Assignments
public class HomeworkList implements Writable {

    private String listTitle;
    private int positionInList;
    private Boolean isInSet = false;
    protected List<Assignment> incompleteHomeworkList;
    protected List<Assignment> submittedHomeworkList;

    private EventLog eventLog;

    /*
     * EFFECTS: List of incomplete assignments is initialized as a new, empty ArrayList<>();
     *          List of submitted assignments is initialized as a new, empty ArrayList<>();
     *          EventLog instance is created;
     */
    public HomeworkList(String listTitle) {
        this.listTitle = listTitle;
        incompleteHomeworkList = new ArrayList<>();
        submittedHomeworkList = new ArrayList<>();

        eventLog = EventLog.getInstance();
    }

    /*
     * REQUIRES: assignmentTitle and assignmentSubject have a non-zero length,
     * assignmentDueDate and assignmentExpectedCompletionDate are 8 digit integers of format YYYY/MM/DD
     * MODIFIES: this
     * EFFECTS: Each Assignment in incompleteHomeworkList is assigned a position number in ascending order
     *          based on their location within the list;
     *          For each assignment in the list, if their expectedCompletionDate
     *          < expectedCompletionDate of the assignment being added, then their position in the list is noted;
     *          Once all assignments are checked, the inputted assignment is added to the list after the recorded
     *          position of the assignment with the latest expectedCompletionDate that is still < expectedCompletionDate
     *          of the new assignment;
     *          Essentially, this process adds the new assignment to the incompleteHomeworkList in ascending order of
     *          expectedCompletionDate;
     *          When assignment is added, new Event is logged stating "Assignment Entitled: " + assignment.getTitle()
                + " Was Added to Incomplete Assignments";
     */
    public void addAssignment(Assignment assignment) {
        int spotInList = 0;
        positionInList = 1;
        for (Assignment a : incompleteHomeworkList) {
            if (assignment.getExpectedCompletionDate() > a.getExpectedCompletionDate()) {
                spotInList = positionInList;
            }
            positionInList++;
        }
        incompleteHomeworkList.add(spotInList, assignment);
        eventLog.logEvent(new Event("Assignment Entitled: " + assignment.getTitle()
                + " Was Added to Incomplete Assignments"));
    }


    /*
     * REQUIRES: 0 < chosenAssignmentNumber <= incompleteHomeworkList.size()
     * MODIFIES: this
     * EFFECTS: Assignment corresponding to chosenAssignmentNumber is removed from incompleteHomeworkList
     *          When assignment is removed, new Event is logged stating "Assignment Entitled: "
     *          + itemToRemove.getTitle() + " Was Removed from Incomplete Assignments";
     */
    public void removeAssignment(Integer chosenAssignmentNumber) {
        isInSet = false;
        positionInList = 1;
        Assignment itemToRemove = new Assignment("","",0,0);

        for (Assignment assignment : incompleteHomeworkList) {
            if (chosenAssignmentNumber == positionInList) {
                itemToRemove = assignment;
                isInSet = true;
            }
            positionInList++;
        }
        if (isInSet) {
            incompleteHomeworkList.remove(itemToRemove);
            eventLog.logEvent(new Event("Assignment Entitled: " + itemToRemove.getTitle()
                    + " Was Removed from Incomplete Assignments"));
        }
    }

    /*
     * REQUIRES: 0 < chosenAssignmentNumber <= incompleteHomeworkList.size()
     * MODIFIES: this
     * EFFECTS: Assignment corresponding to chosenAssignmentNumber is removed from incompleteHomeworkList;
     *          The assignment is then added to the top of submittedHomeworkList and
     *          its completionStatus is set to "Submitted";
     *          When assignment is submitted, new Event is logged stating "Assignment Entitled: "
     *          + itemToSubmit.getTitle() + " Was Submitted";
     */
    public void submitAssignment(Integer chosenAssignmentNumber) {
        isInSet = false;
        positionInList = 1;
        Assignment itemToSubmit = new Assignment("","",0,0);

        for (Assignment assignment : incompleteHomeworkList) {
            if (chosenAssignmentNumber == positionInList) {
                itemToSubmit = assignment;
                isInSet = true;
            }
            positionInList++;
        }
        if (isInSet) {
            itemToSubmit.setCompletionStatus("Submitted");
            incompleteHomeworkList.remove(itemToSubmit);
            submittedHomeworkList.add(0, itemToSubmit);
            eventLog.logEvent(new Event("Assignment Entitled: " + itemToSubmit.getTitle()
                    + " Was Submitted"));
        }
    }

    /*
     * EFFECTS: returns the length (size()) of incompleteHomeworkList;
     */
    public int incompleteListSize() {
        return incompleteHomeworkList.size();
    }

    /*
     * EFFECTS: returns the length (size()) of submittedHomeworkList;
     */
    public int submittedListSize() {
        return submittedHomeworkList.size();
    }

    public Assignment getAssignment(Integer position) {
        return incompleteHomeworkList.get(position);
    }

    public List<Assignment> getIncompleteHomeworkList() {
        return incompleteHomeworkList;
    }

    public List<Assignment> getSubmittedHomeworkList() {
        return submittedHomeworkList;
    }

    public List<Assignment> setIncompleteHomeworkList(List<Assignment> las) {
        incompleteHomeworkList = las;
        return incompleteHomeworkList;
    }

    public List<Assignment> setSubmittedHomeworkList(List<Assignment> las) {
        submittedHomeworkList = las;
        return submittedHomeworkList;
    }

    public String getListTitle() {
        return listTitle;
    }

    public EventLog getEventLog() {
        return eventLog;
    }


    // Converts HomeworkList to a JSON Object
    //**Method modeled after JSONSerializationDemo**
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List Title", listTitle);
        json.put("Incomplete Assignments", assignmentsToJson(incompleteHomeworkList));
        json.put("Submitted Assignments", assignmentsToJson(submittedHomeworkList));

        return json;
    }

    // EFFECTS: returns assignments in this HomeworkList as a JSON array and logs them as new events,
    //          either incomplete or submitted;
    //**Method modeled after JSONSerializationDemo**
    private JSONArray assignmentsToJson(List<Assignment> listOfAssignments) {
        JSONArray jsonArray = new JSONArray();

        for (Assignment assignment : listOfAssignments) {
            jsonArray.put(assignment.toJson());

            if (listOfAssignments.equals(incompleteHomeworkList)) {
                eventLog.logEvent(new Event("Incomplete Assignment Entitled: " + assignment.getTitle()
                        + " Was Saved to File"));
            } else if (listOfAssignments.equals(submittedHomeworkList)) {
                eventLog.logEvent(new Event("Submitted Assignment Entitled: " + assignment.getTitle()
                        + " Was Saved to File"));
            }

        }
        return jsonArray;
    }
}
