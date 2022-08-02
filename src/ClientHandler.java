import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    public static final String ROOT = "www/";
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }



    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String read = reader.readLine();

            String[] s = read.split(" ");


            if (s[1].equals("/index.html")) {
                File file = new File(ROOT + "index.html");
                FileInputStream fileInput = new FileInputStream(file);
                BufferedReader readFile = new BufferedReader(new InputStreamReader(fileInput));
                String line = readFile.readLine();
                BufferedWriter index = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                System.out.print("HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: index/html; charset=UTF-8\r\n" +
                        "Content-Length:" + file.length() + "\r\n\r\n");
                index.write("HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + file.length() + "\r\n\r\n");

                while (line != null) {

                    index.write(line);
                    index.newLine();
                    index.flush();
                    line = readFile.readLine();

                }


            }
            if (s[1].equals("/fuckyou.png")) {
                File file = new File(ROOT + "fuckyou.png");
                FileInputStream fileInput = new FileInputStream(file);

                DataOutputStream png = new DataOutputStream(socket.getOutputStream());


                System.out.print("HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: image/png\r\n" +
                        "Content-Length:" + file.length() + "\r\n\r\n");
                png.writeBytes("HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: image/png \r\n" +
                        "Content-Length: " + file.length() + "\r\n\r\n");


                byte[] lineByte = new byte[1024];
                int n;

                while ((n = fileInput.read(lineByte)) != -1) {


                    png.write(lineByte, 0, n);


                }


            }
            socket.close();

    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
