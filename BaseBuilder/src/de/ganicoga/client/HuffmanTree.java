package de.ganicoga.client;

import java.util.ArrayList;
import java.util.List;

public class HuffmanTree {

	// String shorten
	private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-";

	private Node root;
	private List<int[]> frequencies = new ArrayList<int[]>();

	public HuffmanTree() {
		createTree(create_nodelist());
	}

	private List<Node> create_nodelist() {

		frequencies.add(new int[] { 0, 35 });
		frequencies.add(new int[] { 7, 7 });
		frequencies.add(new int[] { 4, 6 });
		frequencies.add(new int[] { 9, 6 });
		frequencies.add(new int[] { 5, 5 });
		frequencies.add(new int[] { 10, 3 });
		frequencies.add(new int[] { 8, 2 });
		frequencies.add(new int[] { 1, 1 });
		frequencies.add(new int[] { 2, 1 });
		frequencies.add(new int[] { 6, 1 });
		frequencies.add(new int[] { 11, 1 });
		frequencies.add(new int[] { 15, 1 });
		frequencies.add(new int[] { 12, 1 });
		frequencies.add(new int[] { 13, 1 });
		frequencies.add(new int[] { 14, 1 });
		frequencies.add(new int[] { 16, 1 });
		frequencies.add(new int[] { 17, 1 });
		frequencies.add(new int[] { 18, 1 });
		frequencies.add(new int[] { 19, 1 });

		ArrayList<Node> nodes = new ArrayList<Node>();

		for (int[] i : frequencies) {
			Node node = new Node();
			node.symbol = i[0];
			node.frequency = i[1];
			nodes.add(node);
		}

		return nodes;
	}

	private void createTree(List<Node> nodes) {

		int i = nodes.size();

		while (--i > 0) {
			Node node = new Node();

			node.left = find_best(nodes);
			node.right = find_best(nodes);
			node.frequency = node.left.frequency + node.right.frequency;

			nodes.add(node);
		}

		this.root = nodes.get(nodes.size() - 1);

	}

	private Node find_best(List<Node> nodes) {

		Node best = null;
		int freqency = -1;

		for (Node node : nodes) {
			if (node.processed)
				continue;

			if (freqency == -1 || node.frequency <= freqency) {
				freqency = node.frequency;
				best = node;
			}
		}

		best.processed = true;
		return best;

	}

	private boolean isLeaf(Node node) {
		return (node.left == null && node.right == null);
	}

	public String encode(List<Integer> levels, List<Integer> structures) {

		List<Byte> encodedStructures = encodeStructures(structures);
		List<Byte> encodedLevels = encodeLevels(levels);

		List<Byte> fullEncoded = new ArrayList<Byte>();
		fullEncoded.addAll(encodedLevels);
		fullEncoded.addAll(encodedStructures);

		return bits2String(fullEncoded);
	}

	@SuppressWarnings("unchecked")
	public MultiReturnList decode(String string) {

		List<Byte> byteArray = string2Bits(string);

		MultiReturnList m = decodeLevels(byteArray);

		return new MultiReturnList(m.getFirstList(),
				decodeStructures((List<Byte>) m.getSecondList()));

	}

	public List<Byte> encodeStructures(List<Integer> structures) {
		List<Byte> encodedSource = new ArrayList<Byte>();
		for (int i = 0; i < structures.size(); i++) {
			List<Byte> encodedSymbol = this.root.traverse(structures.get(i),
					new ArrayList<Byte>());
			encodedSource.addAll(encodedSymbol);
		}
		return encodedSource;
	}

	public List<Integer> decodeStructures(String string) {
		return decodeStructures(string2Bits(string));
	}

	public List<Integer> decodeStructures(List<Byte> encoded) {
		Node current = this.root;
		List<Integer> decoded = new ArrayList<Integer>();

		for (byte bit : encoded) {
			if (bit == 1) {
				if (current.right != null) {
					current = current.right;
				}
			} else {
				if (current.left != null) {
					current = current.left;
				}
			}

			if (isLeaf(current)) {
				decoded.add(current.symbol);
				current = this.root;
			}
		}

		return decoded;
	}

	public List<Byte> encodeLevels(List<Integer> levels) {

		//int bestPrefix = 0;
		List<Byte> bestList = null;

		int prefix = 1;
		while ((prefix <<= 1) <= 128) {
			List<Byte> current = encodeLevelsPrefix(levels, prefix);
			if (bestList == null) {
				bestList = current;
				//bestPrefix = prefix;
			} else if (current.size() < bestList.size()) {
				bestList = current;
				//bestPrefix = prefix;
			}
		}
/*
		System.out.println("Best encoding: " + bestList.toString());
		System.out.println("with length: " + bestList.size());
		System.out.println("Best Prefix: " + bestPrefix);*/

		return bestList;
	}

