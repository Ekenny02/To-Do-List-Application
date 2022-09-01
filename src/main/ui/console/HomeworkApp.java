package ui.console;

import model.Assignment;
import model.HomeworkList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Homework to-do list application
public class HomeworkApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private HomeworkList homeworkList;
    private int positionInList;
    private Scanner input;

    //EFFECTS: Constructs homeworkList and runs application
    public HomeworkApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        homeworkList = new HomeworkList("Ethan's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHomework();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    //**Method modeled after TellerApp**
    private void runHomework() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        start();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        end();

        System.out.println("\nHappy Studying!!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    //**Method modeled after TellerApp**
    private void processCommand(String command) {
        if (command.equals("1")) {
            doIncomplete();
        } else if (command.equals("2")) {
            doSubmitted();
        } else if (command.equals("3")) {
            doNewAssignment();
        } else if (command.equals("4")) {
            doRemoveAssignment();
        } else if (command.equals("5")) {
            doSubmitAssignment();
        } else {
            System.out.println("Please Select One of the Options Above");
        }
    }

    // EFFECTS: displays menu of options to user
    //**Method modeled after TellerApp**
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1. -> List of All Incomplete Assignments");
        System.out.println("\t2. -> List of All Submitted Assignments");
        System.out.println("\t3. -> Add New Assignment");
        System.out.println("\t4. -> Remove Assignment");
        System.out.println("\t5. -> Submit Assignment");
        System.out.println("\tquit -> Close Application");
    }


    // EFFECTS: returns list of all incomplete assignments
    private void doIncomplete() {

        if (homeworkList.incompleteListSize() > 0) {
            String selection = "";  // force entry into loop

            while (!(selection.equals("back"))) {

                viewIncomplete();

                System.out.println("Enter 'back' to Return to Main Menu...");
                selection = input.next();
            }
        } else {
            System.out.println("There Are No Incomplete Assignments...");
            System.out.println("Enter Any Key to Return to Main Menu...");
            input.next();
        }
    }

    // EFFECTS: returns list of all submitted assignments
    private void doSubmitted() {

        if (homeworkList.submittedListSize() > 0) {
            String selection = "";  // force entry into loop

            while (!(selection.equals("back"))) {

                viewSubmitted();

                System.out.println("Enter 'back' to Return to Main Menu...");
                selection = input.next();
            }
        } else {
            System.out.println("There Are No Submitted Assignments...");
            System.out.println("Enter Any Key to Return to Main Menu...");
            input.next();
        }
    }

    // REQUIRES: assignmentTitle and assignmentSubject have a non-zero length,
    // assignmentDueDate and assignmentExpectedCompletionDate are 8 digit integers of format YYYY/MM/DD
    // MODIFIES: this, homeworkList
    // EFFECTS: adds assignment to list of incomplete assignments
    private void doNewAssignment() {

        System.out.print("Enter Assignment's Subject: ");
        String subject = input.next();
        System.out.print("Enter Assignment's Title: ");
        String title = input.next();
        System.out.print("Enter Date of Expected Completion: ");
        int expectedCompletionDate = input.nextInt();
        System.out.print("Enter Due Date: ");
        int dueDate = input.nextInt();

        Assignment newAssignment;
        newAssignment = new Assignment(title, subject, dueDate, expectedCompletionDate);
        homeworkList.addAssignment(newAssignment);
    }

    // REQUIRES: 0 < chosenPositionInList <= homeworkList.getIncompleteHomeworkList().size()
    // MODIFIES: this, homeworkList
    // EFFECTS: removes assignment from list of incomplete assignments
    private void doRemoveAssignment() {
        if (homeworkList.incompleteListSize() > 0) {

            viewIncomplete();

            System.out.print("Select an Assignment to Remove: ");
            int chosenPositionInList = input.nextInt();

            homeworkList.removeAssignment(chosenPositionInList);

        } else {
            System.out.println("There Are No Incomplete Assignments to Remove...");
            System.out.println("Enter Any Key to Return to Main Menu...");
            input.next();
        }
    }

    // REQUIRES: 0 < chosenPositionInList <= homeworkList.getIncompleteHomeworkList().size()
    // MODIFIES: this, homeworkList
    // EFFECTS: Changes assignment completionStatus to "Submitted", removes assignment from incompleteHomeworkList list,
    // and adds Assignment to submittedHomeworkList
    private void doSubmitAssignment() {
        if (homeworkList.incompleteListSize() > 0) {

            viewIncomplete();

            System.out.print("Select an Assignment to Submit: ");
            int chosenPositionInList = input.nextInt();

            homeworkList.submitAssignment(chosenPositionInList);

        } else {
            System.out.println("There Are No Incomplete Assignments to Submit...");
            System.out.println("Enter Any Key to Return to Main Menu...");
            input.next();
        }
    }

    // EFFECTS: saves the homeworkList to file
    //**Method modeled after JSONSerializationDemo**
    private void saveHomeworkList() {
        try {
            jsonWriter.open();
            jsonWriter.write(homeworkList);
            jsonWriter.close();
            System.out.println("Saved " + homeworkList.getListTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads homeworkList from file
    //**Method modeled after JSONSerializationDemo**
    private void loadHomeworkList() {
        try {
            homeworkList = jsonReader.read();
            System.out.println("Loaded " + homeworkList.getListTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    /*
     * REQUIRES: chosenHomeworkList.size() > 0
     * MODIFIES: this
     * EFFECTS: Abstract method to present chosenHomeworkList in list format and each of its assignment's
     *          relevant information
     */
    public void viewList(List<Assignment> chosenHomeworkList) {
        positionInList = 1;
        for (Assignment assignment : chosenHomeworkList) {

            assignment.extractCompletionDates();
            assignment.extractDueDates();

            System.out.println(positionInList + ". " + assignment.getSubject() + "-" + assignment.getTitle()
                    + "(" + " Date of Expected Completion: " + assignment.getExpectedCompletionYear() + "/"
                    + assignment.getExpectedCompletionMonth() + "/" + assignment.getExpectedCompletionDay() + ","
                    + " Due Date: " + assignment.getDueDateYear() + "/" + assignment.getDueDateMonth()
                    + "/" + assignment.getDueDateDay() + ")");
            positionInList++;
        }
    }

    /*
     * REQUIRES: chosenHomeworkList.size() > 0
     * MODIFIES: this
     * EFFECTS: Presents incompleteHomeworkList in list format and each of its assignment's relevant information
     */
    public void viewIncomplete() {
        viewList(homeworkList.getIncompleteHomeworkList());
    }

    /*
     * REQUIRES: chosenHomeworkList.size() > 0
     * MODIFIES: this
     * EFFECTS: Presents submittedHomeworkList in list format and each of its assignment's relevant information
     */
    public void viewSubmitted() {
        viewList(homeworkList.getSubmittedHomeworkList());
    }

    /*
     * EFFECTS: if input is "1", load previous HomeworkList, else continue to application;
     */
    public void start() {
        System.out.println("\tWould You Like to Load Your Previous Work?");
        System.out.println("\t1. -> Yes");
        System.out.println("\tAny Other Key -> No");
        String decision = input.next();

        if (decision.equals("1")) {
            loadHomeworkList();
        }
    }

    /*
     * EFFECTS: if input is "1", save current HomeworkList and quit application, else quit application;
     */
    public void end() {
        System.out.println("\tWould You Like to Save Your Work?");
        System.out.println("\t1. -> Yes");
        System.out.println("\tAny Other Key -> No");
        String decision = input.next();

        if (decision.equals("1")) {
            saveHomeworkList();
        }
    }

    public static void main(String[] args) {
        try {
            new HomeworkApp();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to run application: file not found");
        }
    }
}