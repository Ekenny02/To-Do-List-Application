package ui.application.pages;

import model.Assignment;
import model.HomeworkList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

//abstract class for application pages (JPanels)

public abstract class Page extends JPanel {

    protected JPanel topArea;
    protected JPanel middleArea;
    protected JPanel bottomArea;

    protected HomeworkList homeworkListPage;

    private int size = 0;

    protected JLabel label;
    protected JButton button;
    protected JTextField textField;

    protected JPanel assignmentArea;
    protected JPanel imageArea;

    protected ImageIcon imageIcon;

    //MODIFIES: this
    //EFFECTS: Constructs JPanel and initializes panels/constructors;
    public Page() {
        setLayout(new BorderLayout());

        topArea = new JPanel();
        middleArea = new JPanel();
        bottomArea = new JPanel();

        label = new JLabel();
        button = new JButton();

        homeworkListPage = new HomeworkList("My Homework List");
        assignmentArea = new JPanel();
        imageArea = new JPanel();
        imageIcon = new ImageIcon();

        createTopItems();
        createMiddleItems();
        createBottomItems();
    }

    //MODIFIES: this
    //EFFECTS: initializes JPanel at top of application
    public void createTopItems() {
        topArea = new JPanel();
        topArea.setLayout(new GridLayout(1, 0));
        add(topArea, BorderLayout.NORTH);

        topItemsToAdd();

    }

    //EFFECTS: abstract method to add items to JPanel at top of application
    public void topItemsToAdd() {
    }

    //MODIFIES: this
    //EFFECTS: initializes JPanel in middle of application
    public void createMiddleItems() {
        middleArea = new JPanel();
        middleArea.setLayout(new GridLayout(1, 0));
        middleArea.setBorder(new EmptyBorder(0, 20, 0, 0));
        add(middleArea, BorderLayout.CENTER);

        middleItemsToAdd();
    }

    //EFFECTS: abstract method to add items to JPanel in middle of application
    public void middleItemsToAdd() {
    }

    //MODIFIES: this
    //EFFECTS: initializes JPanel at bottom of application
    public void createBottomItems() {
        bottomArea = new JPanel();
        bottomArea.setLayout(new GridLayout(1, 0));
        add(bottomArea, BorderLayout.SOUTH);

        bottomItemsToAdd();

    }

    //EFFECTS: abstract method to add items to JPanel at bottom of application
    public void bottomItemsToAdd() {
    }

    //MODIFIES: this
    //EFFECTS: creates 2 panels for assignment displaying pages. One contains an image and the other contains either
    //         incomplete or submitted assignments in list format.
    public void displayAssignments(JPanel assignmentArea, List<Assignment> listOfAssignments, String status) {

        this.assignmentArea = assignmentArea;

        customRepaint();

        assignmentArea = new JPanel();
        imageArea = new JPanel();

        assignmentArea.setLayout(new GridLayout(0, 1));
        imageArea.setLayout(new GridLayout(0, 1));

        middleArea.add(assignmentArea);
        middleArea.add(imageArea);

        imageIcon = new ImageIcon("./data/image.png");

        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        JLabel label = new JLabel(imageIcon);
        imageArea.add(label);

        initializeAssignments(assignmentArea, listOfAssignments, status);
    }

    //MODIFIES: this
    //EFFECTS: clears all items from area in which it is called
    public void customRepaint() {
        middleArea.removeAll();
        middleArea.validate();
        middleArea.repaint();
    }

    //MODIFIES: this
    //EFFECTS: helper method for displayAssignments.
    //         if size == 0, outputs label no assignments to assignmentArea.
    //         if size > 0, outputs labels for all assignments in homeworkListPage
    public void initializeAssignments(JPanel assignmentArea, List<Assignment> listOfAssignments, String status) {

        if (status.equals("Incomplete")) {
            size = homeworkListPage.incompleteListSize();
        } else if (status.equals("Submitted")) {
            size = homeworkListPage.submittedListSize();
        }

        makeItem(assignmentArea, "Label", status + " Assignments:", "no", "BOLD",
                24);

        if (size == 0) {

            makeItem(assignmentArea, "Label", "You Have 0 " + status + " Assignments", "no",
                    "PLAIN", 14);

        } else if (size > 0) {
            int positionInList = 1;
            for (Assignment assignment : listOfAssignments) {

                extractDates(assignment);

                makeItem(assignmentArea, "Label", positionInList + ". " + assignment.getSubject() + "-"
                        + assignment.getTitle() + "(" + " Date of Expected Completion: "
                        + assignment.getExpectedCompletionYear() + "/" + assignment.getExpectedCompletionMonth()
                        + "/" + assignment.getExpectedCompletionDay() + "," + " Due Date: "
                        + assignment.getDueDateYear() + "/" + assignment.getDueDateMonth() + "/"
                        + assignment.getDueDateDay() + ")", "no", "PLAIN", 14);
                positionInList++;
            }
        }
    }

    //EFFECTS: helper method for initializeAssignments. gets day, month, and year from assignment's
    //         dueDate and expectedCompletionDate
    public void extractDates(Assignment assignment) {
        assignment.extractCompletionDates();
        assignment.extractDueDates();
    }

    //MODIFIES: this
    //EFFECTS: makes label, button, or textField based on inputs.
    //         if itemType == "Button", creates JButton with custom font, text, and adds it to area.
    //         if itemType == "Label", creates JLabel with custom font, fontType, centering, text, and adds it to area.
    //         if itemType == "Text", creates JTextField with custom font and adds it to area.
    public void makeItem(JPanel area, String itemType, String text, String centering, String fontType, int fontSize) {
        Font font = new Font("American Typewriter", Font.PLAIN, fontSize);

        if (itemType.equals("Button")) {

            button = new JButton(text);
            button.setFont(font);
            area.add(button);

        } else if (itemType.equals("Label")) {
            label = new JLabel();

            if (centering.equals("yes")) {
                label = new JLabel(text, SwingConstants.CENTER);
            } else if (centering.equals("no")) {
                label = new JLabel(text);
            }
            if (fontType.equals("BOLD")) {
                font = new Font("American Typewriter", Font.BOLD, fontSize);
            }
            label.setFont(font);
            area.add(label);
        } else if (itemType.equals("Text")) {
            textField = new JTextField();
            area.add(textField);

        }
    }
}