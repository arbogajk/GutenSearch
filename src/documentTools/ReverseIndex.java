package documentTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import storage.DocumentStorage;

/**
 * ReverseIndex - Stores index of index terms matched to a Paragraph within a
 * Document.
 * 
 * @author Team J - Joey Arbogast
 * @version 1.0
 */
final class ReverseIndex
{
	// Current working Document number
	private int docNum;
	
	// All index terms
	private List<String> indexTerms;
	
	// Ordered pairs of Document number and paragraph occurrences
	private List<TermPair> paragraphsDocNums;
	
	private Map<String, List<TermPair>> reverseIndex;
	
	/**
	 * Constructor - Populates the index terms list.
	 */
	public ReverseIndex()
	{
		indexTerms = new ArrayList<>();
		reverseIndex = new HashMap<>();
		
		// Load in list of documents in library
		List<Document> docs = DocumentStorage.loadDocuments();
		
		// Get index terms list for each Document/Paragraph
		for (int i = 0; i < docs.size(); i++)
		{
			for (int x = 0; x < docs.get(i).getParagraphs().size(); x++)
			{
				indexTerms.addAll(docs.get(i).getParagraphs().get(x).getIndexTerms());
			}
		}
		
		// Create HashSet to remove duplicate terms from indexTerms list
		Set<String> noDuplicateIndexes = new HashSet<>();
		noDuplicateIndexes.addAll(indexTerms);
		indexTerms.clear();
		indexTerms.addAll(noDuplicateIndexes);
		
		docs = null;
		noDuplicateIndexes = null;
		
		// Calculate occurrence of terms
		this.calculateOccurrences();
	}
	
	/**
	 * calculateOccurrences - Calculates the document number and paragraph numbers
	 * which contain a term.
	 */
	private void calculateOccurrences()
	{
		String term;
		TermPair pair;
		
		List<Document> docs = DocumentStorage.loadDocuments();
		
		// Loop through each term in the indexTerms list
		for (int n = 0; n < indexTerms.size(); n++)
		{
			term = indexTerms.get(n);
			
			// Create a new list of ordered pairs for the current term
			paragraphsDocNums = new ArrayList<>();
			
			// Loop through each document
			for (int i = 0; i < docs.size(); i++)
			{
				docNum = i + 1;
				Document d = docs.get(i);
				
				// Loop through the list of paragraphs for the current document
				for (int j = 0; j < d.getParagraphs().size(); j++)
				{
					Paragraph p = d.getParagraph(j);
					
					// Get index terms from current paragraph and store
					List<String> indexesList = p.getIndexTerms();
					
					// Compare current term to each term in paragraph's index terms list
					for (String index : indexesList)
					{
						if (term.equals(index))
						{
							// Construct TermPair object with the current docNum and paragraph
							pair = new TermPair(docNum, p.getParagraphNum());
							
							// Add ordered pair to list of occurrences
							paragraphsDocNums.add(pair);
						}
					}
				}
			}
			
			this.createReverseIndex(term, paragraphsDocNums);
		}
		
		indexTerms.clear();
		indexTerms = null;
		paragraphsDocNums.clear();
		paragraphsDocNums = null;
	}
	
	/**
	 * createReverseIndex - Inserts term and ordered pair list of occurrences into
	 * Map.
	 * 
	 * @param term index term to map
	 * @param term
	 * @param pair list of occurrences of a term
	 */
	private void createReverseIndex(String term, List<TermPair> pair)
	{
		reverseIndex.put(term, pair);
	}
	
	/**
	 * getReverseIndex - Returns the HashMap of the reverse index.
	 * 
	 * @return Map<String, List<TermPair>> the reverse index Map
	 */
	public Map<String, List<TermPair>> getReverseIndex()
	{
		return new HashMap<String, List<TermPair>>(reverseIndex);
	}
}
