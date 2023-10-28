import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class SoftmaxMovieAgent implements Agent {

  Random rand;
  double tau;
  double alpha;
  private Map<String, Integer> counts;
  private Map<String, Double> avgReward;
  private Map<String, Map<String, Double>> userGenrePrefs;
  private Map<String, String[]> moviesMap;
  private String lastChoice = "";

  public SoftmaxMovieAgent(double tau, double alpha) {
    rand = new Random();
    this.tau = tau;
    this.alpha = alpha;
    counts = new HashMap<>();
    avgReward = new HashMap<>();
    userGenrePrefs = new HashMap<>();
    moviesMap = MovieReader.loadMovies();
  }

  @Override
  public String present(String userId, List<String> choices) {
      double draw = rand.nextDouble();
      double totalValue = 0.0;
      Map<String, Double> genrePrefs = userGenrePrefs.getOrDefault(userId, new HashMap<>());

      for (String choice : choices) {
          double baseReward = avgReward.getOrDefault(choice, 0.0);
          double adjustedReward = baseReward;

          String[] genres = moviesMap.getOrDefault(choice, new String[0]);
          for (String genre : genres) {
              adjustedReward += genrePrefs.getOrDefault(genre, 0.0);
          }

          totalValue += Math.exp(adjustedReward / tau);
      }

      double totalWeight = 0.0;
      for (String choice : choices) {
          double baseReward = avgReward.getOrDefault(choice, 0.0);
          double adjustedReward = baseReward;

          String[] genres = moviesMap.getOrDefault(choice, new String[0]);
          for (String genre : genres) {
              adjustedReward += genrePrefs.getOrDefault(genre, 0.0);
          }

          double choiceWeight = Math.exp(adjustedReward / tau) / totalValue;
          if (draw >= totalWeight && draw <= totalWeight + choiceWeight) {
              lastChoice = choice;
          }
          totalWeight += choiceWeight;
      }
      updateUserGenrePrefs(userId, lastChoice);
      return lastChoice;
  }
  
  private void updateUserGenrePrefs(String userId, String choice) {
	  String[] genres = moviesMap.getOrDefault(choice, new String[0]);

      userGenrePrefs.putIfAbsent(userId, new HashMap<>());
      Map<String, Double> genrePrefs = userGenrePrefs.get(userId);

      for (String genre : genres) {
          genrePrefs.put(genre, genrePrefs.getOrDefault(genre, 0.0) + 1.0);
      }
  }

  public void feedback(double score) {
    // update reward and count for last choice
    int oldCount = this.counts.getOrDefault(this.lastChoice, 0);
    double oldReward = this.avgReward.getOrDefault(this.lastChoice, 0.0);
    this.avgReward.put(this.lastChoice, (oldCount * oldReward + score) / (oldCount + 1));
    this.counts.put(this.lastChoice, oldCount + 1);

    if (tau > 1.0) {
      tau *= alpha;
    }
    else {
      tau = 1.0;
    }
  }
}