package simulation.utilities.structures;

/** Class to provide array functions.
 * @author ykk
 */
public class Array
{
    /** Returns index of object in the array.
     * Returns -1 if not found.
     * @param objects array of objects
     * @param object object to find in array
     * @return index of object in array; else -1
     */
    public static int indexOf(Object[] objects, Object object)
    {
	int i = 0;

	while ((objects[i] != object) && (i < objects.length))
	    i++;

	if (i >= objects.length)
	    return -1;
	else
	    return i;
    }
}
