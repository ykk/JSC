package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Class to provide link cost of 1 if source can transmit to destination, 
 * else infinity .
 * @author ykk
 */
public class Constant
    extends LinkCost
{
    /** Provide link cost to transmit from source to destination.
     * @param source source node
     * @param destination destination node
     * @return 1 if source can transmit to destination, 
     *         else Double.POSITIVE_INFINITY
     */
    public double cost(Node source, Node destination)
    {
	return (source.transmitPartners.contains(destination))?1.0:Double.POSITIVE_INFINITY;
    }
}
