import java.util.HashMap;
public class Ai{
    public Player player = new Player();
    private String[] check = player.board.check;
    private HashMap<String, String> possibleMoves = new HashMap<String, String>();
    public Ai(){
        possibleMoves.put("0, 0", "0, 2;2, 0;2, 2");
        possibleMoves.put("1, 0", "1, 2;0, 0;2, 0");
        possibleMoves.put("2, 0", "2, 0;0, 2;0, 0");
        possibleMoves.put("2, 1", "0, 1;2, 0;2, 2");
        possibleMoves.put("1, 1", "0, 1;1, 2;2, 1;1, 0");
        possibleMoves.put("0, 1", "2, 1;0, 0;0, 2");
        possibleMoves.put("0, 2", "0, 0;2, 0;2, 2");
        possibleMoves.put("1, 2", "1, 0;2, 0;2, 2");
        possibleMoves.put("2, 2", "0, 0;2, 0;0, 2");
    }
    public String findMoves(String lastMove){
        String finalMove = "";
        String[] BoardVals = {"0, 0", "0, 1", "0, 2", "1, 0", "1, 1", "1, 2", "2, 0", "2, 1", "2, 2"};
        String[] checkwin = {"09192","39495","69798","09396","19497","29598","09498","29496"};
        int winVal = 0;
        String[] combinations;
        if(player.board.get(1, 1)){
        	return 1 + ", " + 1;
        }
        for(int a = 0; a < 8; a ++){
            winVal = 0;
            finalMove = "";
            combinations = checkwin[a].split("9");
            for(int b = 0; b < combinations.length; b ++){
                if(check[Integer.parseInt(combinations[b])].contains("O")){
                    winVal ++;
                }
                else if(check[Integer.parseInt(combinations[b])].contains("-")){
                    finalMove = BoardVals[Integer.parseInt(combinations[b])];
                }
                else if(check[Integer.parseInt(combinations[b])].contains("X")){
                    b = combinations.length;
                }
                if(winVal == 2 && finalMove.length() > 0){
                    a = 8;
                    return finalMove;
                }
            }
        }
        for(int a = 0; a < 8; a ++){
            winVal = 0;
            finalMove = "";
            combinations = checkwin[a].split("9");
            for(int b = 0; b < combinations.length; b ++){
                if(check[Integer.parseInt(combinations[b])].contains("X")){
                    winVal ++;
                }
                else if(check[Integer.parseInt(combinations[b])].contains("-")){
                    finalMove = BoardVals[Integer.parseInt(combinations[b])];
                }
                else if(check[Integer.parseInt(combinations[b])].contains("O")){
                    b = combinations.length;
                }
                if(winVal == 2 && finalMove.length() > 0){
                    a = 8;
                    return finalMove;
                }
            }
        }
        String[] moves = possibleMoves.get(lastMove).split(";");
        int picker = Randomizer.nextInt(0, moves.length - 1);
        for(int i = 0; i < moves.length; i ++){
            finalMove = moves[picker].split(", ")[0] + ", " + moves[picker].split(", ")[1];
            if(!player.get(Integer.parseInt(moves[picker].split(", ")[0]), Integer.parseInt(moves[picker].split(", ")[1]))){
                picker = Randomizer.nextInt(0, moves.length - 1);
                finalMove = moves[picker].split(", ")[0] + ", " + moves[picker].split(", ")[1];
            }
        }
        int row = Randomizer.nextInt(0, 2);
        int col = Randomizer.nextInt(0, 2);
        if(!player.get(Integer.parseInt(moves[picker].split(", ")[0]), Integer.parseInt(moves[picker].split(", ")[1]))){
            finalMove = row + ", " + col;
            while(!player.get(row, col)){
                row = Randomizer.nextInt(0, 2);
                col = Randomizer.nextInt(0, 2);
                finalMove = row + ", " + col;
            }
        }
    return finalMove;
    }
    public Player get(){
        return player;
    }
}