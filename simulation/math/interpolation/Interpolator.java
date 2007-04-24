package simulation.math.interpolation;

import simulation.files.images.*;
import java.util.*;

/** Abstract class to provide interpolation of functions.
 * @author ykk
 */
public abstract class Interpolator
{
    //Members
    /** Vector of {@link Positionable}
     */
    public Vector data;

    //Methods
    /** Constructor.
     * @param data vector of {@link Positionable}
     */
    public Interpolator(Vector data)
    {
	this.data = data;
    }

    /** Find smaller nearest point, in terms of x.
     * If none is smaller, return -1.
     * @param x x value to use
     * @return index of smaller nearest pint.
     */
    public int smallerNearest(double x)
    {
	int resultIndex = -1;
	double diff = Double.MAX_VALUE;

	for (int i = 0; i < data.size(); i++)
	    if (((Positionable) data.get(i)).x() < x &&
		(((Positionable) data.get(i)).x() - x) < diff)
	    {
		diff = ((Positionable) data.get(i)).x() - x;
		resultIndex = i;
	    }

	return resultIndex;
    }

    /** Find larger nearest point, in terms of x.
     * If none is smaller, return -1.
     * @param x x value to use
     * @return index of larger nearest pint.
     */
    public int largerNearest(double x)
    {
	int resultIndex = -1;
	double diff = Double.MAX_VALUE;

	for (int i = 0; i < data.size(); i++)
	    if (((Positionable) data.get(i)).x() > x &&
		(((Positionable) data.get(i)).x() - x) < diff)
	    {
		diff = ((Positionable) data.get(i)).x() - x;
		resultIndex = i;
	    }

	return resultIndex;
    }
    
    /** Function to interpolate values.
     * @param inputValue input value for interpolator
     * @return interpolated value
     */
    public abstract double value(double inputValue);
}