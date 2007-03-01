package simulation.results;

/** Basic class for printing result collection.
 * @author ykk
 */
public class ResultPrint
    extends Result
{
    //Members
    /** Separator used during printing.
     * Defaulted to tab.
     */
    public String separator = "\t";

    //Methods
    /** Function to take in a sample of result.
     * The function also maintains an updated average value 
     * and number of samples taken in, while printing output.
     * @param inputValue sample value of result to be input
     * @see #mean 
     * @see #sampleSize
     */
    public void input(double inputValue)
    {
	super.input(inputValue);
	System.out.println(this+separator+inputValue);
    }

    /** Function print results
     * @param object associated object or string
     * @param inputValue input value
     * @see #input(double inputValue)
     */
    public void input(Object object, double inputValue)
    {
	super.input(inputValue);
	System.out.println(this+separator+
			   object+separator+inputValue);
    }
}