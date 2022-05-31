package ui.application.pages;

import model.HomeworkList;

import javax.swing.*;

// Page used to display incomplete assignments

public class IncompletePage extends Page {

    private JButton addButtonIncompletePage;
    private JButton removeButtonIncompletePage;
    private JButton submitButtonIncompletePage;
    private JButton backButtonIncompletePage;
    private JButton quitButtonIncompletePage;

    //MODIFIES: this
    //EFFECTS: Constructs Page, initializes its window name, and sets homeworkListPage;
    public IncompletePage(HomeworkList hwl) {
        setName("Incomplete Assignment Page");
        homeworkListPage = hwl;
    }

    //MODIFIES: this
    //EFFECTS: adds buttons to either go back, add, remove, and submit assignments to topArea
    @Override
    public void topItemsToAdd() {

        makeItem(topArea, "Button","Add Assignment","no","PLAIN",14);
        addButtonIncompletePage = button;

        makeItem(topArea, "Button","Remove Assignment","no","PLAIN",14);
        removeButtonIncompletePage = button;

        makeItem(topArea, "Button","Submit Assignment","no","PLAIN",14);
        submitButtonIncompletePage = button;

        makeItem(topArea, "Button","Back","no","PLAIN",14);
        backButtonIncompletePage = button;

    }

    //MODIFIES: this
    //EFFECTS: adds labels displaying all incomplete assignments to assignmentArea;
    //         renders image in imageArea;
    @Override
    public void middleItemsToAdd() {

        displayAssignments(assignmentArea, homeworkListPage.getIncompleteHomeworkList(),"Incomplete");

    }

    //MODIFIES: this
    //EFFECTS: adds quit button to terminate application to bottomArea;
    @Override
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button","Quit","no","PLAIN",14);
        quitButtonIncompletePage = button;

    }

    public JButton getAddButtonIncompletePage() {
        return addButtonIncompletePage;
    }

    public JButton getRemoveButtonIncompletePage() {
        return removeButtonIncompletePage;
    }

    public JButton getSubmitButtonIncompletePage() {
        return submitButtonIncompletePage;
    }

    public JButton getBackButtonIncompletePage() {
        return backButtonIncompletePage;
    }

    public JButton getQuitButtonIncompletePage() {
        return quitButtonIncompletePage;
    }
}