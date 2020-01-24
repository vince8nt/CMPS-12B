/*
 * Vincent Titterton
 * vtittert
 * 12M
 * 3/4/2019
 * a test file for the list ADT
 */

public class ListTest
{
	public static void main(String[] args)
	{
		List<Integer> test = new List<Integer>();
		List<Integer> test2 = new List<Integer>();
		List<Double> testDouble = new List<Double>();
		
		System.out.println(test.isEmpty());
		test.add(1, (Integer)5);
		System.out.println(test);
		System.out.println(test.isEmpty());
		
		try
		{
			test.add(0, 8);
		}
		catch(ListIndexOutOfBoundsException e)
		{
			System.out.println("0 is out of bounds");
		}
		
		try
		{
			test.add(5, 8);
		}
		catch(ListIndexOutOfBoundsException e)
		{
			System.out.println("5 is out of bounds");
		}
		
		test.add(2, (Integer)7);
		test.add(3, (Integer)8);
		test.add(2, (Integer)6);
		System.out.println(test);
		
		System.out.println("6 = " + test.get(2));
		
		test.remove(3);
		test.remove(1);
		System.out.println(test);
		System.out.println("2 = " + test.size());
		
		test2.add(1, 8);
		test2.add(1, 6);
		System.out.println
		("The statement test == test2 is " + test.equals(test2));
		
		testDouble.add(1, 8);
		testDouble.add(1, 6);
		System.out.println
		("The statement test == testDouble is " + test.equals(testDouble));
		
		test2.add(3, 10);
		System.out.println
		("The statement test == test2 is " + test.equals(test2));
		
		test.removeAll();
		System.out.println(test.isEmpty());
	}
	
}
