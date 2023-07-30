package chess;

public class CheckInput {

	public static boolean checkCoordinateValidity(String input) {

		// Check Base Case
		if (input.isEmpty() || input.isBlank()) {
			System.out.println("You forgot to type in something!");
			return false;
		}

		if (input.length() != 2) {
			System.out.println("Your input has to be of length 2!");
			return false;
		}

		// Check first letter is a number 0 to 8
		char[] coords = input.toCharArray();

		// Check if digit and letter is entered:
		if (!Character.isDigit(coords[0]) || !Character.isLetter(coords[1])) {
			System.out.println("Enter a digit following by letter, for example 1a");
			return false;
		}

		int digit = Character.getNumericValue(coords[0]);
		char letter = coords[1];

		if (digit < 1 || digit > 8) {
			System.out.println("You need to enter correct file (1 --> 8)!");
			return false;
		}

		int ascii = letter;
		if (ascii < 97 || ascii > 104) {
			System.out.println("Need to enter correct rank (a --> h)!");
			return false;
		}

		// Digit + Letter + Correct Size!
		return true;
	}
}
