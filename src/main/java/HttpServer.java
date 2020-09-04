import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * The server connection is handled here. It is built as a Runnable, so as
 * to handle different connections from different clients on seperate threads.
 *
 * The application is run here.
 * By experience, it showed to run stably on Safari browser than the Chrome browser.
 */
public class HttpServer implements Runnable {

    private static String htmlFilePath = "src/main/resources/webFile.html";
    private static String jsonFilePath = "src/main/resources/jsonFile.json";
    private static String notFoundFilePath = "src/main/resources/notFound.html";
    private static String filePath = htmlFilePath;
    private static Socket client;
    private static int port;
    private static String status = "";

    public HttpServer(Socket client) {
        this.client = client;
    }

    public HttpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        sendResponse(status, filePath);
    }

    /**
     * This handles sending of the Response to the client
     * @param status
     * @param filePath
     */
    public static void sendResponse(String status, String filePath) {
        BufferedReader reader = HandleFile.getBufferedReader(filePath);
        WriteToClient.write(client, reader, HandleFile.getContentType(), status);
        System.out.println("Content-Type: " + HandleFile.getContentType()+"\n");
    }

    /**
     * The loads the appropriate file to be sent to the client from the file system,
     * based off the user-entered route.
     * @param route
     */
    public static void processRoute(String route) {
        if (route.equals("/")) {
            filePath = htmlFilePath;
            status = "200 OK";
        } else if (route.equals("/json")) {
            filePath = jsonFilePath;
            status = "200 OK";
        } else {
            filePath = notFoundFilePath;
            status = "404 Not Found";
        }
    }

    /**
     * The program is run by running the main method.
     * @param args
     */
    public static void main(String[] args) {
        new HttpServer(63342);
        System.out.println("http://localhost:63342/");
        Executor executor = Executors.newCachedThreadPool();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket client;

            while (true) {
                client = serverSocket.accept();
                System.out.println("Client Connected");
                executor.execute(new HttpServer(client));
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String route = input.readLine().split(" ")[1];
                System.out.println("route: " + route);
                processRoute(route);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
