package simulation.db.commands;

import simulation.db.*;
import java.sql.*;
import java.util.*;

/** Show tables, i.e., retrieve list of tables in database.
 * @author ykk
 */
public class ShowTables
    implements SQLCommand
{
    //Members
    /** Vector to hold table names.
     */
    public Vector tables = new Vector();

    //Methods
    public String sqlQuery()
    {
	return "SHOW TABLES";
    }

    public int queryType()
    {
	return queryCmd;
    }

    public boolean run(Database db)
    {
	if (debug) System.out.println(sqlQuery());
	SQLExecute execute = new SQLExecute(db, this);

	try
	{
	    execute.rs.first();
	    while (true)
	    {
		tables.add(execute.rs.getString(1));

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

    /** Run trial show tables in mysql.
     * @see SQLExecute#trial(String[] args, SQLCommand cmd)
     * @param args 1st argument is server name
     *             2nd argument is database name
     *             3rd argument is user name
     *             4th argument is password
     */
    public static void main(String[] args)
    {
	ShowTables table = new ShowTables();
	SQLExecute.trial(args, table);
	for (int i = 0; i < table.tables.size(); i++)
	    System.out.println(table.tables.get(i));
    }
}
