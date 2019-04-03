import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HillClimbing {
	public static String[] people = {"Seymour;BOS","Franny;DAL","Zooey;CAK","Walt;MIA","Buddy;ORD","Les;OMA"};
	static HashMap<String, String> peopl = new HashMap<String, String>();
	static ArrayList<String> dat = new ArrayList<String>();
	public static void main(String[]args) {
		getDat();
	}
	public static void getDat() {
		for(int i = 0; i < people.length; i ++) {
			String[] a = people[i].split(";");
			peopl.put(a[0], a[1]);
		}try {
		Scanner in = new Scanner(new File("schedule.txt"));
		while(in.hasNext()) {
			dat.add(in.nextLine());
		}
		System.out.println(dat);
		System.out.println(peopl);
		in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}