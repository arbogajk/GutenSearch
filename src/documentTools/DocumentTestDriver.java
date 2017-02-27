package documentTools;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import storage.DocumentStorage;

/**
 * DocumentTestDriver - Tests functionality of Document and Document Storage
 * classes.
 *
 * @author Team J
 * @version 2.0
 */
public class DocumentTestDriver
{
	
	/**
	 * main - The main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		// Create new test documents and specify source
		Document doc = new Document("test", "joey", "src/Alice In Wonderland");
		Document d2 = new Document("test2", "henry", "src/Alice In Wonderland");
		Document d3 = new Document("test3", "bob", "src/The test file");
		Document d4 = new Document("", "", "src/Domestic Birds");
		
		try
		{
			// Attempt to store files in library
			doc.storeFile(doc);
			d2.storeFile(d2);
			d3.storeFile(d3);
			d4.storeFile(d4);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
		
		// Display contents of library
		Paragraph p = d4.getParagraph(1);
		System.out.println(p.toString());
		ArrayList<Paragraph> paragraphs = (ArrayList<Paragraph>) d4.getParagraphs();
		
		for (Paragraph p2 : paragraphs)
		{
			System.out.println(p2.toString());
			System.out.println(p2.getIndexTerms());
		}
		
		// Tests document loading
		List<Document> documentsList = DocumentStorage.loadDocuments();
	
		for (Document d : documentsList)
		{
			System.out.println(d.toString());
			System.out.println(d.getParagraphs().toString());
		}
		
		ReverseIndex ri = new ReverseIndex();
		System.out.println(ri.getReverseIndex());
		
		Search search = new Search("birds");
		List<Paragraph> results = search.getResults();
		
		if (results.isEmpty())
		{
			System.out.println("No results");
		}
		
		for (Paragraph theParagraphs : results)
		{
			System.out.println(theParagraphs.toString());
		}
	}
	
}
