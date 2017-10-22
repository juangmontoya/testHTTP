/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guillermo
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            //URL obj = new URL("http://296tlf02/ESME/Default.aspx");
            URL obj = new URL("http://crunchify.com/");
            
            URLConnection conn = obj.openConnection();

            HttpURLConnection http = (HttpURLConnection) obj.openConnection();
            http.setRequestMethod("GET");
            http.connect();

            int statusCode = http.getResponseCode();

            StringBuilder content = new StringBuilder();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            
            System.out.println(content);
            

            Map<String, List<String>> map = conn.getHeaderFields();
            System.out.println("Printing All Response Header for URL: " + obj.toString() + "\n");
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            System.out.println("\nGet Response Header By Key ...\n");

            List<String> contentLength = map.get("Content-Length");
            if (contentLength == null) {
                System.out.println("'Content-Length' doesn't present in Header!");
            } else {
                for (String header : contentLength) {
                    System.out.println("Content-Lenght: " + header);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
