package simulation.networks.channels;

import simulation.networks.nodes.*;

/** Class for a zero-one channel.
 * @author ykk
 */
public class ZeroOne
    extends SymmetryChannel
{
    //Members
    /** Threshold distance.
     * Within the distance, nodes can communicate reliably, 
     * else no reception can be performed.
     */
    public double thresholdDistance;

    //Methods
    /** Check if source node can transmit directly to destination.
     */
    public ZeroOne(double thresholdDistance)
    {
	this.thresholdDistance = thresholdDistance;
    }

    public boolean canCommunicate(Node source, Node destination)
    {
	return (Math.sqrt(Math.pow(source.x - destination.x,2.0)+
			  Math.pow(source.y - destination.y,2.0))
		< thresholdDistance);
    }   
}
