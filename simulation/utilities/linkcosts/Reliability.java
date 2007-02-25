package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Class to provide link cost which is decreasing with the distance between source and destination.
 * This can be to abstract the reliability between the source and destination.
 * Cost is given be $e^{\alpha d}$, $\alpha$ is the exponent (<0) and d is the distance. 
 * Returns 0.0 if source cannot communicate with destination.
 * @author ykk
 */
public class Reliability
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
    public Reliability(double exponent)
    {
	if (exponent >= 0)
	    throw new RuntimeException(this+" declared with exponent "+exponent+
				       " which is greater than or equal to 0.");
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