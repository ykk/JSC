package simulation.utilities.linkcosts;

import simulation.networks.nodes.*;

/** Abstract class to provide link cost.
 * @author ykk
 */
public abstract class LinkCost
{
    /** Provide link cost to transmit from source to destination.
     * @param source source node
     * @param destination destination node
     * @return link cost
     */
    public abstract double cost(Node source, Node destination);
}
