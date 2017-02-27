package documentTools;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Indexer - Reads in a stop list and removes all words from index that match.
 *
 * @author Team J
 * @version 2.0
 */
public abstract class Indexer
{
	// Static list of stoplist terms
	private static List<String> stopList;
	private List<String> indexes; // list of index terms
	
	/**
	 * findIndexes - Searches the list of index terms for stoplist words and
	 * removes them.
	 * 
	 * @param text list of index terms to compare to stoplist
	 * @return List<String> filtered index terms
	 */
	protected List<String> findIndexes(List<String> text)
	{
		// Import the stop list
		stopList = new ArrayList<>();
		
		try
		{
			Scanner in = new Scanner(new File("src/stopList.txt"));
			
			// Read each term from file and add it to the stopList
			while (in.hasNextLine())
			{
				stopList.add(in.nextLine());
			}
			
			in.close();
		}
		catch (Exception e1)
		{
			System.out.println("Error reading stopList.txt");
		}
		
		// Remove all words that are on the stop list
		List<String> indexesAL = text;
		List<String> stopListAL = stopList;
		
		indexesAL.removeAll(stopListAL);
		indexes = new ArrayList<>(indexesAL);
		
		// Remove Duplicate terms by using a HashSet
		Set<String> noDuplicateIndexes = new HashSet<>();
		noDuplicateIndexes.addAll(indexes);
		indexes.clear();
		
		indexes.addAll(noDuplicateIndexes);
		noDuplicateIndexes = null;
		
		// Return List of strings of indexed words
		return indexes;
	}
	
	/**
	 * getStopList - Provides a static method to return the stop list terms.
	 * 
	 * @return List<String> stoplist terms
	 */
	public static List<String> getStopList()
	{
		// Import the stop list
		stopList = new ArrayList<>();
		List<String> stopListCopy = new ArrayList<>();
		
		try
		{
			Scanner in = new Scanner(new File("src/stopList.txt"));
			
			// Read the list of stopList terms from the file and add to ArrayList
			while (in.hasNextLine())
			{
				stopList.add(in.nextLine());
			}
			
			in.close();
		}
		catch (Exception e1)
		{
			System.out.println("Error reading stopList.txt");
		}
		
		// Make a copy of the stopList terms and return them
		stopListCopy.addAll(stopList);
		
		return stopListCopy;
	}
}
