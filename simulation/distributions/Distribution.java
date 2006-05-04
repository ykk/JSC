package simulation.distributions;

/** Abstract class for all random distributions.
 * @author ykk
 */
public abstract class Distribution
{
    /** Return if the distribution is discrete.
     */
    public abstract boolean isDiscrete();

    /** Return if the distribution is continuous.
     */
    public boolean isContinuous()
    {
	return !isDiscrete();
    }

    /** Get instance of random distribution.
     */
    public abstract double getInstance();
}
