package simulation.utilities.structures;

import java.util.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to store a routes in a network.
 * @author ykk
 */
public class RouteTable
{
    //Members
    /** Table of next hops.
     */
    private Node[][] nextHop;
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
	return addToRouteTree(tree, root);
    }

    /** Add leaves to route tree.
     * @param tree routing tree to add to
     * @param parent parent node to add leave to
     * @return resulting routing tree
     */
    private RouteTree addToRouteTree(RouteTree tree, Node parent)
    {
	int parentIndex = nodes.indexOf(parent);
	//Get children nodes of parent
	for (int i = 0; i < nodes.size(); i++)
	{
	    if (tree.rootIsSource())
	    {
		//Root is source, i.e., parent to child link
		if (nextHop[parentIndex][i] != null)
		    if (nodes.get(i) == nextHop[parentIndex][i])
		    {
			tree.add(parent, nextHop[parentIndex][i]);
			tree = addToRouteTree(tree, nextHop[parentIndex][i]);
		    }
	    }
	    else
	    {
		//Root is sink, i.e. child to parent link
		if (nextHop[i][parentIndex] != null)
		    if (parent == nextHop[i][parentIndex])
		    {
			tree.add(parent, nextHop[i][parentIndex]);
			tree = addToRouteTree(tree, nextHop[i][parentIndex]);
		    }		
	    }
	}

	return tree;
    }
}
