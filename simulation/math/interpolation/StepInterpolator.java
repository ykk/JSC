package simulation.math.interpolation;

import simulation.files.images.*;
import java.util.*;

/** Class to "interpolate" be giving the nearest value.
 * @author ykk
 */
public class StepInterpolator
    extends Interpolator
{
    /** Constructor
     * @param data vector of {@link Positionable}
     */
    public StepInterpolator(Vector data)
    {
	super(data);
    }
    
    /** Find nearest point, in terms of x.
     * @param x x value to use
     * @return index of smaller nearest pint.
     */
    public int nearest(double x)
    {
	int resultIndex = -1;
	double diff = Double.MAX_VALUE;

	for (int i = 0; i < data.size(); i++)
	    if (Math.abs(((Positionable) data.get(i)).x() - x) < diff)
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
    public double value(double inputValue)
    {
	int index = nearest(inputValue);
	return ((Positionable) data.get(index)).y();
    }
}