package agoda;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URI;
import java.net.http.*;
//import org.json.*

public class NetworkCall2 {
    private static final  String URL_TEMPLATE = "https://jsonmock.hackerrank.com/api/tvseries?page=%d";
    private static record Show(String name, int startYear, int endYear){}
    public static List<String> showsInProduction(int startYear, int endYear) {
        List<String> res = new ArrayList<>();
        int page = 1;
        int totalPages = 1;
        while(page <= totalPages) {
            String response = makeNetworkCall(page);
//            JsonObject result = parseResponse(response);
//            totalPages = result.get("total_pages");
//            List<Show> shows = parseResultData(result.data);
//            List<Show> filterShows = filterShows(shows);
//            for(Show show: filteredShows){
//                res.add(show.name());
//            }
        }
        Collections.sort(res);
        return res;
    }

    private static String makeNetworkCall(int page){
        String url = String.format(URL_TEMPLATE, page);
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args){
        List<String> shows  = showsInProduction(1990, 2000);
        for(String show: shows){
            System.out.println(show);
        }
    }
}

