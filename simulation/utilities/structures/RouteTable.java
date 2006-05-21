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

	boolean toAdd = true;
	while (toAdd)
	{
	    toAdd = false;

	    for (int i = 0; i < tree.nodes.size(); i++)
		for (int j = 0; j < nodes.size(); j++)
		    if (rootIsSource)
		    {
			if (nextHop((Node) tree.nodes.get(i),
				    (Node) nodes.get(j)) == (Node) nodes.get(j))
				if ((Node) tree.nodes.get(i) != (Node) nodes.get(j))
				    if (tree.nodes.indexOf(nodes.get(j)) == -1)
				    {
					tree.add((Node) tree.nodes.get(i),(Node) nodes.get(j));
					toAdd = true;
				    }
		    }
		    else
		    {
			if (nextHop((Node) nodes.get(j),
				    (Node) tree.nodes.get(i)) == (Node) nodes.get(i))
				if ((Node) tree.nodes.get(i) != (Node) nodes.get(j))
				    if (tree.nodes.indexOf(nodes.get(j)) == -1)
				    {
					tree.add((Node) tree.nodes.get(i),(Node) nodes.get(j));
					toAdd = true;
				    }
		    }				
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
}
