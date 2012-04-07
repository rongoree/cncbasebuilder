package de.ganicoga.client;


public class Util {
	
	public static final HuffmanTree tree = new HuffmanTree();

	public static char convert(int s) {

		if (s < 10)
			return Character.forDigit(s, 10);

		s -= 10;
		char[] chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c' };

		return chars[s];

	}

	public static int convert(char s) {

		if (Character.digit(s, 10) < 10 && Character.digit(s, 10) >= 0)
			return Character.digit(s, 10);

		int st;

		switch (s) {
		case 'A':
			st = 10;
			break;
		case 'B':
			st = 11;
			break;
		case 'C':
			st = 12;
			break;
		case 'D':
			st = 13;
			break;
		case 'E':
			st = 14;
			break;
		case 'F':
			st = 15;
			break;
		case 'G':
			st = 16;
			break;
		case 'H':
			st = 17;
			break;
		case 'I':
			st = 18;
			break;
		case 'J':
			st = 19;
			break;
		case 'K':
			st = 20;
			break;
		case 'L':
			st = 21;
			break;
		case 'M':
			st = 22;
			break;
		case 'N':
			st = 23;
			break;
		case 'O':
			st = 24;
			break;
		case 'P':
			st = 25;
			break;
		case 'Q':
			st = 26;
			break;
		case 'R':
			st = 27;
			break;
		case 'S':
			st = 28;
			break;
		case 'T':
			st = 29;
			break;
		case 'U':
			st = 30;
			break;
		case 'V':
			st = 31;
			break;
		case 'W':
			st = 32;
			break;
		case 'X':
			st = 33;
			break;
		case 'Y':
			st = 34;
			break;
		case 'Z':
			st = 35;
			break;
		case 'a':
			st = 36;
			break;
		case 'b':
			st = 37;
			break;
		case 'c':
			st = 38;
			break;
		default:
			st = 0;
			break;
		}

		return st;

	}

	private static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static String intToBase(int base, int decimalNumber) {
		String tempVal = decimalNumber == 0 ? "0" : "";
		int mod = 0;

		while (decimalNumber != 0) {
			mod = decimalNumber % base;
			tempVal = baseDigits.substring(mod, mod + 1) + tempVal;
			decimalNumber = decimalNumber / base;
		}

		return tempVal;
	}

	public static int baseToInt(int base, String number) {
		int iterator = number.length();
		int returnValue = 0;
		int multiplier = 1;

		while (iterator > 0) {
			returnValue = returnValue
					+ (baseDigits.indexOf(number.substring(iterator - 1,
							iterator)) * multiplier);
			multiplier = multiplier * base;
			--iterator;
		}
		return returnValue;
	}

	public static String encode(String string) {
		return tree.encode(string);
	}
	
	public static String decode(String string) {
		return tree.decode(string);
	}
}
