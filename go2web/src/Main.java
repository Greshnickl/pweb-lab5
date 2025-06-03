import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to go2web CLI!");
        System.out.println("Choose an option:");
        System.out.println("1. Request URL");
        System.out.println("2. Search term");
        System.out.println("3. Help");
        System.out.print("Enter choice (1-3): ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter URL (e.g., https://example.com): ");
                String url = scanner.nextLine();
                String response = CacheManager.get(url);
                if (response == null) {
                    response = HttpClient.fetch(url);
                    CacheManager.put(url, response);
                }
                System.out.println(HtmlParser.toPlainText(response));
                break;

            case "2":
                System.out.print("Enter search term: ");
                String query = scanner.nextLine();
                String[] results = SearchEngine.search(query);
                for (int i = 0; i < results.length; i++) {
                    System.out.println((i + 1) + ". " + results[i]);
                }
                break;

            case "3":
            default:
                printHelp();
                break;
        }
    }

    static void printHelp() {
        System.out.println("go2web - Java console app");
        System.out.println("1 - Enter a URL to fetch and display content (cleaned of HTML)");
        System.out.println("2 - Enter search keywords to retrieve top 10 links");
        System.out.println("3 - Show this help");
    }
}
