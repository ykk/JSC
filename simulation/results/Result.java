package simulation.results;

/** Basic class for result collection.
 * @author ykk
 */
public class Result
{
    //Members
    /** Average value maintained by class.
     * Started at value of 0.
     * @see #input(double inputValue)
     */
    public double mean = 0;

    /** Number of samples taken in.
     */
    public double sampleSize = 0;

    //Methods
    /** Function to take in a sample of result.
     * The function also maintains an updated average value 
     * and number of samples taken in.
     * @param inputValue sample value of result to be input
     * @see #mean 
     * @see #sampleSize
     */
    public void input(double inputValue)
    {
	mean = (sampleSize*mean + inputValue)/(sampleSize+1);
	sampleSize++;
    }
}
