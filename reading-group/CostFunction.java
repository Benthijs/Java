import java.math.*;

public class CostFunction{
  /*
  * @param instance The instance of data whose cost needs to be calculated
  * @return The cost of the given instance of data
  */
public static void main(String[] args){
  String extDir = System.getProperty("java.ext.dirs");
  System.out.println(extDir);
  CostFunction main = new CostFunction();
}
public static double Costfunction(int[][] inst){
    int l = inst[0].length;
    double inUse = 0; double variance = 0; int mean = 0; int cache = 0;
    for(int i = 0; i < l; i ++){
      cache = inst[0][i];
      variance = cache * cache; mean += cache;
      inUse += inst[1][i];
    }
    variance /= l; variance -= Math.pow((mean / l), 2);
    //if(inUse > 0){
    //  return 10*Math.abs(variance);
    //}
    return Math.abs(variance) + 2*(inUse/5);
  }
}
