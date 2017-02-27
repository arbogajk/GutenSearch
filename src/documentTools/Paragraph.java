package documentTools;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Paragraph - Extends Document class and stores indexing information about
 * paragraphs within a given document.
 * 
 * @author Team J
 * @version 3.0	
 */
public class Paragraph extends Document implements Serializable
{
	private static final long serialVersionUID = 2L;
	
	private int paragraphNum;
	private String paragraphText;
	private List<String> indexedTerms;
	
	/**
	 * Constructor - Calls the Document class constructor with the title, author,
	 * and filepath of the document, and sets the text and paragraph number
	 * attribute.
	 * 
	 * @param title current document title
	 * @param author current document author
	 * @param filePath current document file path
	 * @param paragraphNum ordinal number
	 * @param text text of the paragraph
	 */
	public Paragraph(String title, String author, String filePath, int paragraphNum, String text)
	{
		super(title, author, filePath);
		
		this.paragraphNum = paragraphNum;
		this.paragraphText = text;
		indexedTerms = new ArrayList<>();
	}
	
	/**
	 * addTerm - Adds a word to the list of index terms.
	 * 
	 * @param term word to add to the list
	 */
	protected void addTerm(String term)
	{
		indexedTerms.add(term);
	}
	
	/**
	 * equals - Compares paragraph objects and determines if they are equal.
	 * 
	 * @param para paragraph to compare with
	 * @return boolean true iff equal
	 */
	public boolean equals(Paragraph para)
	{
		boolean equal = false;
		
		// Check if current paragraph text equals argument
		if (this.getParagraphText().equals(para.getParagraphText())
						&& this.getParagraphNum() == para.getParagraphNum())
		{
			equal = true;
		}
		
		return equal;
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
	 * getIndexTerms - Returns the list of the index terms in the paragraph.
	 * 
	 * @return List<String> index terms
	 */
	public List<String> getIndexTerms()
	{
		return indexedTerms;
	}
	
	/**
	 * getParagraphNum - Returns paragraph ordinal number.
	 * 
	 * @return int paragraph ordinal number
	 */
	public int getParagraphNum()
	{
		return this.paragraphNum;
	}
	
	/**
	 * getParagraphText - Returns paragraph text.
	 * 
	 * @return String paragraph text
	 */
	public String getParagraphText()
	{
		return this.paragraphText;
	}
	
	/**
	 * setIndexTerms - splits the paragraph text into single words.
	 * 
	 * @param text paragraph text
	 */
	protected void setIndexTerms(String text)
	{
		StringBuilder strBuilder = new StringBuilder();
		
		// Loop through each character in the paragraph of text.
		for (int i = 0; i < text.length(); i++)
		{
			// If the text is a space, add the StringBuilder String to the list
			if ((Character.isSpaceChar(text.charAt(i))) && (strBuilder != null)
							|| text.charAt(i) == '\n')
			{
				if (strBuilder.length() != 0)
				{
					addTerm(strBuilder.toString().toLowerCase().trim());
					
					// Clear out the StringBuilder to prepare of another term
					strBuilder.delete(0, strBuilder.length());
				}
			}
			// If text is a letter or digit, add StringBuilder String to the list
			else if ((Character.isLetterOrDigit(text.charAt(i))) || (text.charAt(i) == '-')
							|| (text.charAt(i) == '\''))
			{			
				strBuilder.append(text.charAt(i));
			}
		}

		// Filters the indexTerms list using the stopList
		List<String> unFiltered = new ArrayList<>(indexedTerms);
		this.indexedTerms = this.findIndexes(unFiltered);
	}
	
	/**
	 * setParagraphNum - Sets paragraph ordinal number.
	 * 
	 * @param num paragraph ordinal number
	 */
	protected void setParagraphNum(int num)
	{
		paragraphNum = num;
	}
	
	/**
	 * setParagraphText - Sets paragraph text.
	 * 
	 * @param text paragraph text
	 */
	protected void setParagraphText(String text)
	{
		paragraphText = text;
	}
	
	/**
	 * toString - Displays title, author, ordinal number, and paragraph text.
	 * 
	 * @return String title, author, ordinal number, and paragraph text
	 */
	public String toString()
	{
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(super.toString());
		
		// Append the ordinal number and text
		strBuilder.append("Paragraph Number: " + this.getParagraphNum() + "\n");
		strBuilder.append("Paragraph: " + "\n" + this.getParagraphText());
		
		return strBuilder.toString();
	}
}
