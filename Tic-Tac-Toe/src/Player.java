public class Player{
    private final int HUMAN = 0;
    private final int Ai = 1;
    public Board board = new Board();
    private String lastPlayerMove = "";
    
    /**
     * @param row The row of the players move.
     * @param col The column of the players move.
     */
    public void makeMove(int row, int col){
        lastPlayerMove = row + ", " + col;
        board.set(row, col, HUMAN);
    }
    
    /**
     * @param row The row of the Ai's move.
     * @param col The column of the Ai's move.
     */
    public void oppMakeMove(int row, int col){
        board.set(row, col, Ai);
    }
    
    public boolean get(int row, int col){
        return board.get(row, col);
    }
    
    public String getLastMove(){
       return lastPlayerMove;
    }
}
