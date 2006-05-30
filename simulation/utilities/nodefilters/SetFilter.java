package simulation.utilities.nodefilters;

import java.util.*;
/** Node filter to perform operations between set of nodes.
 * @author ykk
 */
public class SetFilter
{
    /** Return union of sets provided.
     * @param set1 first set of nodes
     * @param set2 second set of nodes
     * @return union of the sets provided
     */
    public static Vector union(Vector set1, Vector set2)
    {
	Vector result = (Vector) set1.clone();

	for (int i = 0; i < set2.size(); i++)
	    if (set1.indexOf(set2.get(i)) == -1)
		result.add(set2.get(i));

	return result;
    }

    /** Return intersection of sets provided.
     * @param set1 first set of nodes
     * @param set2 second set of nodes
     * @return intersection of the sets provided
     */
    public static Vector intersection(Vector set1, Vector set2)
    {
	Vector result = new Vector();

	for (int i = 0; i < set2.size(); i++)
	    if (set1.indexOf(set2.get(i)) != -1)
		result.add(set2.get(i));

	return result;
    }
    
    /** Minus set from the master set.
     * @param master master set of nodes
     * @param set set of nodes
     * @return master minus set
     */
    public static Vector minus(Vector master, Vector set)
    {
	Vector result = (Vector) master.clone() ;

	for (int i = 0; i < set.size(); i++)
	    if (master.indexOf(set.get(i)) != -1)
		result.remove(set.get(i));

	return result;
    }
}
