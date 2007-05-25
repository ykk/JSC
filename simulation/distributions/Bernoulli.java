package simulation.distributions;

/** Class for Bernoulli random variable.
 * @author ykk
 */
public class Bernoulli
    extends Distribution
{
    //Members
    /** Probability of Bernoulli trial, i.e., probability of generating 1.
     */
    public double probability;

    //Methods
    /** Main function to return samples and mean of random variable.
     * Purpose of the function is to test the distribution.
     * @param args 1st argument is probability of 1.0;
     *             2rd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Bernoulli(Double.parseDouble(args[0])),
		     Integer.parseInt(args[1]));
	System.out.println("Bernoulli distribution has mean as probability of 1.");
    }

    /** Constructor.
     * @param prob probability of Bernoulli trial
     */
    public Bernoulli(double prob)
    {
	this.probability = prob;
    }

    public boolean isDiscrete()
    {
	return true;
    }

    public double density(double startValue, double endValue)
    {
	if (endValue < startValue)
	    throw new RuntimeException(this+"'s density function called with endValue <= startValue.  Note that startValue >= endValue");
	
	if (startValue <= 0 && endValue >= 1)
	    return 1.0;
	else if (startValue > 0 && endValue >= 1)
	    return probability;
	else if (startValue <= 0 && endValue < 1)
	    return 1.0-probability;
	else 
	    return 0.0;
    }

    public double cumulativeDensity(double value)
    {
	if (value >= 1.0)
	    return 1.0;
	else if (value <= 0)
	    return 0.0;
	else
	    return 1.0 - probability;
    }

    /** Get instance of Bernoulli random variable.
     * @return instance of Bernoulli trial
     */
    public double getInstance()
    {
	if (Math.random() < probability)
	    return 1.0;
	else
	    return 0.0;
    }
}