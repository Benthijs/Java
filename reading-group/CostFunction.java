import java.math.*;

public class CostFunction{

public static void main(String[] args){
  CostFunction main = new CostFunction();
}

/*
* Examplary external cost function that makes use of variance and a boolean set
* @param instance The instance of data whose cost needs to be calculated
* @return The cost of the given instance of data
*/
public static double Costfunction(int[][] inst){
    int l = inst[0].length;
    double inUse = 0; double variance = 0; int mean = 0; int cache = 0;
    for(int i = 0; i < l; i ++){
      cache = inst[0][i];
      variance = cache * cache; mean += cache;
      inUse += inst[1][i];
    }
    variance /= l; variance -= Math.pow((mean / l), 2);
    return Math.abs(variance) + 2*(inUse/5);
  }
}
