package storage;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import documentTools.Document;
import documentTools.Paragraph;
import documentTools.TitleAuthorExtractionException;

/**
 * StorageTest - Test cases for DocumentStorage class.
 * 
 * @author Team J
 * @version 1.0
 */
public class StorageTest
{
	@Test
	public void testStoreLoadList()
	{
		Document doc1 = new Document("Alice In Wonderland", "Lewis Carroll",
				"src/Alice In Wonderland");
		Document doc2 = new Document("test file1", "Joey", "src/The test file");
		Document doc3 = new Document("Domestic Birds", "John H. Robinson", "src/Domestic Birds");
		
		try
		{
			doc1.storeFile(doc1);
			doc2.storeFile(doc2);
			doc3.storeFile(doc3);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
		
		List<Document> expectedList = new ArrayList<>();
		expectedList.add(doc1);
		expectedList.add(doc2);
		expectedList.add(doc3);
		
		List<Document> actualList = DocumentStorage.loadDocuments();
		
		for (int i = 0; i < actualList.size(); i++)
		{
			assertEquals("loadList()-Title", expectedList.get(i).getTitle(),
					actualList.get(i).getTitle());
			assertEquals("loadList()-Author", expectedList.get(i).getAuthor(),
					actualList.get(i).getAuthor());
		}
		
		for (int x = 0; x < actualList.size(); x++)
		{
			List<Paragraph> paragraphs = actualList.get(x).getParagraphs();
			
			for (int paraNum = 0; paraNum < paragraphs.size(); paraNum++)
			{
				assertEquals("loadList()-Paragraph Text",
						expectedList.get(x).getParagraph(paraNum).getParagraphText(),
						paragraphs.get(paraNum).getParagraphText());
			}
			
			for (int docNum = 0; docNum < actualList.size(); docNum++)
			{
				List<Paragraph> paragraphsActual = actualList.get(docNum).getParagraphs();
				List<Paragraph> paragraphsExpected = expectedList.get(docNum).getParagraphs();
				
				for (int paraIndex = 0; paraIndex < paragraphsActual.size(); paraIndex++)
				{
					List<String> paraActualTerms = paragraphsActual.get(paraIndex).getIndexTerms();
					List<String> paraExpectedTerms = paragraphsExpected.get(paraIndex).getIndexTerms();
					
					for (int termIndex = 0; termIndex < paraActualTerms.size(); termIndex++)
					{
						assertEquals("loadList() - Paragraph Index terms",
								paraExpectedTerms.get(termIndex), paraActualTerms.get(termIndex));
					}
				}
			}
		}
	}
}
