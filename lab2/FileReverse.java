/*
 * Vincent Titterton
 * vtittert
 * 12M
 * 1/24/2019
 * This program takes an input file, separates it into tokens, 
 * and creates a new file listing the reverse of each token
 */

import java.io.*;
import java.util.Scanner;

public class FileReverse {
	public static void main(String[] args) throws IOException
	{
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
		int i, n, lineNumber = 0;
		// check number of command line arguments is at least 2
		if (args.length != 2) {
			System.err.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}
		// open files
		try
		{
			new Scanner(new File(args[0]));
		}
		catch (Exception e)
		{
			System.err.println(args[0] + " (No such file or directory)");
			System.err.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		// read lines from in, extract and print tokens from each line
		while (in.hasNextLine()) {
			lineNumber++;
			// trim leading and trailing spaces, then add one trailing space so
			// split works on blank lines
			line = in.nextLine().trim() + " ";
			// split line around white space
			token = line.split("\\s+");
			// print out tokens
			n = token.length;
			for (i = 0; i < n; i++) {
				out.println(" " + stringReverse(token[i], token[i].length()));
			}
		}
		// close files
		in.close();
		out.close();
	}
	
	public static String stringReverse(String s, int n)
	{
		if(n > 1)
		{
			return s.substring(n - 1, n) + stringReverse(s.substring(0, n - 1), n - 1);
		}
		return s;
	}
}

