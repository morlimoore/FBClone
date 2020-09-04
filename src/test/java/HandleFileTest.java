import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HandleFileTest {
    BufferedReader htmlBuffer;
    BufferedReader jsonBuffer;
    String htmlFilePath = "src/main/resources/webFile.html";
    String jsonFilePath = "src/main/resources/jsonFile.json";



    @Test
    @DisplayName("Can read files in File System accurately")
    void getBufferedReader() {
        try {
            htmlBuffer = HandleFile.getBufferedReader(htmlFilePath);
            jsonBuffer = HandleFile.getBufferedReader(jsonFilePath);

            String htmlStringFromFile = new Scanner(new File(htmlFilePath)).nextLine();
            String htmlStringFromMethod = htmlBuffer.readLine();

            String jsonStringFromFile = new Scanner(new File(jsonFilePath)).nextLine();
            String jsonStringFromMethod = jsonBuffer.readLine();

            assertAll(
                    () -> assertEquals(htmlStringFromFile, htmlStringFromMethod),
                    () -> assertEquals(jsonStringFromFile, jsonStringFromMethod)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Can deduce HTML file type accurately")
    void getHTMLContentType() {
        HandleFile.getBufferedReader(htmlFilePath);
        assertEquals(HandleFile.getContentType(), "text/html");
    }

    @Test
    @DisplayName("Can deduce JSON file type accurately")
    void getJSONContentType() {
        HandleFile.getBufferedReader(jsonFilePath);
        assertEquals(HandleFile.getContentType(), "application/json");
    }
}