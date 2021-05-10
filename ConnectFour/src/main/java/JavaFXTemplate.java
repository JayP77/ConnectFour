//  Author Jay Patel (Jpate260)

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack; 

	
public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	HashMap<String, Scene> sceneMap; // scenes
	private Button b1,b2,b3, original, theme1, theme2;
	private HBox root;
	private Text t1;
	private Stage myStage;
	Stack<GameButton> reverseButton;
	Text playerTurn = new Text();
	Text cPlayer = new Text();
	Text EndText = new Text();
	int temp = 0;
	int check = 2;
	int c = 0; // used for loop column
	int r = 0; // used for loop row
	String aOne = "Player one moved to "; // used for text 
	String bTwo = " "; // used for text 
	String cThree = " "; // used for text 
	int [][] gameBoard;
	Grid newGrid = new Grid(7);
	String str1 = "-fx-background-color: #0EBFE9; "; // color for theme
	String str2 = "-fx-background-color: #990000; "; // color for theme
	String str3 = "-fx-background-color: #FFFF00; "; // color for theme
	String str4 = "-fx-background-color: #FF0000; "; // color for theme
	String str5 = "-fx-background-color: #FF1493; "; // color for theme
	String str6 = "-fx-background-color: #00FF7F; "; // color for theme
	String p1 = str1; // current theme color for player 1
	String p2 = str2; // current theme color for player 2
	Boolean currPlayer = true; // current player true or false

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		sceneMap = new HashMap<String,Scene>();
		primaryStage.setTitle("Welcome to JavaFX this is the Game");
		sceneMap.put("scene", WelcomeScreen(primaryStage));
		sceneMap.put("game", GameScreen(primaryStage));
		sceneMap.put("end", EndScreen(primaryStage));
		primaryStage.setScene(sceneMap.get("scene"));
		primaryStage.show();
	}
	
	public Scene WelcomeScreen(Stage primaryStage) {
		
		//Creating an image 
		Image pic = new Image("connect4.png");
		ImageView v = new ImageView(pic);
		v.setPreserveRatio(true);
		v.setFitHeight(1000); 
		v.setFitWidth(1000); 
		v.setRotate(90);	
		b1 = new Button("Start Game");
		b1.setAlignment(Pos.CENTER);
		b1.setTextFill(Color.WHITE);
		b1.setMaxWidth(400);
		b1.setMaxHeight(100);
		b1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #0000FF, #FF0000)");
		b1.setOnMouseEntered(e -> b1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #FF0000, #0000FF)"));
		b1.setOnMouseExited(e -> b1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #0000FF, #FF0000)"));

		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);

		t1 = new Text();
		t1.setEffect(is);
		t1.setX(20);
		t1.setY(100);
		t1.setText("Welcome To Connect 4");
		Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.BLUE)};
		LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		t1.setFill(lg1);
		t1.setFont(Font.font(null, FontWeight.BOLD, 45));
		t1.setStroke(Color.WHITE);
		t1.setTranslateY(-275);
		
		VBox vb = new VBox(v,t1,b1);
		vb.setAlignment(Pos.CENTER);
		root = new HBox(vb);
	
		root.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #FF0000, #0000FF)");
		root.setAlignment(Pos.CENTER);
		HBox.setHgrow(root, Priority.ALWAYS);	

		b1.setOnAction(e -> primaryStage.setScene(sceneMap.get("game")));
		return new Scene(root, 700,700);
	}
	
	public Scene GameScreen(Stage primaryStage) {
		
		reverseButton = new Stack<GameButton>();
		BorderPane pane = new BorderPane();
		Pane gameboardPane = new Pane();
		pane.setPadding(new Insets(70));
		b2 = new Button("End Game");
		original = new Button("Original");
		theme1 = new Button("Theme1");
		theme2 = new Button("Theme2");
		
		int rows = 8;
	    int columns = 7;
		ArrayList<Grid> columnArray = new ArrayList<>();
		gameBoard = new int[rows][columns];
		  //GridPane gridpane = new GridPane();
		int currentRow = 1;
	    int currentCol = 1; 
		
		
		//newGrid.addGameButton(new GameButton(1,1));
		
		while(currentRow <= 6) {
			while (currentCol <= 7) {
				newGrid.addGameButton(new GameButton(currentRow,currentCol));
				currentCol++;
			}
			currentCol = 1;
			currentRow++;
		} 
		
		Menu menu = new Menu("Menu");
		//MenuItem menuItem1 = new MenuItem("Game Play");
		Menu subMenu = new Menu("Game Play");
		MenuItem menuItem1_1 = new MenuItem("reverse move");
		subMenu.getItems().add(menuItem1_1);
		
		menuItem1_1.setOnAction(e -> {
			 GameButton b = reverseButton.pop(); // Getting Button
			 gameBoard[b.getCol()][b.getRow()] = 1;
			 newGrid.removePiece();
			 b.getButton().setStyle("-fx-background-color: #F5F5DC; "); // Original Color
			 b.getButton().setDisable(false); // Enabling the button again
			 //b.getButton();
			 System.out.print(b.getCol());
			 System.out.println(b.getRow());
			 
		});
		Menu subMenu2 = new Menu("Themes");
		MenuItem menuItem2_1 = new MenuItem("original theme");
		subMenu2.getItems().add(menuItem2_1);
		MenuItem menuItem2_2 = new MenuItem("theme one");
		subMenu2.getItems().add(menuItem2_2);
		MenuItem menuItem2_3 = new MenuItem("theme two");
		subMenu2.getItems().add(menuItem2_3);
		
		menuItem2_1.setOnAction(e -> {
			
			for (GameButton b : newGrid.getPieceArray()) {
				
				if(b.getButton().getStyle() == p1) {
					b.getButton().setStyle(str1);
				}
				if(b.getButton().getStyle() == p2) {
					b.getButton().setStyle(str2);
				}
			}
			p1 = str1;
			p2 = str2;
			pane.setStyle("-fx-background-color: #838B8B;");
		});
		menuItem2_2.setOnAction(e -> {
			
			for (GameButton b : newGrid.getPieceArray()) {
				
				if(b.getButton().getStyle() == p1) {
					b.getButton().setStyle(str3);
				}
				if(b.getButton().getStyle() == p2) {
					b.getButton().setStyle(str4);
				}
			}
			p1 = str3;
			p2 = str4;
			pane.setStyle("-fx-background-color: #778899;");
		});
		menuItem2_3.setOnAction(e -> {
			
			for (GameButton b : newGrid.getPieceArray()) {
				
				if(b.getButton().getStyle() == p1) {
					b.getButton().setStyle(str5);
				}
				if(b.getButton().getStyle() == p2) {
					b.getButton().setStyle(str6);
				}
			}
			p1 = str5;
			p2 = str6;
			pane.setStyle("-fx-background-color: #AFEEEE;");
		});
		
		Menu subMenu3 = new Menu("Options");
		MenuItem menuItem3_1 = new MenuItem("how to play");
		subMenu3.getItems().add(menuItem3_1);
		MenuItem menuItem3_2 = new MenuItem("new game");
		subMenu3.getItems().add(menuItem3_2);
		MenuItem menuItem3_3 = new MenuItem("exit");
		subMenu3.getItems().add(menuItem3_3);
		
		menu.getItems().add(subMenu);
		menu.getItems().add(subMenu2);
		menu.getItems().add(subMenu3);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menu);
		//Popup menu
		menuItem3_1.setOnAction(e -> {
		    System.out.println("Menu Item 3_1 Selected");
		    Popup popup = new Popup();
		    TextArea t = new TextArea("Connect Four is played on a grid of 7 columns and 6 rows.\n It is a two"
	        		+ "player game where each player takes a turn dropping a checker into a slot (one of the"
	        		+ "columns) on the game board.\n That checker will fall down the column and either land on"
	        		+ "top of another checker or land on the bottom row.\n To win the game, a player needs to get 4 of their checkers in a vertical, horizontal or"
	        		+ "diagonal row before the other player."+"\n\nPress [ESC] to exit.");
		    t.setEditable(false);
		    t.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
		    t.setFont(new Font("Helvetica", 15)); 
	        popup.setX(300);
	        popup.setY(200);
	        popup.getContent().addAll(t);
	        popup.setHideOnEscape(true);
	        popup.show(primaryStage);
		});
		// Reseting the Board
		menuItem3_2.setOnAction(e -> {
			System.out.println("Menu Item 3_2 Selected");
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
			for (GameButton b : newGrid.getPieceArray()) {
				b.getButton().setDisable(false);
				b.getButton().setStyle("-fx-background-color: #F5F5DC; ");
			}
			// Reseting Pieces
			newGrid.resetPieces();
		});
		// Exiting the progam
		menuItem3_3.setOnAction(e -> {
			System.out.println("Menu Item 3_3 Selected");
			System.exit(0);
		});

		    GridPane grid = new GridPane();
	        grid.setVgap(8);
	        grid.setHgap(10);
	        int a = 0;
	        int b = 0;
	        //Adding all the pieces to the Grid
	        for (GameButton e : newGrid.getPieceArray()) {
	           // System.out.println(e.getID() + " "); 
	            grid.add(e.getButton(),e.getRow(),e.getCol());
	        } 

	        // sets the array accordingly to col and row
	        for(int i = 1; i < 8; i++) {
	        	for(int j = 1; j < 7; j++) {
	        		gameBoard[i][j] = gameBoard[i][j];
	        	}
	        }

	      	// set value of 1 to last row used for starting bottom to top
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
	  
	        grid.setAlignment(Pos.CENTER);	       
			cPlayer.setText("Player 1");
			// loop for checking win/tie and setting button color accordingly
			// Main algorithm
			for (GameButton e : newGrid.getPieceArray()) {// Looping through the entire grid to check 
				e.getButton().setOnAction(g->{ 
					if(gameBoard[e.getCol()][e.getRow()] == 1 ){
						
						bTwo = String.valueOf(e.getRow())+","+String.valueOf(e.getCol());
						cThree = ". This move is valid";
					}
					else {
						if(newGrid.getPieces() %2 == 1){
						aOne = "Player two moved to ";
						} else {
						aOne = "Player one moved to ";
						}
						bTwo = String.valueOf(e.getRow())+","+String.valueOf(e.getCol());
						cThree = ". This is NOT a valid move. Player one pick again.";
					}
				if(gameBoard[e.getCol()][e.getRow()] == 1 ){ // Making sure we start at the bottom
					newGrid.addPiece();
					bTwo = String.valueOf(e.getRow())+","+String.valueOf(e.getCol());
				if(newGrid.getPieces() %2 == 0 && gameBoard[e.getCol()][e.getRow()] == 1 ) { // Switching turns
					e.getButton().setStyle(p1);
					gameBoard[e.getCol()][e.getRow()] = 3;
					gameBoard[e.getCol()-1][e.getRow()] = 1;
					e.getButton().setDisable(true);
					reverseButton.add(e);
					aOne = "Player two moved to ";
					cThree = ". This move is valid";
					cPlayer.setText("Player 1");
					
				} else if(newGrid.getPieces() %2 == 1 && gameBoard[e.getCol()][e.getRow()] == 1 ) { // if odd other players turn
					e.getButton().setStyle(p2);
					gameBoard[e.getCol()][e.getRow()] = 2;
					gameBoard[e.getCol()-1][e.getRow()] = 1;
					e.getButton().setDisable(true);
					reverseButton.add(e);
					aOne = "Player one moved to ";
					cPlayer.setText("Player 2");
				} }
				if(newGrid.getPieces() == 42){ // When all pieces place and no win 
					EndText.setText("IT IS A TIE!!!!!!");
					primaryStage.setScene(sceneMap.get("end")); // setting the end screen
					//break;
				}
				// This forloop will check win
				for( c = 1; c < 8; c++) {
					for( r = 1; r < 7; r++) {
					// vertical
					if(c - 4 > 0) {
						if(gameBoard[c][r] == 3 && gameBoard[c-1][r] == 3 && gameBoard[c-2][r] == 3 &&  gameBoard[c-3][r] == 3){
							System.out.println("FINISHED1 P2");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c-1][r] == 2 && gameBoard[c-2][r] == 2 &&  gameBoard[c-3][r] == 2){
							System.out.println("FINISHED1 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					//horizontal
					if(r - 4 > 0) {
						if(gameBoard[c][r] == 3 && gameBoard[c][r-1] == 3 && gameBoard[c][r-2] == 3 && gameBoard[c][r-3] == 3){
							System.out.println("FINISHED2");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c][r-1] == 2 && gameBoard[c][r-2] == 2 && gameBoard[c][r-3] == 2){
							System.out.println("FINISHED2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					//vertical
					if(c + 4 <= 6) {
						if(gameBoard[c][r] == 3 && gameBoard[c + 1][r] == 3 && gameBoard[c+2][r] == 3 && gameBoard[c+3][r] == 3){
							System.out.println("FINISHED3");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c + 1][r] == 2 && gameBoard[c+2][r] == 2 && gameBoard[c+3][r] == 2){
							System.out.println("FINISHED3 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					// horizontal
					if(r + 4 <= 7) {
						if(gameBoard[c][r] == 3 && gameBoard[c][r + 1] == 3 && gameBoard[c][r+2] == 3 && gameBoard[c][r+3] == 3 ){
							System.out.println("FINISHED4");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c][r + 1] == 2 && gameBoard[c][r+2] == 2 && gameBoard[c][r+3] == 2 ){
							System.out.println("FINISHED4 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					//diagnol
					if(r + 4 <= 7 && c + 4 <= 6) {
						if(gameBoard[c][r] == 3 && gameBoard[c+1][r + 1] == 3 && gameBoard[c+2][r+2] == 3  && gameBoard[c+3][r+3] == 3 ){
							System.out.println("FINISHED5");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c+1][r + 1] == 2 && gameBoard[c+2][r+2] == 2 && gameBoard[c+3][r+3] == 2 ){
							System.out.println("FINISHED5 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					//diagnol
					if(r - 4 >= 0 && c - 4 >= 0) {
						if(gameBoard[c][r] == 3 && gameBoard[c-1][r-1] == 3 && gameBoard[c-2][r-2] == 3 && gameBoard[c-3][r-3] == 3){
							System.out.println("FINISHED6");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c-1][r-1] == 2 && gameBoard[c-2][r-2] == 2 && gameBoard[c-3][r-3] == 2){
							System.out.println("FINISHED6 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					//diagnol
					if(r + 4 <= 7 && c - 4  >= 0) {
						if(gameBoard[c][r] == 3 && gameBoard[c - 1][r+1] == 3 && gameBoard[c-2][r+2] == 3 && gameBoard[c-3][r+3] == 3){
							System.out.println("FINISHED7");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c - 1][r+1] == 2 && gameBoard[c-2][r+2] == 2 && gameBoard[c-3][r+3] == 2){
							System.out.println("FINISHED7 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
					}
					//diagnol
					if(r - 4 >= 0 && c + 4 <= 6) {
						if(gameBoard[c][r] == 3 && gameBoard[c+1][r-1] == 3 && gameBoard[c+2][r-2] == 3 && gameBoard[c+3][r-3] == 3){
							System.out.println("FINISHED8");
							EndText.setText("Player 2 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}
						if(gameBoard[c][r] == 2 && gameBoard[c+1][r-1] == 2 && gameBoard[c+2][r-2] == 2 && gameBoard[c+3][r-3] == 2){
							System.out.println("FINISHED8 P2");
							EndText.setText("Player 1 Won");
							primaryStage.setScene(sceneMap.get("end"));
							break;
						}	
					}
				}
			}	
			playerTurn.setText(aOne+bTwo+cThree);
			;});
		}
		playerTurn.setFont(Font.font(null, FontWeight.BOLD, 15));
		cPlayer.setFont(Font.font(null, FontWeight.BOLD, 10));
	    HBox Hpane = new HBox(10, menuBar, b2, cPlayer);
	    HBox BText = new HBox(10, playerTurn);
	    Hpane.setAlignment(Pos.CENTER);
	    BText.setAlignment(Pos.CENTER); 
		VBox paneCenter = new VBox(10, Hpane,grid,original, theme1, theme2, BText);
		paneCenter.setAlignment(Pos.CENTER);
		pane.setCenter(paneCenter);
		pane.setBottom(BText);
		pane.setTop(Hpane);
		pane.setCenter(grid);
		pane.setStyle("-fx-background-color: #838B8B");
		b2.setOnAction(e -> primaryStage.setScene(sceneMap.get("end")));
		return new Scene(pane, 700,700);
	}
	

	public Scene EndScreen(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		Button exit = new Button("Exit");
		b3 = new Button("Play another Game");
		EndText.setFont(Font.font(null, FontWeight.BOLD, 45));
		EndText.setStroke(Color.WHITE);
		b3.setStyle("-fx-background-color: WHITE");
		exit.setStyle("-fx-background-color: WHITE");
		HBox endScreenButtons = new HBox(10, b3,exit);
		endScreenButtons.setAlignment(Pos.CENTER);
		VBox paneCenter = new VBox(10,EndText,endScreenButtons);
		paneCenter.setAlignment(Pos.CENTER);
		pane.setCenter(paneCenter);
		pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #008080, #39FF14)");
		// resets board
		b3.setOnAction(e -> {
			System.out.println("Menu Item 3_2 Selected");
			for(int i = 1; i < 8; i++) {
	        	for(int j = 1; j < 7; j++) {
	        		if(i == 7) {
	        			gameBoard[i][j] = 1;
	        		}
	        		else {
	        			gameBoard[i][j] = 0;
	        		}
	        	}
				cPlayer.setText("Player 1");
	        }
			for (GameButton b : newGrid.getPieceArray()) {
				b.getButton().setDisable(false);
				b.getButton().setStyle("-fx-background-color: #F5F5DC; ");
			}
			// Reseting Pieces
			newGrid.resetPieces();
			primaryStage.setScene(sceneMap.get("game"));
		});
		exit.setOnAction(e -> {
			System.exit(0);
		});
		
		return new Scene(pane, 700,700);
	}

}
