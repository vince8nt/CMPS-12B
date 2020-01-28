/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 2/18/2019
 * a dictionary made from a linked list
 */

public class Dictionary implements DictionaryInterface
{
	private Node head = null;
	
	public boolean isEmpty()
	{
		if (size() == 0)
			return true;
		return false;
	}

	public int size()
	{
		int counter = 0;
		Node n = head;
		while (n != null)
		{
			counter++;
			n = n.next;
		}
		return counter;
	}

	public String lookup(String key)
	{
		if (head == null)
			return null;
		Node n = head.findKey(key);
		if (n == null)
			return null;
		return n.value;
	}

	public void insert(String key, String value) throws DuplicateKeyException
	{
		if (lookup(key) != null)
			throw new DuplicateKeyException("cannot insert duplicate keys");
		if (head == null)
			head = new Node(key, value);
		else
		{
			Node n = head;
			while (n.next != null)
			{
				n = n.next;
			}
			n.next = new Node(key, value);
		}
		
		
	}

	public void delete(String key) throws KeyNotFoundException
	{
		if (head == null)
			throw new KeyNotFoundException("cannot delete non-existent key");
		Node n = head.findKey(key);
		if (n == null)
			throw new KeyNotFoundException("cannot delete non-existent key");
		if (head.key.equals(key))
			head = head.next;
		else
		{
			n = head;
			while (!n.next.key.equals(key))
			{
				n = n.next;
			}
			n.next = n.next.next;
		}
		
	}

	public void makeEmpty()
	{
		head = null;
	}
	
	public String toString()
	{
		String s = "";
		Node n = head;
		while (n != null)
		{
			s += n.key + " " + n.value + "\n";
			n = n.next;
		}
		return s;
	}
	
	private class Node
	{
		String key, value;
		Node next;
		
		private Node(String key, String value)
		{
			this.key = key;
			this.value = value;
			next = null;
		}
		
		private Node findKey(String key)
		{
			Node n = head;
			while (n != null)
			{
				if (n.key.equals(key))
					return n;
				n = n.next;
			}
			return null;
		}
	}
}
