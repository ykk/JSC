package simulation.distributions;

/** Class for Exponential random variable.
 * @author ykk
 */
public class Exponential
    extends Distribution
{
    //Members
    /** Mean of distribution.
     * Defaulted to 1.
     */
    public double mean=1;

    //Methods
    /** Main function to return samples and mean of random variable.
     * Purpose of the function is to test the distribution.
     * @param args 1st argument is mean of Exponential distribution;
     *             2nd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Exponential(Double.parseDouble(args[0])),Integer.parseInt(args[1]));
	System.out.println("Exponential distribution has variance = mean squared.");
    }

    /** Constructor for Exponential distribution with specified mean.
     * @param mean mean of distribution
     */
    public Exponential(double mean)
    {
	this.mean = mean;
    }

    public boolean isDiscrete()
    {
	return false;
    }

    public double density(double startValue, double endValue)
    {
	if (endValue <= startValue)
	    throw new RuntimeException(this+"'s density function called with endValue <= startValue.  Note that startValue > endValue strictly");

	return cumulativeDensity(endValue)-cumulativeDensity(startValue);
    }

    public double cumulativeDensity(double value)
    {
	if (value <= 0)
	    throw new RuntimeException(this+"'s cumulativeDensity function called with value <= 0.  Note that value > 0 strictly");

	return 1-Math.exp(-1*(1/mean)*value);
    }

    /** Get instance of Exponential random variable.
     * @return instance of Exponential random variable
     */
    public double getInstance()
    {
	return -1*mean*Math.log(Math.random());
    }
}
