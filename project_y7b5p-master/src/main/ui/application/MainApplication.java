package ui.application;

import model.Assignment;
import model.Event;
import model.HomeworkList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.application.pages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Runs/initializes homework to-do list JFrame Application

public class MainApplication extends JFrame implements ActionListener, WindowListener {

    private JLayeredPane contentPane;

    private LoadPage loadHomeworkListPage;
    private MainPage mainPage;
    private IncompletePage incompletePage;
    private SubmittedPage submittedPage;
    private AddPage addPage;
    private RemovePage removePage;
    private SubmitPage submitPage;
    private SavePage savePage;

    private HomeworkList homeworkList;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //MODIFIES: this
    //EFFECTS: Constructs main JFrame and initializes pages/constructors;
    public MainApplication() {

        setTitle("Loading Page");

        homeworkList = new HomeworkList("My Homework List");

        initLoad();
        initMain();
        initIncomplete();
        initSubmitted();
        initAdd();
        initRemove();
        initSubmit();
        initSave();

        initContent();

    }

    //MODIFIES: this
    //EFFECTS: initializes content pane and adds all pages;
    public void initContent() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        contentPane = new JLayeredPane();
        contentPane.setLayout(new CardLayout());

        setContentPane(contentPane);

        contentPane.add(loadHomeworkListPage, "Load Page");
        contentPane.add(mainPage, "Main Page");
        contentPane.add(incompletePage, "Incomplete Page");
        contentPane.add(submittedPage, "Submitted Page");
        contentPane.add(addPage, "Add Page");
        contentPane.add(removePage, "Remove Page");
        contentPane.add(submitPage, "Submit Page");
        contentPane.add(savePage, "Save Page");

