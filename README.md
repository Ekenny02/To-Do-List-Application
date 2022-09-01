# Ethan's Homework To-Do List

## Proposal:

**This Application Will:**
- Keep track of an assignment's title, subject, due date, date of expected completion, and status ("Incomplete",or "Submitted")
- Produce lists of all incomplete and submitted assignments for the week
- Overall, display an organized plan to ensure the student is productive and completes all of their assignments on time

This application is intended to be used by university students as an organization tool because keeping track of one's assignments is often a tedious process and this tool will make that slightly easier. Having access to all of your assignments in a single, organized, space can drastically increase one's productivity and this project is a digital representation of my current weekly planning methods that I employ to ensure I complete all of my assignments on time. It is an interest of mine because as I began the transition to a university schedule, I have learned to appreciate a well organized schedule. Primarily because, In the beginning, I struggled to remember all of my assignments and noticed myself falling behind because of it. This was only fixed when I began planning each week.  

## User Stories

**Phase 1**

- As a user, I want to be able to view the list of incomplete assignments on my homework list, in format (Subject-Title(Date of expected completion, Due date)), ordered by date of expected completion (Earliest to Latest)
- As a user, I want to be able to view the list of submitted assignments on my homework list, in format (Subject-Title(Date of expected completion, Due date)), ordered from last submitted to first submitted
- As a user, I want to be able to add an assignment to my homework list of incomplete assignments
- As a user, I want to be able to remove an assignment from my homework list of incomplete assignments
- As a user, I want to be able to submit an assignment from the list of incomplete assignments in my homework list (change status from "Incomplete" to "Submitted", remove from list of incomplete assignments, and add to list of submitted assignments)

**Phase 2**

- As a user, when I select the quit option from the application menu, I want to be prompted to either save my homework list and quit or quit the application without saving 
- As a user, when I start the application, I want to be given the option to load my previously saved homework list from file or continue to a new HomeworkList.

**Phase 4: Task 2 (representative sample of events)**

"Event Log:

Fri Nov 26 14:13:32 PST 2021 <br>
Assignment Entitled: Project Deliverable 15 Was Added to Incomplete Assignments

Fri Nov 26 14:13:38 PST 2021 <br>
Assignment Entitled: Project Deliverable 15 Was Removed from Incomplete Assignments

Fri Nov 26 14:13:48 PST 2021 <br>
Assignment Entitled: Project Deliverable 1 Was Added to Incomplete Assignments

Fri Nov 26 14:13:57 PST 2021 <br>
Assignment Entitled: Project Deliverable 2 Was Added to Incomplete Assignments

Fri Nov 26 14:14:08 PST 2021 <br>
Assignment Entitled: Project Deliverable 3 Was Added to Incomplete Assignments

Fri Nov 26 14:14:15 PST 2021 <br>
Assignment Entitled: Project Deliverable 4 Was Added to Incomplete Assignments

Fri Nov 26 14:14:26 PST 2021 <br>
Assignment Entitled: Project Deliverable 1 Was Submitted

Fri Nov 26 14:14:33 PST 2021 <br>
Assignment Entitled: Project Deliverable 2 Was Submitted

Fri Nov 26 14:14:37 PST 2021 <br>
Assignment Entitled: Project Deliverable 3 Was Submitted

Fri Nov 26 14:14:50 PST 2021 <br>
Incomplete Assignment Entitled: Project Deliverable 4 Was Saved to File

Fri Nov 26 14:14:50 PST 2021 <br>
Submitted Assignment Entitled: Project Deliverable 3 Was Saved to File

Fri Nov 26 14:14:50 PST 2021 <br>
Submitted Assignment Entitled: Project Deliverable 2 Was Saved to File

Fri Nov 26 14:14:50 PST 2021 <br>
Submitted Assignment Entitled: Project Deliverable 1 Was Saved to File

No More Events Were Logged."

**Phase 4: Task 3**

The Design Represented in the UML class diagram represents a JFrame application based around a JLayeredPane containing different "Pages" (JPanels). Although originally I planned to create a single class for the entire application, I ultimately decided that specific page classes extending a single abstract page class would be more readable in the long term. Since both page classes and the main application had to share a HomeworkList, I decided to create a HomeworkList instance for each, pass the Main app's HomeworkList into multiple page classes as parameters, and ultimately, update the Page HomeworkList as operations are performed. The Main application has JsonReader and JsonWriter fields in order to read or write to the save file when saving a current HomeworkList or loading the previous HomeworkList. Furthermore, the HomeworkList class creates an EventLog instance in order to log all the events occurring (Save,Load, Add, Remove, Submit). Finally, a list of Incomplete Assignments and a list of Submitted Assignments are part of HomeworkList in order to display and complete all necessary operations pertaining to the HomeworkList and its assignments. In terms of Refactoring, the only things I would do are try to improve the speed of operations or improve the HomeworkList and Assignment Designs. Within the HomeworkList and Assignment classes, I would refactor the add,remove, submit, extractCompletionDates, and extractDueDates methods because they are definitely more complicated than they need to be. I am almost certain I could create some abstract methods to improve readability. Other than that, I feel that I refactored my page classes as much as I can, creating an extends relationship to an abstract class and even refactoring JLabels, JButtons, and JTextFields to a single method accepting parameters based on the item you want. I don't really think they could be simplified any further. Regarding the speed of operations, I considered implementing a hashset instead of some lists could make traversing the HomeworkList faster. However, I am yet to test this implementation. 