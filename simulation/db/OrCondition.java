package simulation.db;

import java.util.*;

/** Class for OR condition in query statement.
 * @author ykk
 */
public class OrCondition
    extends AndCondition
{
    //Members
    /** Joining string.
     */
    protected String joinStr = " OR ";

    //Methods
    /** Constructor.
     * @param cond1 condition 1
     * @param cond2 condition 2
     */
    public OrCondition(Condition cond1, Condition cond2)
    {
	super(cond1,cond2);
    }

    /** Constructor.
     * @param conds conditions
     */
    public OrCondition(Vector conds)
    {
	super(conds);
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