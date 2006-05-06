package simulation.distributions;

/** Class for Uniform random variable.
 * @author ykk
 */
public class Uniform
    extends Distribution
{
    //Members
    /** Start value of range.
     * Defaulted to 0.
     */
    public double startValue=0;

    /** End value of range.
     * Defaulted to 1.
     */
    public double endValue=1;

    //Members
    /** Mean of distribution.
     * Defaulted to 0.5.
     */
    public double mean=0.5;

    //Methods
    /** Main function to return samples and mean of random variable.
     * Purpose of the function is to test the distribution.
     * @param args 1st argument is start value of range;
     *             2nd argument is end value of range;
     *             3rd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Uniform(Double.parseDouble(args[0]),Double.parseDouble(args[1])),Integer.parseInt(args[2]));
	System.out.println("Uniform distribution has mean = (1/2)(start+end) and variance = (1/12)(end-start)^2.");
    }

    /** Constructor for Uniform distribution with specified start and end of range.
     * @param startValue start value of range
     * @param endValue end value of range
     */
    public Uniform(double startValue , double endValue)
    {
	this.startValue = startValue;
	this.endValue = endValue;
	this.mean = 0.5*(startValue+endValue);
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
	if (value < startValue)
	    return 0.0;
	else if (value >= endValue)
	    return 1.0;
	else
	    return (value-startValue)/(endValue-startValue);
    }

    /** Get instance of Uniform random variable.
     * @return instance of Uniform random variable
     */
    public double getInstance()
    {
	return Math.random()*(endValue-startValue)+startValue;
    }
}
