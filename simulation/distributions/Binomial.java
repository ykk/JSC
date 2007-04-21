package simulation.distributions;

import simulation.math.probability.*;

/** Class for Binomial random variable.
 * @author ykk
 */
public class Binomial
    extends Distribution
{
    //Members
    /** Object to tabulate Binomial coefficents.
     */
    public simulation.math.probability.Binomial binomCoeff = new simulation.math.probability.Binomial();
    /** Probability of Bernoulli trial.
     */
    public double probability;
    /** Size of sample space.
     */
    public int sampleSize;

    //Methods
    /** Main function to return samples and mean of random variable.
     * Purpose of the function is to test the distribution.
     * @param args 1st argument is sampleSize of Binomial distribution;
     *             2nd argument is probability of Bernoulli trial;
     *             3rd argument is the number of samples to taken
     * @see #testFunction(Distribution distri, int sampleSize)
     */
    public static void main(String[] args)
    {
	testFunction(new Binomial(Integer.parseInt(args[0]),Double.parseDouble(args[1])),
		     Integer.parseInt(args[2]));
	System.out.println("Binomial distribution has mean sampleSize*probability of Bernoulli trial\n"+
			   "and variance sampleSize*probability*(1-probability).");
    }

    /** Constructor.
     * @param size size of sample space
     * @param prob probability of Bernoulli trial
     */
    public Binomial(int size, double prob)
    {
	this.sampleSize = size;
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

	double probSum = 0;
	for (int i = (int) Math.ceil(startValue); i <= endValue; i++)
	{
	    probSum += binomCoeff.coefficient(sampleSize, i)*Math.pow(probability,i)*
		Math.pow(1-probability,sampleSize-i);
	}
	return probSum;
    }

    public double cumulativeDensity(double value)
    {
	return density(0, value);
    }

    /** Get instance of Binomial random variable.
     * Function uses Bernoulli trial and count the outputs.
     * @return instance of Binomial random variable
     */
    public double getInstance()
    {
	double result = 0;
	for (int i = 0; i < sampleSize; i++)
	    if (Math.random() < probability) result++;

	return result;
    }


}