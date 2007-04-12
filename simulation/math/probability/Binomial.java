package simulation.math.probability;

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
    public double coefficient(int m, int n)
    {
	//Check for valid n
	if (n > m)
	    throw new RuntimeException("n must be less than or equal to m, m="+m+" & n="+n+" is not valid.");

	//Check for valid n
	if (n < 0)
	    throw new RuntimeException("n must be greater or equal to 0, n="+n+" is not valid.");

	Vector pascalLine = lineOfPascal(m);

	return ((Double) pascalLine.get(n)).doubleValue();
    }

    /** Tabulate line m of Pascal triangle.
     * @param m number of the line
     * @return vector of double with values of line m of Pascal triangle
     */
    public Vector lineOfPascal(int m)
    {
	Vector result = new Vector();
	result.add(new Double(1));

	//Check for valid m
	if (m < 0)
	    throw new RuntimeException("There is no line "+m+" for the Pascal triangle.");

	//Boundary cases
	if (m == 0)
	    return result;
	else if (m == 1)
	{
	    result.add(new Double(1));
	    return result;
	}

	Vector prevLine = lineOfPascal(m-1);
	double tmpDouble;
	for (int i = 0; i < m-1; i++)
	{
	    tmpDouble = ((Double) prevLine.get(i)).doubleValue() +
		((Double) prevLine.get(i+1)).doubleValue();
	    if (tmpDouble < 0) throw new RuntimeException("double value "+m+" "+tmpDouble);
	    result.add(new Double(tmpDouble));
	}
	result.add(new Double(1));

	return result;
    }
}