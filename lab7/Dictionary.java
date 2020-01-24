/*
 * Vincent Titterton
 * vtittert
 * 12M
 * 3/6/2019
 * a Dictionary ADT made with a binary tree
 */

public class Dictionary implements DictionaryInterface
{
	Node root;
	int numPairs;
	
	// constructor for Dictionary
	public Dictionary()
	{
		root = null;
		numPairs = 0;
	}
	
	// returns true if this Dictionary is empty, false otherwise
	public boolean isEmpty()
	{
		return (numPairs == 0);
	}

	// returns the number of entries in the Dictionary
	public int size()
	{
		return numPairs;
	}

	// returns the value v corresponding to key or returns null
	public String lookup(String key)
	{
		Node n = findKey(root, key);
		return ( n == null ? null : n.value );
	}

	// inserts a new key value pair
	public void insert(String key, String value) throws DuplicateKeyException
	{
		Node n, a, b;
		if (findKey(root, key) != null)
			throw new DuplicateKeyException("cannot insert() duplicate key: " + key);
		n = new Node(key, value);
		b = null;
		a = root;
		
		while (a != null)
		{
			b = a;
			if (key.compareTo(a.key) < 0)
				a = a.left;
			else
				a = a.right;
		}
		
		if (b == null)
			root = n;
		else if (key.compareTo(b.key) < 0)
			b.left = n;
		else
			b.right = n;
		numPairs++;
	}

	// deletes the entry with key
	public void delete(String key) throws KeyNotFoundException
	{
		Node n, p, s;
		
		n = findKey(root, key);
		if (n == null)
			throw new KeyNotFoundException("cannot delete() non-existent key: " + key);
		
		// case 1: no children
		if (n.left == null && n.right == null)
		{
			if (n == root)
				root = null;
			else
			{
				p = findParent(n, root);
				if (p.right == n)
					p.right = n.left;
				else
					p.left = null;
			}
		}
		
		// case 2: left, but no right children
		else if (n.right == null)
		{
			if (n == root)
				root = n.left;
			else
			{
				p = findParent(n, root);
				if (p.right == n)
					p.right = n.left;
				else
					p.left = n.left;
			}
		}
		
		// case 3: right, but no left children
		else if (n.left == null)
		{
			if (n == root)
				root = n.right;
			else
			{
				p = findParent(n, root);
				if (p.right == n)
					p.right = n.right;
				else
					p.left = n.right;
			}
		}
		
		// case 4: two children
		else
		{
			s = findLeftmost(n.right);
			n.key = s.key;
			n.value = s.value;
			p = findParent(s, n);
			if (p.right == s)
				p.right = s.right;
			else
				p.left = s.right;
		}
		numPairs--;
	}
	
	// resets the Dictionary to its empty state
	public void makeEmpty()
	{
		root = null;
		numPairs = 0;
	}
	
	// overrides Object's toString()
	public String toString()
	{
		Node n = root;
		StringBuffer s = new StringBuffer("");
		printInOrder(s, n);
		return s.toString();
	}
	
	// overrides Object's equals()
	public boolean equals(Object obj)
	{
		// fix this
		return false;
	}
	
	// ----------------------------------------------------------
	// private methods
	// ----------------------------------------------------------
	
	// appends the subtree under root to s
	private void printInOrder(StringBuffer s, Node root)
	{
		if (root != null)
		{
			printInOrder(s, root.left);
			s.append(root.key + " " + root.value + "\n");
			printInOrder(s, root.right);
		}
	}
	
	// returns the key in the subtree of root or returns null
	private Node findKey(Node root, String key)
	{
		if (root == null || key.equals(root.key))
			return root;
		if (key.compareTo(root.key) < 0)
			return (findKey(root.left, key));
		return (findKey(root.right, key));
	}
	
	// returns the parent of target in the subtree of root or returns null
	private Node findParent(Node target, Node root)
	{
		Node parent = null;
		if (target != root)
		{
			parent = root;
			while (parent.left != target && parent.right != target)
			{
				if (target.key.compareTo(parent.key) < 0)
					parent = parent.left;
				else
					parent = parent.right;
			}
		}
		return parent;
	}
	
	// returns the leftmost Node in the subtree of root
	private Node findLeftmost(Node root)
	{
		Node left = root;
		if (left != null)
			for ( ; left.left != null; left = left.left);
		return left;
	}
	
	// ----------------------------------------------------------
	// private inner-class
	// ----------------------------------------------------------
	
	// the Node class
	private class Node
	{
		String key, value;
		Node left, right;
		
		// constructor for Node
		private Node(String key, String value)
		{
			this.key = key;
			this.value = value;
			left = right = null;
		}
	}
}






