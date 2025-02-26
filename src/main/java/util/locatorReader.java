package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
public class locatorReader {
    private JsonObject locators;
    public locatorReader(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            locators = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load locators from file: " + filePath, e);
        }
    }

    public  String getLocatorType(String elementName) {
        return locators.getAsJsonObject(elementName).get("locatorType").getAsString();
    }

    public  String getLocatorValue(String elementName) {
        return locators.getAsJsonObject(elementName).get("locatorValue").getAsString();
    }


}
