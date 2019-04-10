import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class StochasticOptimizationFoundation {
	static String[] people = {"Seymour;BOS","Franny;DAL","Zooey;CAK","Walt;MIA","Buddy;ORD","Les;OMA"};
	static HashMap<String, String> peopl = new HashMap<String, String>();
	static ArrayList<String[]> dataset = new ArrayList<String[][]>();
	static ArrayList<String> sol = new ArrayList<String>();
	static ArrayList<String> newsol = new ArrayList<String>();


  public static void main(String[]args) {
    int iterations = 100
    getDat(JOptionPane.showInputDialog("fileName", "fileName", 0));
		simulatedAnnealing(iterations, );
	}

  /**
  * Parses the dataset into its allocated ArrayList.
  */
  public static void getDat(String fileName) {
		try {
		Scanner in = new Scanner(new File(fileName));
		while(in.hasNext()) {
			dataset.add(in.nextLine());
		}
		in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


  /*
  * Performs simulated annealing on a dataset given a cost function
  * @param iterations The number of iterations of the annealing process
  * @return The optimum value as according to this stochastic annealing approach
  */
	public static int simulatedAnnealing(int iterations, int temperature, int cool, int stepSize){
    int metaBest = 0;
    int best = 0;
    int cost = 0;
    int ncost = 0;
    for(int i, i <= iterations, i ++){
      // Random indice of dataset
      i = Math.random() * dataset.size();
      while(temperature > 0.1){
        // Direction of descention along that dimension
        direction = stepSize * ((Math.round(Math.random() * 2) * 2) - 3);
        cost = costFunction(i);
        if(i + direction >= 0 && i + direction < dataset.size()){
        ncost = costFunction(i + direction);
        }else{
          break;
        }
        if(ncost <= cost || Math.random() < Math.pow(Math.e, (-ncost - cost)/ temperature)){
          i += direction;
          best = i;
        }
        temperature *= cool;
      }if(costFunction(best) < costFunction(metaBest)){
        metaBest = best;
      }
    }
    System.out.println("Cost: " + metaBest);
		return metaBest;
	}



	public static int costFunction(int index){
    CostFunction.costFunction(dataset.get(index));
  }

}
