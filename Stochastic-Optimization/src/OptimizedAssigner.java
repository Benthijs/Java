import java.util.ArrayList;
public class OptimizedAssigner{
  static String[] chores = {"Empty", "Fill", "Laundry"};
  static String[] people = {"Ben;1;3", "Elsa;1;3", "Mom;1;3"};
  static ArrayList<Integer> sol = new ArrayList<Integer>();
  public static void main(String[] args){
    randSearch();
  }
  public static int randSearch(){
		int bestCost = 100000;
		int d;
		for(int i = 0; i < (d = people.length * 2); i ++){
      ArrayList<Integer> assignments = new ArrayList<Integer>();
      for(int f = 1;f <= chores.length; f ++){
        assignments.add(f);
      }
      int[] s = new int[people.length];
			for(int b = 0; b < s.length; b ++){
        int ind = (int)(Math.random() * assignments.size());
        int ind2 = (int)(Math.random() * assignments.size());
        int next = assignments.get(ind2);
        assignments.set(ind2, assignments.get(ind));
        assignments.set(ind, next);
			}
			int totalCost;
			totalCost = costFunction(assignments);
			if(totalCost < bestCost){
				bestCost = totalCost;
        sol = assignments;
			}
		}System.out.println("randSearch (" + d + " iterations): " + bestCost);
		//print(sol);
    System.out.println(sol);
		sol.clear();
		return bestCost;
	}
	public static void print(ArrayList<String> sol){
		for(int i = 0; i < sol.size(); i ++){
			System.out.println(people[i % 6].split(";")[0] + "'s ticket: " +sol.get(i));
		}
	}
	public static int costFunction(ArrayList<Integer> sol){
		int totalCost = 0;
		for(int g = 0; g < people.length; g ++){
			String[] a = people[g].split(";");
      if(Integer.parseInt(a[1]) != sol.get(g) && Integer.parseInt(a[2]) != sol.get(g)){
        totalCost += 2;
      }else if(Integer.parseInt(a[1]) != sol.get(g)){
        totalCost += 1;
      }
      }
		return totalCost;
	}
}
