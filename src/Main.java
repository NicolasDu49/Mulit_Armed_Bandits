public class Main {

  public static void main(String[] args) {
    
     Bandit b = new SimpleBandit();
     Agent a = new RandomAgent();
     b.run(a, 1000);
     System.out.println("Random Agent / simple bandit: score=" + b.getScore() + " regret per turn="
         + b.getTotalRegret() / b.getCount());
	  
//	  Bandit b = new SimpleBandit();
//	  Agent a = new EpsilonDecreasingAgent(0.1, 1.0);
//	  b.run(a, 1000);
//	  System.out.println("Epsilon Greedy Agent / simple bandit: score=" + b.getScore() + " regret per turn="
//	       + b.getTotalRegret() / b.getCount());
	  
//	  Bandit b = new SimpleBandit();
//	  Agent a = new EpsilonDecreasingAgent(0.2, 0.99);
//	  b.run(a, 1000);
//	  System.out.println("Epsilon Decreasing Agent / simple bandit: score=" + b.getScore() + " regret per turn="
//	       + b.getTotalRegret() / b.getCount());
	  
//	  Bandit b = new SimpleBandit();
//	  Agent a = new SoftmaxAgent(1.0, 1.0);
//	  b.run(a, 1000);
//	  System.out.println("Softmax Agent / simple bandit: score=" + b.getScore() + " regret per turn="
//		     + b.getTotalRegret() / b.getCount());
	  
//	  Bandit b = new MovieBandit();
//	  Agent a = new RandomAgent();
//	  b.run(a, 0);
//	  System.out.println("Random Agent / movie bandit: score=" + b.getScore() + " regret per turn="
//		     + b.getTotalRegret() / b.getCount());
	  
//	  Bandit b = new MovieBandit();
//	  Agent a = new SoftmaxAgent(1.0, 1.0);
//	  b.run(a, 0);
//	  System.out.println("Softmax Agent / movie bandit: score=" + b.getScore() + " regret per turn="
//		     + b.getTotalRegret() / b.getCount());
	  
//	  Bandit b = new MovieBandit();
//	  Agent a = new SoftmaxMovieAgent(2.0, 0.999);
//	  b.run(a, 0);
//	  System.out.println("Softmax Agent (w/ context) / movie bandit: score=" + b.getScore() + " regret per turn="
//		     + b.getTotalRegret() / b.getCount());
  }

}
