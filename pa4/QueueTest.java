/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 3/4/2019
 * a test file for the Queue ADT
 */

public class QueueTest
{
	public static void main(String args[])
	{
		Queue test = new Queue();
		Queue test2 = new Queue();
		for (int i = 0; i < 8; i++)
		{
			// have to do this cause Job doesn't override .equals()
			Job j = new Job(i + 1, i + 2);
			test.enqueue(j);
			test2.enqueue(j);
		}
		System.out.println(test);
		System.out.println(test2);
		System.out.println(test.equals(test2));
		
		for (int i = 0; i < 4; i++)
		{
			test.dequeue();
		}
		System.out.println(test);
		System.out.println(test2);
		System.out.println(test.equals(test2));
		

		test.dequeueAll();
		System.out.println(test);
		System.out.println(test.length());
	}
	
	// tests enqueue() and toString()
	private Queue test1()
	{
		Queue test = new Queue();
		for (int i = 0; i < 4; i++)
		{
			test.enqueue(i);
			System.out.println(test);
		}
		return test;
	}
	
	// tests dequeue()
	private void test2()
	{
		Queue test = test1();
		for (int i = 0; i < 4; i++)
		{
			System.out.println(test.dequeue());
		}
		
		// now throw queueEmptyException
		test.dequeue();
	}
}









