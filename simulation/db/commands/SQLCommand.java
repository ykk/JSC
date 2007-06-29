package simulation.db.commands;

import simulation.db.*;

/** Interface for all SQL commands.
 * @author ykk
 */
public interface SQLCommand
{
    //Methods
    /** Return SQL query string.
     * @return query string
     */
    public String queryString();

    /** Run query.
     * @param db reference to database
     * @return success of query
     */
    public boolean run(Database db);
}