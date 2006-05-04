package simulation.distributions;

/** Abstract class for all random distributions.
 * @author ykk
 */
public abstract class Distribution
{
    /** Return if the distribution is discrete.
     * @return if distribution is discrete
     * @see #isContinuous()
     */
    public abstract boolean isDiscrete();

    /** Return if the distribution is continuous.
     * @return if distribution is continuous
     * @see #isDiscrete()
     */
    public boolean isContinuous()
    {
	return !isDiscrete();
    }

    /** Get instance of random distribution.
     */
    public abstract double getInstance();

    /** Return probability of distribution lies within specified interval.
     * @param startValue value starting range under consideration (included)
     * @param endValue value ending range under consideration (excluded)
     * @return probability of distribution lies within startValue and endValue
     */
    public abstract double density(double startValue, double endValue);

    /** Return probability of distribution being below the specified value.
     * @param value value to consider
     * @return probability of distribution being less than value
     */
    public abstract double cumulativeDensity(double value);
}
