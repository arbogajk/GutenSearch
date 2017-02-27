package documentTools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Test;
/**
 * ParagraphTest - Test cases for Paragraph class.
 * 
 * @author Team J
 * @version 1.0
 */
public class ParagraphTest
{
	/**
	 * testGetIndexTerms - Tests the method getIndexTerms().
	 */
	@Test
	public void testGetIndexTerms()
	{
		int paraNum = 2;
		
		String author = "Joey";
		String filepath = "src/Alice In Wonderland";
		String title = "Alice In Wonderland";
		
		String text = "This is a paragraph text.  There is some text here.\n"
						+ "Some more text here on a new line.  There is more text.\n"
						+ "This is the final line of text for the paragraph.\n";
				
		Paragraph p1 = new Paragraph(title, author, filepath, paraNum, text);
		p1.setIndexTerms(p1.getParagraphText());
		
		List<String> expectedIndexTerms = new ArrayList<>();
		
		expectedIndexTerms.add("paragraph");
		expectedIndexTerms.add("text");
		expectedIndexTerms.add("text");
		expectedIndexTerms.add("text");
		expectedIndexTerms.add("new");
		expectedIndexTerms.add("line");
		expectedIndexTerms.add("text");
		expectedIndexTerms.add("final");
		expectedIndexTerms.add("line");
		expectedIndexTerms.add("text");
		expectedIndexTerms.add("paragraph");
		
		Set<String> noDuplicateTerms = new HashSet<>();
		noDuplicateTerms.addAll(expectedIndexTerms);
		
		expectedIndexTerms.clear();
		expectedIndexTerms.addAll(noDuplicateTerms);
		
		List<String> actualTerms = p1.getIndexTerms();
		
		for (int i = 0; i < p1.getIndexTerms().size(); i++)
		{
			assertEquals("getIndexTerms()", expectedIndexTerms.get(i), actualTerms.get(i));
		}
	}

	/**
	 * testParagraphGetters - Tests getting paragraphs.
	 */
	@Test
	public void testParagraphGetters()
	{
		int paraNum = 2;
		
		String author = "Joey";
		String filepath = "src/Alice In Wonderland";
		String title = "Alice In Wonderland";
		
		String text = "This is a paragraph test.  There is some text here.\n"
						+ "Some more text here on a new line.  There is more text.\n"
						+ "This is the final line of text for the paragraph.\n";
				
		Paragraph p1 = new Paragraph(title, author, filepath, paraNum, text);
		
		assertEquals("getTitle()", title, p1.getTitle());
		assertEquals("getAuthor()", author, p1.getAuthor());
		assertEquals("getParagraphNum()", paraNum, p1.getParagraphNum());
		assertEquals("getParagraphText()", text, p1.getParagraphText());
	}
}
