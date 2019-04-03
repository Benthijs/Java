import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
/**
 * @author Benjamin Mason
 * @version 1.0
 */
public class DataSetFilter{
	public static void main(String[]args){
			filter("ratings.csv");
	}
	/**
	 * Filters excess information from each line of the original data set.
	 * @param fileName The name of the file that contains the data set.
	 */
	public static void filter(String fileName){
		try{
			String line;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			PrintWriter out = new PrintWriter(new FileOutputStream("out.txt"));
			in.readLine();
			while ((line = in.readLine()) != null) {
				out.println(line.split(",")[0] + ", " + line.split(",")[1] + ", " + line.split(",")[2]);
			}
			in.close();out.close();
			System.out.println("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
