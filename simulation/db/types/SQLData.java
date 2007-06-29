package simulation.db.types;

/** Interface for all SQL data types.
 * @author ykk
 */
public interface SQLData
{
    //Methods
    /** Return string for SQL commands.
     * @return string for commands
     */
    public String toString();

    /** Indicate SQL data type number.
     * Should be declared as static.
     * @see java.sql.Types
     */
    public int typeNumber();
}