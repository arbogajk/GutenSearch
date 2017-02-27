package documentTools;

/**
 * SuppressWarnings - Suppresses serial warnings.
 */
@SuppressWarnings("serial")

/**
 * TitleAuthorExtractionException - alerts user that they must manually specify
 * title and/or author to index the document.
 * 
 * @author Team J
 * @version 1.0
 */
public class TitleAuthorExtractionException extends Exception
{
	/**
	 * TitleAuthorExtractionException - Warns user that a manual Author and/or Title is 
	 * necessary.
	 */
	public TitleAuthorExtractionException()
	{
		super("Indexing Unsuccessful! \nManually specify an author/title.");
	}
	
}
