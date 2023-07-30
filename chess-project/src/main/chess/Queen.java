package chess;

public class Queen extends Piece {

	public Queen(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			this.setSymbol("♕");
		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			this.setSymbol("♛");
		}
	}

	public PieceColour getColour() {
		return colour;
	}

	@Override
	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// Queen = Rook + Bishop Combined

		// Check if a Rook move:
		if (i0 == i1 || j0 == j1) {

			// Check horizontal movement:
			if (i0 == i1) {

				System.out.println("Checking row i= " + i0);
				if (j0 > j1) {
					// Checks if there is a piece in the way
					for (int j = j0 - 1; j > j1; j--) {
						System.out.println("	Checking piece: j= " + j);
						if (Board.hasPiece(i0, j)) {
							System.out.println("	Found blocking piece!");
							return false;
						}
						System.out.println("Clear Square!\n");
					}
				} else {

					// Checks if there is a piece in the way
					for (int j = j0 + 1; j < j1; j++) {
						System.out.println("	Checking piece: j= " + j);
						if (Board.hasPiece(i0, j)) {
							System.out.println("	Found blocking piece!");
							return false;
						}
						System.out.println("Clear Square!\n");
					}
				}

			}

			// Check vertical movements
			if (j0 == j1) {

				System.out.println("Checking column j= " + j0);
				if (i0 > i1) {
					for (int i = i0 - 1; i > i1; i--) {
						System.out.println("	Checking piece: i= " + i);

						if (Board.hasPiece(i, j0)) {
							System.out.println("	Found blocking piece!");
							return false;
						}
						System.out.println("Clear Square!\n");
					}
				} else {
					// Check for piece in the way
					for (int i = i0 + 1; i < i1; i++) {
						System.out.println("	Checking piece: i= " + i);

						if (Board.hasPiece(i, j0)) {
							System.out.println("	Found blocking piece!");
							return false;
						}
						System.out.println("Clear Square!\n");

					}
				}
			}

			// Check destination square
			if (Board.hasPiece(i1, j1)) {
				// Non-empty square = destination.
				if (Board.getPiece(i1, j1).getColour().equals(this.colour)) {
					System.out.println("Can't land on your own colour!");
					return false;
				}
				return true;
			} else {
				// Empty destination + nothing in the way = legal
				return true;
			}
		}

		// Check Bishop move:
		// Both have to move by an equal amount for diagonal move
		int rowDiff = Math.abs(i0 - i1);
		int colDiff = Math.abs(j0 - j1);
		if (rowDiff == colDiff) {
			// Is a diagonal move --> apply bishop rules
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

		// If here, not a bishop or rook move --> illegal
		System.out.println("Not a legal queen move!");
		return false;
	}
}
