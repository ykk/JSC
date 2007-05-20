package simulation.utilities.structures;

import java.util.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to store a routes in a network.
 * @author ykk
 */
public class RouteTable
    implements Cloneable
{
    //Members
    /** Table of next hops.
     */
    public Node[][] nextHop;
    /** Cost between nodes.
     */
    public double[][] cost;
    /** Vector of nodes.
     * Cloned from vector given at construction.
     */
    public Vector nodes;

    //Methods
    /** Constructor for a route table, given set of nodes.
     * @param nodes vector of nodes to contain routes for.
     */
    public RouteTable(Vector nodes)
    {
	int nodeNumber = nodes.size();
	this.nodes = (Vector) nodes.clone();
	nextHop = new Node[nodeNumber][nodeNumber];

	//Set initial
	for (int i = 0; i < nodeNumber; i ++)
	    for (int j = 0; j < nodeNumber; j++)
		if (i==j)
		    nextHop[i][j] = (Node) nodes.get(i);
		else
		    nextHop[i][j] = null;
    }

    /** Return route tree size of node as source/sink.
     * @param node node to be root
     * @param rootIsSource indicate if root is source, else is sink
     * @return size of route tree
     */
    public int treeSize(Node node, boolean rootIsSource)
    {
	int size = 0;
	int index = nodes.indexOf(node);
	for (int i = 0; i < nodes.size(); i++)
	    if (rootIsSource)
		size += (nextHop[index][i] != null) ?1:0;
	    else
		size += (nextHop[i][index] != null) ?1:0;

	return size;
    }

    /** Return node giving maximum route tree size as source/sink.
     * @param rootIsSource indicate if root is source, else is sink
     * @return node providing maximum route tree size
     */
    public Node maxTreeNode(boolean rootIsSource)
    {
	Node maxNode = null;
	int maxSize = 0;
	for (int i = 0; i < nodes.size(); i++)
	    if (treeSize((Node) nodes.get(i), rootIsSource) > maxSize)
	    {
		maxNode = (Node) nodes.get(i);
		maxSize = treeSize(maxNode, rootIsSource);
	    }
	
	return maxNode;
    }

    /** Return maximum route tree size as any node as source/sink.
     * @param rootIsSource indicate if root is source, else is sink
     * @return maximum route tree size
     */
    public int maxTreeSize(boolean rootIsSource)
    {
	return treeSize(maxTreeNode(rootIsSource), rootIsSource);
    }

    /** Assign next hop
     * @param source source node
     * @param destination destination node
     * @param nextHop node to be next hop of source
     */
    public void assignNextHop(Node source, Node destination, Node nextHop)
    {
	this.nextHop[nodes.indexOf(source)][nodes.indexOf(destination)] = nextHop;
    }

    /** Get next hop between source and destination.
     * @param source source node
     * @param destination destination node
     * @return node that is next hop for source to reach destination
     */
    public Node nextHop(Node source, Node destination)
    {
	return nextHop[nodes.indexOf(source)][nodes.indexOf(destination)];
    }

    /** Return route from source to destination.
     * Return NULL if route does not exist.
     * @param source source node
     * @param destination destination node
     * @return route between source and destination
     */
    public Route getRoute(Node source, Node destination)
    {
	Route route = new Route(true);

	while ((source != destination) && (source != null))
	{
	    route.add(new Link(source, nextHop(source,destination)));
	    source = nextHop(source,destination);
	}

	route.cost = cost[nodes.indexOf(source)][nodes.indexOf(destination)];
	if (source == null)
	    return null;
	else
	    return route;
    }

    /** Return route tree from a source or to a destination.
     * @param root source or destination, i.e., root
     * @param rootIsSource indicate if root is source, else is sink
     * @return routing tree for source/destination
     */
    public RouteTree getRouteTree(Node root, boolean rootIsSource)
    {
	RouteTree tree = new RouteTree(root, rootIsSource);
	int rootIndex = nodes.indexOf(root);
	Route route;

	for (int i = 0; i < nodes.size(); i++)
	{
	    if (rootIsSource)
		route = getRoute(root, (Node) nodes.get(i));
	    else
		route = getRoute((Node) nodes.get(i), root);

	    if (route != null)
		tree.add(route);
	}

	return tree;
    }

    /** Cloneable interface
     * Returns a clone of this routing table. 
     * The copy will contain a reference to a clone of the 
     * internal data, not a reference to the original 
     * internal data of this routing table.
     * @return clone of routing table
     */
    public Object clone()
    {
	RouteTable cloned = new RouteTable(nodes);
	int nodeNumber = nodes.size();
	
	for (int i = 0; i < nodeNumber; i++)
	    for (int j = 0; j < nodeNumber; j++)
		cloned.assignNextHop((Node) nodes.get(i),
				     (Node) nodes.get(j),
				     nextHop((Node) nodes.get(i),
					     (Node) nodes.get(j)));
	
	return cloned;
    }

    /** Check if fully connected.
     * @return if fully connected
     */
    public boolean connected()
    {
	for (int i = 0; i < nodes.size(); i++)
	    for (int j = 0; j < nodes.size(); j++)
		if (nextHop[i][j] == null)
		    return false;

	return true;
    }
}
