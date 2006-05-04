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
    /** Main function to return Poisson random variable.
     * @param args 1st argument is mean of Poisson distribution.
     */
    public static void main(String[] args)
    {
	System.out.println("Poisson RV with mean "+args[0]+" = "+(new Poisson(Double.parseDouble(args[0]))).getInstance());
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
	double probSum = 0;

	for (int i = (int) Math.ceil(startValue); i < endValue; i++)
	    probSum += (Math.pow(mean,i)*Math.exp(-1*mean))/factorial(i);

	return probSum;
    }

    /** Return factorial of given integer.
     */
    private double factorial(int i)
    {
	if (i <= 1)
	    return 1.0;
	else
	    return ((double) i)*factorial(i-1);
    }

    public double cumulativeDensity(double value)
    {
	return density(0, value);
    }

    /** Get instance of Poisson random variable.
     * @return instance of Poisson random variable
     */
    public double getInstance()
    {
	double randNo = Math.random();
	int value = 0;
	while (cumulativeDensity(((double) value)+0.1) < randNo)
	    value++;

	return value;
    }
}
