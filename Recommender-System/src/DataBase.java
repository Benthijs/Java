import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * @author Benjamin Mason
 * @version 1.0
 */
public class DataBase extends JFrame{
    private static final long serialVersionUID = 1L;
    private String[][] data;
    private JButton[] buttons = new JButton[3];
    private int a = 0;
    private JLabel[][] indata;
    private JPanel dataPanel = new JPanel();
    public static void main(String[]args){
        DataBase window = new DataBase();
        window.setVisible(true);
    }
    /**
     * Sets up the GUI.
     */
    public DataBase(){
        super();
        Container main = getContentPane();
        setTitle("Data Base");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setBackground(Color.black);
        createButtons(main);
        data(main);
        add(dataPanel);
        JScrollPane pictureScrollPane = new JScrollPane(dataPanel);
        pictureScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        pictureScrollPane.setForeground(Color.gray);
        pictureScrollPane.setBackground(Color.BLACK);
        main.add(pictureScrollPane, BorderLayout.CENTER);
        setSize(new Dimension(240, 180));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1200,900);
    }
    /**
     * Filters and prints the data into a neat list.
     * @param main Contains the GUI's content.
     */
    public void data(Container main){
        try{
        	File name = new File("reccomendations.txt");
            Scanner filescan = new Scanner(name);
            while(filescan.hasNext()){
                filescan.nextLine();
                a ++;
            }
            data = new String[a][3];
            Scanner fileprint = new Scanner(name);
            fileprint.nextLine();
            int i = 0;
            while(fileprint.hasNext()){
                	String line = fileprint.nextLine();
                	String dat[] = line.split(",, ");
                	System.out.println(line);
                	for(int b = 0; b < 3; b ++){
                        if(dat[b].equals("Not based on Genre")){
                        	dat[b] = "No";
                        }else if(dat[b].equals("Based on Genre")){
                        	dat[b] = "Yes";
                        }
                		data[i][b] = dat[b]; 
                    }
                	i ++;
            }
            	draw(a, main);
            filescan.close();
            fileprint.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param length(length of the data array)
     * @param main
     */
    public void draw(int length, Container main){
        indata = new JLabel[a][3];
        Font font = new Font("Times New Roman", Font.BOLD, 12);
        dataPanel.setLayout(new GridLayout(length, 3));
        dataPanel.setBackground(Color.BLACK);
        for(int i = 0; i < indata.length; i ++){
            if(indata.length > 1) {
            		for(int a = 0; a < 3; a ++){
            			if(a == 0 && data[i][a].charAt(7) == ',') {
            				indata[i][a] = new JLabel(data[i][a].substring(1, 7) + data[i][a].substring(8, data[i][a].length() - 1));
            			}else {
            				indata[i][a] = new JLabel(data[i][a]);
            			}
        				indata[i][a].setForeground(Color.white);
        				indata[i][a].setFont(font);
        				dataPanel.add(indata[i][a]);
        			}
            }else {
            		indata[i][0] = new JLabel(data[i][0]);
            }
        }
    }
    /**
     * Creates the buttons at the top of the GUI.
     * @param main Contains the GUI's content.
     */
    public void createButtons(Container main){
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        String[] names = {"Movie","Genre","Genre Based", "movie", "genre", "genre based"};
        for(int i = 0; i < buttons.length; i ++){
            buttons[i] = new JButton(names[i]);
            buttons[i].setFont(font);
            buttons[i].setFocusable(false);
            buttonPanel.add(buttons[i]);
        }
        main.add(buttonPanel, BorderLayout.NORTH);
    }
}