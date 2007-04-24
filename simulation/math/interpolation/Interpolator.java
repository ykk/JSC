package simulation.math.interpolation;

/** Abstract class to provide interpolation of functions.
 * @author ykk
 */
public abstract class Interpolator
{
    /** Function to interpolate values.
     * @param inputValue input value for interpolator
     * @return interpolated value
     */
    public abstract double value(double inputValue);
}