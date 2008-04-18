package simulation.db.commands;

import simulation.db.*;
import java.sql.*;

/** Class to execute SQL commands.
 * @see SQLCommand
 * @author ykk
 */
public class SQLExecute
{
    //Members
    /** Result for update commands, i.e., number of rows updated.
     */
    public int result;
    /** Reference to result set.
     */
    public ResultSet rs = null;
    /** Reference to result set processor.
     */
    public SQLResultSetProcessor rsProc = null;
    /** Reference to SQL statement.
     */
    protected Statement stmt = null;
    /** Error returned.
     * Null is no error.
     */
    public SQLException err = null;

    //Methods
    /** Run query.
     * @param db reference to database
     * @param command reference to SQL command
     */
    public SQLExecute(Database db, SQLCommand command)
    {
	try
	{
	    stmt = db.connection.
		createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
	    switch (command.queryType())
	    {
	    case SQLCommand.queryCmd:
		rs = stmt.executeQuery(command.sqlQuery());
		rsProc = new SQLResultSetProcessor(rs);
		break;
	    case SQLCommand.updateCmd:
		result = stmt.executeUpdate(command.sqlQuery());
		rs = null;
		rsProc = null;
		break;
	    default:
		throw new RuntimeException(this+
					   " receives unknown query type "+
					   command.queryType());
	    }
	} catch (SQLException sqlEx)
	{
	    err = sqlEx;
	}
    }

    /** Close statement and result set.
     */
    public void close()
    {
	if (rs != null)
	{
	    try
	    {
		rs.close();
	    } catch (SQLException sqlEx)
	    {
		throw new RuntimeException(this+" encounters SQL exception "
					   +sqlEx);
	    }

	    rs = null;
	}

	if (stmt != null)
	{
	    try
	    {
		stmt.close();
	    } catch (SQLException sqlEx)
	    {
		throw new RuntimeException(this+" encounters SQL exception "
					   +sqlEx);
	    }
	    
	    stmt = null;
	}
    }

    /** Run trial show tables in mysql.
     * @param args 1st argument is server name
     *             2nd argument is database name
     *             3rd argument is user name
     *             4th argument is password
     * @param cmd reference to command
     */
    public static void trial(String[] args, SQLCommand cmd)
    {
	Database db = new simulation.db.mysql.Database(args[1], args[2], 
						       args[3], args[0]);
	cmd.run(db);
	db.close();
    }
}
