package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Class to provide link cost which is the log of distance between source and destination. 
 * Returns infinity if source cannot communicate with destination.
 * @author ykk
 */
public class LogDistance
    extends LinkCost
{
    /** Provide link cost to transmit from source to destination.
     * @param source source node
     * @param destination destination node
     * @return distance in between if source can transmit to destination, 
     *         else Double.POSITIVE_INFINITY
     */
    public double cost(Node source, Node destination)
    {
	if(source.transmitPartners.contains(destination))
	    return Math.log(source.distance(destination));
	else
	    return Double.POSITIVE_INFINITY;
    }
}