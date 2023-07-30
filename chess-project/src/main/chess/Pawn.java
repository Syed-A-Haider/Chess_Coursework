package chess;

public class Pawn extends Piece {
	private boolean firstMove = true;
	private int multiplier;

	public Pawn(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			this.setSymbol("♙");
			// White piece moves up board (down coords)
			this.multiplier = -1;

		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			this.setSymbol("♟");
			// Black piece moves down board (up coords)
			this.multiplier = 1;
		}
	}

	public PieceColour getColour() {
		return colour;
	}

	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {

		// Only an edge case for the tests as this can't happen irl
		if (firstMove) {
			if (this.colour == PieceColour.WHITE) {
				if (i0 != 6) {
					firstMove = false;
				}
			} else {
				if (i0 != 1) {
					firstMove = false;
				}
			}
		}

		// Check if moving forward 1 square (only if empty)
		// if 5 = (6 + -1*1)
		if ((i1 == (i0 + (1 * this.multiplier))) && (j0 == j1)) {
			if (Board.hasPiece(i1, j1)) {
				// Can't move to non empty place
				System.out.println("Can't move the pawn to a straight non-empty square!");
				return false;
			}
			if (firstMove)
				firstMove = false;
			return true;
		}

		// Attempt to move 2 squares forward
		if ((i1 == (i0 + (2 * this.multiplier))) && (j0 == j1)) {

			// Has to be first move
			if (!firstMove) {
				System.out.println("Can only move pawn 2 squares if first move!");
				return false;
			}
			// Can't jump over a piece
			if (Board.hasPiece(i0 + (1 * this.multiplier), j0)) {
				System.out.println("Pawn can't jump over a piece!");
				return false;
			}

			// Has to jump to an empty square
			if (Board.hasPiece(i1, j0)) {
				System.out.println("Pawn can't jump to non-empty square!");
				return false;
			}

			// Valid move
			if (firstMove)
				firstMove = false;
			return true;
		}

		// Check if diagonal called
		if ((i1 == (i0 + (1 * this.multiplier))) && ((j1 == j0 + 1) || (j1 == j0 - 1))) {
			// Only valid if a capture!
			if (!Board.hasPiece(i1, j1))
				return false;

			// Has a piece
			Piece capturedPiece = Board.getPiece(i1, j1);

			// Has to be different colour
			if (capturedPiece.getColour() == this.colour)
				return false;

			// Valid capture, piece of different colour
			if (firstMove)
				firstMove = false;
			return true;
		}

		// No valid move been described
		return false;
	}
}
