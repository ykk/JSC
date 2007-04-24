package simulation.communications.channels.data;

/** Class to hold empirical channel information, with variance of reception probabilities.
 * Useful for constructing probability map on network.
 * @author ykk
 */
public class ReceiveProbVar
    extends ReceiveProb
{
    //Members
    /** Standard deviation of receive probability.
     */
    public double stdDev;
    /** Flag to let y-coordinate be standard deviation.
     * False by default.
     */
    public boolean yIsStdDev = false;

    //Methods
    /** Constructor.
     * @param distance distance
     * @param probability reception probability
     * @param stdDev standard deviation of reception probability
     */
    public ReceiveProbVar(double distance, double probability, double stdDev)
    {
	super(distance,probability);
	this.stdDev = stdDev;
    }

    /** String representation.
     */
    public String toString()
    {
	return distance+"\t"+probability+"\t"+stdDev;
    }

    public double y()
    {
	return (yIsStdDev)? stdDev: probability;
    }
}