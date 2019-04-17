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
    int iterations = 100;int temperature = 100; double cool = 0.97; int stepSize = 1;
		JFrame frame = new JFrame("fileName");
		getDat(JOptionPane.showInputDialog(frame, "fileName", 0));
		int metaBest = simulatedAnnealing(iterations, temperature, cool, stepSize);
		System.out.println("Your metaBest is: " + dataset.get(metaBest) + " Index being: " + metaBest);
		System.out.println(dataset);
	}

  /**
  * Parses the dataset into its allocated ArrayList.
  */
public static void getDat(String fileName) {
		//PythonInterpreter dataPickaxe = new PythonInterpreter();
		//dataPickaxe.exec("import dataPickaxe\ndataPickaxe.dataPickaxe()");
		//dataPickaxe.execfile("dataPickaxe.py");
		//PyInstance dataPick = dataPickaxe.createClass("dataPickaxe", "int[][]");
		//dataPickaxe.invoke("run");
		try {
		Scanner in = new Scanner(new File(fileName));
		while(in.hasNext()) {
			String[] a = in.nextLine().split(":");
			int[] b = new int[2]; int[][] d = new int[5][2];
			for(int i = 0; i < a.length; i ++){
				String[] c = {a[i].replace("[", "").replace("]", "").split(",")[0], a[i].replace("[", "").replace("]", "").split(",")[1]};
				b[0] = Integer.parseInt(c[0]); b[1] = Integer.parseInt(c[1].replace(" ", ""));
				System.out.println(a[i].replace("[", "").replace("]", "").split(","));
				d[i][0] = b[0]; d[i][1] = b[1];
			}
			//System.out.println(d);
			dataset.add(d);
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
public static int simulatedAnnealing(int iterations, int temperature, double cool, int stepSize){
    int metaBest = 0;
    int best = 0;
    double cost = 0;
    double ncost = 0;
    for(int i = 0; i <= iterations; i ++){
      // Random indice of dataset
      int index = (int)(Math.random() * (double)dataset.size());
      while(temperature > 0.1){
        // Direction of descention along that dimension
        int direction = stepSize * (int)((Math.round(Math.random() -0.5) * 2));
        cost = costFunction(i);
        if(index + direction >= 0 && index + direction < dataset.size()){
        ncost = costFunction(index + direction);
        }else{
          break;
        }
        if(ncost <= cost || Math.random() < Math.pow(Math.E, (-ncost - cost)/ temperature)){
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


public static double costFunction(int index){
		return CostFunction.Costfunction(dataset.get(index));
  }

}
