package simulation.db.mysql;

import java.sql.*;

/** Class for row in MySQL database.
 * Takes ordered set of database types.
 * Uses {@link java.sql.Types} for type indices.
 * @author ykk
 */
public class DBRow
    extends simulation.db.DBRow
{
    public String objString(int dataIndex)
    {
	if (this.get(dataIndex) == null)
	    return "NULL";

	if (rowDef == null)
	    return "'"+super.objString(dataIndex)+"'";

	switch (rowDef.get(dataIndex))
	{
	default:
	    return "'"+this.get(dataIndex).toString()+"'";
	}
    }
}
