package simulation.distributions;

/** Class for Poisson random variable.
 * @author ykk
 */
public class Poisson
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
     * @param args 1st argument is mean of Poisson distribution;
     *             2nd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Poisson(Double.parseDouble(args[0])),Integer.parseInt(args[1]));
	System.out.println("Poisson distribution has the same value for variance and mean.");
    }

    /** Constructor for Poisson distribution with specified mean.
     * @param mean mean of distribution
     */
    public Poisson(double mean)
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

	double probSum = 0;
	double currValue=Math.exp(-1*mean);
	for (int i = 0; i < endValue; i++)
	{
	    if (i >= Math.ceil(startValue))
		probSum += currValue;
	    currValue *= mean/(i+1);
	}
	return probSum;
    }

    public double cumulativeDensity(double value)
    {
	return density(0, value);
    }

    /** Get instance of Poisson random variable.
     * @return instance of Poisson random variable
     * @see #getSmallInstance()
     */
    public double getInstance()
    {
	int value = 0;
	for (int i = 0; i < (mean/100); i++)
	{
	    if ((i+1)*100 < mean)
		value += (new Poisson(100)).getSmallInstance();
	    else
		value += (new Poisson(mean-(i*100))).getSmallInstance();
	}

	return (double) value;
    }

    /** Get instance of Poisson random variable with small mean.
     * Use {@link #getInstance()} for mean greater than 100.
     * @return instance of Poisson random variable
     * @see #getInstance()
     */
    public double getSmallInstance()
    {
	if (mean > 100)
	    throw new RuntimeException(this+"'s getSmallInstance function does not work for mean above 100.  Please use getInstance()!");

	double randNo = Math.random();
	int value = 0;
	while (cumulativeDensity(((double) value)+0.1) < randNo)
	    value++;
	
	return (double) value;
    }
}
