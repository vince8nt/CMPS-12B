/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 2/18/2019
 * personal test cases for Dictionary.java
 */

public class DictionaryTest
{
	static Dictionary test = new Dictionary();
	public static void main(String[] args)
	{
		insert();
		//duplicateKeys();
		isEmpty();
		delete();
		//nonEx();
		lookup();
	}
	
	public static void insert()
	{
		//tests insert() and toString()
		test.makeEmpty();
		System.out.println("dict1:");
		System.out.println(test);
		test.insert("a", "apple");
		System.out.println("dict2:");
		System.out.println(test);
		test.insert("b", "banana");
		System.out.println("dict3:");
		System.out.println(test);
	}
	
	public static void duplicateKeys()
	{
		// tests insert() for duplicate key exception
		test.makeEmpty();
		test.insert("a", "apple");
		System.out.println(test);
		test.insert("a2", "apple");
		System.out.println(test);
		test.insert("a", "pear");
	}
	
	public static void isEmpty()
	{
		// tests isEmpty() and makeEmpty()
		test.makeEmpty();
		System.out.println(test.isEmpty());
		test.insert("a", "apple");
		System.out.println(test.isEmpty());
		test.insert("o", "orange");
		System.out.println(test.isEmpty());
		test.makeEmpty();
		System.out.println(test.isEmpty());
	}
	
	public static void delete()
	{
		// tests delete()
		test.makeEmpty();
		test.insert("a", "apple");
		test.insert("b", "banana");
		test.insert("o", "orange");
		System.out.println("dict1:");
		System.out.println(test);
		test.delete("a");
		System.out.println("dict2:");
		System.out.println(test);
		test.delete("o");
		System.out.println("dict3:");
		System.out.println(test);
		test.delete("b");
		System.out.println("dict4:");
		System.out.println(test);
	}
	
	public static void nonEx()
	{
		//tests delete() for nonexistent keys
		test.makeEmpty();
		test.insert("a", "apple");
		test.delete("b");
	}
	public static void lookup()
	{
		// tests lookup()
		test.makeEmpty();
		System.out.println(test.lookup("a"));
		test.insert("a", "apple");
		System.out.println(test.lookup("a"));
		test.insert("b", "banana");
		test.insert("o", "orange");
		System.out.println(test.lookup("o"));
		System.out.println(test.lookup("c"));
	}
}
