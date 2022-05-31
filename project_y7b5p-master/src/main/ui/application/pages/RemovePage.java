package ui.application.pages;

import model.HomeworkList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Page used to remove assignments

public class RemovePage extends Page {

    private JButton cancelButtonRemovePage;
    private JButton removeButtonRemovePage;

    private JTextField removeTextField;

    //MODIFIES: this
    //EFFECTS: Constructs Page, initializes its window name, and sets homeworkListPage;
    public RemovePage(HomeworkList hwl) {
        setName("Remove Page");
        homeworkListPage = hwl;
    }

    //MODIFIES: this
    //EFFECTS: adds label and textField prompting an assignment to remove to topArea
    @Override
    public void topItemsToAdd() {
        JLabel removeLabel;

        makeItem(topArea, "Label","Select Number to Remove:","no","BOLD",14);
        removeLabel = label;
        removeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        makeItem(topArea,"Text","","","",0);
        removeTextField = textField;
    }

    //MODIFIES: this
    //EFFECTS: adds labels displaying all incomplete assignments to assignmentArea;
    //         renders image in imageArea;
    @Override
    public void middleItemsToAdd() {

        displayAssignments(assignmentArea, homeworkListPage.getIncompleteHomeworkList(),"Incomplete");

    }

    //MODIFIES: this
    //EFFECTS: adds buttons for removing assignment or canceling to bottomArea
    @Override
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button","Cancel","no","PLAIN",14);
        cancelButtonRemovePage = button;

        makeItem(bottomArea, "Button","Remove","no","PLAIN",14);
        removeButtonRemovePage = button;

    }

    public JButton getCancelButtonRemovePage() {
        return cancelButtonRemovePage;
    }

    public JButton getRemoveButtonRemovePage() {
        return removeButtonRemovePage;
    }

    public JTextField getRemoveTextField() {
        return removeTextField;
    }
}