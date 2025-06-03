public class SearchEngine {
    public static String[] search(String query) {
        String url = "https://html.duckduckgo.com/html/?q=" + query.replace(" ", "+");
        String html = HttpClient.fetch(url);
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<a[^>]*href=\"(.*?)\"[^>]*>(.*?)</a>");
        java.util.regex.Matcher matcher = pattern.matcher(html);
        java.util.ArrayList<String> results = new java.util.ArrayList<>();

        while (matcher.find() && results.size() < 10) {
            String link = matcher.group(1);
            if (!link.contains("duckduckgo.com")) {
                results.add(link);
            }
        }

        return results.toArray(new String[0]);
    }
}
