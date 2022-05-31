package ui.application.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Page used to add new assignments

public class AddPage extends Page {

    private JButton cancelButtonAddPage;
    private JButton addButtonAddPage;

    private JTextField title;
    private JTextField subject;
    private JTextField expectedCompletionDate;
    private JTextField dueDate;

    //MODIFIES: this
    //EFFECTS: Constructs Page and initializes its window name;
    public AddPage() {
        setName("Add Page");
    }

    //MODIFIES: this
    //EFFECTS: adds labels to distinguish different textFields to topArea
    @Override
    public void topItemsToAdd() {

        makeItem(topArea, "Label", "Title:", "yes", "BOLD", 14);

        makeItem(topArea, "Label", "Subject:", "yes", "BOLD", 14);

        makeItem(topArea, "Label", "Expected Completion Date (YYYYMMDD):", "yes",
                "BOLD", 14);

        makeItem(topArea, "Label", "Due Date (YYYYMMDD):", "yes", "BOLD",
                14);

    }

    //MODIFIES: this
    //EFFECTS: adds textFields for input of assignment parameters to middleArea
    @Override
    public void middleItemsToAdd() {

        middleArea.setBorder(new EmptyBorder(200, 0, 200, 0));

        makeItem(middleArea,"Text","","","",0);
        title = textField;

        makeItem(middleArea,"Text","","","",0);
        subject = textField;

        makeItem(middleArea,"Text","","","",0);
        expectedCompletionDate = textField;

        makeItem(middleArea,"Text","","","",0);
        dueDate = textField;

    }

    //MODIFIES: this
    //EFFECTS: adds buttons for adding assignment or canceling to bottomArea
    @Override
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button", "Cancel", "no", "PLAIN", 14);
        cancelButtonAddPage = button;

        makeItem(bottomArea, "Button", "Add", "no", "PLAIN", 14);
        addButtonAddPage = button;

    }

    public JButton getCancelButtonAddPage() {
        return cancelButtonAddPage;
    }

    public JButton getAddButtonAddPage() {
        return addButtonAddPage;
    }

    public JTextField getTitle() {
        return title;
    }

    public JTextField getSubject() {
        return subject;
    }

    public JTextField getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public JTextField getDueDate() {
        return dueDate;
    }
}