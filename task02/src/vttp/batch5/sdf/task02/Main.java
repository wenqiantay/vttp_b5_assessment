package vttp.batch5.sdf.task02;

public class Main {

	public static void main(String[] args) throws Exception {

		//tttboard = read board configuration
		if(args.length < 1) {
			System.out.println("Please provide the TTT board file.");
		}

		Game game = new Game(args[0]);
		game.readFile();
		game.printBoard();
	
		

		

		

			
		
	}
}
