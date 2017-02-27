package documentTools;

/**
 * TermPair - Stores an ordered pair for document number and paragraph number.
 * 
 * @author Team J - Joey Arbogast
 * @version 1.0
 */
public class TermPair
{
	private int docNum;
	private int paraNum;
	
	/**
	 * Constructor - Sets Document and Paragraph numbers.
	 * 
	 * @param docNum Document number
	 * @param paraNum Paragraph number
	 */
	public TermPair(int docNum, int paraNum)
	{
		this.docNum = docNum;
		this.paraNum = paraNum;
	}
	
	/**
	 * getDocNum - Returns document number.
	 * 
	 * @return int document number
	 */
	public int getdocNum()
	{
		return this.docNum;
	}
	
	/**
	 * getParaNum - Returns paragraph number.
	 * 
	 * @return int paragraph number
	 */
	public int getParaNum()
	{
		return this.paraNum;
	}
}
