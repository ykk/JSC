package simulation.utilities.routes;

import java.util.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.channels.*;
import simulation.utilities.structures.*;
import simulation.utilities.routes.*;
import simulation.utilities.linkcosts.*;
import simulation.files.images.*;

/** Class to discover routes using Dijsktra algorithm.
 * @author ykk
 */
public class Dijkstra
{
    //Members
    /** Link cost reference.
     */
    public LinkCost linkCost;
    /** Indicate if root of tree is source or sink
     */
    public boolean rootIsSource;
    /** Debug flag.
     */
    private boolean debug = false;

    //Methods
    /** Constructor for creating Dijkstra.
     * @param linkCost link cost definition between nodes
     * @param rootIsSource indicate if root is source or sink
     */
    public Dijkstra(LinkCost linkCost, boolean rootIsSource)
    {
	this.linkCost = linkCost;
	this.rootIsSource = rootIsSource;
    }

    /** Return route between 2 nodes.
     * @param network reference to network
     * @param linkCost link cost definition
     * @param source reference to source
     * @param destination to destination
     * @return shortest path route between root and child
     */
    public static Route shortestpath(Network network, LinkCost linkCost, 
				     Node source, Node destination)
    {
	Dijkstra spst = new Dijkstra(linkCost, true);
	RouteTree tree = spst.spst(network, source);
	return tree.getRoute(destination);
    }

    /** Return shortest path spanning tree.
     * Uses Dijsktra algorithm, therefore no negative link cost is allowed.
     * Reference to
     * <PRE>@InBook{Bertsekas92,
     * author = {Dimitri Bertsekas and Robert Gallager},
     * title = {Data Networks},
     * chapter = {5.2},
     * publisher = {Prentice Hall},
     * year = {1992},
     * edition = {2},
     * pages = {401--403},
     * }</PRE>
     * @param network reference to network
     * @param root reference to root node
     * @return return shortest path spanning tree
     */
    public RouteTree spst(Network network, Node root)
    {
	Vector nodes = (Vector) network.nodes.clone();
	Vector costToNode = new Vector();
	RouteTree tree = new RouteTree(root, rootIsSource);
	Link minCostLink = new Link(null,null);
	double costToParent;
	double costOfLink;

	//Remove root and add its cost to vector of costs
	nodes.remove(root);
	costToNode.add(new Double(0.0));

	while ((nodes.size() != 0) && (minCostLink != null))
	{
	    minCostLink = getMinLink(tree.nodes, costToNode, nodes);
	    if (minCostLink != null)
	    {
		tree.add(minCostLink);
		if (rootIsSource)
		{
		    costToParent = ((Double) 
				    costToNode.get(tree.nodes.indexOf(minCostLink.source))).doubleValue();
		    costOfLink = linkCost.cost(minCostLink.source, minCostLink.destination);
		    costToNode.add(new Double(costSum(costToParent,costOfLink)));
		    nodes.remove(minCostLink.destination);
		}
		else
		{
		    costToParent = ((Double) 
				    costToNode.get(tree.
						   nodes.indexOf(minCostLink.destination))).doubleValue();
		    costOfLink = linkCost.cost(minCostLink.source, minCostLink.destination);
		    costToNode.add(new Double(costSum(costToParent,costOfLink)));
		    nodes.remove(minCostLink.source);
		}
	    }
	}

	tree.costToNode = costToNode;
	return tree;
    }

    /** Find minimum cost link from/to selected set.
     * @param selected selected set of nodes
     * @param costToSelected cost to selected nodes
     * @param notSelected set of nodes not selected
     * @return minimum cost link from/to selected set
     */
    private Link getMinLink(Vector selected, Vector costToSelected, Vector nonSelected)
    {
	Node parent =null;
	Node child = null;
	double minCost = Double.MAX_VALUE;

	for (int i = 0; i < selected.size(); i++)
	    for (int j = 0; j < nonSelected.size(); j++)
		if (rootIsSource)
		{
		    if (costSum(linkCost.cost((Node) selected.get(i),(Node) nonSelected.get(j)),
				((Double) costToSelected.get(i)).doubleValue()) < minCost)
		    {
			parent = (Node) selected.get(i);
			child = (Node) nonSelected.get(j);
			minCost = costSum(linkCost.cost(parent,child), 
					  ((Double) costToSelected.get(i)).doubleValue());
		    }
		}
		else
		{
		    if (costSum(linkCost.cost((Node) nonSelected.get(j),(Node) selected.get(i)),
				((Double) costToSelected.get(i)).doubleValue()) < minCost)
		    {
			parent = (Node) selected.get(i);
			child = (Node) nonSelected.get(j);
			minCost = costSum(linkCost.cost(child,parent),
					  ((Double) costToSelected.get(i)).doubleValue());
		    }
		}

	if (debug) System.out.println(minCost);
	if (minCost == Double.MAX_VALUE)
	    return null;
	else if (rootIsSource)
	    return new Link(parent,child);
	else
	    return new Link(child,parent);
    }

    /** Function to sum two link costs.
     * @param cost1 first cost
     * @param cost2 second cost
     * @return sum of costs
     */
    private double costSum(double cost1, double cost2)
    {
	if ((cost1 < 0) || (cost2 < 0))
	    throw new RuntimeException(this+" experiences negative link cost of "+
				       cost1 +" or/and "+cost2 +" which is not allowed.");
	return cost1+cost2;
    }

    /** Function to test Dijsktra by using it on a network and drawing the result.
     * @param args 1st argument is density of network
     */
    public static void main(String[] args)
    {
	//Create network
	Network testNet = new Network(new CircleNetArea(5), 
				      new Node(new Coordinate(0,0), new ZeroOne(1)), 
				      new Poisson(Double.parseDouble(args[0])));
	//Get Dijsktra SPST
	Dijkstra spst = new Dijkstra(new Constant(),true);
	RouteTree testRT = spst.spst(testNet, (Node) testNet.nodes.get(0));

	//Draw routing tree
	testRT.draw("testDijsktraImage.jpg", ImageFile.JPEG_TYPE, testNet, 100, 20);
    }
}
