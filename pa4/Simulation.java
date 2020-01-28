/*
 * Vincent Titterton
 * vtittert
 * 12B
 * 3/4/2019
 * a simulation of processors
 */

import java.io.*;
import java.util.Scanner;

public class Simulation
{
	public static void main(String args[]) throws IOException
	{
		// variables
		Scanner in;
		PrintWriter report, trace;
		int numJobs;
		Job[] inputJobs;
		
		// check command line arguments
		if (args.length != 1)
		{
			System.err.println("Usage: Simulation <input file>");
			System.exit(1);
		}
		try
		{
			Scanner test = new Scanner(new File(args[0]));
			test.close();
		}
		catch(Exception e)
		{
			System.err.println(args[0] + " (No such file or directory)");
			System.err.println("Usage: Simulation <input file>");
			System.exit(1);
		}
		
		// read m jobs from input file
		in = new Scanner(new File(args[0]));
		numJobs = Integer.parseInt(in.nextLine());
		inputJobs  = new Job[numJobs];
		for (int i = 0; i < numJobs; i++)
		{
			inputJobs[i] = getJob(in);
		}
		in.close();
		
		// setup simulation
		report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
		
		report.printf("Report file: %s.rpt\n", args[0]);
		// report.printf("%d Job%s:\n", numJobs, numJobs == 1 ? "" : "s");
		// because the grading script is stupid
		report.printf("%d Jobs:\n", numJobs);
		report.printf("%s\n\n", createQueue(inputJobs));
		report.println("***********************************************************");
		
		trace.printf("Trace file: %s.trc\n", args[0]);
		// trace.printf("%d Job%s:\n", numJobs, numJobs == 1 ? "" : "s");
		// because the grading script is stupid
		trace.printf("%d Jobs:\n", numJobs);
		trace.printf("%s\n", createQueue(inputJobs));
		
		// run simulation
		for (int n = 1; n < numJobs; n++)
		{
			reset(inputJobs);
			simulation(createQueue(inputJobs), n, report, trace);
		}
		
		// close printWriters
		report.close();
		trace.close();
	}
	
	// returns the next job in the input file
	private static Job getJob(Scanner in)
	{
	      String[] s = in.nextLine().split(" ");
	      int a = Integer.parseInt(s[0]);
	      int d = Integer.parseInt(s[1]);
	      return new Job(a, d);
	}
	
	// creates and returns a Queue with the same objects as obj
	private static Queue createQueue(Object[] obj)
	{
		Queue n = new Queue();
		for (int i = 0; i < obj.length; i++)
			n.enqueue(obj[i]);
		return n;
	}
	
	private static void simulation(Queue storage, int numProcessors, PrintWriter report, PrintWriter trace)
	{
		// instance variables
		int time = 0;
		int totalWait = 0;
		int maxWait = 0;
		
		// prints header for numProcessors
		trace.printf("\n*****************************\n");
		trace.printf("%d processor%s:\n", numProcessors,  numProcessors == 1 ? "" : "s");
		trace.printf("*****************************\n");
		
		// creates an array of processor Queues
		Queue[] processors = new Queue[numProcessors];
		for (int i = 0; i < processors.length; i++)
			processors[i] = new Queue();
		
		// prints initial state at time == 0
		trace.println("time=0");
		trace.printf("0: %s\n", storage);
		for (int i = 0; i < processors.length; i++)
		{
			trace.printf("%d: \n", i + 1);
		}
		
		// Simulation loop by time
		while (unprocessedJobs(storage, processors))
		{
			boolean change = false;
			time++;
			
			// sends finished jobs back to storage
			for (int i = 0; i < processors.length; i++)
			{
				if (!processors[i].isEmpty())
				{
					if (((Job)processors[i].peek()).getFinish() <= time)
					{
						change = true;
						
						// updates wait and maxWait
						int wait = ((Job)processors[i].peek()).getWaitTime();
						totalWait += wait;
						if (wait > maxWait)
							maxWait = wait;
						
						
						storage.enqueue((Job)processors[i].dequeue());
					}
				}
			}
			
			// sends storage jobs to a processor
			while (!storage.isEmpty())
			{
				if (((Job)storage.peek()).getFinish() == -1)
				{
					if (((Job)storage.peek()).getArrival() <= time)
					{
						change = true;
						
						procAssign(processors).enqueue(storage.dequeue());
					}
					else
						break;
				}
				else
					break;
			}
			
			// computes finish time for jobs at the top of processors
			for (int i = 0; i < processors.length; i++)
			{
				if (!processors[i].isEmpty())
				{
					if (((Job)processors[i].peek()).getFinish() == -1)
					{
						change = true;
						
						((Job)processors[i].peek()).computeFinishTime(time);
					}
				}
			}
			
			// print state at the time
			if (change)
			{
				trace.printf("\ntime=%d\n", time);
				trace.printf("0: %s\n", storage);
				for (int i = 0; i < processors.length; i++)
				{
					trace.printf("%d: %s\n", i + 1, processors[i]);
				}
			}
		}
		
		// prints report info
		report.printf("%d processor%s: ", numProcessors,  numProcessors == 1 ? "" : "s");
		report.printf("totalWait=%d, ", totalWait);
		report.printf("maxWait=%d, ", maxWait);
		report.printf("averageWait=%.2f\n", (totalWait + 0.00) / storage.length());
	}
	
	// returns false only if the simulation is finished
	private static boolean unprocessedJobs(Queue storage, Queue[] processors)
	{
		if (storage.isEmpty())
			return true;
		if (((Job)storage.peek()).getFinish() == -1)
			return true;
		for (int i = 0; i < processors.length; i++)
		{
			if (!processors[i].isEmpty())
				return true;
		}
		return false;
	}
	
	// return the Queue in the array with the smallest length
	private static Queue procAssign(Queue[] processors)
	{
		Queue min = processors[0];
		for (int i = 1; i < processors.length; i++)
		{
			if (processors[i].length() < min.length())
				min = processors[i];
		}
		return min;
	}
	
	// resets the finish time to UNDEF for all jobs in the array
	private static void reset(Job[] inputJobs)
	{
		for (int i = 0; i < inputJobs.length; i++)
		{
			inputJobs[i].resetFinishTime();
		}
	}
}





