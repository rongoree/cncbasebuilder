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

	public String encode(List<Integer> source) {
		List<Byte> encodedSource = new ArrayList<Byte>();

		for (int i = 0; i < source.size(); i++) {
			List<Byte> encodedSymbol = this.root.traverse(source.get(i),
					new ArrayList<Byte>());
			encodedSource.addAll(encodedSymbol);
		}

		return bits2String(encodedSource);
	}

	public List<Integer> decode(String string) {
		return decode(string2Bits(string));
	}

	private List<Integer> decode(List<Byte> encoded) {
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

	public List<Byte> encode_levels(List<Integer> levels) {
		List<Byte> out = new ArrayList<Byte>();

		int prefix = 32;

		int n = 8;
		while (n-- > 0){
			out.add((byte) (((1 << n) & prefix) != 0 ? 1 : 0));
		}

		// create a new array prepending the length of the levels
		List<Integer> len_levels = new ArrayList<Integer>();
		len_levels.add(levels.size());
		for (int l : levels) {
			len_levels.add(l);
		}
		for (int l : len_levels) {
			int q = (l / prefix);
			while (q-- > 0) {
				out.add((byte) 1);
			}
			out.add((byte) 0);

			int i = get_golomb_len(prefix);
			while (i-- > 0) {
				out.add((byte) (((1 << i) & l) != 0 ? 1 : 0));
			}
		}
		return /*bits2String*/(out);
	}

	public List<Integer> decode_levels(String encoded) {
		return decode_levels(string2Bits(encoded));
	}

	public List<Integer> decode_levels(List<Byte> bits) {
		List<Integer> out = new ArrayList<Integer>();

		int prefix = 0, n = 8;
		while (n-- > 0) {
			prefix += bits.remove(0) != 0 ? (1 << n) : 0;
		}

		int level_len = -1;

		while (bits.size() > 0 && (level_len == -1 || out.size() < level_len)) {
			int q = 0;
			while (bits.remove(0) == 1) {
				q++;
			}
			int level = 0;

			int i = get_golomb_len(prefix);
			while (i-- > 0) {
				level += bits.remove(0) != 0 ? 1 << i : 0;
			}

			if (level_len == -1) {
				level_len = level + q * prefix;
			} else {
				out.add(level + q * prefix);
			}
		}

		return out;
	}

	private int get_golomb_len(int prefix) {
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
}
