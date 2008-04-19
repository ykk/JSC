package simulation.db;

import java.util.*;

/** Class for AND condition in query statement.
 * @author ykk
 */
public class AndCondition
    extends Condition
{
    //Members
    /** Condition string, which is formed after calling toString.
     * @seee #toString()
     */
    public String condition;
    /** Joining string.
     */
    protected String joinStr = " AND ";
    /** Vector of conditions.
     */
    public Vector conditions = new Vector();

    //Methods
    /** Constructor.
     * @param cond1 condition 1
     * @param cond2 condition 2
     */
    public AndCondition(Condition cond1, Condition cond2)
    {
	conditions.add(cond1);
	conditions.add(cond2);
    }

    /** Constructor.
     * @param conds conditions
     */
    public AndCondition(Vector conds)
    {
	conditions = conds;
    }    

    public String toString()
    {
	condition = "(";
	for (int i = 0; i < conditions.size(); i++)
	    condition += conditions.get(i) + 
		((i == (conditions.size()-1))?"":joinStr);
	condition += ")";
	return condition;
    }
}