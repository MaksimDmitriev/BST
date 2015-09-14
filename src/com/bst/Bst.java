package com.bst;

public class Bst {

	private Node root;

	public Bst(int... keys) {
		if (keys == null) {
			return;
		}
		for (int key : keys) {
			insert(key);
		}
	}

	public void insert(int key) {
		Node parent = null;
		Node current = root;
		while (current != null) {
			parent = current;
			if (key == current.key) {
				return;
			}
			if (key < current.key) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if (parent == null) {
			root = new Node(key);
		} else if (key < parent.key) {
			parent.left = new Node(key);
			parent.left.parent = parent;
		} else if (key > parent.key) {
			parent.right = new Node(key);
			parent.right.parent = parent;
		}
	}

	public void remove(int key) {
		Node parent = null;
		Node current = root;
		while (current != null) {
			current = parent;
			if (key < current.key) {
				current = current.left;
			} else if (key > current.key) {
				current = current.right;
			} else {
				// delete
				Node y = current.right;
				Node z = current.left;
				Node w = current.parent;
				if (current == w.left) {
					if (y != null && z != null) {
						w.left = z;
						Node zRightMost = max(z);
						zRightMost.right = y;
						y.parent = zRightMost;
					} else if (y == null) { // TODO: y == null && z == null falls here
						w.left = z;
						z.parent = w;
					} else {
						w.left = y;
						y.parent = w;
					}
				} else {
					if (y != null && z != null) {
						w.right = z;
						Node zRightMost = max(z);
						zRightMost.right = y;
						y.parent = zRightMost;
					} else if (y != null) {
						w.right = y;
						y.parent = w;
					} else {
						w.right = z;
						z.parent = w;
					}
				}
				w.left = z;
				Node zRightMost = max(z);
				zRightMost.right = y;
				return;
			}
		}
	}

	private Node max(Node start) {
		Node parent = start;
		while (start != null) {
			parent = start;
			start = start.right;
		}
		return parent;
	}

	private static class Node {

		private int key;
		private Node left;
		private Node right;
		private Node parent;

		private Node(int key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return Integer.toString(key);
		}
	}

}
