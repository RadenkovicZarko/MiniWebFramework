package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class main {
    public static void main(String[] args) {
        try {
            // Kreirajte URL objekat sa ciljnom putanjom
            URL url = new URL("http://localhost:8080/test"); // Zamijenite sa odgovarajućom putanjom i portom

            // Otvaranje HTTP veze
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Postavljanje HTTP metode na GET
            connection.setRequestMethod("GET");

            // Čitanje odgovora
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Prikazivanje odgovora
//            System.out.println(response.toString());

            // Zatvaranje veze
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
