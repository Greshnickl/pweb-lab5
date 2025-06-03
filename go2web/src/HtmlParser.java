public class HtmlParser {
    public static String toPlainText(String html) {
        return html.replaceAll("<[^>]*>", "").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&");
    }
}
