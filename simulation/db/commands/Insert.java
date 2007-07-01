package simulation.db.commands;

import simulation.db.*;

/** Insert command.
 * @author ykk
 */
public class Insert
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
    public String sqlQuery()
    {
	return "INSERT INTO "+tableName+
	    " VALUES ("+values.toString()+");";
    }

    public boolean run(Database db)
    {
	return true;
    }
}