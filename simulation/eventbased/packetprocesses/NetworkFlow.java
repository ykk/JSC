package simulation.eventbased.packetprocesses;

import simulation.utilities.linkcosts.*;
import simulation.networks.*;
import simulation.networks.nodes.*;

/** Class to manage network flow in event driven simulation.
 * @author ykk
 */
public class NetworkFlow
    extends simulation.networks.simulator.NetworkFlow
{

    /** Constructor.
     * @param source source node
     * @param sink sink node
     * @param network reference to network
     * @param linkCost reference to link cost
     */
    public NetworkFlow(Node source, Node sink, 
		       Network network, LinkCost linkCost)
    {
	super(source, sink, network, linkCost);
    }

    /** Constructor.
     * Randomly chooses source-sink.
     * @param network reference to network
     * @param linkCost reference to link cost
     */
    public NetworkFlow(Network network, LinkCost linkCost)
    {
	super(network,linkCost);
    }

}