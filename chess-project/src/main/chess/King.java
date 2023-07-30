package chess;

public class King extends Piece {

	public King(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			this.setSymbol("♔");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			this.setSymbol("♚");
		}
	}

	public PieceColour getColour() {
		return colour;
	}

	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// Can only move 1 block away (forwards or diagonals)

		int rowDiff = Math.abs(i0 - i1);
		int colDiff = Math.abs(j0 - j1);

		// Horizontal or vertical move
		if (i0 == i1 || j0 == j1) {

			// If moved by both axis or more then 1 in another axis, failed move
			if (rowDiff == colDiff || (rowDiff != 1 && rowDiff != 0) || (colDiff != 1 && colDiff != 0)) {
				System.out.println("Incorrect King move!");
				return false;
			}

			// Destination has a piece!
			if (Board.hasPiece(i1, j1)) {
				if (Board.getPiece(i1, j1).getColour().equals(this.colour)) {
					System.out.println("Can't move to own piece!");
					return false;
				}
			}
			return true;
		}
		// Has to be diagonal move if not straight
		else {
			if (rowDiff > 1 || colDiff > 1 || rowDiff != colDiff) {
				// Not a legal diagnonal move
				System.out.println("Not a legal diagonal move!");
				return false;
			}

			// Check destination square
			if (Board.hasPiece(i1, j1)) {
				if (Board.getPiece(i1, j1).getColour().equals(this.colour)) {
					System.out.println("Can't land on your own piece!");
					return false;
				}
			}

			return true;
		}

	}
}
