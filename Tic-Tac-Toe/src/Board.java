import javax.swing.JOptionPane;
public class Board{
    private String[][] board = new String[3][3];
    public String[] check = new String[9];
    private int[][] boardVals = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private boolean won = false;
    private int oppScore = 0;
    private int playerScore = 0;
    public Board(){
        for(int i = 0; i < board.length; i ++){
            for(int a = 0; a < board[0].length; a ++){
                board[i][a] = "-";
                check[boardVals[i][a]] = "-";
            }
        }
    }
    /**
     * @param row The row of the board.
     * @param col The column of the board.
     * @return The status of the position.
     */
    public boolean get(int row, int col){
        return board[row][col].equals("-");
    }
    
    /**
     * @param row The row of the board.
     * @param col The column of the board.
     * @return The status of the position.
     */
    public String get(int row, int col, String i){
    	return board[row][col];
    }
    
    /**
     * @param row The row of the move.
     * @param col The column of the move.
     * @param player The player that made the move.
     */
    public void set(int row, int col, int player){
        String[] marker = {"X", "O", "-"};
        board[row][col] = marker[player];
        check[boardVals[row][col]] = marker[player];
    }
    
    public void checkWin(){
        String[] checkwin = {"09192","39495","69798","09396","19497","29598","09498","29496"};
        for(int a = 0; a < 8; a ++){
            String[] combinations = checkwin[a].split("9");
            int winVal = 0;
            for(int b = 0; b < combinations.length; b ++){
                if(check[Integer.parseInt(combinations[b])].contains("X")){
                    winVal ++;
                }
                if(winVal == 3){
                    a = 8;
                    playerScore ++;
                    JOptionPane.showMessageDialog(null, "Score: "+ getScore() + ". You won", "You won!", JOptionPane.PLAIN_MESSAGE);
                    won = true;
                }
            }
            winVal = 0;
            for(int b = 0; b < combinations.length; b ++){
                if(check[Integer.parseInt(combinations[b])].contains("O")){
                    winVal ++;
                }
                if(winVal == 3){
                    a = 8;
                    oppScore ++;
                    JOptionPane.showMessageDialog(null, "Score: "+ getScore() + ". Opponent won", "Opponent won :(", JOptionPane.PLAIN_MESSAGE);
                    won = true;
                }
            }
            winVal = 0;
        }
    }
    public boolean isFull(){
    	int occupied = 0;
    	for(int i = 0; i < board.length; i ++){
    		for(int a = 0; a < board[0].length; a ++){
    			if(!board[i][a].contains("-")){
    				occupied ++;
    			}
    		}
    	}
    	if(occupied == 9){
    		return true;
    	}
    	return false;
    }
    
    public boolean won(){
    	return won;
    }
    
    public void setWon(){
    	won = false;
    }
    
    /**
     * @return The scores of the player and the opponent.
     */
    public String getScore(){
    	return playerScore + "-" + oppScore;
    }
}
