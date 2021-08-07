/*
 * This is a Tic Tac Toe game made with object oriented programming applying knowledge I learned in AP Java.
 * @author Benjamin Mason.
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
public class TicTacToe extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton[][] buttons = new JButton[3][3];
	private static Ai com = new Ai();
    private static Player player = com.get(); 
	public static void main(String[]args){
		TicTacToe gui = new TicTacToe();
		gui.setVisible(true);
	}
	public TicTacToe(){
		super();
		Container main = getContentPane();
		setSize(600, 600);
		setTitle("Tic Tac Toe Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gameInterface(main);
	}
	private void gameInterface(Container main){
		JPanel theButtons = new JPanel(new GridLayout(3, 3));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		for(int i = 0; i < buttons.length; i ++){
			for(int a = 0; a < buttons[0].length; a ++){
				buttons[i][a] = new JButton("" + i + a);
				buttons[i][a].addActionListener(this);
				buttons[i][a].setForeground(new Color(0, 0, 0, 0));
				theButtons.add(buttons[i][a]);
				buttons[i][a].setBorder(border);
			}	
		}
		main.add(theButtons);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < buttons.length; i ++){
			for(int a = 0; a < buttons[0].length; a ++){
				if(e.getActionCommand().equals("" + i + a)){
					buttons[i][a].setText("X");
					buttons[i][a].setForeground(Color.BLACK);
					player.makeMove(i, a);
					player.board.checkWin();
			        if(!player.board.isFull() && !player.board.won()){
			        	String[] choice = com.findMoves(player.getLastMove()).split(", ");
			        	buttons[Integer.parseInt(choice[0])][Integer.parseInt(choice[1])].setText("O");
			        	buttons[Integer.parseInt(choice[0])][Integer.parseInt(choice[1])].setForeground(Color.BLACK);
			        	player.oppMakeMove(Integer.parseInt(choice[0]), Integer.parseInt(choice[1]));
			        	player.board.checkWin();
			        }
			        if(player.board.isFull() || player.board.won()){
						player.board.setWon();
						for(int b = 0; b < 3; b ++){
							for(int c = 0; c < 3; c++){
								player.board.set(b, c, 2);
								buttons[b][c].setForeground(new Color(0, 0, 0, 0));
								buttons[b][c].setText("" + b + c);
								System.out.println(player.board.isFull());
							}
						}
					}
				}
			}
		}
	}
	
}