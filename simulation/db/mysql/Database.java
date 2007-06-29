package simulation.db.mysql;

import java.sql.*;

/** Class for MySQL database.
 * @author ykk
 */
public class Database
    extends simulation.db.Database
{
    //Methods
    public Database(String dbName, String username, String passwd, 
		    int portNo, String serverName)
    {
	super(dbName, username, passwd, portNo, serverName);
    }

    public Database(String dbName, String username, String passwd,
		    String serverName)
    {
	super(dbName, username, passwd, serverName);
    }

    public Database(String dbName, String username)
    {
	super(dbName, username);
    }

    public Database(String dbName, String username, String passwd)
    {
	super(dbName, username, passwd);
    }

    protected Connection connect()
    {
        try 
	{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) 
	{
            throw new RuntimeException(this+" encourters error "+ex);
        }
	try
	{
            return DriverManager.getConnection(url());
        } catch (SQLException ex)
	{
            throw new RuntimeException("SQLException: " + ex.getMessage()+
				       "\nSQLState: " + ex.getSQLState()+
				       "\nVendorError: " + ex.getErrorCode());
        }
    }

    /** Create url string.
     * @return url string
     */
    public String url()
    {
	String output = "jdbc:mysql://";

	output += serverName;
	if (portNo != -1) output += ":"+portNo;
	output += "/"+dbName;
	output += "?user="+username;
	if (passwd != null) output+= "&password="+passwd;

	return output;
    }

    /** Test function.
     * @see #test(Database db)
     * @param args 1st argument is server name
     *             2nd argument is database name
     *             3rd argument is user name
     *             4th argument is password
     */
    public static void main(String[] args)
    {
	test(new Database(args[1], args[2], args[3], args[0]));
    }
}