	private List<Byte> encodeLevelsPrefix(List<Integer> levels, int prefix) {
		
		List<Byte> out = int2Bits(prefix, 8);
		
		// new list consisting of the list length followed by the list itself
		List<Integer> len_levels = new ArrayList<Integer>();
		len_levels.add(levels.size());
		for (int l : levels) {
			len_levels.add(l);
		}

		for (int i = 0; i < len_levels.size(); i++) {

			int current = len_levels.get(i);

			// determine delta
			if (i > 1)
				current -= len_levels.get(i - 1);

			// add signedness
			if (i > 0) {
				out.add((byte) (current >= 0 ? 0 : 1));
				// abs(current)
				current = Math.abs(current);
			}

			int q = current / prefix;
			while (q-- > 0) {
				out.add((byte) 1);
			}
			out.add((byte) 0);

			// add number current in binary form using golomb length
			int bitLength = getGolombLength(prefix);
			out.addAll(int2Bits(current, bitLength));
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> decodeLevels(String encoded) {
		return (List<Integer>) decodeLevels(string2Bits(encoded))
				.getFirstList();
	}

	public MultiReturnList decodeLevels(List<Byte> bits) {
		List<Integer> out = new ArrayList<Integer>();

		int prefix = bits2Int(bits, 8);

		//System.out.println("Decoded Prefix: " + prefix);

		int level_len = -1;
		int previous_level = 0;

		while (bits.size() > 0 && (level_len == -1 || out.size() < level_len)) {

			// determine signedness
			int sign = 1;

			if (level_len != -1) {
				sign = bits.remove(0) == 1 ? -1 : 1;
			}

			int q = 0;
			while (bits.remove(0) == 1) {
				q++;
			}
			
			int bitLength = getGolombLength(prefix);
			
			int level = bits2Int(bits, bitLength);

			// get length of levels
			if (level_len == -1) {
				level_len = level + q * prefix;
			} else {
				previous_level += (level + q * prefix) * sign;
				out.add(previous_level);
			}
		}

		return new MultiReturnList(out, bits);
	}

	private int getGolombLength(int prefix) {
		return (int) (Math.log(prefix) / Math.log(2));
	}

	private static String bits2String(List<Byte> bits) {
		String out = "";
		byte cur = 0;

		for (int i = 0; i < bits.size(); i++) {
			cur |= bits.get(i) << (i % 6);

			if (i % 6 == 5 && i > 0) {
				out += ALPHABET.charAt(cur);
				cur = 0;
			}
		}

		// padding
		out += ALPHABET.charAt(cur);
		out += ALPHABET.charAt(bits.size() % 6);

		return out;

	}

	private static List<Byte> string2Bits(String str) {
		List<Byte> out = new ArrayList<Byte>();

		int c = 0;
		for (; c < str.length() - 2; c++) {
			int val = ALPHABET.indexOf(str.charAt(c));
			for (int i = 0; i < 6; i++) {
				out.add((byte) (val >> i & 1));
			}
		}

		// padding
		int padsize = ALPHABET.indexOf(str.charAt(c + 1));
		for (int i = 0; i < padsize; i++) {
			out.add((byte) (ALPHABET.indexOf(str.charAt(c)) >> i & 1));
		}

		return out;
	}

	private int bits2Int(List<Byte> bits, int bitLength) {
		int sum = 0;
		for (int i = bitLength - 1; i >= 0; i--) {
			sum += (bits.remove(0) != 0 ? (1 << i) : 0);
		}
		return sum;
	}

	private List<Byte> int2Bits(int number, int bitLength) {
		List<Byte> bits = new ArrayList<Byte>();
		for (int i = bitLength - 1; i >= 0; i--) {
			bits.add((byte) (((1 << i) & number) != 0 ? 1 : 0));
		}
		return bits;
	}

	private class Node {

		public int symbol;
		public int frequency = 0;
		public Node left = null;
		public Node right = null;
		public boolean processed = false;;

		public List<Byte> traverse(int symbol, List<Byte> data) {
			// Leaf
			if (this.right == null && this.left == null) {
				if (symbol == this.symbol) {
					return data;
				} else {
					return null;
				}

				// node
			} else {
				List<Byte> left = null;
				List<Byte> right = null;

				if (this.left != null) {
					List<Byte> leftPath = new ArrayList<Byte>();
					leftPath.addAll(data);
					leftPath.add((byte) 0);

					left = this.left.traverse(symbol, leftPath);
				}

				if (this.left != null) {
					List<Byte> rightPath = new ArrayList<Byte>();
					rightPath.addAll(data);
					rightPath.add((byte) 1);
					right = this.right.traverse(symbol, rightPath);
				}

				if (left != null) {
					return left;
				} else {
					return right;
				}
			}
		}
	}

	public class MultiReturnList {
		private List<? extends Number> firstList;
		private List<? extends Number> secondList;

		public MultiReturnList(List<? extends Number> firstList,
				List<? extends Number> secondList) {
			this.firstList = firstList;
			this.secondList = secondList;
		}

		public List<? extends Number> getFirstList() {
			return firstList;
		}

		public List<? extends Number> getSecondList() {
			return secondList;
		}
	}
}
