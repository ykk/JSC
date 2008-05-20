package simulation.networks.simulator;

import simulation.utilities.structures.*;
import simulation.utilities.linkcosts.*;
import simulation.utilities.routes.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.distributions.*;
import java.util.*;

/** Class to manage network flow.
 * @author ykk
 */
public class NetworkFlow
{
    //Members
    /** Source node.
     */
    public Node source;
    /** Sink node.
     */
    public Node sink;
    /** Reference to route between source and sink.
     * @see #getRoute()
     */
    public Route route;
    /** Reference to network.
     */
    public Network network;
    /** Reference to link cost.
     */
    public LinkCost linkCost;

    //Methods
    /** Constructor.
     * Dummy constructor for inheritance.
     */
    public NetworkFlow()
    {
    }

    /** Constructor.
     * @param source source node
     * @param sink sink node
     * @param network reference to network
     * @param linkCost reference to link cost
     */
    public NetworkFlow(Node source, Node sink, 
		       Network network, LinkCost linkCost)
    {
	this.source = source;
	this.sink = sink;
	this.network = network;
	this.linkCost = linkCost;
	this.getRoute();
    }

    /** Constructor.
     * Randomly chooses source-sink.
     * @param network reference to network
     * @param linkCost reference to link cost
     */
    public NetworkFlow(Network network, LinkCost linkCost)
    {
	if (network.nodes.size() < 2)
	    throw new RuntimeException("Network with less than 2 nodes"+
				       " cannot have a flow!");

	Uniform choose = new Uniform(0, network.nodes.size());
	source = sink = 
	    (Node) network.nodes.get((int) Math.floor(choose.getInstance()));
	while (sink == source)
	    sink = (Node) network.nodes.
		get((int) Math.floor(choose.getInstance()));

	this.network = network;
	this.linkCost = linkCost;
	this.getRoute();
    }

    /** Get route for flow.
     * @see #route
     */
    public Route getRoute()
    {
	route = Dijkstra.shortestpath(network, linkCost, source, sink);
	return route;
    }
}