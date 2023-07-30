package chess;

public class Rook extends Piece {

	public Rook(PieceColour pc) {
		if (pc.equals(PieceColour.WHITE)) {
			this.colour = PieceColour.WHITE;
			this.setSymbol("♖");

		} else if (pc.equals(PieceColour.BLACK)) {
			this.colour = PieceColour.BLACK;
			this.setSymbol("♜");
		}
	}

	public PieceColour getColour() {
		return colour;
	}

	public boolean isLegitMove(int i0, int j0, int i1, int j1) {
		// Can either move horizontally or vertically:
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
		// If here, not a vertical or horizontal move
		return false;
	}

}
