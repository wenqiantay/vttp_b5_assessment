package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Game {
    
    private char[][] board;
    private String boardFile;
    private int width = 0;
    private int height = 0;
    private int offSetY = 0;
    private int offSetX = 0;

    public Game(String tttFile) {
        boardFile = tttFile;
    }

    public void readFile(){
        
		try(BufferedReader br = new BufferedReader(new FileReader(boardFile))) {
		String line;
		while ((line = br.readLine()) != null) {
            line = line.trim();
            initialiseBoard(width, height);
            populateBoard(br);
		}
        } catch (Exception e) {
            System.err.println("Error reading file." + e.getMessage());
        }
    }
    
    //Check win
    private static boolean hasWon(char[][] board, char symbol) {
        if ((board[0][0] == symbol && board [0][1] == symbol && board [0][2] == symbol) ||
			(board[1][0] == symbol && board [1][1] == symbol && board [1][2] == symbol) ||
			(board[2][0] == symbol && board [2][1] == symbol && board [2][2] == symbol) ||
			
			(board[0][0] == symbol && board [1][0] == symbol && board [2][0] == symbol) ||
			(board[0][1] == symbol && board [1][1] == symbol && board [2][1] == symbol) ||
			(board[0][2] == symbol && board [1][2] == symbol && board [2][2] == symbol) ||
			
			(board[0][0] == symbol && board [1][1] == symbol && board [2][2] == symbol) ||
			(board[0][2] == symbol && board [1][1] == symbol && board [2][0] == symbol) ) {
			return true;
		}
		return false;
    }
    

    public void printBoard(){
        System.out.println("Board: \n");
        for(int i = 0; i < height; i++) {
            System.out.println(new String(board[i]));
        }
    }

    private char[][] initialiseBoard(int width, int height){
        char[][] board = new char[height][0];
        for( int y = 0; y < height; y++) {
            board[y] = Constants.BLANK.substring(0, width).toCharArray();
        }
        return board;
    }

    private void populateBoard(BufferedReader br) throws IOException {
        String line;
        int y = offSetY;
        while ((line = br.readLine()) != null) {
            char[] data = line.toCharArray();
            for(int x = 0; x < data.length; x++) {
                board[y][x + offSetX] = data[x];
                y++;
            }
        }
    }

}
