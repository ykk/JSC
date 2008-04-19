package simulation.db.commands;

import simulation.db.*;
import java.sql.*;

/** Update command.
 * @author ykk
 */
public class Update
    implements SQLCommand
{
    //Members
    /** Debug flag.
     * Default to false.
     */
    public boolean debug = false;
    /** Name of table.
     */
    public DBTable tableName;
    /** Set expression.
     */
    public String expr;
    /** Where condition.
     * Default to null, i.e., no condition.
     */
    public Condition condition = null;

    //Methods
    /** Constructor.
     * @param expr select expression
     * @param table database table
     * @param condition condition to apply
     */
    public Update(String expr, DBTable table, Condition condition)
    {
	this.expr = expr;
	tableName = table;
	this.condition = condition;
    }

    public String sqlQuery()
    {	
	String output = "UPDATE "+tableName.tableString()+
	    " SET "+expr;
	if (condition != null) output +=  " WHERE "+condition;
	return output;
    }

    public int queryType()
    {
	return updateCmd;
    }

    public boolean run(Database db)
    {
	if (debug) System.out.println(sqlQuery());
	SQLExecute execute = new SQLExecute(db, this);
	if (debug) System.out.println("Run error: "+execute.err);

	execute.close();
	return (execute.err == null);
    }
}