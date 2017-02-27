package documentTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * DocumentTest - Test cases for Document class.
 * 
 * @author Team J
 * @version 1.0
 */
public class DocumentTest
{
	/**
	 * testCountParagraphs - Tests counting paragraphs.
	 */
	@Test
	public void testCountParagraphs()
	{
		int numOfParagraphs = 7;
		
		String author = "Joey";
		String filepath = "src/Alice's Adventures In Wonderland";
		String title = "Alice In Wonderland";
		
		Document doc = new Document(title, author, filepath);
		
		try
		{
			doc.storeFile(doc);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
		
		doc.countParagraphs();
		
		assertEquals("countParagraphs()", numOfParagraphs, doc.countParagraphs());
	}
	
	/**
	 * testGetFile - Tests retrieving the file for use in the program.
	 */
	@Test
	public void testGetFile()
	{
		String author = "Joey";
		String filepath = "src/Alice's Adventures In Wonderland";
		String title = "Alice In Wonderland";
		
		Document doc = new Document(title, author, filepath);
		
		try
		{
			doc.storeFile(doc);
			String expectedFilepath = "D:\\programming\\workspace"
							+ "\\GutenSearchv2.0\\Alice In Wonderland";
			File actualFile = doc.getFile();
			assertEquals("getFile()", expectedFilepath, actualFile.getAbsolutePath());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * testGetParagraph - Tests  retrieving paragraphs from a file. 
	 */
	@Test
	public void testGetParagraph()
	{
		int paragraphNum = 3;
		
		String author = "Lewis Carroll";
		String filepath = "src/Alice's Adventures In Wonderland";
		String title = "Alice In Wonderland";
		
		Document doc = new Document(title, author, filepath);
		
		String pText = "There was nothing so VERY remarkable in that; nor did Alice think it so\n"
						+ "VERY much out of the way to hear the Rabbit say to itself, 'Oh dear!\n"
						+ "Oh dear! I shall be late!' (when she thought it over afterwards, it\n"
						+ "occurred to her that she ought to have wondered at this, but at the time\n"
						+ "it all seemed quite natural); but when the Rabbit actually TOOK A WATCH\n"
						+ "OUT OF ITS WAISTCOAT-POCKET, and looked at it, and then hurried on,\n"
						+ "Alice started to her feet, for it flashed across her mind that she had\n"
						+ "never before seen a rabbit with either a waistcoat-pocket, or a watch\n"
						+ "to take out of it, and burning with curiosity, she ran across the field\n"
						+ "after it, and fortunately was just in time to see it pop down a large\n"
						+ "rabbit-hole under the hedge.\n";
				
		try
		{
			doc.storeFile(doc);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
		
		int expectedParaNum = 4;
		
		String expectedAuthor = author;
		String expectedFilepath = "D:\\programming\\workspace"
						+ "\\GutenSearchv2.0\\Alice In Wonderland";
		String expectedTitle = title;
		
		String expectedText = pText;
		
		assertEquals("getParagraph()-Title", expectedTitle,
						doc.getParagraph(paragraphNum).getTitle());
		assertEquals("getParagraph()-Author", expectedAuthor,
						doc.getParagraph(paragraphNum).getAuthor());
		assertEquals("getParagraph()-filepath", expectedFilepath,
						doc.getParagraph(paragraphNum).getFilePath());
		assertEquals("getParagraph()-Number", expectedParaNum,
						doc.getParagraph(paragraphNum).getParagraphNum());
		assertEquals("getParagraph()-Text", expectedText,
						doc.getParagraph(paragraphNum).getParagraphText());
	}
	
	/**
	 * testGetParagraphs - Tests getting multiple paragraphs.
	 */
	@Test
	public void testGetParagraphs()
	{
		String author = "Lewis Carroll";
		String filepath = "src/Alice's Adventures In Wonderland";
		String title = "Alice In Wonderland";
		
		Document doc = new Document(title, author, filepath);
		Document doc2 = new Document(title, author, filepath);
		
		try
		{
			doc.storeFile(doc);
			doc2.storeFile(doc);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
		
		List<Paragraph> paragraphs1 = doc.getParagraphs();
		List<Paragraph> paragraphs2 = doc2.getParagraphs();
		
		for (int i = 0; i < paragraphs1.size(); i++)
		{
			assertEquals("getParagraphs()", paragraphs1.get(i).getTitle(),
							paragraphs2.get(i).getTitle());
			assertEquals("getParagraph()-Author", paragraphs1.get(i).getAuthor(),
							paragraphs2.get(i).getAuthor());
			assertEquals("getParagraph()-Number", paragraphs1.get(i).getParagraphNum(),
							paragraphs2.get(i).getParagraphNum());
			assertEquals("getParagraph()-Text", paragraphs1.get(i).getParagraphText(),
							paragraphs2.get(i).getParagraphText());
		}
	}
	
	/**
	 * testReverseIndex - Tests indexing of terms.
	 */
	@Test
	public void testReverseIndex()
	{
		String author = "Lewis Carroll";
		String filepath = "src/Alice's Adventures In Wonderland";
		String title = "Alice In Wonderland";
		
		Document doc = new Document(title, author, filepath);
		Document doc2 = new Document(title, author, filepath);
		
		try
		{
			doc.storeFile(doc);
			doc2.storeFile(doc);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (TitleAuthorExtractionException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * testStoreFile - Tests creating a document file.
	 */
	@Test
	public void testStoreFile()
	{
		String author = "Joey";
		String filepath = "src/Alice's Adventures In Wonderland";
		String title = "Alice In Wonderland";
		
		Document doc = new Document(title, author, filepath);
		Document doc2 = new Document("", "", filepath);
		Document doc3 = new Document(null, null, filepath);
		
		try
		{
			doc.storeFile(doc);
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
		
		assertEquals("storeFile-with author", author, doc.getAuthor());
		assertEquals("storeFile-with Title", title, doc.getTitle());
		assertEquals("storeFile-blank author", "Lewis Carroll", doc2.getAuthor());
		assertEquals("storeFile-blank title", "Alice's Adventures in Wonderland", doc2.getTitle());
		assertEquals("storeFile-null author", "Lewis Carroll", doc3.getAuthor());
		assertEquals("storeFile-null title", "Alice's Adventures in Wonderland", doc3.getTitle());
	}
}
