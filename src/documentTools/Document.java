package documentTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import storage.DocumentStorage;

/**
 * Document - Imports files into the GutenSearch system. This class is for use
 * with the Admin UI.
 *
 * @author Team J
 * @version 4.0
 */
public class Document extends Indexer implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// Reference to attributes used in the class
	private String author;
	private String filePath;
	private String message;
	private String title;
	
	private List<Paragraph> paragraphs;
	
	/**
	 * Constructor - Assigns title, author, and requested path for the file to be
	 * imported.
	 *
	 * @param title title of the document
	 * @param author author of the document
	 * @param filePath location of the document on local computer
	 */
	public Document(String title, String author, String filePath)
	{
		this.author = author;
		this.filePath = filePath;
		this.title = title;
		
		// Instantiates the ArrayList of paragraphs, reverseIndex and the message
		paragraphs = new ArrayList<>();
		message = "";
	}
	
	/**
	 * add - Adds Paragraph object ArrayList.
	 * 
	 * @param p Paragraph to add
	 */
	protected void add(Paragraph p)
	{
		paragraphs.add(p);
	}
	
	/**
	 * countParagraphs - Finds paragraph breaks in the document and gives them
	 * distinct values.
	 *
	 * @return int number of paragraphs
	 */
	protected int countParagraphs()
	{
		int count = 0; // Initialize counter
		
		try
		{
			Paragraph para;
			
			File docToCount = this.getFile();
			Scanner in = new Scanner(docToCount);
			StringBuilder strBuilder = new StringBuilder();
			
			// Read in document
			while (in.hasNextLine())
			{
				String line = in.nextLine(); // Store the line of text
				
				// Check if the String contains the words "Title:" or "Author:"
				if (line.contains("Title:") || line.contains("Author:"))
				{
					// Skip these lines
					in.nextLine();
				}
				// If the String is not empty, append the line of text to the
				// StringBuilder
				else if (!(line.equals("")))
				{
					strBuilder.append(line + "\n");
				}
				// If the String is blank...
				else
				{
					count++; // Increment counter, found a paragraph
					
					// Create a Paragraph, set index terms, and add to ArrayList
					para = new Paragraph(this.getTitle(), this.getAuthor(), this.getFilePath(), count,
							strBuilder.toString());
					para.setIndexTerms(para.getParagraphText());
					add(para);
					
					// Clear out the StringBuilder to build a new paragraph.
					strBuilder.delete(0, strBuilder.length());
				}
			}
			// If it's the end of the file then build a paragraph with what is in the
			// string builder
			if (!(in.hasNextLine()))
			{
				count++;
				// Create a Paragraph, set index terms, and add to ArrayList
				para = new Paragraph(this.getTitle(), this.getAuthor(), this.getFilePath(), count,
						strBuilder.toString());
				add(para);
				para.setIndexTerms(para.getParagraphText());
				// Clear out the StringBuilder to build a new paragraph.
				strBuilder.delete(0, strBuilder.length());
			}
			
			in.close();
		}
		catch (FileNotFoundException e)
		{
			message = e.getMessage();
		}
		
		return count;
	}
	
	/**
	 * equals - Compares Document objects.
	 * 
	 * @param doc Document to compare
	 * @return boolean true if equal
	 */
	public boolean equals(Document doc)
	{
		// Check if the title, author and list of paragraphs are equal to this
		// Document
		return this.getTitle().equals(doc.getTitle()) && (this.getAuthor().equals(doc.getAuthor())
			&& (this.getParagraphs().containsAll(doc.getParagraphs())));
	}
	
	/**
	 * getAuthor - Returns current document author.
	 *
	 * @return String current document author
	 */
	public String getAuthor()
	{
		return this.author;
	}
	
	/**
	 * getFile - Returns a File object with the document file path.
	 *
	 * @return File current Document object
	 * @throws FileNotFoundException iff user specified file cannot be located
	 */
	public File getFile() throws FileNotFoundException
	{
		return new File(this.getFilePath());
	}
	
	/**
	 * getFilePath - Returns current location of the document.
	 * 
	 * @return String current document location
	 */
	public String getFilePath()
	{
		return this.filePath;
	}
	
	/**
	 * getMessage - Displays a message to the admin.
	 *
	 * @return String message displayed
	 */
	public String getMessage()
	{
		return this.message;
	}
	
	/**
	 * getParagraph - Returns specific paragraph by location in document from the
	 * list of paragraphs in the document.
	 * 
	 * @param paragraphNum location of Paragraph to return
	 * @return Paragraph Paragraph object and ordinal number of specified location
	 */
	public Paragraph getParagraph(int paragraphNum)
	{
		return paragraphs.get(paragraphNum);
	}
	
	/**
	 * getParagraphs - Returns a list of Paragraph objects.
	 * 
	 * @return List<Paragraph> ArrayList of Paragraphs
	 */
	public List<Paragraph> getParagraphs()
	{
		return this.paragraphs;
	}
	
	/**
	 * getTitle - Returns current document title.
	 *
	 * @return String current document title
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
	 * hashCode - Required when overriding equals().
	 * 
	 * @return 0
	 */
	public int hashCode()
	{
		return 0;
	}
	
	/**
	 * setAuthor - Assigns current document author.
	 *
	 * @param author author of the document
	 */
	protected void setAuthor(String author)
	{
		this.author = author;
	}
	
	/**
	 * setFilePath - Assigns location of the document.
	 * 
	 * @param filePath location of the document
	 */
	protected void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	/**
	 * setTitle - Assigns current document title.
	 *
	 * @param title title of the document
	 */
	protected void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * storeFile - Copies text from source document to a new file.
	 * 
	 * @param doc Document object to be stored
	 * @throws FileNotFoundException iff user specified file cannot be located
	 * @throws TitleAuthorExtractionException if title and/or author cannot be
	 *         extracted
	 */
	public void storeFile(Document doc)
			throws FileNotFoundException, TitleAuthorExtractionException
	{
		// If title and author are both unassigned...
		if ((title != null && author != null) && (!(title.equals("")) && (!(author.equals("")))))
		{
			// Create and read in new file
			File fileOut = new File(title);
			PrintWriter out = new PrintWriter(fileOut);
			Scanner in = new Scanner(new File(filePath));
			
			while (in.hasNextLine())
			{
				out.println(in.nextLine());
			}
			
			in.close();
			out.close();
			
			// Set the file path to the location of the new document
			this.setFilePath(fileOut.getAbsolutePath());
		}
		else
		{
			Scanner in = new Scanner(new File(filePath));
			String line = in.nextLine();
			
			while (in.hasNextLine())
			{
				// Set title if unassigned
				if ((title == null || title.equals("")) && line.contains("Title:"))
				{
					this.setTitle(line.substring(6, line.length()).trim());
					this.storeFile(this);
				}
				// Set author if unassigned
				else if ((author == null || author.equals("")) && line.contains("Author:"))
				{
					this.setAuthor(line.substring(7, line.length()).trim());
					this.storeFile(this);
				}
				
				line = in.nextLine();
			}
			
			in.close();
		}
		
		// Error if title or author was not extracted and not specified
		if (getTitle().equals("") || (author.equals("")))
		{
			throw new TitleAuthorExtractionException();
		}
		else
		{
			message = "Document Successfully Uploaded!";
		}
		
		// Count Paragraphs and store document in library collection
		this.countParagraphs();
		DocumentStorage.storeDocuments(this);
	}
	
	/**
	 * toString - Displays document title and author.
	 * 
	 * @return String document title and author
	 */
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Title: " + this.getTitle() + "\n");
		builder.append("Author: " + this.getAuthor() + "\n");
		
		return builder.toString();
	}
}
