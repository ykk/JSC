package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Class to provide link cost which is the exponent of distance, $e^{-\alpha d}$.
 * Returns 0 if source cannot communicate with destination.
 * Cost is bounded between 0 and 1, if exponent < 1.
 * Can be thought of as probability of correct reception.
 * @author ykk
 */
public class DistanceExponent
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
    public DistanceExponent(double exponent)
    {
	this.exponent = exponent;
    }

    /** Provide link cost to transmit from source to destination.
     * @param source source node
     * @param destination destination node
     * @return distance in between if source can transmit to destination, 
     *         else 0
     */
    public double cost(Node source, Node destination)
    {
	if(source.transmitPartners.contains(destination))
	    return Math.pow(Math.E,source.distance(destination)*exponent);
	else
	    return 0.0;
    }
}