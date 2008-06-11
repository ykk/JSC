package simulation.utilities.structures;

import java.util.*;

/** Vector class extended to maintain an unique collection.
 * @author ykk
 */
public class UniqueVector
    extends Vector
{
    /** Add comparable object into sorted collection if not found
     * @param object object to insert
     */
    public void add(Comparable object)
    {
	boolean isNew = true;
	for (int i = 0; i < size(); i++)
	    if (object.compareTo(get(i)) == 0)
		isNew = false;

	if (isNew)
	    super.add(object);
    }
}