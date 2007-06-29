package simulation.db.commands;

import simulation.db.*;

/** Insert command.
 * @author ykk
 */
public class SQLInsert
    implements SQLCommand
{
    //Members
    /** Name of table.
     */
    public String tableName;
    /** Values to insert.
     */
    public DBRow values;

    //Methods
    public String queryString()
    {
	return "INSERT INTO "+tableName+
	    " VALUES ("+values.toString()+");";
    }

    public boolean run(Database db)
    {
	return true;
    }
}