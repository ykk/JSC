package simulation.math;

import java.util.*;

/** Class to tabulate Binomial coefficients, using Pascal triangle.
 * @author ykk
 */
public class Binomial
{
    //Methods
    /** Tabulate Binomial coefficient of m choose n.
     * @param m number of elements to choose from
     * @param n number of elements to choose
     * @return Binomial coefficient of m choose n
     */
    public long coefficient(int m, int n)
    {
	return 0;
    }

    /** Tabulate line m of Pascal triangle.
     * @param m number of the line
     * @return vector of long with values of line m of Pascal triangle
     */
    public Vector lineOfPascal(int m)
    {
	Vector result = new Vector();
	result.add(new Long(1));

	//Check for valid m
	if (m < 0)
	    throw new RuntimeException("There is no line "+m+" for the Pascal triangle");

	//Boundary cases
	if (m == 0)
	    return result;
	else if (m == 1)
	{
	    result.add(new Long(1));
	    return result;
	}

	Vector prevLine = lineOfPascal(m-1);
	long tmpLong;
	for (int i = 0; i < m-1; i++)
	{
	    tmpLong = ((Long) prevLine.get(i)).longValue() +
		((Long) prevLine.get(i+1)).longValue();
	    if (tmpLong < 0) throw new RuntimeException("long value "+m+" "+tmpLong);
	    result.add(new Long(tmpLong));
	}
	result.add(new Long(1));

	return result;
    }

    public static void main(String[] args)
    {
	Binomial t = new Binomial();

	for (int i = 0; i < 100; i ++)
	    System.out.println(t.lineOfPascal(i));
    }
}