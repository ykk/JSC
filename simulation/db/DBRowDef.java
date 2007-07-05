package simulation.db;

import java.util.*;

/** Definition class for row in database.
 * Takes ordered set of types.
 * @author ykk
 */
public class DBRowDef
{
    //Members
    /** Vector of type indices.
     */
    public Vector types = new Vector();

    //Methods
    /** Add type index.
     * @param typeIndex index of type
     */
    public void add(int typeIndex)
    {
	types.add(new Integer(typeIndex));
    }

    /** Return type index of specified column. 
     * @param index column index of row
     * @return integer representing types
     */
    public int get(int index)
    {
	return ((Integer) types.get(index)).intValue();
    }
}