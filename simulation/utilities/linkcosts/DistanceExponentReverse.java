package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Class to provide link cost which is one minus exponent of distance, $1- e^{\alpha d}$.
 * Returns Double.MAX_VALUE if source cannot communicate with destination.
 * Cost is bounded between 0 and 1, if exponent < 0.
 * Can be thought of as probability of errorneous reception.
 * @author ykk
 */
public class DistanceExponentReverse
    extends LinkCost
{
    //Members
    /** Exponent used.
     */
    public double exponent;

    //Methods
    /** Constructor.
     * @param exponent exponent to use
     */
    public DistanceExponentReverse(double exponent)
    {
	this.exponent = exponent;
    }

    /** Provide link cost to transmit from source to destination.
     * @param source source node
     * @param destination destination node
     * @return one minus exponetial of distance in between 
     * if source can transmit to destination, else 1.0
     */
    public double cost(Node source, Node destination)
    {
	if(source.transmitPartners.contains(destination))
	    return 1-Math.pow(Math.E,source.distance(destination)*exponent);
	else
	    return Double.MAX_VALUE;
    }
}