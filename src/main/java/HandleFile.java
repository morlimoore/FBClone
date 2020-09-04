import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This deals with all that has to do with the files to be sent to the client.
 * It receives the path to the file as a parameter, generates an nio.Path object off it,
 * passes it to a file reader, which in turn uses it to generate a bufferedReader, to read the
 * file contents to the client.
 *
 * It returns the bufferedReader object created.
 */
public class HandleFile {

    private static String contentType = null;

    /**
     * This generates a bufferedReader of the file sent to it
     * @param filePath
     * @return BufferedReader
     */
    public static BufferedReader getBufferedReader(String filePath) {
        BufferedReader bufferedReader = null;
        Path filePathObj = Paths.get(filePath);
        try {
            bufferedReader = new BufferedReader(new FileReader(filePathObj.toFile()));
            contentType = Files.probeContentType(filePathObj);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    /**
     * Returns the content-type of the requested file
     * @return String
     */
    public static String getContentType() {
        return contentType;
    }
}
