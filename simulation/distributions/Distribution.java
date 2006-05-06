package simulation.distributions;

import simulation.results.*;

/** Abstract class for all random distributions.
 * @author ykk
 */
public abstract class Distribution
{
    /** Main function to return samples, mean and variance of random variable.
     * Purpose of the function is to test the distribution.
     * @param distri distribution being tested
     * @param sampleSize number of samples to take
     */
    public static void testFunction(Distribution distri, int sampleSize)
    {
	ResultVar testResult = new ResultVar();
	double value;

	for (int i = 0; i < sampleSize; i++)
	{
	    value = distri.getInstance();
	    testResult.input(value);

	    System.out.println(testResult.sampleSize+"\t"+value+"\t"+testResult.mean+"\t"+testResult.variance);
	}
	    System.out.println("Sample Size\tValue\tMean\tVariance");
    }

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
