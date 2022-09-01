package persistence;

import model.Assignment;
import model.HomeworkList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads HomeworkList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // **Method modeled after JSONSerializationDemo**
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads HomeworkList from file and returns separate JSONObject's for incomplete and
    // submitted assignment lists;
    // throws IOException if an error occurs reading data from file
    //**Method modeled after JSONSerializationDemo**
    public HomeworkList read() throws IOException {
        String jsonDataAllAssignments = readFile(source);
        JSONObject jsonObjectAllAssignments = new JSONObject(jsonDataAllAssignments);
        return parseHomeworkList(jsonObjectAllAssignments);
    }

    // EFFECTS: reads source file as string and returns it
    // **Method modeled after JSONSerializationDemo**
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses HomeworkList from JSON object and returns it
    //**Method modeled after JSONSerializationDemo**
    private HomeworkList parseHomeworkList(JSONObject jsonObjectAllAssignments) {
        String listTitle = jsonObjectAllAssignments.getString("List Title");
        HomeworkList hwl = new HomeworkList(listTitle);

        addAssignments("Incomplete Assignments", hwl.getIncompleteHomeworkList(), jsonObjectAllAssignments);
        addAssignments("Submitted Assignments", hwl.getSubmittedHomeworkList(), jsonObjectAllAssignments);
        return hwl;
    }

    // MODIFIES: hwl
    // EFFECTS: parses assignments from JSON object and adds them to HomeworkList
    //**Method modeled after JSONSerializationDemo**
    private void addAssignments(String title, List<Assignment> listOfAssignments, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray(title);
        for (Object json : jsonArray) {
            JSONObject nextAssignment = (JSONObject) json;
            addAssignment(listOfAssignments, nextAssignment);
        }
    }

    // MODIFIES: hwl
    // EFFECTS: parses assignment from JSON object and adds it to HomeworkList
    //**Method modeled after JSONSerializationDemo**
    private void addAssignment(List<Assignment> listOfAssignments, JSONObject jsonObject) {
        String assignmentTitle = jsonObject.getString("Title");
        String assignmentSubject = jsonObject.getString("Subject");
        int assignmentDueDate = jsonObject.getInt("Due-Date");
        int assignmentExpectedCompletionDate = jsonObject.getInt("Expected Completion Date");

        Assignment assignment = new Assignment(assignmentTitle, assignmentSubject, assignmentDueDate,
                assignmentExpectedCompletionDate);
        listOfAssignments.add(assignment);
    }
}
