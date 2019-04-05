package Utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    JSONParser parser = new JSONParser();

    public String ReadJSONFile(String node_value, String file_path) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(file_path));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(node_value);
    }
}
