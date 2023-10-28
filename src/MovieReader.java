import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieReader {

    public static Map<String, String[]> loadMovies() {
        Map<String, String[]> movies = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File("src/movies.csv"));
            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String movieId = data[0];
                    String[] genres = data[2].split("\\|");
                    if (!genres[0].equals("(no genres listed)")) {
                        count++;
                        movies.put(movieId, genres);
                    }
                }
            }
            scanner.close();
            System.out.println(count + " movies listed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }
}