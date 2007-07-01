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
    /** Reference to result set.
     */
    public ResultSet rs = null;
    /** Reference to SQL statement.
     */
    protected Statement stmt = null;

    //Methods
    /** Run query.
     * @param db reference to database
     * @param command reference to SQL command
     */
    public SQLExecute(Database db, SQLCommand command)
    {
	try
	{
	    stmt = db.connection.createStatement();
	    rs = stmt.executeQuery(command.sqlQuery());
	} catch (SQLException sqlEx)
	{
	    throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
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
		throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
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
		throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
	    }
	    
	    stmt = null;
	}
    }
}