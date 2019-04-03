import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program Utilizes a data set by following the branches till it comes across
 * a solution that is appropriate for the given instance of data.
 * @author Benjamin Mason
 * @version 1.0
 */
public class TreeUtilizer{
  static TreeBuilder hi = new TreeBuilder();
  public static void main(String[] args) {
	  hi.main(args);
	  System.out.println(utilizer(10, 1018, 56));
	  //for(int i = 1; i < 100; i ++) {
		//  hi.bestVal = i;
		//  hi.main(args);
		//  System.out.println(hi.bestVal);
	  try {
		 Scanner in = new Scanner(new File("data.txt"));
		 int correct = 0;
		 //for(int i = 0)
		 while(in.hasNext()) {
			 String[] attributes = in.nextLine().split(", ");
			 if(utilizer(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2])) == (attributes[3].equals("true"))) {
				 correct ++;
			 }//System.out.println(correct);
		 }in.close();System.out.println("Overal calculated accuracy: " + (double)correct / 717);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  //}
  }
  /**
   * A method that finds the outcome of the given instance of data.
   * @param temp The average temperature of the given instance of data.
   * @param pressure The average pressure of the given instance of data.
   * @return A boolean which indicates the classified outcome.
   */
  public static boolean utilizer(int temp, int pressure, int humidity) {
	  int[] curr_instance = {temp, pressure, humidity};
	  ArrayList<String> tree = hi.nav;
	  double curr_nodeID = Double.parseDouble(tree.get(0).split(", ")[4]) * 10;
	  int i = 0;
	  while(tree.get(i).split(", ").length > 4) {
		  String[] contents = tree.get(i).split(", ");
		  if(contents[3].equals(">")) {
			  if(curr_instance[Integer.parseInt(contents[1])] > Integer.parseInt(contents[2])) {
				 curr_nodeID += 10;
			  }else if(curr_instance[Integer.parseInt(contents[1])] <= Integer.parseInt(contents[2])){
				 curr_nodeID += 11;
			  }for(int a = i; a < tree.size(); a ++) {
				  //System.out.println(curr_nodeID + " " + (int)(10 * Double.parseDouble(tree.get(a).split(", ")[tree.get(a).split(", ").length - 2])));
				  if(tree.get(a).split(", ").length > 4 && curr_nodeID == (int)(10 * Double.parseDouble(tree.get(a).split(", ")[4]))) {
					  i = a;
					  a = tree.size();
					  //System.out.println(a + "###");
				  }else if((curr_nodeID / 10) == Double.parseDouble(tree.get(a).split(", ")[2])) {
					  return tree.get(a).split(", ")[1].equals("true");
				  }else if(a == tree.size() - 1) {
					  curr_nodeID += 1;
					  for(a = i; a < tree.size(); a ++) {
						  //System.out.println(curr_nodeID + " " + (int)(10 * Double.parseDouble(tree.get(a).split(", ")[tree.get(a).split(", ").length - 2])));
						  if(tree.get(a).split(", ").length > 4 && curr_nodeID == (int)(10 * Double.parseDouble(tree.get(a).split(", ")[4]))) {
							  i = a;
							  a = tree.size();
						  }else if((curr_nodeID / 10) == Double.parseDouble(tree.get(a).split(", ")[2])) {
							  return tree.get(a).split(", ")[1].equals("true");
						  }else if(a == tree.size() - 1) {
							  System.out.println("Unsuccesful :(");
							  return false;
						  }
				  }
				  }
			  }
		  }
	  }
	  System.out.println("Unsuccesful :(");
	  return false;
  }
}
