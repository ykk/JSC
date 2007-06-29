package simulation.db.types;

/** Class for variable number of characters.
 * @author ykk
 */
public class VarChar
    implements SQLData
{
    //Members
    /** Maximum number of characters.
     * Default to 0 which is no restriction.
     */
    public int maxLength = 0;
    /** Reference to string.
     */
    public String stringRef;

    //Methods
    /** Constructor.
     * @param maxLength maximum length
     * @param string string to initialize to
     */
    public VarChar(int maxLength, String string)
    {
	this.maxLength = maxLength;
	stringRef = string;
    }

    /** Constructor.
     * @param maxLength maximum length
     */
    public VarChar(int maxLength)
    {
	this(maxLength, "");
    }

    /** Constructor.
     */
    public VarChar()
    {
	this(0, "");
    }

    public String toString()
    {
	if (maxLength != 0)
	    if (stringRef.length() > maxLength)
		throw new RuntimeException(this+" contains string "+stringRef+
					   " that exceeds max length "+maxLength);
	return "'"+stringRef+"'";
    }

    public int typeNumber()
    {
	return java.sql.Types.VARCHAR;
    }
}