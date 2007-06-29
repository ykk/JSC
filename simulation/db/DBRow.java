package simulation.db;

import java.util.*;

/** Class for row in database.
 * Takes ordered set of database types.
 * @author ykk
 */
public class DBRow
    extends Vector
{
    /** Return comma separated list of values.
     * @return string representation
     */
    public String toString()
    {
	String finalStr="";
	for (int i = 0; i < this.size(); i++)
	{
	    finalStr += this.get(i);
	    if (i != (size()-1)) finalStr += ",";
	}

	return finalStr;
    }
}