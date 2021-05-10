import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GameButton extends Button{
	
	Button b;
    int col;
    int row;
	
    //Rows and columns start at 1, not 0
    public GameButton(int row, int col){
        this.row = row;
        this.col = col;
        b = new Button();
		b.setMinSize(68,68);
		b.setMaxSize(100,100);
		b.setPrefSize(20,20);
		b.setStyle("-fx-background-color: #F5F5DC; ");
    }
	
	// access columnn
    public int getCol() {
        return col;
    }
	// access row
    public int getRow() {
        return row;
    }
    // access button
    Button getButton() {
    	return b;
    }
}
