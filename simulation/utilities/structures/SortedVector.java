package simulation.utilities.structures;

import java.util.*;
import simulation.utilities.structures.*;

/** Vector class extended to maintain a sorted collection.
 * @author ykk
 */
public class SortedVector
    extends Vector
{
    /** Get index for object.
     * The index points to the first object that is largest than the object.
     * @param object object to get index for
     */
    public int index(Comparable object)
    {
	Comparable obj;
	int min = 0;
	int max = this.size();

	while (min != max)
	{
	    obj = (Comparable) this.get((int) Math.floor((min+max)/2));
	    if (object.compareTo(obj) >= 0)
		min = ((int) Math.floor((min+max)/2))+1;
	    else
		max = (int) Math.floor((min+max)/2);
	}

	return min;
    }
    
    /** Add comparable object into sorted collection
     * @param object object to insert
     */
    public void add(Comparable object)
    {
	super.add(index(object),object);
    }

    /** Test function demonstrating sorted vector.
     */
    public static void main(String[] args)
    {
	SortedVector testSV = new SortedVector();
	Double testValue;

	for (int i = 0; i < 10; i++)
	{
	    testValue = new Double(Math.floor(Math.random()*10)/10);
	    System.out.println(testSV.index(testValue)+"-"+testValue);
	    testSV.add(testValue);
	    System.out.println(testSV);
	}
    }
}
	
