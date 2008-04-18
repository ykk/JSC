package simulation.db;

/** Class for condition in query statement.
 * @author ykk
 */
public class Condition
{
    //Members
    /** Condition string.
     */
    public String condition = null;

    //Methods
    /** Constructor.
     */
    public Condition()
    {
    }

    /** Constructor.
     * @param condition condition string
     */
    public Condition(String condition)
    {
	this.condition = condition;
    }

    /** Constructor.
     * @param col column name
     * @param value value to have
     */
    public Condition(String col, String value)
    {
	this.condition = col+"='"+value+"'";
    }

    public String toString()
    {
	return condition;
    }
}