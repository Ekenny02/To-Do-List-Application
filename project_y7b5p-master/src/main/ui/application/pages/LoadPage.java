package ui.application.pages;

import model.HomeworkList;

import javax.swing.*;

// Page used to load previous homeworkList

public class LoadPage extends Page {

    private JButton yesButtonLoadHomeworkListPage;
    private JButton noButtonLoadHomeworkListPage;

    //MODIFIES: this
    //EFFECTS: Constructs Page, initializes its window name, and sets homeworkListPage;
    public LoadPage(HomeworkList hwl) {
        homeworkListPage = hwl;
    }

    //MODIFIES: this
    //EFFECTS: adds label "Load Previous HomeworkList?" to middleArea
    @Override
    public void middleItemsToAdd() {

        makeItem(middleArea, "Label", "Load Previous HomeworkList?", "yes",
                "BOLD", 24);

    }

    //MODIFIES: this
    //EFFECTS: adds buttons to either load previous homeworkList or continue to new homeworkList to bottomArea;
    public void bottomItemsToAdd() {

        makeItem(bottomArea,"Button","Yes","no","PLAIN",14);
        yesButtonLoadHomeworkListPage = button;

        makeItem(bottomArea,"Button","No","no","PLAIN",14);
        noButtonLoadHomeworkListPage = button;

    }

    public JButton getYesButtonLoadHomeworkListPage() {
        return yesButtonLoadHomeworkListPage;
    }

    public JButton getNoButtonLoadHomeworkListPage() {
        return noButtonLoadHomeworkListPage;
    }
}
