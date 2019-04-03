import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
/***
 * This program reads a dataset and implements the appropriate data into a file
 * in the appropriate format to be read by the program that requires that data.
 * @author Benjamin Mason
 * @version 1.1
 */
public class DataSetFilter{
	public static void main(String[]args){
			filter("WeatherData.csv");
	}
	/**
	 * Filters excess information from each line of the original data set,
	 * and finds the average temperature, and pressure.
	 * @param fileName The name of the file that contains the data set.
	 */
	public static void filter(String fileName){
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			PrintWriter out = new PrintWriter(new FileOutputStream("data.txt"));
      in.readLine();
      String line = in.readLine();
			String init = line.split(",")[0];double temptot = 0;double presstot = 0;int numdat = 0;double prectot = 0;
				double humtot = 0;
	do { //numdat = 0 if daily
		//System.out.println(line.split(",")[11]);
        if(init.equals(line.split(",")[0])){
          if(!line.split(",")[2].equals("")){
            temptot += Double.parseDouble(line.split(",")[2]);
          }if(!line.split(",")[4].equals("")){
              humtot += Double.parseDouble(line.split(",")[4]);
          }if(!line.split(",")[10].equals("")){
            presstot += Double.parseDouble(line.split(",")[10]);
          }if(!line.split(",")[13].equals("")){
            prectot += Double.parseDouble(line.split(",")[13]); //or 15
          }
          numdat ++;
        }else{
        	int precip = 0;
		if(prectot > 0) {
			precip = 1;
		}else {
			precip = 0;
		}
        	out.println((int)temptot / numdat + ", " + (int)presstot / numdat + ", " + (int)humtot / numdat + ", " + precip);
          init = line.split(",")[0];
          if(!line.split(",")[2].equals("")){
            temptot = Double.parseDouble(line.split(",")[2]);
          }else{
        	  		temptot = 0;
		  }if(!line.split(",")[4].equals("")){
			  humtot = Double.parseDouble(line.split(",")[4]);
		  }else{
			  humtot = 0;
		  }if(!line.split(",")[10].equals("")){
            presstot = Double.parseDouble(line.split(",")[10]);
          }else{
						presstot = 0;
					}if(!line.split(",")[13].equals("")){
            prectot = Double.parseDouble(line.split(",")[13]); //or 15
          }else{
						prectot = 0;
					}numdat = 0;
        }
			}while((line = in.readLine()) != null && line.split(",").length > 0);
			in.close();out.close();
			System.out.println("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
