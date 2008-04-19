package simulation.db.commands;

import simulation.db.*;
import java.util.*;

/** Insert command.
 * @author ykk
 */
public class Insert
    implements SQLCommand
{
    //Members
    /** Debug flag.
     * Default to false.
     */
    public boolean debug = false;
    /** Name of table.
     */
    public DBTable tableName;
    /** Values to insert.
     */
    public DBRow values;
    /** Columns to insert.
     */
    public Vector cols;

    //Methods
    /** Constructor.
     * @param table name of table
     * @param row reference of row to insert
     */
    public Insert(DBTable table, DBRow row)
    {
	tableName = table;
	values = row;
	cols = null;
    }

    /** Constructor.
     * @param table name of table
     * @param row reference of row to insert
     * @param cols columns to insert values to
     */
    public Insert(DBTable table, DBRow row, Vector cols)
    {
	tableName = table;
	values = row;
	this.cols = cols;
    }

    public String sqlQuery()
    {
	String fullTableName = tableName.tableString();
	if (cols != null)
	{
	    fullTableName += " (";
	    for (int i = 0; i < cols.size(); i++)
		fullTableName += cols.get(i).toString()
		    + ((i == (cols.size()-1))?")":",");
	}
	return "INSERT INTO "+fullTableName+
	    " VALUES ("+values.rowString()+");";
    }

    public int queryType()
    {
	return updateCmd;
    }

    public boolean run(Database db)
    {
	if (debug) System.out.println(sqlQuery());
	SQLExecute execute = new SQLExecute(db, this);
	if (debug) System.out.println("Run error: "+execute.err);

	execute.close();
	return (execute.err == null);
    }
}
