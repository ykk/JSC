package simulation.db.commands;

import simulation.db.*;

/** Interface for all SQL commands.
 * Command to be executed via {@link SQLExecute}
 * @author ykk
 */
public interface SQLCommand
{
    //Members
    /** Types of query -- Query with results.
     */
    public static final int queryCmd = 0;
    /** Types of query -- Query to manipulate data.
     */
    public static final int updateCmd = 1;

    //Methods
    /** Return SQL query string.
     * @return query string
     */
    public String sqlQuery();

    /** Return type of query.
     * @return type of query
     */
    public int queryType();

    /** Run query.
     * @param db reference to database
     * @return success of query
     */
    public boolean run(Database db);
}
