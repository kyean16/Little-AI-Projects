package nonGui;
/**
 * 
 * @author Keyean
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TSP 
{
	 private int[][] map; //2D array
	 private int numCities; //Cities
	 private int maxGeneration; //Max Generation
	 private int currentGeneration;//Curent Generation
	 private int numSamples; //Max num of Samples per Generation
	 private ArrayList<TSPTour> oldGen;
	 private ArrayList<TSPTour> newGen;
	 private TSPTour bestTour; //Best TPSTour
	 
	 /**
	  * Constructor that sets up the search
	  */
	 public TSP(int[][] newMap, int newNumCities, int newMaxGeneration, int newNumSamples)
	 {
		 map = newMap;
		 numCities = newNumCities;
		 maxGeneration = newMaxGeneration;
		 numSamples = newNumSamples;
		 currentGeneration = 0; 
		 oldGen = new ArrayList<TSPTour>();
		 newGen = new ArrayList<TSPTour>();
		 Search();
		 System.out.println("");
		 System.out.println("The best path found is path: " + bestTour.pathToString() );
		 System.out.println("Generation " + bestTour.getGeneration());
		 System.out.println("Fitness " + bestTour.getFitness());
		
	 }
	 
	 /**
	  * Creates random TSP tour
	  */
	 public TSPTour generateTour()
	 {
		 ArrayList<Integer> path = new ArrayList <Integer>();
		 for(int i = 0 ; i < numCities ; i++)
		 {
			path.add(i); 
		 }
		 Collections.shuffle(path);
		 TSPTour newTour = new TSPTour(path,numCities);
		 newTour.Fitness(map);
		 newTour.setGeneration(currentGeneration);
		 return newTour;
	 }
	 
	 /**
	  * Search 
	  */
	 public void Search()
	 {
		 
		 System.out.println();
		 //Create initial pop size population
		 for(int i = 0 ; i < numSamples ; i++)
		 {
			oldGen.add(generateTour());
		 }
		 Collections.sort(oldGen); //Sort using Fitness as comparable

		 System.out.println("Gen:" + currentGeneration);
		 bestTour = oldGen.get(0); //Set the best path.
		 currentGeneration++;
		 while(currentGeneration <= maxGeneration) //Keep doing until Max Generation is not complte.
		 {
			 newGen = new ArrayList<TSPTour>(); //Start Fresh;
			 
			 while(newGen.size()<numSamples)
			 {
				 //Selection
				 int posX = Selection();
				 TSPTour individualX = oldGen.get(posX);
				 individualX.setGeneration(currentGeneration);
				 int posY = Selection();
				 TSPTour individualY = oldGen.get(posY);
				 individualY.setGeneration(currentGeneration);
				 
				 //Cross Over
				 Random random = new Random();
				 int begin = random.nextInt(numCities);
				 int end = random.nextInt(numCities);
				 if(end < begin)
				 {
					 int temp = begin;
					 begin = end;
					 end = temp;
				 }
				 if(begin == end)
				 {
					 if(end < numCities-1)
						 end++;
					 else
						 begin--;
				 }
				 newGen.add(CrossOver(individualX, begin,end));
				 newGen.add(CrossOver(individualY, begin,end));
			 }
			 
			//Mutate one item.
			 Random mutate = new Random();
			 int i = mutate.nextInt(numSamples);
			 newGen.set(i, Mutate(newGen.get(i)));
			 oldGen = newGen; //Replaces ArrayList
			 Collections.sort(oldGen);
			 
			 System.out.println("\nGen:" + currentGeneration);
			 System.out.println("Best Route for this Gen: " + oldGen.get(0).getPath());
			 System.out.println("Fitness: " + oldGen.get(0).getFitness());
			 if(oldGen.get(0).getFitness() < bestTour.getFitness())
			 {
				 bestTour = oldGen.get(0);
			 }
			 currentGeneration++;
		 }
	 }
	 
	 /**
	  * Selects an individual depending on a random number
	  */
	 public int Selection()
	 {
		 int total = 0;
		 for(int i = (numSamples * 10) ; i >= 10 ; i = i - 10 )
		 {
			 total += i;
		 }
		 
		 Random random = new Random();
		 int pick = random.nextInt(total);
		 int position = 0;
		 int range = 0;
		 for(int i = (numSamples * 10) ; i >= 10 ; i = i - 10 )
		 {
			 if(pick > range && pick < (range = range + i))//Falls in range
			 {
				 return position;
			 }
			 position++;
		 }
		 return 0;
	 }
	 
	 /**
	  * Cross Over
	  */
	 public TSPTour CrossOver(TSPTour ind, int startRange , int endRange)
	 {
		 
		ArrayList<Integer> crossOverPath = new ArrayList<Integer>();
		ArrayList<Integer> oldPath = ind.getPath();
		ArrayList<Integer> newPath = new ArrayList<Integer>();
		
		for(int i = startRange ; i <= endRange ; i++)
		{
			crossOverPath.add(oldPath.get(i));
		}
		Collections.shuffle(crossOverPath);
		for(int i = 0 ; i <oldPath.size() ; i++)
		{
			newPath.add(oldPath.get(i));
		}
		
		int x = 0;
		for(int i = startRange ; i <= endRange ; i++)
		{
			newPath.set(i, crossOverPath.get(x));
			x++;
		}
		TSPTour newTour = new TSPTour(newPath,numCities);
		newTour.setPath(newPath);
		newTour.setGeneration(currentGeneration);
		newTour.Fitness(map);
		return newTour;
	 }
	 
	 /**
	  * Mutate
	  */
	 public TSPTour Mutate(TSPTour Tour)
	 {
		Random random = new Random();
		ArrayList<Integer> mutation = Tour.getPath();
		
		int first = random.nextInt(numCities);
		int second = random.nextInt(numCities);
		int temp = mutation.get(first);
		
		mutation.set(first, mutation.get(second));
		mutation.set(second, temp);
		Tour.setPath(mutation);
		Tour.Fitness(map);
		
		return Tour;
	 } 
}
