package simulation.db;

import java.sql.*;

/** Class for table in database.
 * @author ykk
 */
public class DBTable
{
    //Members
    /** String holding table name.
     */
    private String tableName;

    //Methods
    /** Constructor.
     */
    public DBTable()
    {
    }

    /** Constructor.
     * @param tableName string holding table name
     */
    public DBTable(String tableName)
    {
	this.tableName = tableName;
    }

    /** Return string representing table.
     * @return string representing table
     */
    public String tableString()
    {
	return tableName;
    }
}