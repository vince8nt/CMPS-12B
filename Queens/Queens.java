/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 1/29/2019
 * Checks how many solutions to the n queens problem
 * there is for an specific n and can print the solutions too
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Queens
{
	public static void error()
	{
		System.err.println("Usage: Queens [-v] number");
		System.err.println("Option: -v verbose output, print all solutions");
		System.exit(1);
	}
	
	public static void main(String[] args) throws IOException
	{	
		int numIndex = 0;
		String mode = "";
		
		// check for correct args and set numIndex and mode
		if (args.length == 1)
		{
			numIndex = 0;
			mode = "";
		}
		else if (args.length == 2)
		{
			if (!args[0].equals("-v"))
				error();
			numIndex = 1;
			mode = "verbose";
		}
		else
			error();
		
		// check if entered size is integer and is >= 0
		try
		{
			int test = Integer.parseInt(args[numIndex]);
			if (test < 0)
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			error();
		}
		
		// create the board
		int size = Integer.parseInt(args[numIndex]);
		int[][] board = new int[size + 1][size + 1];
		
		// print desired output
		System.out.println(size+"-Queens has "+findSolutions(board, 1, mode)+" solutions");
		
	}
	
	static void placeQueen(int[][] board, int y, int x)
	{
		board[y][x]++;
		board[y][0] = x;
		for (int yCount = y + 1; yCount < board.length; yCount++)
		{
			for (int xCount = 1; xCount < board[yCount].length; xCount++)
			{
				if (xCount==x || yCount==y || xCount-yCount==x-y || xCount+yCount==x+y)
					board[yCount][xCount]--;
			}
		}
	}
	
	static void removeQueen(int[][] board, int y, int x)
	{
		board[y][x]--;
		board[y][0] = 0;
		for (int yCount = y + 1; yCount < board.length; yCount++)
		{
			for (int xCount = 1; xCount < board[yCount].length; xCount++)
			{
				if (xCount==x || yCount==y || xCount-yCount==x-y || xCount+yCount==x+y)
					board[yCount][xCount]++;
			}
		}
	}
	
	static void printBoard(int[][] board)
	{
		System.out.print("(");
		for (int y = 1; y < board.length; y++)
		{
			if (y != 1)
				System.out.print(", ");
			for (int x = 1; x < board[y].length; x++)
			{
				if (board[y][x] == 1)
					System.out.print(x);
			}
		}
		System.out.println(")");
	}
	
	static int findSolutions(int[][] board, int y, String mode)
	{
		if (y >= board.length)
		{
			if (mode.equals("verbose"))
				printBoard(board);
			return 1;
		}
		
		int accSum = 0;
		for (int x = 1; x < board[y].length; x++)
		{
			if (board[y][x] == 0)
			{
				placeQueen(board, y, x);
				accSum += findSolutions(board, y + 1, mode);
				removeQueen(board, y, x);
			}
		}
		return accSum;
	}
}











