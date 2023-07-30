package chess;

public class Knight extends Piece {

	public Knight(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			this.setSymbol("♘");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			this.setSymbol("♞");
		}
	}

	public PieceColour getColour() {
		return colour;
	}

	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// Knight moves in L shapes
		// One side must move by 2, one side must move by one.

		int rowDiff = Math.abs(i0 - i1);
		int colDiff = Math.abs(j0 - j1);

		// Must be different (one by 1, one by 2)
		if (rowDiff == colDiff) {
			System.out.println("Not an L shape movement! EqualDiff");
			return false;
		} else if ((rowDiff != 1 && rowDiff != 2) || (colDiff != 1 && colDiff != 2)) {
			System.out.println("Not an L shape movement! Not 1/2 diff");
			return false;
		}

		// Check destination square!

		if (Board.hasPiece(i1, j1)) {
			if (Board.getPiece(i1, j1).getColour().equals(this.colour)) {
				System.out.println("Can't land on own colour!");
				return false;
			}
		}

		// Correct L shape movement :)
		// Landing on empty/different colour piece
		return true;
	}
}
