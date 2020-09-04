import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This sends the output to the client.
 */
public class WriteToClient {

    /**
     * Writes the HTML content bytes and file contents to the client.
     * @param client
     * @param reader
     * @param contentType
     * @param status
     */
    public static void write(Socket client, BufferedReader reader, String contentType, String status) {
        try {
            OutputStream clientOutputStream = client.getOutputStream();
            clientOutputStream.write(("HTTP/1.1 " + status + ";\r\n").getBytes());
            clientOutputStream.write(("Content-Type: " + contentType + "\r\n").getBytes());
            clientOutputStream.write(("\r\n").getBytes());

            //Write file contents; line by line to client
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                clientOutputStream.write(line.getBytes());
            }
            clientOutputStream.write("\r\n\r\n".getBytes());
            clientOutputStream.flush();
            clientOutputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
