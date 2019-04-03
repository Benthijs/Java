import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author Benjamin Mason
 * @version 1.0
 */
public class RecommenderInterface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	static String[] labels = {"The Imitation Game (116797)", "Deadpool (122904)", "Captain America: Civil War (122920)", "X-Men: Apocalypse (122924)",
			"The Martian (134130)", "Inside Out (134853)", "Suicide Squad (135536)",
			"The Jungle Book (137857)", "Jason Bourne (160438)", "The Beatles: Eight Days a Week - The Touring Years (163949)"};
	static ArrayList<String> label = new ArrayList<String>(Arrays.asList(labels));
	static String[] ratings = new String[labels.length];
	static JComboBox<Object>[] movies;
	static int a = 0;
	static String[] args1;
	static Console con = new Console();
	static JComboBox<Object>[] selectedMovie;
	public static void main(String[]args){
		OneAttributeDataSetFilter.main(args);
		con.setVisible(true);
		con.log("Dataset Converted");
		RecommenderInterface main = new RecommenderInterface();
		main.setVisible(true);
		args1 = args;
	}
	/**
	 * Sets up the GUI.
	 */
	public RecommenderInterface(){
		super();
		Container main = getContentPane();
		setTitle("Recommender System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pageContent(main);
		main.setBackground(Color.WHITE);
	}
	/**
	 * Adds the page content.
	 * @param main Contains the GUI's content.
	 */
	@SuppressWarnings("unchecked")
	public void pageContent(Container main){
		movies = new JComboBox[4];
		selectedMovie = new JComboBox[4];
		JPanel optionPane = new JPanel(new GridLayout(labels.length + 1, 2));
		optionPane.setBackground(Color.WHITE);
		String[] options = {"0.0", "0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0", "n/a"};
		for(int i = 0; i < movies.length; i ++){
			selectedMovie[i] = new JComboBox<Object>(label.toArray());
			selectedMovie[i].setSelectedIndex(0);
			selectedMovie[i].addActionListener(this);
			optionPane.add(selectedMovie[i]);
			movies[i] = new JComboBox<Object>(options);
			movies[i].setSelectedIndex(options.length - 1);
			movies[i].addActionListener(this);
			optionPane.add(movies[i]);
		}
		JButton button = new JButton("Make Recommendation");
		button.addActionListener(this);
		optionPane.add(button);
		main.add(optionPane, BorderLayout.CENTER);
	}
	/**
	 * Sends the data to RecommationSystem.java
	 * @param e The source of the action performed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contains("Make Recommendation")){
			try{
				ArrayList<String> content = new ArrayList<String>();
				PrintWriter out = new PrintWriter(new FileOutputStream("input.txt"));
				PrintWriter out1 = new PrintWriter(new FileOutputStream("createdDataSet.txt", true));
				Scanner in = new Scanner(new File("createdDataset.txt"));
				for(int i = 0; i < 4; i ++){
					if(!((String)movies[i].getSelectedItem()).contains("n/a")){
						content.add(selectedMovie[i].getSelectedItem().toString().split(" ")[selectedMovie[i].getSelectedItem().toString().split(" ").length - 1].replace("(", "").replace(")", "") + ", " + movies[i].getSelectedItem());
					}
				}
				String next;
				for(int i = 0; i < 4; i ++) {
					for(int a = 0; a < content.size() - 1; a ++) {
						if((int)Double.parseDouble(content.get(a).split(", ")[0]) > (int)Double.parseDouble(content.get(a + 1).split(", ")[0])) {
							next = content.get(a + 1);
							content.set(a + 1, content.get(a));
							content.set(a, next);
						}
					}
				}
				int high = 0;String line;int newVal;
				while(in.hasNext()) {
					line = in.nextLine();
					if(high < (newVal = Integer.parseInt(line.split(", ")[0]))) {
						System.out.println(high);
						high = newVal;
					}
				}
				for(int i = 0; i < content.size(); i ++) {
					out.println(content.get(i));
					out1.println(high + 1 + ", " + content.get(i));
				}
				out.close();out1.close();in.close();
				dispose();
				RecommendationSystem.compute(new File("input.txt"));
				con.log("Recommendations Calculated");
				if(RecommendationSystem.movies.size() > 0) {
					DataBase.main(args1);
				}
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Here", JOptionPane.PLAIN_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}
