package simulation.gui;

import javax.swing.text.*;

/** Document to limit length of string.
 * @author http://www.rgagnon.com/javadetails/java-0198.html
 */
public class LimitDocument
    extends PlainDocument
{
    //Members
    /** Store limit.
     */
    public int limit;
  
    //Methods
    /** Constructor.
     * @param limit limit of text length
     */
    public LimitDocument(int limit)
    {
	super();
	this.limit = limit;
    }
 
    /** Inserts some content into the document.
     * Inserting content causes a write lock to be held 
     * while the actual changes are taking place, followed 
     * by notification to the observers on the thread that 
     * grabbed the write lock.
     * @param offset the starting offset >= 0
     * @param str the string to insert; does nothing with null/empty strings
     * @param attr the attributes for the inserted content
     */
    public void insertString(int offset, String  str, AttributeSet attr)
	throws BadLocationException
    {
	if (str == null) return;
	
	if ((getLength() + str.length()) <= limit)
	    super.insertString(offset, str, attr);
    }
}
