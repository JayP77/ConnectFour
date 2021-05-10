import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
class MyTest {
	int c = 0;
	int r = 0;
	
	int [][] gameBoard1;
	int [][] gameBoard;
	public void Construct(){
		int rows = 8;
		int columns = 7;
		gameBoard = new int[rows][columns];
	        for(int i = 1; i < 8; i++) {
	        	for(int j = 1; j < 7; j++) {
	        		gameBoard[i][j] = gameBoard[i][j];
	        	}
	        }
	        for(int i = 1; i < 8; i++) {
	        	for(int j = 1; j < 7; j++) {
	        		if(i == 7) {
	        			gameBoard[i][j] = 1;
	        		}
	        		else {
	        			gameBoard[i][j] = 0;
	        		}
	        	}
	        }
	}
	@Test
	void GridConstruction() {
		Grid g1 = new Grid(7);
		assertEquals(7,g1.getColumnNumber(), "wrong size");
	}
	@Test
	void GridConstruction2() {
		Grid g1 = new Grid(7);
		assertEquals(0,g1.getPieces(), "wrong size");
	}
	@Test
	void GameWinTesting() {
		int rows = 8;
		int columns = 7;
		gameBoard1 = new int[rows][columns];
		
		for(int i = 1 ; i < 8; i++) {
			
			for(int j = 1 ; j < 7; j++) {
				gameBoard1[i][j] = 1;
			}
		}
		int counter = 0;
		assertEquals(8, gameBoard1.length, "wrong size" );
		for(int e: gameBoard1[2]) {
			if(e == 0 && counter == 0) {
				assertEquals(0, e, "wrong size" );
				counter = 1;
			}
			else {
				assertEquals(1, e, "wrong size" );
			}
			System.out.println(e);
		}
	}
	@Test
	void LogicCheckHorizontal() {
		Construct();
	    //Horizontal Win check     
		gameBoard[1][6] = 3;
		gameBoard[1][5] = 3;
		gameBoard[1][4] = 3;
		gameBoard[1][3] = 3;
		assertEquals(1, WinCheck(), "Incorrect" );
		Construct();
		
		//Second Horizontal win check
		gameBoard[2][3] = 2;
		gameBoard[2][4] = 2;
		gameBoard[2][5] = 2;
		gameBoard[2][6] = 2;
		assertEquals(1, WinCheck(), "Incorrect" );
		}
		
		@Test
		void LogicCheckVertical() {
		Construct();
		
		
		//Vertical Win Check
		gameBoard[2][1] = 3;
		gameBoard[3][1] = 3;
		gameBoard[4][1] = 3;
		gameBoard[5][1] = 3;
		assertEquals(1, WinCheck(), "Incorrect" );
		Construct();
		
		//Second Vertical Win Check
		gameBoard[5][2] = 2;
		gameBoard[4][2] = 2;
		gameBoard[3][2] = 2;
		gameBoard[2][2] = 2;
		assertEquals(1, WinCheck(), "Incorrect" );
		}
		
		@Test
		void LogicCheckDiagnol() {
		Construct();
		
		//Diagonal win check
		gameBoard[1][1] = 3;
		gameBoard[2][2] = 3;
		gameBoard[3][3] = 3;
		gameBoard[4][4] = 3;
		assertEquals(1, WinCheck(), "Incorrect" );
		Construct();
		
		//Second Diagonal win check
		gameBoard[4][4] = 2;
		gameBoard[3][3] = 2;
		gameBoard[2][2] = 2;
		gameBoard[1][1] = 2;
		assertEquals(1, WinCheck(), "Incorrect" );
		
		}
		void incorrectCombination() {
			
		Construct();
		// Wrong combination check should return 0
		gameBoard[4][4] = 3;
		gameBoard[3][3] = 2;
		gameBoard[2][2] = 3;
		gameBoard[1][1] = 2;
		assertEquals(0, WinCheck(), "Incorrect" );
		
		//Second Wrong combination check should return 0
		Construct();
		gameBoard[4][4] = 3;
		gameBoard[3][4] = 2;
		gameBoard[3][2] = 3;
		gameBoard[7][1] = 2;
		assertEquals(0, WinCheck(), "Incorrect" );
		
		}
		
