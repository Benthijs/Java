import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import org.python.util.PythonInterpreter;
import org.python.core.PyInstance;

public class SimulatedAnnealing {
	static ArrayList<int[][]> dataset = new ArrayList<int[][]>();


public static void main(String[]args) {
		// Exemplary main method
		int iterations = 100;int temperature = 100; double cool = 0.97; int stepSize = 1;
		JFrame frame = new JFrame("fileName");
		getDat("schedules.txt");//JOptionPane.showInputDialog(frame, "fileName", 0)
		int metaBest = simulatedAnnealing(iterations, temperature, cool, stepSize);
		System.out.println("Index of metaBest is: " + metaBest + " Your metaBest set being: ");
		int[][] d = dataset.get(metaBest);
		for(int i = 0; i < d[0].length; i ++){
			System.out.println(d[0][i] + ", " + d[1][i]);
		}
	}

  /**
  * Parses the dataset into its allocated ArrayList. Is created to function with
	* dataPickage.py - certainly not ideal.
  */
public static void getDat(String fileName) {
		try {
		Scanner in = new Scanner(new File(fileName));
		while(in.hasNext()) {
			String[] a = in.nextLine().split(":");
			int[] b = new int[2]; int[][] d = new int[2][5];
			for(int i = 0; i < a.length; i ++){
				b[0] = Integer.parseInt(a[i].replace("[", "").replace("]", "").split(",")[0]);
				b[1] = Integer.parseInt(a[i].replace("[", "").replace("]", "").split(",")[1].replace(" ", ""));
				d[0][i] = b[0]; d[1][i] = b[1];
			}dataset.add(d);
		}in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


  /*
  * Performs simulated annealing on a dataset given a cost function
  * @param iterations The number of iterations of the annealing process
  * @return The optimum value as according to this stochastic annealing approach
  */
public static int simulatedAnnealing(int iterations, int temperature, double cool, int stepSize){
		int metaBest = 0; int best = 0; int index = 0;
		double cost = 0;
    double ncost = 0;
    for(int i = 0; i <= iterations; i ++){
      // Random indice of dataset
			metaBest = (int)(Math.random() * (double)dataset.size());
			best = (int)(Math.random() * (double)dataset.size());
      index = (int)(Math.random() * (double)dataset.size());
      while(temperature > 0.1){
        // Direction of descention along that dimension
        int direction = stepSize * (int)((Math.round(Math.random() -0.5) * 2));
        cost = costFunction(index);
        if(index + direction >= 0 && index + direction < dataset.size()){
        	ncost = costFunction(index + direction);
        }else{
          break;
        }
				if(ncost <= cost || Math.random() < Math.pow(Math.E, (-ncost - cost)/ temperature)){
          index += direction;
          best = index;
        }
        temperature *= cool;
      }if(costFunction(best) < costFunction(metaBest)){
        metaBest = best;
      }
    }
    System.out.println("Cost: " + costFunction(metaBest));
		return metaBest;
	}

/*
* Implementation of an iterativesearch algorithm
* @return The index of the optimum solution
*/
public static int iterativeSearch(){
	int best = 0;
	// Search complete dataset for ideal solution as according to cost function
	for(int i = 0; i < dataset.size(); i ++){
		if (costFunction(i) < costFunction(best)){
			best = i;
		}
	}
	System.out.println("Cost: " + costFunction(best));
	return best;
}


/*
* @return The return of the external cost function
*/
public static double costFunction(int index){
		return CostFunction.Costfunction(dataset.get(index));
  }

}
