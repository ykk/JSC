package simulation.optimization.dynprogram;

/** Class to store transition probabilities for state-action pair.
 * If sparse vector given, stored in a more efficient manner than using array.
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
    /** Indicator for sparse matrix.
     */
    protected boolean sparse;
    /** Array of probabilities.
     */
    private double[][] prob;
    /** Length of matrix
     */
    protected double length;

    //Methods
    /** Constructor.
     * @param prob array of prob
     * @param tolerance tolerance threshold for check
     */
    public TransitProb(double[] prob, double tolerance)
    {
	this.tolerance = tolerance;
	this.length = prob.length;
	int count = checkSparse(prob);
    
	if (sparse)
	{
	    int currIndex = 0;
	    this.prob = new double[count][2];
	    for (int i = 0; i < length; i++)
		if (prob[i] != 0)
		{
		    this.prob[currIndex][0] = i;
		    this.prob[currIndex][1] = prob[i];
		    currIndex++;
		}
	}
	else
	{
	    this.prob = new double[1][prob.length];
	    for (int i = 0; i < length; i++)
		this.prob[0][i] = prob[i];
	}

	check();
    }

    /** Constructor.
     * @param prob array of prob
     */
    public TransitProb(double[] prob)
    {
	this(prob, 0);
    }

    /** Check sparse
     * @param prob probability vector
     * @return number of non-zero item
     */
    private int checkSparse(double[] prob)
    {
	int sum = 0;
	for (int i = 0; i < length; i++)
	    if (prob[i] != 0) sum += 1;
	
	sparse = (sum < (length/2));
	return sum;
    }

    /** Get probability value
     * @param index index of value
     * @return probability of index
     */
    public double getProb(int index)
    {
	if (sparse)
	{
	    for (int i = 0; i < prob.length; i++)
		if (prob[i][0] == index)
		    return prob[i][1]; 
	    return 0.0;
	}
	else
	    return prob[0][index];
    }

    /** Check accuracy of probabilities.
     */
    public void check()
    {
	double sum = 0;
	if (sparse)
	    for (int i = 0; i < prob.length; i++)
		sum += prob[i][1];
	else
	    for (int i = 0; i < length; i++)
		sum += prob[0][i];
	
	if (Math.abs(1.0 - sum) > tolerance)
	    throw new RuntimeException(this+" sums to "+sum+" instead of 1!  Exceeded tolerance of "+tolerance+".");
    }

    /** String representation.
     */
    public String toString()
    {
	String outStr = "[";
	int count = 0;
	if (sparse)
	    for (int i = 0; i < length; i++)
		if (count < prob.length && prob[count][0] == i)
		{
		    outStr += prob[count][1]+ " ";
		    count++;
		}
		else
		    outStr += "0 ";
	else
	    for (int i = 0; i < length; i++)
		outStr += prob[0][i]+" ";

	return outStr+"]";
    }
}