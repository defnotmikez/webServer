
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert port number: ");
        String port = scanner.nextLine();

        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));


        while (true) {

            Socket acceptClient = serverSocket.accept();
            System.out.println("Client IP Address: " + acceptClient.getInetAddress().getHostName());
            Thread thread = new Thread(new ClientHandler(acceptClient));
            thread.start();

        }

    }
}






