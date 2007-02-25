package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Class to provide link cost which is increasing with the distance between source and destination.
 * This can be to abstract the unreliability between the source and destination.
 * Cost is given be $1 - e^{\alpha d}$, $\alpha$ is the exponent (<0) and d is the distance. 
 * Returns Infinity if source cannot communicate with destination.
 * @author ykk
 */
public class Unreliability
    extends Reliability
{
    //Methods
    /** Constructor.
     * @param exponent exponent to use
     */
    public Unreliability(double exponent)
    {
	super(exponent);
    }

    /** Provide link cost to transmit from source to destination.
     * @param source source node
     * @param destination destination node
     * @return distance in between if source can transmit to destination, 
     *         else Double.POSITIVE_INFINITY
     */
    public double cost(Node source, Node destination)
    {
	if(source.transmitPartners.contains(destination))
	    return 1-Math.pow(Math.E,source.distance(destination)*exponent);
	else
	    return Double.POSITIVE_INFINITY;
    }
}