package test.datafetchers;

import java.net.URI;
import keys.keys;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BingDataFetcher implements DataFetcher {

    @Override
    public int fetchData(String searchInput) {
        // API keys and base-URI.
        String apiKey = keys.KEYDATA.BING_KEY;
        String bingUri = "https://api.bing.microsoft.com/v7.0/search?q=" + searchInput;

        // HTTP client for fetching data. Request layout with added header data.
        HttpClient bingClient = HttpClient.newHttpClient();
        HttpRequest bingRequest = HttpRequest.newBuilder().GET().header("Ocp-Apim-Subscription-Key", apiKey)
                .uri(URI.create(bingUri)).build();

        // Asynchronous call utilizing the defined http request, containing header data
        // and URI.
        CompletableFuture<HttpResponse<String>> bingResponse = bingClient.sendAsync(bingRequest,
                HttpResponse.BodyHandlers.ofString());

        // Try get body of the http response.
        String bodyResult = "";
        try {
            bodyResult = bingResponse.get().body();
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
        String[] splittedResult = body.split("totalEstimatedMatches");
        splittedResult[1] = splittedResult[1].substring(3);
        String[] numericResult = splittedResult[1].split(",");
        return Integer.parseInt(numericResult[0]);
    }

    // Unit test for fetchData
    @Test
    public void testFetchData() {
        DataFetcher fetcher = new BingDataFetcher();
        int result = fetcher.fetchData("test");
        assertNotEquals(0, result);
    }
}
