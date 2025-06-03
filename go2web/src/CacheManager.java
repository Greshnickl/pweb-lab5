import java.nio.file.*;
import java.util.*;

public class CacheManager {
    private static final String CACHE_DIR = "cache/";

    static {
        new java.io.File(CACHE_DIR).mkdirs();
    }

    public static String get(String url) {
        String file = CACHE_DIR + url.hashCode() + ".cache";
        if (Files.exists(Path.of(file))) {
            try {
                return Files.readString(Path.of(file));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static void put(String url, String content) {
        String file = CACHE_DIR + url.hashCode() + ".cache";
        try {
            Files.writeString(Path.of(file), content);
        } catch (Exception ignored) {}
    }
}
