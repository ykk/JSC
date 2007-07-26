package simulation.db.commands;

import simulation.db.*;
import java.sql.*;

/** Processor for SQL result set.
 * @author ykk
 */
public class SQLResultSetProcessor
{
    //Members
    /** Reference to result set.
     */
    protected ResultSet rs;
    /** Reference to metadata.
     */
    protected ResultSetMetaData metadata;

    //Methods
    /** Constructor.
     * @param rs reference to result set
     */
    public SQLResultSetProcessor(ResultSet rs)
    {
	this.rs = rs;
	try
	{
	    this.metadata = rs.getMetaData();
	} catch (SQLException sqlEx)
	{
	    throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
	}
    }

    /** Return number of columns.
     * @return number of columns
     */
    public int colCount()
    {
	try
	{
	    return metadata.getColumnCount();
	} catch (SQLException sqlEx)
	{
	    throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
	}
	
    }
    
    /** Return number of rows.
     * @return number of rows.
     */
    public int rowCount()
    {
	int currRow, lastRow;
	try
	{
	    currRow = rs.getRow();
	    rs.last();
	    lastRow = rs.getRow();
	    rs.absolute(currRow);
	} catch (SQLException sqlEx)
	{
	    throw new RuntimeException(this+" encounters SQL exception "+sqlEx);
	}

	return lastRow;
    }
}
