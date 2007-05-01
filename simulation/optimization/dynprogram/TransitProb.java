package simulation.optimization.dynprogram;

/** Class to store action for transition probabilities.
 * @author ykk
 */
public class TransitProb
{
    //Members
    /** Associated state.
     */
    public State state;
    /** Associated action.
     */
    public Action action;
    /** Array of probabilities.
     */
    protected double[] prob;

    //Methods
    /** Constructor.
     * @param prob array of prob
     */
    public TransitProb(double[] prob)
    {
	this.prob = new double[prob.length];
	for (int i = 0; i < prob.length; i++)
	    this.prob[i] = prob[i];

	check();
    }

    /** Check accuracy of probabilities.
     */
    public void check()
    {
	double sum = 0;
	for (int i = 0; i < prob.length; i++)
	    sum += prob[i];

	if (sum != 1.0)
	    throw new RuntimeException(this+" sums to "+sum+" instead of 1!");
    }

    /** String representation.
     */
    public String toString()
    {
	String outStr = "[";
	for (int i = 0; i < prob.length; i++)
	    outStr += prob[i]+" ";

	return outStr+"]";
    }
}