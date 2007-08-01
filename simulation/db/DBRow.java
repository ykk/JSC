package simulation.db;

import java.util.*;
import java.sql.*;

/** Class for row in database.
 * Takes ordered set of database types.
 * @author ykk
 */
public class DBRow
    extends Vector
{
    //Members
    /** Row definition.
     */
    public DBRowDef rowDef;

    //Methods
    /** Return comma separated list of string.
     * For use with {@link simulation.db.commands.Insert}.
     * @return string representation
     */
    public String rowString()
    {
	String result = "";

	for (int i = 0; i < size(); i++)
	{
	    result += objString(i);
	    if (i != (size()-1)) result += ",";
	}

	return result;
    }

    /** String convertor for types.
     * To handle null.
     * @param dataIndex index of object to convert into string
     * @return string representation of object for database
     */
    public String objString(int dataIndex)
    {
	return get(dataIndex).toString();
    }
}
