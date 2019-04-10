import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StochasticOptimizationFoundation {
	static String[] people = {"Seymour;BOS","Franny;DAL","Zooey;CAK","Walt;MIA","Buddy;ORD","Les;OMA"};
	static HashMap<String, String> peopl = new HashMap<String, String>();
	static ArrayList<String> dat = new ArrayList<String>();
	static ArrayList<String> sol = new ArrayList<String>();
	static ArrayList<String> newsol = new ArrayList<String>();
	public static void main(String[]args) {
		getDat();
		/*int av = (randSearch() + randSearch() + randSearch() + randSearch() + randSearch() +
		randSearch() + randSearch() + randSearch() + randSearch() + randSearch()) / 10;
		int av1 = (simulatedAnnealing() + simulatedAnnealing() + simulatedAnnealing() + simulatedAnnealing()
		+ simulatedAnnealing() + simulatedAnnealing() + simulatedAnnealing() + simulatedAnnealing()
		+ simulatedAnnealing() + simulatedAnnealing()) / 10;
		System.out.println("Average randSearch: " + av + "\n" + "Average simulatedAnnealing: " + av1);*/
		randSearch();simulatedAnnealing();
	}
	public static void getDat() {
		try {
		Scanner in = new Scanner(new File("schedule.txt"));
		while(in.hasNext()) {
			dat.add(in.nextLine());
		}
		in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static int randSearch(){
		int bestCost = 100000;
		int[] a = {1, 21, 41, 61, 81, 101, 1, 21, 41, 61, 81, 101};
		int d;
		for(int i = 0; i < (d = 200); i ++){
			sol.clear();
			int[] s = new int[12];
			for(int b = 0; b < s.length; b ++){
				if(b < 6){
					s[b] = (int)(Math.random() * 5) * 2;
				}else{
					s[b] = ((int)(Math.random() * 4) * 2) + 1;
				}
			}
			int totalCost;
			for(int c = 0; c < s.length; c ++){
				sol.add(dat.get(s[c] + a[c]));
			}
			totalCost = costFunction(sol);
			if(totalCost < bestCost){
				bestCost = totalCost;
			}
		}System.out.println("randSearch (" + d + " iterations): " + bestCost);
		//print(sol);
		sol.clear();
		return bestCost;
	}
	public static int simulatedAnnealing(){
		int bestCost = 1000000;int iterations = 0;
		for(int o = 0; o < 200; o ++){
			iterations ++;
			float temperature = 100;
			double cool = 0.95;
			int[] s = new int[12];
			for(int i = 0; i < s.length; i ++){
				if(i < 6){
					s[i] = (int)(Math.random() * 5) * 2;
				}else{
					s[i] = ((int)(Math.random() * 4) * 2) + 1;
				}
			}
			int[] s1 = s;
			int[] a = {0, 20, 40, 60, 80, 100, 0, 20, 40, 60, 80, 100};
			int r;int t;int totalCost;int totalCost1;
			while(temperature > 0.1){
				sol.clear();
				r = (int)(Math.random() * 12) + 0;
				t = (int)Math.random();
				if(t == 0 && s1[r] < 18){
					s1[r] += 2;
				}else if(s1[r] > 0){
					s1[r] -= 2;
				}
				for(int c = 0; c < s.length; c ++){
					sol.add(dat.get(s[c] + a[c]));
					newsol.add(dat.get(s1[c] + a[c]));
				}
				totalCost = costFunction(sol);
				totalCost1 = costFunction(newsol);
				newsol.clear();
					if(totalCost1 < totalCost){
					s = s1;
				}else{
					double p = Math.pow(Math.E, ((-totalCost1-totalCost) / temperature));
					if(Math.random() < p){
						s = s1;
					}
				}if(totalCost < bestCost){
					bestCost = totalCost;
				}
				temperature *= cool;
			}
		}System.out.println("simulatedAnnealing (" + iterations +" iterations): " + bestCost);
		print(sol);
		sol.clear();
		return bestCost;
	}
	public static int[] findLine(){
		int[] a = new int[people.length * 2];
		for(int i = 0; i < people.length; i ++){
			for(int b = 0; b < sol.size(); b ++){
				if(sol.get(b).contains(people[i].split(";")[1])){
					a[i] = b;
					b = sol.size();
				}
			}
		}for(int i = people.length; i < people.length * 2; i ++){
			for(int b = 0; b < sol.size(); b ++){
				if(sol.get(b).contains(people[i].split(";")[1])){
					a[i] = b;
					b = sol.size();
				}
		}
		}
		return a;
	}
	public static void print(ArrayList<String> sol){
		for(int i = 0; i < sol.size(); i ++){
			System.out.println(people[i % 6].split(";")[0] + "'s ticket: " +sol.get(i));
		}
	}
	public static int costFunction(ArrayList<String> sol){
		int totalCost = 0;String low = "24:00";String high = "00:00";
		for(int i = 0; i < sol.size(); i ++){
			String[] a = sol.get(i).split(",");
			if(getTime(a[3]) < getTime(low)){
				low = a[3];
			}if(getTime(a[3]) > getTime(high)){
				high = a[3];
			}
			totalCost += Integer.parseInt(a[4]);
		}totalCost += timeDif(low, high) * 12;
		return totalCost;
	}
	public static double getTime(String time){
		return Integer.parseInt(time.split(":")[0]) + Integer.parseInt(time.split(":")[1]) / 10;
	}
	public static double timeDif(String initTime, String curTime){
		return Double.parseDouble(curTime.split(":")[0]) - Double.parseDouble(initTime.split(":")[0])
		+ (Double.parseDouble(curTime.split(":")[1]) - Double.parseDouble(initTime.split(":")[1])) / 60;
	}
}
