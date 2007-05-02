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

    /** Two dimensional array to one dimensioanl array.
     * @param dim2array two dimensional array
     * @return two dimensional array
     */
    public static double[] changeDim(double[][] dim2array)
    {
	double[] single = new double[dim2array.length*dim2array[0].length];

	for (int i = 0; i < dim2array.length; i++)
	    for (int j = 0; j < dim2array[0].length; j++)
		single[i*dim2array[0].length+j] = dim2array[i][j];

	return single;
    }

    /** Print array.
     * @param array array to print
     * @return string of array
     */
    public static String print(int[] array)
    {
	String outStr = "[";
	for (int i = 0; i < array.length; i++)
	{
		outStr += array[i];
		if (i != array.length-1)
		    outStr += "\t";
	}

	return outStr+"]";
    }

    /** Print array.
     * @param array array to print
     * @return string of array
     */
    public static String print(double[] array)
    {
	String outStr = "[";
	for (int i = 0; i < array.length; i++)
	{
		outStr += array[i];
		if (i != array.length-1)
		    outStr += "\t";
	}

	return outStr+"]";
    }

    /** Print array.
     * @param array array to print
     * @return string of array
     */
    public static String print(double[][] array)
    {
	String outStr = "[";
	for (int i = 0; i < array.length; i++)
	{
	    for (int j = 0; j < array[0].length; j++)
	    {
		outStr += array[i][j];
		if (j != array[0].length-1)
		    outStr += "\t";
	    }
	    if (i != array.length -1 ) 
		outStr += "\n";
	}

	return outStr+"]";
    }
}
