package simulation.db.commands;

import simulation.db.*;
import java.sql.*;

/** Select command.
 * @author ykk
 */
public class Select
    implements SQLCommand
{
    //Members
    /** Debug flag.
     * Default to false.
     */
    public boolean debug = false;
    /** Name of table.
     * Default to null, i.e., no table referenced.
     */
    public String tableName = null;
    /** Select expression.
     */
    public String expr;
    /** Where condition.
     * Default to null, i.e., no condition.
     */
    public String condition = null;
    /** Ordering.
     * Default to null, i.e., no ordering.
     */
    public String order = null;
    /** Result in array of {@link DBRow}.
     */
    public DBRow[] result;

    //Methods
    public String sqlQuery()
    {
	String output = "SELECT "+expr;
	if (tableName != null) output +=  " FROM "+tableName;
	if (condition != null) output +=  " WHERE "+condition;
	if (order != null) output +=  " ORDER BY "+order;

	return output;
    }

    public int queryType()
    {
	return queryCmd;
    }

    public boolean run(Database db)
    {
	if (debug) System.out.println(sqlQuery());
	SQLExecute execute = new SQLExecute(db, this);
	int colCount = execute.rsProc.colCount();
	int rowCount = execute.rsProc.rowCount();
	result = new DBRow[rowCount];

	try
	{
	    execute.rs.first();
	    while (true)
	    {
		for (int i = 0; i < rowCount; i++) 
		{
		    result[i] = new DBRow();
		    result[i].rowDef = execute.rsProc.rowDef;
		    for (int j = 0; j < colCount ; j++)
			result[i].add(execute.rs.getObject(j+1));
		}

		if (execute.rs.isLast())
		{
		    execute.close();
		    return true;
		}

		execute.rs.next();
	    }
	} catch (SQLException sqlEx)
	{
	    throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
	}
    }
}
