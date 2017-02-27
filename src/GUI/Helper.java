package GUI;

import documentTools.Document;
import documentTools.Indexer;
import documentTools.Paragraph;
import documentTools.Search;
import documentTools.TitleAuthorExtractionException;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AdminHelper - Helper class, meant to be the middle man between the GUI and
 * the logic.
 *
 * @author Team J - Joey Arbogast
 * @version 3.0
 */
public class Helper
{
	private String searchText;
	
	private Document doc;
	private List<Paragraph> resultsList;
	private List<String> searchTerms;
	
	/**
	 * Constructor - Passes user input to the Document class.
	 *
	 * @param filepath document location on local computer
	 * @param title document title
	 * @param author document author
	 * @throws FileNotFoundException iff user specified file cannot be located
	 * @throws TitleAuthorExtractionException if title and/or author cannot be
	 *         extracted
	 */
	public Helper(String filepath, String title, String author)
			throws FileNotFoundException, TitleAuthorExtractionException
	{
		// Create a new Document and pass Strings to its constructor
		doc = new Document(filepath, title, author);
		doc.storeFile(doc);
	}
	
	/**
	 * Constructor - Used to instantiate attributes for the search function in the
	 * user GUI.
	 * 
	 * @param searchText search term
	 */
	public Helper(String searchText)
	{
		resultsList = new ArrayList<>();
		searchTerms = new ArrayList<>();
		
		// Make searchText lowercase and store a reference to it
		this.searchText = searchText.toLowerCase();
		
		StringBuilder strBuilder = new StringBuilder();
		
		// Loop through searchText and split into individual terms
		for (int i = 0; i < this.getSearchText().length(); i++)
		{
			// Split on space character
			if (Character.isSpaceChar(this.getSearchText().charAt(i)))
			{
				searchTerms.add(strBuilder.toString());
				strBuilder.delete(0, strBuilder.length());
			}
			else
			{
				strBuilder.append(this.getSearchText().charAt(i));
			}
		}
		
		// Add the last term to the searchTerms
		searchTerms.add(strBuilder.toString());
		
		strBuilder = null;
		
		// Filter out any stop list terms from the searchTerms list
		searchTerms.removeAll(Indexer.getStopList());
	}
	
	/**
	 * getMessage - Displays a message to the admin.
	 *
	 * @return String message
	 */
	public String getMessage()
	{
		return this.doc.getMessage();
	}
	
	/**
	 * getSearchResults - Returns search results and adds toString of each
	 * paragraph to a list.
	 * 
	 * @return List<String> List of paragraphs containing term
	 */
	public List<String> getSearchResults()
	{
		this.search();
		
		List<String> results = new ArrayList<>();
		
		// Add toStrings for paragraphs to the results list
		for (Paragraph p : resultsList)
		{
			results.add(p.toString());
		}
		
		return results;
	}
	
	/**
	 * getSearchText - Returns search text
	 * 
	 * @return String search text
	 */
	public String getSearchText()
	{
		return this.searchText;
	}

	/**
	 * removeDuplicateResults - Removes duplicate results.
	 */
	public void removeDuplicateResults()
	{
		Set<Paragraph> dups = new HashSet<>();
		dups.addAll(resultsList);
		
		resultsList.clear();
		resultsList.addAll(dups);
	}

	/**
	 * search - Creates a Search object with each term in searchTerms and gets
	 * results.
	 */
	public void search()
	{
		// Loop through each term in the searchTerms list
		for (int i = 0; i < searchTerms.size(); i++)
		{
			Search search = new Search(searchTerms.get(i));
			
			// Find paragraphs containing term
			List<Paragraph> paraResults = search.getResults();
			
			// Check for null list
			if (paraResults != null)
			{
				resultsList.addAll(paraResults);
			}
		}
		
		removeDuplicateResults();
	}
}
