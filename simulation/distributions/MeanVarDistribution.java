package simulation.distributions;

/** Interface for Distribution that can be defined by mean and variance.
 * @author ykk
 */
public interface MeanVarDistribution
{
    /** Generate instance of distribution, using mean and variance.
     * @param mean mean of distribution
     * @param variance variance of distribution 
     */
    public Distribution newDistribution(double mean, double variance);
}