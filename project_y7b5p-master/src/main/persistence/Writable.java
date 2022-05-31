package persistence;

import org.json.JSONObject;

//**Interface modeled after JSONSerializationDemo**
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}