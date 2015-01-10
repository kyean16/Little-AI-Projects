package nonGui;

import java.util.Scanner;

/**
 * Genetic search for finding the shortest path for TSP
 * 
 * @author Keyean
 *
 */


public class Driver {
	
	 private int[][] map; //2D array
	 private int numCities; //Cities
	 private int maxGeneration; //Max Generation
	 private int numSamples; //Max num of Samples per Generation
	 private TSPTour bestTour; //Best TPSTour
	
	 public static void main(String[] args)
	 {
	      Driver drive = new Driver();
	 }
	 
	 /**
	  * Constructor. Ask the user for a file name, number of generation allowed then call TPS search 
	  */
	 public Driver()
	 {
		 Scanner keyboard = new Scanner(System.in);
		 System.out.print("Please enter a text file:");
		 String file = keyboard.next();
		 TSPReader tspReader = new TSPReader(file); //Creates a FileReader;
		 while(!tspReader.isValid())
		 {
			System.out.println("The text file does not exist or is not in the right format");
			System.out.print("Please enter a text file:");
			file = keyboard.next();
			if(file.toLowerCase().equals("quit"))
			{
				break;
			}
			tspReader = new TSPReader(file); //Creates a FileReader;
		 }
		 numCities = tspReader.getNumCities(); //Sets Number of Cities
		 map = tspReader.getMap(); //Sets the 2-D Map

		 System.out.print("Please enter the Maximum Number of Generation:");
		 String stringGen= keyboard.next();
		 while(maxGeneration <= 0)
		 {
			 try
			 {
				 maxGeneration = Integer.parseInt(stringGen); //Sets maxGeneration
				 if(maxGeneration <= 0)
				 {
					 System.out.print("Please enter the Maximum Number of Generation:");
					 stringGen = keyboard.next();
				 }
			 }
		     catch(NumberFormatException e)
			 {
				 System.out.print("Please enter the Maximum Number of Generation:");
				 stringGen = keyboard.next();
			 }
		 }
		 
		 System.out.print("Please enter the number of Samples per Generation:");
		 String genSample = keyboard.next();
		 while(numSamples <= 0)
		 {
			 try
			 {
				 numSamples = Integer.parseInt(genSample); //Sets numSamples
				 if(numSamples <= 0)
				 {
					 System.out.print("Please enter the number of Samples per Generation:");
					 genSample = keyboard.next();
				 }
			 }
		     catch(NumberFormatException e)
			 {
				 System.out.print("Please enter the number of Samples per Generation:");
				 genSample = keyboard.next();
			 }
		 }
		 BeginSearch();
	 }
	 
	 /**
	  * Creates a new TSP and then prints the result of the best path once found. 
	  */
	 public void BeginSearch()
	 {
		 TSP genetics = new TSP(map, numCities, maxGeneration ,numSamples);
	 }

}
