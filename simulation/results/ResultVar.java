package simulation.results;

/** Basic class for result collection, with variance.
 * @author ykk
 */
public class ResultVar
    extends Result
{
    /** Variance value maintained by class.
     * Started at value of 0.
     * Assumed independent samples being taken, thus Var[X]=E[X^2]-E[X]^2.
     * @see #input(double inputValue)
     */
    public double variance = 0;

    /** Mean of samples squared maintained, used for variance calculation.
     * Started at value of 0.
     * @see #variance
     */
    private double squaredMean = 0;

    /** Function to take in a sample of result.
     * The function also maintains an updated average and variance value s
     * and number of samples taken in.
     * @param inputValue sample value of result to be input
     * @see #mean 
     * @see #variance
     * @see #sampleSize
     */
    public void input(double inputValue)
    {
	squaredMean = (sampleSize*squaredMean + Math.pow(inputValue,2.0))/(sampleSize+1);
	super.input(inputValue);

	variance = squaredMean-Math.pow(mean,2.0);
    }
}
