package simulation.db.commands;

import simulation.db.*;

/** Show tables.
 * @author ykk
 */
public class ShowTables
    implements SQLCommand
{
    //Methods
    public String sqlQuery()
    {
	return "SHOW TABLES";
    }

    public boolean run(Database db)
    {
	return true;
    }

    /** Run trial show tables.
     * @param args 1st argument is 
     */
    public static void main(String[] args)
    {
	
    }
}