package simulation.optimization.dynprogram;

/** Class to store transition probabilities for state-action pair.
 * @author ykk
 */
public class TransitProb
{
    //Members
    /** Tolerance for check.
     * Default to 0.
     */
    public double tolerance = 0;
    /** Associated state.
     */
    public State state;
    /** Associated action.
     */
    public Action action;
    /** Array of probabilities.
     */
    public double[] prob;

    //Methods
    /** Constructor.
     * @param prob array of prob
     * @param tolerance tolerance threshold for check
     */
    public TransitProb(double[] prob, double tolerance)
    {
	this.tolerance = tolerance;
	this.prob = new double[prob.length];
	for (int i = 0; i < prob.length; i++)
	    this.prob[i] = prob[i];

	check();
    }

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

	if (Math.abs(1.0 - sum) > tolerance)
	    throw new RuntimeException(this+" sums to "+sum+" instead of 1!  Exceeded tolerance of "+tolerance+".");
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