package documentTools;

/**
 * SuppressWarnings - Suppresses serial warnings.
 */
@SuppressWarnings("serial")

/**
 * InvalidFileTypeException - alerts user that they have attempted to index a
 * file type not supported by GutenSearch.
 * 
 * @author Team J
 * @version 1.0
 */
public class InvalidFileTypeException extends Exception
{
	/**
	 * Constructor - Assigns message to display.
	 */
	public InvalidFileTypeException()
	{
		super("Invalid file type! \nPlease enter a .txt file.");
	}
}
