package connect4;
//Hassan Raja ID:112249751
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connect4 {
	private char[][] board;;
	private int width = 7;
	private int height = 6;
	
	public Connect4() {
		this.board = new char[this.height][this.width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.board[i][j] = ' ';
			}
			System.out.print("\n");
		}
	}
	public void printBoard(){
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print("|" + board[i][j] + "|");
			}
			System.out.print("\n");
		}
		System.out.println(".....................");
	}
	
	static Scanner input = new Scanner(System.in);
	public int validNum() {
		String num = input.nextLine().trim();
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException ex){
			System.out.println("Enter number please!");
		} catch (NullPointerException ex) {
			System.out.println("oops");
		}
		return validNum();
	}
	
	public void makeMove(char symbol, String s) {
		System.out.print("Drop a " + s + " disk at column (0–6): ");
		int col;
		
		try {
			col = validNum();
			
			for(int i = this.height-1; i>=0; i--) {
				if(board[i][col] == ' ') {
					board[i][col] = symbol;
					break;
				}	
			}
			
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Enter a number between 0 and " + (this.width-1));
			makeMove(symbol, s);
		} catch (InputMismatchException ex) {
			System.out.println("Enter an integer!");
			makeMove(symbol, s);
		}  catch (Exception ex) {
			System.out.println("oop, You entered the wrong value! Good-bye!!!");
			makeMove(symbol, s);
		}
		return;
	}
	
	public boolean getWinner() {
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				char player = board[i][j];
				if(player == ' ')
					continue; //does not check the empty parts of board
				if(j+3 < width && player == board[i][j+1] && player == board[i][j+2] && player == board[i][j+3]) { //checks horizontally
					return true;
				}
				if(i+3 < height && player == board[i+1][j] && player == board[i+2][j] && player == board[i+3][j]) { //checks vertically
					return true;
				}
				if(j+3 < width && i+3 < height && player == board[i+1][j+1] && player == board[i+2][j+2] && player == board[i+3][j+3]) { //checks diagonal up and right
					return true;
				}
				if(j-3 >= 0 && i+3 < height && player == board[i+1][j-1] && player == board[i+2][j-2] && player == board[i+3][j-3]) { //checks diagonal up and left
					return true;
				}
			}
		}
		return false; //no winner
	}

	

	public static void main(String[] args) throws Exception {
		Connect4 game = new Connect4();
		game.printBoard();

		while(true) {
			
			game.makeMove('R', "red");
			game.printBoard();
			if(game.getWinner() == true) {
				System.out.println("The red player won!");
				break;
			}
			
			game.makeMove('Y', "yellow");
			game.printBoard();
			if(game.getWinner() == true) {
				System.out.println("The yellow player won!");
				break;
			}	
		}
		input.close();
		System.exit(0);
		
	}
}
