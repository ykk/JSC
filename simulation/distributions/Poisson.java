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
    /** Main function to return Poisson random variable
     */
    public static void main(String[] args)
    {
	System.out.println("Poisson RV = "+(new Poisson(Double.parseDouble(args[1]))).getInstance());
    }

    /** Constructor for Poisson distribution with specified mean.
     * @param mean
     */
    public Poisson(double mean)
    {
	this.mean = mean;
    }

    /** Return if the distribution is discrete.
     */
    public boolean isDiscrete()
    {
	return true;
    }

    /** Get instance of Poisson random variable.
     */
    public double getInstance()
    {
	return Math.random();
    }
}
