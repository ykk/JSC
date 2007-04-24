package simulation.communications.channels.data;

import simulation.files.images.*;

/** Class to hold empirical channel information, in terms of distance and reception probabilities.
 * @author ykk
 */
public class ReceiveProb
    implements Positionable
{
    //Members
    /** Distance.
     */
    public double distance;
    /** Reception probability.
     */
    public double probability;

    //Methods
    /** Constructor.
     * @param distance distance
     * @param probability reception probability
     */
    public ReceiveProb(double distance, double probability)
    {
	this.distance = distance;
	this.probability = probability;
    }

    /** Comparable interface.
     */
    public int compareTo(Object obj)
    {
	return (new Double(this.distance)).compareTo(new Double(((ReceiveProb) obj).distance));
    }

    /** String representation.
     */
    public String toString()
    {
	return distance+"\t"+probability;
    }

    public double x()
    {
	return distance;
    }

    public double y()
    {
	return probability;
    }
}
