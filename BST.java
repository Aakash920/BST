package binarySearchTree;

public class BST {

	public class Node {
		int data;
		Node left;
		Node right;
	}

	private Node root;

	public BST(int[] arr) {
		this.root = construct(arr, 0, arr.length - 1);
	}

	private Node construct(int[] arr, int lo, int hi) {
		// base case
		if (lo > hi) {
			return null;
		}

		int mid = (lo + hi) / 2;

		// create a new Node
		Node nn = new Node();
		nn.data = arr[mid];

		nn.left = construct(arr, lo, mid - 1);
		nn.right = construct(arr, mid + 1, hi);

		return nn;

	}

	public void display() {
		display(root);
	}

	private void display(Node node) {
		if (node == null) {
			return;
		}

		// self Work
		String str = "";

		if (node.left == null) {
			str += ".";
		} else {
			str += node.left.data;
		}
		str += "->" + node.data + "<-";

		if (node.right == null) {
			str += ".";
		} else {
			str += node.right.data;
		}
		System.out.println(str);

		// left
		display(node.left);
		// right
		display(node.right);
	}

	public boolean find(int item) {
		return find(root, item);
	}

	private boolean find(Node node, int item) {
		if (node == null) {
			return false;
		}

		if (item < node.data) {
			return find(node.left, item);
		} else if (item > node.data) {
			return find(node.right, item);
		} else {
			return true;
		}
	}

	public int max() {
		return max(root);
	}

	private int max(Node node) {
		if (node.right == null) {
			return node.data;
		}
		// if node.right != null then we return result of recursive code.
		return max(node.right);
	}

	public void add(int item) {
		add(root, item);
	}

	private void add(Node node, int item) {
		if (item > node.data) {
			if (node.right == null) {
				// creation of new node
				Node nn = new Node();
				// put item inside node
				nn.data = item;
				// connect this new node to right of previous node
				node.right = nn;
			} else {
				add(node.right, item);
			}
		} else {
			if (node.left == null) {
				// creation of new node
				Node nn = new Node();
				// put item inside node
				nn.data = item;
				// connect this new node to left of previous node
				node.left = nn;
			} else {
				add(node.left, item);
			}
		}
	}

	public void remove(int item) {
		remove(root, null, false, item);
	}

	private void remove(Node node, Node parent, boolean ilc, int item) {
		if (item > node.data) {
			remove(node.right, node, false, item);
		} else if (item < node.data) {
			remove(node.left, node, true, item);
		} else {
			if (node.right == null && node.left == null) {
				if (ilc) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			} else if (node.left == null && node.right != null) {
				if (ilc) {
					parent.left = node.right;
				} else {
					parent.right = node.right;
				}
			} else if (node.left != null && node.right == null) {
				if (ilc) {
					parent.left = node.left;
				} else {
					parent.right = node.left;
				}
			} else {
				int max = max(node.left);
				node.data = max;

				remove(node.left, node, true, max);
			}
		}
	}

	public void printDecreasing() {
		printDecreasing(root);
	}

	private void printDecreasing(Node node) {
		if (node == null) {
			return;
		}
		// R
		printDecreasing(node.right);
		// N
		System.out.println(node.data);
		// L
		printDecreasing(node.left);

	}

	int sum = 0;

	public void replaceWithSumLarger() {
		replaceWithSumLarger(root);
	}

	private void replaceWithSumLarger(Node node) {
		if (node == null) {
			return;
		}
		// R
		replaceWithSumLarger(node.right);

		// node update
		int temp = node.data;
		node.data = sum;
		sum += temp;

		// L
		replaceWithSumLarger(node.left);

	}

	public void printInRange(int lo, int hi) {
		printInRange(root, lo, hi);
	}

	private void printInRange(Node node, int lo, int hi) {
		if (node == null) {
			return;
		}
		if (node.data < lo) {
			printInRange(node.right, lo, hi);
		} else if (node.data > hi) {
			printInRange(node.left, lo, hi);
		} else if (node.data >= lo && node.data <= hi) {
			System.out.println(node.data);
			printInRange(node.left, lo, hi);
			// if we print there then answer will be in ascending order
			// or when we write syso statement above printInRange(node.left, lo, hi); then
			// then answer will not be in ascending order . it is in random order.
//			System.out.println(node.data);
			printInRange(node.right, lo, hi);
		}
	}

}
