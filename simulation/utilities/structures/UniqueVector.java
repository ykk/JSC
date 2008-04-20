package simulation.utilities.structures;

import java.util.*;
import simulation.utilities.structures.*;

/** Sorted vector class extended to maintain an unique collection.
 * @author ykk
 */
public class UniqueVector
    extends SortedVector
{
    /** Add comparable object into sorted collection if not found
     * @param object object to insert
     */
    public void add(Comparable object)
    {
	int i = index(object);
	if (i != 0)
	{
	    Comparable inSet = (Comparable) super.get(i-1);
	    if (object.compareTo(inSet) == 0)
		return;
	}

	super.add(object);
    }
}