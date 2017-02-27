package documentTools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import storage.DocumentStorage;

/**
 * Search - Searches for paragraphs that contain a search term.
 * 
 * @author Team J - Joey Arbogast
 * @version 1.0
 */
public class Search
{
	private String searchTerm;
	private Map<String, List<TermPair>> reverseIndex;
	
	private ReverseIndex revIndex;
	
	/**
	 * Constructor - Sets search term and initializes ReverseIndex object and Map
	 * of reverseIndexTerms.
	 * 
	 * @param term search term
	 */
	public Search(String term)
	{
		revIndex = new ReverseIndex();
		
		// Get the reverseIndex and store it in the Map
		reverseIndex = revIndex.getReverseIndex();
		
		// Store the search term, make lowercase, and trim any whitespace
		searchTerm = term.toLowerCase().trim();
	}
	
	/**
	 * getResults - Returns a list of the paragraphs as results.
	 * 
	 * @return List<Paragraph> paragraphs
	 */
	public List<Paragraph> getResults()
	{
		// Load in list of all documents in library
		List<Document> docs = DocumentStorage.loadDocuments();
		
		// Search for term and store the return in occurrences
		List<TermPair> occurrences = searchForTerm();
		
		if (occurrences == null || occurrences.isEmpty())
		{
			return null;
		}
		
		List<Paragraph> results = new ArrayList<>();
		
		// Loop through each TermPair object in the occurrences list
		for (TermPair tp : occurrences)
		{
			// Retrieve the document number and paragraph number of the occurrence
			int docNum = tp.getdocNum();
			int paragraphNum = tp.getParaNum();
			
			// Store retrieved document and paragraph
			Document d = docs.get(docNum - 1);
			Paragraph p = d.getParagraph(paragraphNum - 1);
			
			// Add paragraph to the results list
			results.add(p);
		}
		
		return results;
	}
	
	/**
	 * getReverseIndex - Returns reverseIndex Map.
	 * 
	 * @return Map<String,List<TermPair>> Map of the reverse index
	 */
	public Map<String, List<TermPair>> getReverseIndex()
	{
		return this.reverseIndex;
	}
	
	/**
	 * getSearchTerm - Returns a searchTerm.
	 * 
	 * @return String searchTerm
	 */
	public String getSearchTerm()
	{
		return this.searchTerm;
	}
	
	/**
	 * searchForTerm - Searches for term in reverseIndex list.
	 * 
	 * @return List<TermPair> List of TermPair objects
	 */
	protected List<TermPair> searchForTerm()
	{
		List<TermPair> occurrences = new ArrayList<>();
		
		// Check if ReverseIndex contains search term
		if (reverseIndex.containsKey(searchTerm))
		{
			// Add the term from the reverseIndex to the occurrences list
			occurrences = reverseIndex.get(searchTerm);
			Set<TermPair> duplicates = new HashSet<>();
			duplicates.addAll(occurrences);
			occurrences.clear();
			occurrences.addAll(duplicates);
		}
		else
		{
			return null;
		}
		
		return new ArrayList<>(occurrences);
	}
}
