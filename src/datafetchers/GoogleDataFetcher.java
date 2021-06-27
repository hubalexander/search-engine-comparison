package src.datafetchers;

import java.net.URI;
import keys.keys;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GoogleDataFetcher implements DataFetcher {

    @Override
    public int fetchData(String searchInput) {
        // API keys and base-URI.
        String googleKey = keys.KEYDATA.GOOGLE_API_KEY;
        String engineID = keys.KEYDATA.GOOGLE_ENGINE_ID;
        String googleUri = "https://www.googleapis.com/customsearch/v1?key=" + googleKey + "&cx=" + engineID + "&q="
                + searchInput;

        // HTTP client for fetching data. Request layout with added header data.
        HttpClient googleClient = HttpClient.newHttpClient();
        HttpRequest googleRequest = HttpRequest.newBuilder().GET().header("accept", "application/json")
                .uri(URI.create(googleUri)).build();

        // Asynchronous call utilizing the defined http request, containing header data
        // and URI.
        CompletableFuture<HttpResponse<String>> googleResponse = googleClient.sendAsync(googleRequest,
                HttpResponse.BodyHandlers.ofString());

        // Try get body of the http response.
        String bodyResult = "";
        try {
            bodyResult = googleResponse.get().body();
        } catch (InterruptedException e) {
            System.out.println("error");
        } catch (ExecutionException e) {
            System.out.println("error");
        }

        return splitBodyResult(bodyResult);
    }

    @Override
    public int splitBodyResult(String body) {
        // Extraction of numeric result from http response.
        String[] splittedResult = body.split("totalResults");
        splittedResult[1] = splittedResult[1].substring(4);
        String[] numericResult = splittedResult[1].split("\"");
        return Integer.parseInt(numericResult[0]);
    }
}
