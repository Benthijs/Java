public class CostFunction{
  /*
  * @param instance The instance of data whose cost needs to be calculated
  * @return The cost of the given instance of data
  */
public static int CostFunction(String[][] inst){
    int l = inst[0].length;
    int[] instance = new int[l];
    int inUse = 0; double variance = 0; int mean = 0; int cache = 0;
    for(int i = 0; i < l; i ++){
      cache = (int) inst[0][i];
      variance = cache ** 2; mean += cache;
      inUse += (int) inst[1][i];
    }
    variance /= l; variance -= (mean / l) ** 2;
    return variance + 2*(inUse/5);
  }
}
