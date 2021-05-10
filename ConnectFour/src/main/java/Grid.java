import java.util.ArrayList;

public class Grid {
    private ArrayList<GameButton> pieceArray = new ArrayList<> ();

    private int columnNumber;
    private int placedPieces = 0;

	// initiates
    public Grid(int columnNumber) {
        this.columnNumber = columnNumber;
    }

	// creates a gamebutton
    public void addGameButton(GameButton piece) {
        pieceArray.add(piece);
    }

	// Add game piece
    public void addPiece() {
        placedPieces++;
    }
    // Remove game piece
    public void removePiece() {
        placedPieces--;
    }

    public ArrayList<GameButton> getPieceArray() {
        return pieceArray;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
	// access pieces
    public int getPieces() {
        return placedPieces;
    }
	// resets pieces
    public void resetPieces() {
        placedPieces = 0;
    }

}