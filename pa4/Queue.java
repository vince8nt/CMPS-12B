/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 3/4/2019
 * a Queue ADT
 */

public class Queue implements QueueInterface
{
	private Node head;
	private Node tail;
	private int numItems;
	
	public Queue()
	{
		head = null;
		tail = null;
		numItems = 0;
	}
	
	public boolean isEmpty()
	{
		return (numItems == 0);
	}

	public int length()
	{
		return numItems;
	}
	
	public void enqueue(Object newItem)
	{
		Node n = new Node(newItem);
		if (numItems == 0)
		{
			head = n;
			tail = n;
		}
		else
		{
			tail.next = n;
			tail = n;
		}
		numItems++;
	}

	public Object dequeue() throws QueueEmptyException
	{
		if (numItems == 0)
			throw new QueueEmptyException("cannot dequeue() empty queue");
		Node n = head;
		head = head.next;
		if(head == null)
			tail = null;
		numItems--;
		return n.item;
	}

	public Object peek() throws QueueEmptyException
	{
		if (numItems == 0)
			throw new QueueEmptyException("cannot peek() empty queue");
		return head.item;
	}

	public void dequeueAll() throws QueueEmptyException
	{
		if (numItems == 0)
			throw new QueueEmptyException("cannot dequeueAll() empty queue");
		head = null;
		tail = null;
		numItems = 0;
	}
	
	public String toString()
	{
		Node n = head;
		StringBuffer s = new StringBuffer("");
		while(n != null)
		{
			s.append(n.item + " ");
			n = n.next;
		}
		if (s.length() > 0)
			s.deleteCharAt(s.length() - 1);
		return (s.toString());
	}
	
	public boolean equals(Object obj)
	{
		Queue test;
		if (obj instanceof Queue)
		{
			test = (Queue)obj;
			if (numItems == test.numItems)
				return (nodesEqual(head, test.head));
		}
		return false;
	}
	
	// assumes that both are the same length
	private boolean nodesEqual(Node n, Node w)
	{
		if (n == null)
			return true;
		if (!n.item.equals(w.item))
			return false;
		return nodesEqual(n.next, w.next);
	}
	
	class Node
	{
		Object item;
		Node next;
		
		public Node(Object obj)
		{
			item = obj;
			next = null;
		}
	}
}
