package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		//tttboard = read board configuration
		if(args.length < 1) {
			System.out.println("Please provide the TTT board file.");
		}
		String file = args[0];
		System.out.printf("Processing: %s\n\n", file);
		String[][] board = generateBoard(file);
		System.out.println("Board: ");
		printBoard(board);
		System.out.println("----------------------");
		List<int[]> pos = getEmptyPos(board);
		//evaluate board
		for(int[] p: pos){
			int util = getUtility(board, p);
			board[p[0]][p[1]] = Constants.BLANK;
			System.out.printf("y = %d , x = %d, utility = %d\n", p[0], p[1], util);
		}
		
	}

	public static int evaluateBoard(String[][] board){
		boolean playerWin = false;
		boolean opponentWin = false;
		int countOPieces = 0;
		int countEmptySpace = 0;

		//check rows
		for(int row = 0; row < board.length; row++) {
			if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
				if(board[row][0].equals(Constants.PLAYER))
				playerWin = true;
		} else {

				for(int col = 0; col < board[0].length; col++) {
					if (board[row][col].equals(Constants.OPPONENT)) {
						countOPieces++;
					} else if (board[row][col].equals(Constants.BLANK)){
						countEmptySpace++;
					}
				}
				if(countOPieces == 2 && countEmptySpace == 1){
					opponentWin = true;
				}
			}
		}
		//check column
		for(int col = 0; col < board[0].length; col++) {
			if(board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])){
				if(board[0][col].equals(Constants.PLAYER)){
					playerWin = true;
				}
			} else {
				for(int row = 0; row < board.length; row++){
					if(board[row][col].equals(Constants.OPPONENT)){
						countOPieces++;
					} else if (board[row][col].equals(Constants.BLANK)){
						countEmptySpace++;
				}
				if(countOPieces == 2 && countEmptySpace == 1) {
					opponentWin = true;
				}
			}

		}
		//check diagonal
		if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])|| board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])){
			if(board[1][1].equals(Constants.PLAYER))
			playerWin = true;
		} else {
			for(int i = 0; i < board.length; i++){
				if(board[i][i].equals(Constants.OPPONENT)) {
					countOPieces++;
				} else if(board[i][i].equals(Constants.BLANK)){
					countEmptySpace++;
					}
				}
			}
			if(countOPieces == 2 && countEmptySpace == 1){
				opponentWin = true;
			} else {
				//reset count
				countEmptySpace = 0;
				countOPieces = 0;
			}

			int[][] diagonalPos = {{2,0}, {1,1}, {0,2}};
			for(int[] pos: diagonalPos) {
				if(board[pos[0]][pos[1]].equals(Constants.OPPONENT)){
					countOPieces++;
				} else if (board[pos[0]][pos[1]].equals(Constants.BLANK)){
					countEmptySpace++;
				}
			}
			if(countOPieces == 2 && countEmptySpace == 1){
				opponentWin = true;
			}
		}	
		//playerwin = 1 opponentwin = -1 draw = 0
		if(playerWin){
			return 1;
		} else if (opponentWin){
			return -1;
		} else {
			return 0;
		}
	}

	public static int getUtility(String[][] board, int[] pos){
		board[pos[0]][pos[1]] = Constants.PLAYER;
		int utility = evaluateBoard(board);
		return utility;
	}
	

	public static String[][] generateBoard(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));

		//Generate board using 2D Array 3x3
		String[][] board = new String[3][3];
		int index = 0;
		String line = "";

		while(line != null) {
			line = br.readLine();
			if(line == null)
				break;
		//populate the board
		String[] position = line.split("");
		board[index] = position;
		index++;
		}
		br.close();
		return board;
	}

	public static void printBoard(String[][] board){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static List<int[]> getEmptyPos(String[][] board){
		List<int[]> emptyPos = new ArrayList<>();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j].equals(Constants.BLANK)){
				int[] pos = {i, j};
				emptyPos.add(pos);
				}
			}
		}
		return emptyPos;
	}
}
