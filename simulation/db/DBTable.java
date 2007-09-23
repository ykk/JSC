package simulation.db;

import java.sql.*;

/** Abstract class for table in database.
 * @author ykk
 */
public abstract class DBTable
{
    /** Return string representing table.
     * @return string representing table
     */
    public abstract String tableString();
}