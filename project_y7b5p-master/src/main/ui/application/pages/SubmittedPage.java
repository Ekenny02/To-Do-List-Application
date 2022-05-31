package ui.application.pages;

import model.HomeworkList;

import javax.swing.*;

// Page used to display submitted assignments

public class SubmittedPage extends Page {

    private JButton backButtonSubmittedPage;
    private JButton quitButtonSubmittedPage;

    //MODIFIES: this
    //EFFECTS: Constructs Page, initializes its window name, and sets homeworkListPage;
    public SubmittedPage(HomeworkList hwl) {
        setName("Submitted Assignment Page");
        homeworkListPage = hwl;
    }

    //MODIFIES: this
    //EFFECTS: adds button to go back to mainPage to topArea
    @Override
    public void topItemsToAdd() {

        makeItem(topArea, "Button","Back","no","PLAIN",14);
        backButtonSubmittedPage = button;

    }

    //MODIFIES: this
    //EFFECTS: adds labels displaying all submitted assignments to assignmentArea;
    //         renders image in imageArea;
    @Override
    public void middleItemsToAdd() {
        displayAssignments(assignmentArea, homeworkListPage.getSubmittedHomeworkList(),"Submitted");
    }

    //MODIFIES: this
    //EFFECTS: adds quit button to terminate application to bottomArea;
    @Override
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button", "Quit", "no", "PLAIN", 14);
        quitButtonSubmittedPage = button;

    }

    public JButton getBackButtonSubmittedPage() {
        return backButtonSubmittedPage;
    }

    public JButton getQuitButtonSubmittedPage() {
        return quitButtonSubmittedPage;
    }
}