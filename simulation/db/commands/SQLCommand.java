package simulation.db.commands;

import simulation.db.*;

/** Interface for all SQL commands.
 * Command to be executed via {@link SQLExecute}
 * @author ykk
 */
public interface SQLCommand
{
    //Methods
    /** Return SQL query string.
     * @return query string
     */
    public String sqlQuery();

    /** Run query.
     * @param db reference to database
     * @return success of query
     */
    public boolean run(Database db);
}