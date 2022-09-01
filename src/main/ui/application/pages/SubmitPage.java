package ui.application.pages;

import model.HomeworkList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Page used to submit assignments

public class SubmitPage extends Page {

    private JButton cancelButtonSubmitPage;
    private JButton submitButtonSubmitPage;

    private JTextField submitTextField;

    //MODIFIES: this
    //EFFECTS: Constructs Page, initializes its window name, and sets homeworkListPage;
    public SubmitPage(HomeworkList hwl) {
        setName("Submit Page");
        homeworkListPage = hwl;
    }

    //MODIFIES: this
    //EFFECTS: adds label and textField prompting an assignment to submit to topArea
    @Override
    public void topItemsToAdd() {
        JLabel submitLabel;

        makeItem(topArea, "Label","Select Number to Submit:","no","BOLD",14);
        submitLabel = label;
        submitLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        makeItem(topArea,"Text","","","",0);
        submitTextField = textField;

    }

    //MODIFIES: this
    //EFFECTS: adds labels displaying all incomplete assignments to assignmentArea;
    //         renders image in imageArea;
    @Override
    public void middleItemsToAdd() {

        displayAssignments(assignmentArea, homeworkListPage.getIncompleteHomeworkList(),"Incomplete");

    }

    //MODIFIES: this
    //EFFECTS: adds buttons for submitting assignment or canceling to bottomArea
    @Override
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button","Cancel","no","PLAIN",14);
        cancelButtonSubmitPage = button;

        makeItem(bottomArea, "Button","Submit","no","PLAIN",14);
        submitButtonSubmitPage = button;

    }


    public JButton getCancelButtonSubmitPage() {
        return cancelButtonSubmitPage;
    }

    public JButton getSubmitButtonSubmitPage() {
        return submitButtonSubmitPage;
    }

    public JTextField getSubmitTextField() {
        return submitTextField;
    }
}
