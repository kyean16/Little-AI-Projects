package nonGui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Keyean
 *
 */

public class TSPReader 
{
	private int[][] map; //2D array
	private int numCities; //Cities
	private boolean valid;
	
	/**
	 * Constructor, checks if fileName is a filename that exists if it does not exists it return false. true if exists.
	 * @param fileName
	 */
	public TSPReader(String fileName)
	{
		valid = true; //Assume everything is fine
		try
		{
	          FileReader inputFile = new FileReader(fileName);
	          BufferedReader bufferReader = new BufferedReader(inputFile);
	          Scanner TSP = new Scanner(bufferReader); 
	          //Parses the information in the file.	  
	          if(TSP.hasNextInt())
	          {
	        	  numCities = TSP.nextInt();
	        	  map = new int[numCities][numCities]; //Set the 2-D Array.
	          }
	          int row = 0;
	          int column = 0;
	          while(TSP.hasNextInt())
	          {
	        	  map[row][column] = TSP.nextInt();
	        	  if(column < numCities-1)
	        	  {
	        		  column++;
	        	  }
	        	  else
	        	  {
	        		  column = 0;
	        		  row ++;
	        	  }
	          }
	          //Sets whether or not the information was well parsed or not.
	          checkValid();
		}
		catch(IOException ex)
		{
			valid = false;
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			valid = false;
		}
	}
	
	/**
	 * Sets whether or not the 2-D Array is valid or not.
	 */
	public void checkValid()
	{
		
		//Checks if diagonal is correct
		for(int diagonal = 0 ; diagonal < numCities ; diagonal++)
		{
			if(map[diagonal][diagonal] != 0)
			{
				valid = false;
				return;
			}
		}
		
		//Check if there are Zero > numCities
		int zero = 0;
		
		for(int row = 0; row < numCities; row++)
		{
			for(int column = 0; column < numCities; column++)
			{
				if(map[row][column] == 0)
				{
					zero++;
				}
				if(zero > numCities)
				{
					valid = false;
					return;
				}
			}
		}
	}
	/**
	 * Returns whether or not the input file is valid or not.
	 * @return valid
	 */
	public boolean isValid()
	{
		return valid;
	}
	/**
	 * Return the Number Of Cities
	 * @return numCities
	 */
	public int getNumCities()
	{
		return numCities;
	}
	
	/**
	 * Return the 2-D array Map.
	 * @return map
	 */
	public int[][] getMap()
	{
		return map;
	}
}
