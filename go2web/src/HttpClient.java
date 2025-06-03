import java.net.*;
import java.io.*;

public class HttpClient {
    public static String fetch(String urlStr) {
        try {
            URL url = new URL(urlStr);
            String host = url.getHost();
            String path = url.getPath().isEmpty() ? "/" : url.getPath();
            if (url.getQuery() != null) path += "?" + url.getQuery();

            Socket socket = new Socket(host, 80);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("GET " + path + " HTTP/1.1\r\n");
            writer.write("Host: " + host + "\r\n");
            writer.write("User-Agent: go2web/1.0\r\n");
            writer.write("Accept: text/html,application/json\r\n");
            writer.write("Connection: close\r\n");
            writer.write("\r\n");
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            boolean isRedirect = false;
            String location = null;

            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
                if (line.startsWith("Location: ")) {
                    location = line.substring("Location: ".length()).trim();
                    isRedirect = true;
                }
                if (line.equals("")) break;
            }

            if (isRedirect && location != null) {
                return fetch(location);
            }

            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            socket.close();
            return response.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