	//LogicCheckHorizontal
	public int WinCheck() {
			for( c = 1; c < 8; c++) {
				for( r = 1; r < 7; r++) {
				if(c - 4 > 0) {
					if(gameBoard[c][r] == 3 && gameBoard[c-1][r] == 3 && gameBoard[c-2][r] == 3 &&  gameBoard[c-3][r] == 3){
						System.out.println("FINISHED1 P2");
						return 1;
					}
					if(gameBoard[c][r] == 2 && gameBoard[c-1][r] == 2 && gameBoard[c-2][r] == 2 &&  gameBoard[c-3][r] == 2){
						System.out.println("FINISHED1 P2");				
						return 1;
					}
				}
			if(r - 4 > 0) {
				if(gameBoard[c][r] == 3 && gameBoard[c][r-1] == 3 && gameBoard[c][r-2] == 3 && gameBoard[c][r-3] == 3){
					System.out.println("FINISHED2");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c][r-1] == 2 && gameBoard[c][r-2] == 2 && gameBoard[c][r-3] == 2){
					System.out.println("FINISHED2");
					return 1;
				}
			}
			if(c + 4 <= 6) {
				if(gameBoard[c][r] == 3 && gameBoard[c + 1][r] == 3 && gameBoard[c+2][r] == 3 && gameBoard[c+3][r] == 3){
					System.out.println("FINISHED3");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c + 1][r] == 2 && gameBoard[c+2][r] == 2 && gameBoard[c+3][r] == 2){
					System.out.println("FINISHED3 P2");
					return 1;
				}
			}
			if(r + 4 <= 7) {
				if(gameBoard[c][r] == 3 && gameBoard[c][r + 1] == 3 && gameBoard[c][r+2] == 3 && gameBoard[c][r+3] == 3 ){
					System.out.println("FINISHED4");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c][r + 1] == 2 && gameBoard[c][r+2] == 2 && gameBoard[c][r+3] == 2 ){
					System.out.println("FINISHED4 P2");
					return 1;
				}
			}
			if(r + 4 <= 7 && c + 4 <= 6) {
				if(gameBoard[c][r] == 3 && gameBoard[c+1][r + 1] == 3 && gameBoard[c+2][r+2] == 3  && gameBoard[c+3][r+3] == 3 ){
					System.out.println("FINISHED5");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c+1][r + 1] == 2 && gameBoard[c+2][r+2] == 2 && gameBoard[c+3][r+3] == 2 ){
					System.out.println("FINISHED5 P2");
					return 1;
				}
			}
			if(r - 4 >= 0 && c - 4 >= 0) {
				if(gameBoard[c][r] == 3 && gameBoard[c-1][r-1] == 3 && gameBoard[c-2][r-2] == 3 && gameBoard[c-3][r-3] == 3){
					System.out.println("FINISHED6");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c-1][r-1] == 2 && gameBoard[c-2][r-2] == 2 && gameBoard[c-3][r-3] == 2){
					System.out.println("FINISHED6 P2");
					return 1;
				}
			}
			if(r + 4 <= 7 && c - 4  >= 0) {
				if(gameBoard[c][r] == 3 && gameBoard[c - 1][r+1] == 3 && gameBoard[c-2][r+2] == 3 && gameBoard[c-3][r+3] == 3){
					System.out.println("FINISHED7");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c - 1][r+1] == 2 && gameBoard[c-2][r+2] == 2 && gameBoard[c-3][r+3] == 2){
					System.out.println("FINISHED7 P2");
					return 1;
				}
			}
			if(r - 4 >= 0 && c + 4 <= 6) {
				if(gameBoard[c][r] == 3 && gameBoard[c+1][r-1] == 3 && gameBoard[c+2][r-2] == 3 && gameBoard[c+3][r-3] == 3){
					System.out.println("FINISHED8");
					return 1;
				}
				if(gameBoard[c][r] == 2 && gameBoard[c+1][r-1] == 2 && gameBoard[c+2][r-2] == 2 && gameBoard[c+3][r-3] == 2){
					System.out.println("FINISHED8 P2");
					return 1;
				}	
			}
		}
	
		}
		return 0;
	}
}
