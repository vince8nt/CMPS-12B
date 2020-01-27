/* Vincent Titterton
 * vtittert
 * 12B
 * 1/21/2019
 * java program for binary and merge sort
 */
import java.io.*;
import java.util.Scanner;

public class Search
{
	public static void main(String[] args) throws IOException
	{
		
		// check number of command line arguments
		if(args.length < 2)
		{	
			System.err.println("Usage: Search file target1 [target2 ..]");
			System.exit(1);
		}
		
		// check if file exists
		try
		{
			new Scanner(new File(args[0]));
		}
		catch (Exception e)
		{
			System.err.println(args[0] + " (No such file or directory)");
			System.err.println("Usage: Search file target1 [target2 ...]");
			System.exit(1);
		}
		
		// count the number of lines in file
		Scanner in = new Scanner(new File(args[0]));
		int lineCount = 0;
		while( in.hasNextLine() )
		{
			in.nextLine();
			lineCount++;
		}
		in.close();
		
		// create String[] inputLines
		String[] inputLines = new String[lineCount];
		in = new Scanner(new File(args[0]));
		int counter = 0;
		while( in.hasNextLine() )
		{
			inputLines[counter] = in.nextLine();
			counter++;
		}
		in.close();
		
		
		// create int[] order
		int order[] = new int[lineCount];
		for(int i = 0; i < order.length; i++)
		{
			order[i] = i + 1;
		}
		
		//Sort inputLines
		mergeSort(inputLines, order, 0, order.length - 1);
		
		//Print desired outputs
		for(int i = 1; i < args.length; i++)
		{
			int index = BinarySearch(inputLines, 0, inputLines.length - 1, args[i]);
			if(index == -1)
				System.out.println(args[i] + " not found");
			else
				System.out.println(args[i] + " found on line " + order[index]);
		}
		
	}

	public static void mergeSort(String[] list, int[] lineNums, int begin, int end)
	{
		int middle;
		if(begin < end)
		{
			middle = (begin + end) / 2;
			mergeSort(list, lineNums ,begin, middle);
			mergeSort(list, lineNums, middle + 1, end);
			merge(list, lineNums, begin, middle, end);
		}
	}

	public static void merge(String[] list, int[] lineNums, int begin, int middle, int end)
	{
		String[] first = new String[middle - begin + 1];
		int[] firstNums = new int[middle - begin + 1];
		String[] second = new String[end - middle];
		int[] secondNums = new int[end - middle];

		for(int i = 0; i < first.length; i++)
		{
			first[i] = list[begin + i];
			firstNums[i] = lineNums[begin + i];
		}
		for(int i = 0; i < second.length; i++)
		{
			second[i] = list[middle + i + 1];
			secondNums[i] = lineNums[middle + i + 1];
		}

		int indexFirst = 0, indexSecond = 0;
		for(int i = begin; i <= end; i++)
		{
			if(indexFirst < first.length && indexSecond < second.length)
			{
				if(first[indexFirst].compareTo(second[indexSecond]) < 0)
				{
					list[i] = first[indexFirst];
					lineNums[i] = firstNums[indexFirst];
					indexFirst++;
				}
				else
				{
					list[i] = second[indexSecond];
					lineNums[i] = secondNums[indexSecond];
					indexSecond++;
				}
			}
			else if(indexFirst < first.length)
			{
				list[i] = first[indexFirst];
				lineNums[i] = firstNums[indexFirst];
				indexFirst++;
			}
			else
			{
				list[i] = second[indexSecond];
				lineNums[i] = secondNums[indexSecond];
				indexSecond++;
			}
		}
	}

	public static int BinarySearch(String[] list, int begin, int end, String target)
	{
		int middle;
		if(begin > end)
		{
			return -1;
		}
		else
		{
			middle = (begin + end) / 2;
			if(target.equals(list[middle]))
				return middle;
			else if(target.compareTo(list[middle]) < 0)
				return BinarySearch(list, begin, middle - 1, target);
			else
				return BinarySearch(list, middle + 1, end, target);
		}
	}
}
