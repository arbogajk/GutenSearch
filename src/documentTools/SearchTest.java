package documentTools;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * SearchTest - Test cases for Search class.
 * 
 * @author Team J
 * @version 1.0
 */
public class SearchTest
{
	/**
	 * testSearch - tests the search method.
	 */
	@Test
	public void testSearch()
	{
		String term1="birds";
		String term2="Alice and rabits";
	
		Search search=new Search(term1);
		Search search2 = new Search(term2);
		
		assertEquals(term1,search.getSearchTerm());
		assertEquals(term2.toLowerCase(),search2.getSearchTerm());
	}
}
