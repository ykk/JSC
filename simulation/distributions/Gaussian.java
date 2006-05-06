package simulation.distributions;

/** Class for Gaussian random variable.
 * @author ykk
 */
public class Gaussian
    extends Distribution
{
    //Members
    /** Mean of distribution.
     * Defaulted to 1.
     */
    public double mean=1;

    /** Variance of distribution.
     * Defaulted to 0.
     */
    public double variance=0;

     //Methods
    /** Main function to return samples and mean of random variable.
     * Purpose of the function is to test the distribution.
     * @param args 1st argument is mean of Gaussian distribution;
     *             2nd argument is variable of Gaussian distribution;
     *             3rd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Gaussian(Double.parseDouble(args[0]),Double.parseDouble(args[1])),Integer.parseInt(args[2]));
	System.out.println("Gaussian distribution has specified mean and variance.");
    }

    /** Constructor for Gaussian distribution with specified mean and variance.
     * @param mean mean of distribution
     * @param variance variance of distribution
     */
    public Gaussian(double mean, double variance)
    {
	this.mean = mean;
	this.variance = variance;
    }

    public boolean isDiscrete()
    {
	return false;
    }

    public double density(double startValue, double endValue)
    {
	throw new RuntimeException(this+"'s density function is not implemented.");
    }

    public double cumulativeDensity(double value)
    {
	throw new RuntimeException(this+"'s cumulativeDensity function is not implemented.");
    }

    /** Get instance of Gaussian random variable.
     * @return instance of Gaussian random variable
     */
    public double getInstance()
    {
	return mean+Math.sqrt(variance)*Math.sqrt(-2*Math.log(Math.random()))*Math.cos(2*Math.PI*Math.random());
    }
}   
