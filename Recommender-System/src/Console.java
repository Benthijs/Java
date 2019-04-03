import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * @author Benjamin Mason
 * @version 1.0
 */
public class Console extends JFrame{
    private static final long serialVersionUID = 1L;
    private int a = 0;
    private JLabel[] indata;
    private JPanel dataPanel = new JPanel();
    private Container main = getContentPane();
    public static void main(String[]args){
        Console window = new Console();
        window.setVisible(true);
    }
    /**
     * Sets up the consoles GUI.
     */
    public Console(){
        super();
        setTitle("Console");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setBackground(Color.black);
        add(dataPanel, BorderLayout.WEST);
        dataPanel.setBackground(Color.BLACK);
        main.setBackground(Color.BLACK);
        JScrollPane pictureScrollPane = new JScrollPane(dataPanel);
        pictureScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        pictureScrollPane.setForeground(Color.gray);
        pictureScrollPane.setBackground(Color.BLACK);
        main.add(pictureScrollPane, BorderLayout.CENTER);
        setSize(new Dimension(240, 180));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1200,900);
        indata = new JLabel[35];
        dataPanel.setLayout(new GridLayout(35, 1));
        for(int a = 0; a < indata.length; a ++) {
        		indata[a] = new JLabel();
        		dataPanel.add(indata[a], BorderLayout.WEST);
        }
    }
    /**
     * Logs the newest event on the console.
     * @param content The string containing the information that is being logged.
     */
    public void log(String content){
        Font font = new Font("Consolas", Font.PLAIN, 18);
        indata[a].setText("	" + content);
        indata[a].setForeground(Color.white);
        indata[a].setFont(font);
        System.out.println("Y");
        a ++;
    }
}