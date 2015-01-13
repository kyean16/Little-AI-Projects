package genetics;

import java.util.ArrayList;

/**
 * 
 * @author Keyean
 *
 */

public class TSPTour implements Comparable
{
	private int fitness;
	private int numCities;
	public ArrayList<Integer> path;
	private int generation;
	
	public TSPTour()
	{
		path = new ArrayList<Integer>();
	}
	
	/**
	 * 
	 */
	public TSPTour(ArrayList<Integer> tour, int newNumCities)
	{
		path = new ArrayList<Integer>();
		for(int i = 0 ; i < tour.size(); i++)
		{
			path.add(tour.get(i));
		}
		numCities = newNumCities;
	}
	
	/**
	 * Calculates the fitness of that specific tour;
	 * @param map
	 */
	public void Fitness(int[][] map)
	{
		for(int i = 0 ; i < numCities; i++)
		{
			if(i < numCities-1)
			{
				fitness += map[path.get(i)][path.get(i+1)];
			}
			else //Loop back
			{
				fitness += map[path.get(i)][path.get(0)];
			}
		}
		//System.out.println("\n"+ fitness);
	}
	
	/**
	 * Return fitness
	 * @return fitness
	 */
	public int getFitness()
	{
		return fitness;
	}
	
	/**
	 * Set Path
	 * @param tour
	 */
	public void setPath(ArrayList<Integer> tour)
	{
		path =new ArrayList<Integer>();
		for(int i = 0 ; i < tour.size(); i++)
		{
			path.add(tour.get(i));
		}
	}
	
	/**
	 * Return the Path ArrayList
	 */
	public ArrayList<Integer> getPath()
	{
		return path;
	}
	
	public String pathToString()
	{
		String text ="";
		for(int i = 0 ; i < path.size() ; i++)
		{
			text += path.get(i) + " ";
		}
		return text;
	}
	
	/**
	 * Set the generation
	 * @param currentGen
	 */
	public void setGeneration(int currentGen)
	{
		generation = currentGen;
	}
	
	/**
	 * Get the generation of the tour
	 * @return generation
	 */
	public int getGeneration()
	{
		return generation;
	}

	/**
	 * Compares using fitness
	 */
	@Override
	public int compareTo(Object compare) {
		int compareFitness=((TSPTour)compare).getFitness();
		return this.fitness-compareFitness;
	}
}