        addWindowListener(this);
    }

    //MODIFIES: this, yesButtonLoadHomeworkListPage, noButtonLoadHomeworkListPage, homeworkListPage
    //EFFECTS: initializes JSON reader to load previous HomeworkList;
    //         initializes loadHomeworkListPage and adds action listeners to its buttons;
    public void initLoad() {
        jsonReader = new JsonReader(JSON_STORE);

        loadHomeworkListPage = new LoadPage(homeworkList);
        loadHomeworkListPage.getYesButtonLoadHomeworkListPage().addActionListener(this);
        loadHomeworkListPage.getNoButtonLoadHomeworkListPage().addActionListener(this);
    }

    //MODIFIES: this, incompleteButtonMainPage, submittedButtonMainPage
    //EFFECTS: initializes mainPage and adds action listeners to its buttons;
    public void initMain() {
        mainPage = new MainPage();
        mainPage.getIncompleteButtonMainPage().addActionListener(this);
        mainPage.getSubmittedButtonMainPage().addActionListener(this);
    }

    //MODIFIES: this, addButtonIncompletePage, removeButtonIncompletePage, submitButtonIncompletePage,
    //          backButtonIncompletePage, quitButtonIncompletePage, homeworkListPage
    //EFFECTS: initializes incompletePage, sets its homeworkList, and adds action listeners to its buttons;
    public void initIncomplete() {

        incompletePage = new IncompletePage(homeworkList);

        incompletePage.getAddButtonIncompletePage().addActionListener(this);
        incompletePage.getRemoveButtonIncompletePage().addActionListener(this);
        incompletePage.getSubmitButtonIncompletePage().addActionListener(this);
        incompletePage.getBackButtonIncompletePage().addActionListener(this);
        incompletePage.getQuitButtonIncompletePage().addActionListener(this);
    }

    //MODIFIES: this, backButtonSubmittedPage, quitButtonSubmittedPage, homeworkListPage
    //EFFECTS: initializes submittedPage, sets its homeworkList, and adds action listeners to its buttons;
    public void initSubmitted() {
        submittedPage = new SubmittedPage(homeworkList);

        submittedPage.getBackButtonSubmittedPage().addActionListener(this);
        submittedPage.getQuitButtonSubmittedPage().addActionListener(this);
    }

    //MODIFIES: this, addButtonAddPage, cancelButtonAddPage, title, subject, expectedCompletionDate, dueDate
    //EFFECTS: initializes addPage and adds action listeners to its buttons/JTextFields;
    public void initAdd() {
        addPage = new AddPage();

        addPage.getAddButtonAddPage().addActionListener(this);
        addPage.getCancelButtonAddPage().addActionListener(this);
        addPage.getTitle().addActionListener(this);
        addPage.getSubject().addActionListener(this);
        addPage.getExpectedCompletionDate().addActionListener(this);
        addPage.getDueDate().addActionListener(this);
    }

    //MODIFIES: this, removeButtonRemovePage, cancelButtonRemovePage, removeTextField, homeworkListPage
    //EFFECTS: initializes removePage, sets its homeworkList, and adds action listeners to its buttons/JTextField;
    public void initRemove() {
        removePage = new RemovePage(homeworkList);
        removePage.getRemoveButtonRemovePage().addActionListener(this);
        removePage.getCancelButtonRemovePage().addActionListener(this);
        removePage.getRemoveTextField().addActionListener(this);
    }

    //MODIFIES: this, submitButtonSubmitPage, cancelButtonSubmitPage, submitTextField, homeworkListPage
    //EFFECTS: initializes submitPage, sets its homeworkList, and adds action listeners to its buttons/JTextField;
    public void initSubmit() {
        submitPage = new SubmitPage(homeworkList);
        submitPage.getSubmitButtonSubmitPage().addActionListener(this);
        submitPage.getCancelButtonSubmitPage().addActionListener(this);
        submitPage.getSubmitTextField().addActionListener(this);
    }

    //MODIFIES: this, yesButtonSavePage, noButtonSavePage, homeworkListPage
    //EFFECTS: initializes JSON writer to save current HomeworkList;
    //         initializes savePage, sets its homeworkList, and adds action listeners to its buttons;
    public void initSave() {
        jsonWriter = new JsonWriter(JSON_STORE);

        savePage = new SavePage(homeworkList);
        savePage.getYesButtonSavePage().addActionListener(this);
        savePage.getNoButtonSavePage().addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: handles button and JTextField events
    @Override
    public void actionPerformed(ActionEvent e) {
        handleLoad(e);
        handleMain(e);
        handleSubmitted(e);
        handleIncomplete(e);
        handleAdd(e);
        handleRemove(e);
        handleSubmit(e);
        handleSave(e);
    }

    //MODIFIES: this, assignmentArea, HomeworkListPage
    //EFFECTS: if yesButtonLoadHomeworkListPage is clicked, load previous homeworkList from file, update
    //         assignment areas, and switch to mainPage. if noButtonLoadHomeworkListPage is clicked, switch to mainPage;
    public void handleLoad(ActionEvent e) {
        if (e.getSource() == loadHomeworkListPage.getYesButtonLoadHomeworkListPage()) {
            try {

                for (Assignment assignment : jsonReader.read().getIncompleteHomeworkList()) {
                    homeworkList.addAssignment(assignment);
                }

                for (int i = jsonReader.read().getSubmittedHomeworkList().size() - 1; i >= 0; i--) {

                    homeworkList.getIncompleteHomeworkList().add(0,
                            jsonReader.read().getSubmittedHomeworkList().get(i));

                    homeworkList.submitAssignment(1);
                }

            } catch (IOException ex) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            incompletePage.middleItemsToAdd();
            submittedPage.middleItemsToAdd();
            switchPanels(mainPage);
        } else if (e.getSource() == loadHomeworkListPage.getNoButtonLoadHomeworkListPage()) {
            switchPanels(mainPage);
        }
    }

    //MODIFIES: this
    //EFFECTS: if incompleteButtonMainPage is clicked, switch to incompletePage. if submittedButtonMainPage is clicked,
    //         switch to submittedPage
    public void handleMain(ActionEvent e) {
        if (e.getSource() == mainPage.getIncompleteButtonMainPage()) {
            switchPanels(incompletePage);
        } else if (e.getSource() == mainPage.getSubmittedButtonMainPage()) {
            switchPanels(submittedPage);
        }
    }

    //MODIFIES: this
    //EFFECTS: if backButtonSubmittedPage is clicked, switch to mainPage. if quitButtonSubmittedPage is clicked,
    //         switch to savePage
    public void handleSubmitted(ActionEvent e) {
        if (e.getSource() == submittedPage.getBackButtonSubmittedPage()) {
            switchPanels(mainPage);
        } else if (e.getSource() == submittedPage.getQuitButtonSubmittedPage()) {
            switchPanels(savePage);
        }
    }

    //MODIFIES: this
    //EFFECTS: if addButtonIncompletePage is clicked, switch to addPage. if removeButtonIncompletePage is clicked,
    //         switch to removePage. if submitButtonIncompletePage is clicked, switch to submitPage.
    //         if backButtonIncompletePage is clicked, switch to mainPage. if quitButtonIncompletePage is clicked,
    //         switch to savePage.
    public void handleIncomplete(ActionEvent e) {
        if (e.getSource() == incompletePage.getAddButtonIncompletePage()) {
            switchPanels(addPage);
        } else if (e.getSource() == incompletePage.getRemoveButtonIncompletePage()) {
            removePage.middleItemsToAdd();
            switchPanels(removePage);
        } else if (e.getSource() == incompletePage.getSubmitButtonIncompletePage()) {
            submitPage.middleItemsToAdd();
            switchPanels(submitPage);
        } else if (e.getSource() == incompletePage.getBackButtonIncompletePage()) {
            switchPanels(mainPage);
        } else if (e.getSource() == incompletePage.getQuitButtonIncompletePage()) {
            switchPanels(savePage);
        }
    }

    //MODIFIES: this, title, subject, expectedCompletionDate, dueDate, homeworkListPage, assignmentArea
    //EFFECTS: if addButtonAddPage is clicked, create new assignment from values held in JTextFields,
    //         add it to homeworkList,set JTextFields to "", and switch to incompletePage with updated assignmentArea.
    //         if cancelButtonAddPage, switch to incompletePage
    public void handleAdd(ActionEvent e) {

        if (e.getSource() == addPage.getAddButtonAddPage() || e.getSource() == addPage.getCancelButtonAddPage()) {

            if (e.getSource() == addPage.getAddButtonAddPage()) {

                String title = addPage.getTitle().getText();
                String subject = addPage.getSubject().getText();
                String expectedCompletionDateString = addPage.getExpectedCompletionDate().getText();
                String dueDateString = addPage.getDueDate().getText();

                try {
                    homeworkList.addAssignment(new Assignment(title, subject, Integer.parseInt(dueDateString),
                            Integer.parseInt(expectedCompletionDateString)));
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Date");
                }

                incompletePage.middleItemsToAdd();
                switchPanels(incompletePage);
            } else if (e.getSource() == addPage.getCancelButtonAddPage()) {
                switchPanels(incompletePage);
            }

            addPage.getTitle().setText("");
            addPage.getSubject().setText("");
            addPage.getExpectedCompletionDate().setText("");
            addPage.getDueDate().setText("");
        }
    }

    //MODIFIES: this, removeTextField, homeworkListPage, assignmentArea
    //EFFECTS: if removeButtonRemovePage is clicked, remove assignment associated with entered number
    //         from homeworkListPage,set JTextField to "", and switch to incompletePage with updated assignmentArea.
    //         if cancelButtonRemovePage, switch to incompletePage
    public void handleRemove(ActionEvent e) {

        if (e.getSource() == removePage.getRemoveButtonRemovePage()
                || e.getSource() == removePage.getCancelButtonRemovePage()) {

            if (e.getSource() == removePage.getRemoveButtonRemovePage()) {

                String removeString = removePage.getRemoveTextField().getText();

                try {
                    homeworkList.removeAssignment(Integer.parseInt(removeString));
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Selection");
                }
                incompletePage.middleItemsToAdd();
                switchPanels(incompletePage);
            } else if (e.getSource() == removePage.getCancelButtonRemovePage()) {
                switchPanels(incompletePage);
            }

            removePage.getRemoveTextField().setText("");
        }
    }

    //MODIFIES: this, submitTextField, homeworkListPage, assignmentArea
    //EFFECTS: if submitButtonSubmitPage is clicked, submit assignment associated with entered number
    //         from homeworkListPage,set JTextField to "", and switch to incompletePage with updated assignmentArea.
    //         if cancelButtonSubmitPage, switch to incompletePage
    public void handleSubmit(ActionEvent e) {

        if (e.getSource() == submitPage.getSubmitButtonSubmitPage()
                || e.getSource() == submitPage.getCancelButtonSubmitPage()) {

            if (e.getSource() == submitPage.getSubmitButtonSubmitPage()) {

                String submitString = submitPage.getSubmitTextField().getText();

                try {
                    homeworkList.submitAssignment(Integer.parseInt(submitString));
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Selection");
                }
                incompletePage.middleItemsToAdd();
                submittedPage.middleItemsToAdd();
                switchPanels(incompletePage);
            } else if (e.getSource() == submitPage.getCancelButtonSubmitPage()) {
                switchPanels(incompletePage);
            }

            submitPage.getSubmitTextField().setText("");
        }
    }

    //MODIFIES: this
    //EFFECTS: if yesButtonSavePage is clicked, save current homeworkList to file terminate application,
    //         and output logged events to console;
    //         if noButtonSavePage is clicked, terminate application, and output logged events to console;
    public void handleSave(ActionEvent e) {
        if (e.getSource() == savePage.getYesButtonSavePage()) {

            try {
                jsonWriter.open();
                jsonWriter.write(homeworkList);
                jsonWriter.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
            System.out.println("Event Log: " + "\n");
            for (Event event : homeworkList.getEventLog()) {
                System.out.println(event.toString() + "\n");
            }
            System.out.println("No More Events Were Logged.");
            System.exit(0);
        } else if (e.getSource() == savePage.getNoButtonSavePage()) {

            System.out.println("Event Log: " + "\n");
            for (Event event : homeworkList.getEventLog()) {
                System.out.println(event.toString() + "\n");
            }

            System.out.println("No Assignments Were Saved to File" + "\n");

            System.out.println("No More Events Were Logged.");
            System.exit(0);
        }
    }

    //REQUIRES: contentPane is not empty
    //MODIFIES: this
    //EFFECTS: updates JPanel to display entered panel/page
    public void switchPanels(JPanel panel) {

        contentPane.removeAll();
        contentPane.add(panel);
        contentPane.repaint();
        contentPane.revalidate();

        setTitle(panel.getName());
    }

    //EFFECTS: Upon Window Closing, print EventLog to console
    @Override
    public void windowClosing(WindowEvent e) {

        System.out.println("Event Log: " + "\n");
        for (Event event : homeworkList.getEventLog()) {
            System.out.println(event.toString() + "\n");
        }
        System.out.println("No More Events Were Logged.");

    }

    //EFFECTS: runs new MainApplication
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication main = new MainApplication();
            main.pack();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }

    //EFFECTS: unused WindowListener Method
    @Override
    public void windowOpened(WindowEvent e) {

    }

    //EFFECTS: unused WindowListener Method
    @Override
    public void windowClosed(WindowEvent e) {

    }

    //EFFECTS: unused WindowListener Method
    @Override
    public void windowIconified(WindowEvent e) {

    }

    //EFFECTS: unused WindowListener Method
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    //EFFECTS: unused WindowListener Method
    @Override
    public void windowActivated(WindowEvent e) {

    }

    //EFFECTS: unused WindowListener Method
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}