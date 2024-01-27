package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class EchoClient {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static String GET_URL = "https://omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=5ad80bb6";

    /**
     *
     * @param title String del titulo de la pelicula a buscar
     * @return Devuelve un String en forma de JSON que nos devuelve la API con la informacion de la pelicula buscada
     * @throws IOException
     */
    public static String RsponseApi(String title) throws IOException {
        String apiAnswer = "";
        if (!Objects.equals(title, "")) {
            GET_URL += title;
            GET_URL += API_KEY;

            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                apiAnswer = response.toString();

            } else {
                apiAnswer = "GET request not worked";
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
        }
        GET_URL = "http://www.omdbapi.com/?t=";
        return apiAnswer;
    }
}

