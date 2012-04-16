package de.ganicoga.client;

import java.util.ArrayList;
import java.util.List;

import de.ganicoga.client.HuffmanTree.MultiReturnList;


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
	
	public static List<Integer> oldString2IntList(String string){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < string.length(); i++) {
			list.add(convert(string.charAt(i)));
		}
		return list;
		
	}

	public static String encode(List<Integer> decodedLevels, List<Integer> decodedStructures) {
		return tree.encode(decodedLevels, decodedStructures);
	}
	
	public static MultiReturnList decode(String string) {
		return tree.decode(string);
	}
}
