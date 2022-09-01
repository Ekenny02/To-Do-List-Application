package ui.application.pages;

import model.HomeworkList;

import javax.swing.*;

// Page used to save current homeworkList

public class SavePage extends Page {

    private JButton yesButtonSavePage;
    private JButton noButtonSavePage;

    //MODIFIES: this
    //EFFECTS: Constructs Page, initializes its window name, and sets homeworkListPage;
    public SavePage(HomeworkList hwl) {
        setName("Save Page");
        homeworkListPage = hwl;
    }

    //MODIFIES: this
    //EFFECTS: adds label "Save this HomeworkList?" to middleArea
    @Override
    public void middleItemsToAdd() {

        makeItem(middleArea, "Label", "Save this HomeworkList?", "yes", "BOLD",
                24);

    }

    //MODIFIES: this
    //EFFECTS: adds buttons to either save current homeworkList or terminate application to bottomArea;
    public void bottomItemsToAdd() {

        makeItem(bottomArea, "Button", "yes", "no", "PLAIN", 14);
        yesButtonSavePage = button;

        makeItem(bottomArea, "Button", "No", "no", "PLAIN", 14);
        noButtonSavePage = button;

    }

    public JButton getYesButtonSavePage() {
        return yesButtonSavePage;
    }

    public JButton getNoButtonSavePage() {
        return noButtonSavePage;
    }
}
