package chess;

public class Bishop extends Piece {

	public Bishop(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			this.setSymbol("♗");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			this.setSymbol("♝");
		}
	}

	public PieceColour getColour() {
		return colour;
	}

	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// Not a diagonal move
		if (j0 == j1 || i0 == i1)
			return false;

		// Both have to move by an equal amount for diagonal move
		int rowDiff = Math.abs(i0 - i1);
		int colDiff = Math.abs(j0 - j1);
		if (rowDiff != colDiff) {
			System.out.println("Not a diagonal move!");
			return false;
		}

		// If i0 < i1, moves down the board
		// If j0 < j1, moves right on the board
		int rStep = (i1 > i0) ? 1 : -1;
		int cStep = (j1 > j0) ? 1 : -1;

		// Start on the first piece past current
		int row = i0 + rStep;
		int col = j0 + cStep;

		while (row != i1) {
			// Check for any blocking pieces
			if (Board.hasPiece(row, col)) {
				System.out.println("There is a piece in the way!");
				return false;
			}
			// Increment both values
			row += rStep;
			col += cStep;
		}
		// At this point, should be no pieces in the way

		// Check destination is empty or different colour
		if (!Board.hasPiece(i1, j1)) {
			return true;
		} else {
			if (Board.getPiece(i1, j1).getColour().equals(this.colour)) {
				System.out.println("Can't land on own piece!");
				return false;
			}
			return true;
		}
	}
}
