package simulation.db;

import java.sql.*;

/** Abstract class for database.
 * @author ykk
 */
public abstract class Database
{
    //Members
    /** Name of database.
     */
    protected String dbName;
    /** Port Number.
     * Default to unspecified at -1.
     */
    protected int portNo = -1;
    /** Name of server.
     * Default to localhost.
     */
    protected String serverName = "locahost";
    /** Username.
     */
    protected String username;
    /** Password.
     * Deafult to null.
     */
    protected String passwd = null;
    /** Reference to database connection.
     */
    public Connection connection;

    //Methods
    /** Constructor.
     * @param dbName name of database
     * @param username name of user
     * @param passwd password
     * @param portNo port number
     * @param serverName name of server
     */
    public Database(String dbName, String username, String passwd, 
		    int portNo, String serverName)
    {
	this.dbName = dbName;
	this.username = username;
	this.passwd = passwd;
	this.portNo = portNo;
	this.serverName = serverName;

	connection = connect();
    }

    /** Constructor.
     * @param dbName name of database
     * @param username name of user
     * @param passwd password
     * @param serverName name of server
     */
    public Database(String dbName, String username, String passwd,
		    String serverName)
    {
	this.dbName = dbName;
	this.username = username;
	this.passwd = passwd;
	this.serverName = serverName;

	connection = connect();
    }

    /** Constructor.
     * @param dbName name of database
     * @param username name of user
     * @param passwd password
     */
    public Database(String dbName, String username, String passwd)
    {
	this.dbName = dbName;
	this.username = username;
	this.passwd = passwd;

	connection = connect();
    }

    /** Constructor.
     * @param dbName name of database
     * @param username name of user
     */
    public Database(String dbName, String username)
    {
	this.dbName = dbName;
	this.username = username;

	connection = connect();
    }

    /** Connect to database.
     * @return reference to database connection
     */
    protected abstract Connection connect();

    /** Close connection to database.
     */
    public void close()
    {
	try
	{
	    connection.close();
	} catch (SQLException ex)
	{
            throw new RuntimeException("SQLException: " + ex.getMessage()+
				       "\nSQLState: " + ex.getSQLState()+
				       "\nVendorError: " + ex.getErrorCode());
        }
    }

    /** Test function.
     * @param db database reference
     */
    public static void test(Database db)
    {
	

	try
	{
	    db.connection.close();
        } catch (SQLException ex)
	{
            throw new RuntimeException("SQLException: " + ex.getMessage()+
				       "\nSQLState: " + ex.getSQLState()+
				       "\nVendorError: " + ex.getErrorCode());
        }
    }
}