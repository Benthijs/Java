import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/***
 * This program is able to build a decision tree that in theory could be built
 * from any data set whether the data structure is integer or decimal.
 * @author Benjamin Mason
 * @version 1.0
 */
public class TreeBuilder{
  static ArrayList<String[]> data = new ArrayList<String[]>();
  static ArrayList<String> nav = new ArrayList<String>();
  static int bestVal = 6;
  static ArrayList<String[]> datadiv = new ArrayList<String[]>(); //false always comes first first coll in each ArrayList contains
  //the column and value of the determining factor, greater/less than or null(if string), and the next two options. Once the next two options are created the data is removed.
  //ex. 1, 21, <(or > (== not used)), 2 (false) or 3 (true) {col, attribute, great/less, next, 2ndnext}
  public static void main(String[] args){
	  retrieveDat();recursiveTreeBuilder(data, entropy(data), 0);System.out.println(nav);
	  try {
		  PrintWriter out = new PrintWriter(new FileOutputStream("out.txt"));
		  out.println(nav);
		  out.close();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  /**
   * Retrieves the data from the given dataset.
   */
  public static void retrieveDat() {
	  try {
		  Scanner in = new Scanner(new File("data.txt"));
		  while(in.hasNext()) {
			  data.add(in.nextLine().split(", "));
		  }in.close();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  /**
  * Calculates the the entropy of the data in a given branch of the current
  * version of the tree assuming that there are only two solutions to the problem
  * true or false.
  * @param currdat An array containing the current data under the branch.
  * @return A double representing the entropy of the solutions within that branch.
  */
  public static double entropy(ArrayList<String[]> currdat) {
   int outcome1 = 0;int outcome2 = 0;
   for(int i = 1;i < currdat.size();i ++) {
	   if(currdat.get(i)[currdat.get(1).length - 1].equals("true")) {
		   outcome1 ++;
	   }else {
		   outcome2 ++;
	   }
   }double p1 = outcome1 / (double)currdat.size();double p2 = outcome2 / (double)currdat.size();
   if(!Double.isNaN(((p1 * (Math.log10(p1) / Math.log10(2)) + p2 * (Math.log10(p2) / Math.log10(2))) * -1))) {
	   return ((p1 * (Math.log10(p1) / Math.log10(2)) + p2 * (Math.log10(p2) / Math.log10(2))) * -1);
   }
    return 0;
  }
  /**
   * A method that finds whether the solution is true or false for each outcome.
   * @param currdat The data in which the majority solution is in question.
   * @return A boolean representing the outcome in question.
   */
  public static boolean calcmajor(ArrayList<String[]> currdat) {
	  int outcome1 = 0;int outcome2 = 0;
	   for(int i = 1;i < currdat.size();i ++) {
		   if(currdat.get(i)[currdat.get(1).length - 1].equals("true")) {
			   outcome1 ++;
		   }else {
			   outcome2 ++;
		   }
	   }return outcome1 > outcome2;
  }
  /**
   * A method that splits a tree based upon a given row and a given attribute.
   * @param row The row of the data that determining the split.
   * @param attribute The value of the data determining the split.
   * @pamam index The index in which the current data split is stored in datadiv.
   * @return Return the index in which the false strand of data is stored.
   */
  public static ArrayList<ArrayList<String[]>> treeSplit(int col, String attribute, ArrayList<String[]> data, String great) {
	  ArrayList<ArrayList<String[]>> alldat = new ArrayList<ArrayList<String[]>>();
	  ArrayList<String[]> dat1 = new ArrayList<String[]>();
	  ArrayList<String[]> dat2 = new ArrayList<String[]>();
	  //Integer checker
	  boolean integer = true;
	  if(great == null) {
		  integer = false;
	  }
	  if(integer) {
		  int threshhold = Integer.parseInt(attribute);
		  boolean greatthan = true;
		  if(great.equals("<")) {
			  greatthan = false;
		  }int a = 0;
		  //Looks at every integer and determines if it meets requirements.
		  for(int i = 0;i < data.size(); i ++) {
			  //System.out.println(data.get(i)[col]);
			  if(greatthan && Integer.parseInt(data.get(i)[col]) > threshhold) {
				  dat1.add(data.get(a));
			  }else if(!greatthan && Integer.parseInt(data.get(i)[col]) < threshhold) {
				  dat1.add(data.get(a));
			  }else if(data.get(i)[col] != null && data.get(i)[col] != ""){
				  dat2.add(data.get(a));
			  }
			  a ++;
		  }
	  }else {
		  String threshhold = attribute;
		  int a = 0;
		  //Looks at every string and determines if it is equal to the attribute string.
		  for(int i = 0;i < data.size() - 1; i ++) {
			  if(threshhold.equals(data.get(i)[col])) {
				  dat1.add(data.get(a));
			  }else if(data.get(i)[col] != null && data.get(i)[col] != ""){
				  dat2.add(data.get(a));
			  }a ++;
		  }
	  }alldat.add(dat1);alldat.add(dat2); //dat1 true outcome dat2 false outcome
	  return alldat;
  }
  /**
   * A method that finds whether or not a given string is an integer.
   * @param integer The data in question.
   * @return Whether or not the data in question is an integer.
   */
  public static boolean isInteger(String integer) {
	  char[] attributes = integer.toCharArray();
	  for(int i = 0; i < attributes.length; i ++) {
		  if(((int)attributes[i] < 48 || (int)attributes[i] > 57) && (int)attributes[i] != 45 && (int)attributes[i] != 46) {
			  return false;
		  }
	  }return true;
  }
  /**
   * This method build a tree recursively through the process of finding the best attribute of the best column
   * and saving that split. From there it would call this method again for each branch with the new branch data.
   * It would continue untill the lowest average entropy of the next two branches is lower than the entropy of the
   * unsplit branch.
   * @param dat The data that is going to be split.
   */
  public static void recursiveTreeBuilder(ArrayList<String[]> dat, double prevEntropy, double nodeID) {
	  double lowest_entropy = prevEntropy; //start at entropy(data)
	  String lowest_entatribute = "";
	  int lowest_entcol = 0;
	  double curr_entropy = 0;
	  boolean lowerval = false;
	  //Loops through every attribute of every instance of data excluding the declaration attribute.
	  if(dat.size() > 0) {
		  ArrayList<ArrayList<String[]>> da = new ArrayList<ArrayList<String[]>>();
		  for(int a = 0; a < dat.get(0).length - 1; a ++) {
			  for(int i = 0; i < dat.size(); i ++) {
				  if(isInteger(dat.get(i)[a])) {
					  da = treeSplit(a, dat.get(i)[a], dat, ">");
					  curr_entropy = (entropy(da.get(0)) + entropy(da.get(1))) / 2;
				  }else if(!isInteger(dat.get(i)[a])){
					  da = treeSplit(a, dat.get(i)[a], dat, null);
					  curr_entropy = (entropy(da.get(0)) + entropy(da.get(1))) / 2;
				  }
				  	if(Math.abs(curr_entropy * ((da.get(0).size() - da.get(1).size()) / bestVal)) < lowest_entropy) { //or Math.abs(curr_entropy * ((da.get(0).size() - da.get(1).size()) / bestVal))//or curr_entropy < lowest_entropy
				  		lowest_entropy = curr_entropy;
				  		lowest_entatribute = dat.get(i)[a] + "";
				  		lowest_entcol = a;
				  		lowerval = true;
				  		//System.out.println(lowest_entropy);
				  	}
			  }
		  }
	  }
	  if(lowerval) {
		  if(isInteger(lowest_entatribute)) {
			  ArrayList<ArrayList<String[]>> data = treeSplit(lowest_entcol, lowest_entatribute, dat, ">");
			  if(data.get(0).size() > 0 && data.get(1).size() > 0) {
				  nav.add("[" + ", " + lowest_entcol + ", " + lowest_entatribute +", " + ">" + ", " + (nodeID + 1) + ", " + "]");
				  recursiveTreeBuilder(data.get(0), lowest_entropy, nodeID + 1); //true
				  recursiveTreeBuilder(data.get(1), lowest_entropy, nodeID + 1.1); //false
			  }else {
				  //System.out.println(calcmajor(dat) + " " + entropy(dat) + " " + dat.size());
				  nav.add("[" + ", " + calcmajor(dat) + ", " + (nodeID + 1) + ", " + "]");
			  }
		  }else if(!isInteger(lowest_entatribute)){
			  ArrayList<ArrayList<String[]>> data = treeSplit(lowest_entcol, lowest_entatribute, dat, null);
			  if(data.get(0).size() > 0 && data.get(1).size() > 0) {
				  nav.add("[" + ", " + lowest_entcol + ", " + lowest_entatribute +", " + "n/a" + ", " + (nodeID + 1) + ", " + "]"); //+ calcmajor(data.get(0)) + ", "
				  recursiveTreeBuilder(data.get(0), lowest_entropy, nodeID + 1);
				  recursiveTreeBuilder(data.get(1), lowest_entropy, nodeID + 1.1);
			  }else {
				  //System.out.println(calcmajor(dat) + " " + entropy(dat) + " " + dat.size());
				  nav.add("[" + ", " + calcmajor(dat) + ", " + (nodeID + 1) + ", " + "]");
			  }
		  }
	  }else {
		  //System.out.println(calcmajor(dat) + " " + entropy(dat) + " " + dat.size());
		  nav.add("[" + ", " + calcmajor(dat) + ", " + (nodeID + 1) + ", " + "]");
	  }
  }
}
