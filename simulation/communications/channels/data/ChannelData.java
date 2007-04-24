package simulation.communications.channels.data;

import simulation.utilities.structures.*;

/** Class to hold empirical channel information.
 * Data is in the form of a vector distance and reception probabilities.
 * @author ykk
 */
public class ChannelData
    extends SortedVector
{
    /** Add new distance and probability pair.
     * @param distance distance
     * @param probability reception probability at distance
     */
    public void add(double distance, double probability)
    {
	super.add(new ReceiveProb(distance, probability));
    }

    /** Add new distance and probability pair, with standard deviation.
     * @param distance distance
     * @param probability reception probability at distance
     * @param stdDev standard deviation of reception probability
     */
    public void add(double distance, double probability, double stdDev)
    {
	super.add(new ReceiveProbVar(distance, probability, stdDev));
    }

    /** Switch y-coordinate to probability/standard deviation.
     * Only valid for {@link ReceiveProbVar}.
     * @param isStdDev indicate that y is standard deviation
     */
    public void switchY(boolean isStdDev)
    {
	for (int i = 0; i < size(); i++)
	    ((ReceiveProbVar) get(i)).yIsStdDev = isStdDev;
    }

    /** String representation.
     */
    public String toString()
    {
	String tmpString = "Distance\tProbability\tStandard Deviation[if available]\n";
	for (int i = 0; i < size(); i++)
	    tmpString += this.get(i).toString()+"\n";
    
	return tmpString;
    }
}