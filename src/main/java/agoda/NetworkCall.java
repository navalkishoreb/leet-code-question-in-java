//package agoda;
//
//import java.io.*;
//import java.net.*;
//import java.util.*;
//import java.util.regex.*;
//
//import org.json.*;
//
//public class NetworkCall {
//
//    public static List<String> showsInProduction(int startYear, int endYear) {
//        List<String> result = new ArrayList<>();
//
//        try {
//            int page = 1;
//            int totalPages = 1;
//
//            while (page <= totalPages) {
//                String url = "https://jsonmock.hackerrank.com/api/tvseries?page=" + page;
//                String response = callAPI(url);
//
//                JSONObject json = new JSONObject(response);
//                totalPages = json.getInt("total_pages");
//                JSONArray data = json.getJSONArray("data");
//
//                for (int i = 0; i < data.length(); i++) {
//                    JSONObject show = data.getJSONObject(i);
//                    String name = show.getString("name");
//                    String runtime = show.getString("runtime_of_series");
//
//                    int[] years = parseYears(runtime);
//                    int showStart = years[0];
//                    int showEnd = years[1];
//
//                    if (showStart >= startYear &&
//                            (endYear == -1 ? showEnd == -1 : showEnd != -1 && showEnd <= endYear)) {
//                        result.add(name);
//                    }
//                }
//                page++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Collections.sort(result);
//        return result;
//    }
//
//    // HTTP GET call
//    private static String callAPI(String urlStr) throws Exception {
//        URL url = new URL(urlStr);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//
//        BufferedReader br = new BufferedReader(
//                new InputStreamReader(conn.getInputStream()));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//        br.close();
//        return sb.toString();
//    }
//
//    // Extract start & end year
//    private static int[] parseYears(String runtime) {
//        Pattern p = Pattern.compile("(\\d{4})(?:-(\\d{4})?)?");
//        Matcher m = p.matcher(runtime);
//
//        if (m.find()) {
//            int start = Integer.parseInt(m.group(1));
//            int end = (m.group(2) == null) ? start : Integer.parseInt(m.group(2));
//            if (runtime.contains("-)")) end = -1; // still running
//            return new int[]{start, end};
//        }
//        return new int[]{-1, -1};
//    }
//}
//
//}
