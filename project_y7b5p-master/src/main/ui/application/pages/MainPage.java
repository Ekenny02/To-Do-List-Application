package ui.application.pages;

import javax.swing.*;

// Main Page used to select assignments to view

public class MainPage extends Page {

    private JButton submittedButtonMainPage;
    private JButton incompleteButtonMainPage;

    //MODIFIES: this
    //EFFECTS: Constructs Page and initializes its window name;

    public MainPage() {
        setName("Main Page");
    }

    //MODIFIES: this
    //EFFECTS: adds label "Ethan's Homework List Application" to middleArea
    @Override
    public void middleItemsToAdd() {

        makeItem(middleArea,"Label", "Ethan's Homework List Application", "yes",
                "BOLD", 24);

    }

    //MODIFIES: this
    //EFFECTS: adds buttons to redirect to incompletePage and submittedPage to bottomArea
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button","Incomplete Assignments","no","PLAIN",
                14);

        incompleteButtonMainPage = button;

        makeItem(bottomArea, "Button","Submitted Assignments","no","PLAIN",
                14);

        submittedButtonMainPage = button;

    }

    public JButton getIncompleteButtonMainPage() {
        return incompleteButtonMainPage;
    }

    public JButton getSubmittedButtonMainPage() {
        return submittedButtonMainPage;
    }
}