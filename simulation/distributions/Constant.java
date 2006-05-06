package simulation.distributions;

/** Class for Constant random variable.
 * @author ykk
 */
public class Constant
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
     * @param args 1st argument is mean of Constant distribution;
     *             2nd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Constant(Double.parseDouble(args[0])),Integer.parseInt(args[1]));
	System.out.println("Constant has specified mean and zero for variance.");
    }

    /** Constructor for Constant with specified mean/value.
     * @param mean mean of distribution
     */
    public Constant(double mean)
    {
	this.mean = mean;
    }

    public boolean isDiscrete()
    {
	return true;
    }

    public double density(double startValue, double endValue)
    {
	if (endValue <= startValue)
	    throw new RuntimeException(this+"'s density function called with endValue <= startValue.  Note that startValue > endValue strictly");

	if (endValue > mean && startValue <= mean)
	    return 1.0;
	else
	    return 0.0;
    }

    public double cumulativeDensity(double value)
    {
	if (value >= mean)
	    return 1.0;
	else
	    return 0.0;
    }

    /** Get instance of a constant.
     * @return instance of constant
     */
    public double getInstance()
    {
	return mean;
    }
}
