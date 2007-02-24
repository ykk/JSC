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

/** Class to discover routes using Minimum Spanning Tree algorithm.
 * @author ykk
 */
public class MST
{
    //Members
    /** Link cost reference.
     */
    public LinkCost linkCost;
    /** Indicate if root of tree is source or sink
     */
    public boolean rootIsSource;

    //Methods
    /** Constructor for creating MST.
     * @param linkCost link cost definition between nodes
     * @param rootIsSource indicate if root is source or sink
     */
    public MST(LinkCost linkCost, boolean rootIsSource)
    {
	this.linkCost = linkCost;
	this.rootIsSource = rootIsSource;
    }

    /** Return minimum spanning tree.
     * @param network reference to network
     * @param root reference to root node
     * @return return shortest path spanning tree
     */
    public RouteTree spst(Network network, Node root)
    {
	Vector nodes = (Vector) network.nodes.clone();
	RouteTree tree = new RouteTree(root, rootIsSource);
	Link minCostLink = new Link(null,null);
	nodes.remove(root);

	while ((nodes.size() != 0) && (minCostLink != null))
	{
	    minCostLink = getMinLink(tree.nodes, nodes);
	    if (minCostLink != null)
	    {
		tree.add(minCostLink);
		if (rootIsSource)
		    nodes.remove(minCostLink.destination);
		else
		    nodes.remove(minCostLink.source);
	    }
	}

	return tree;
    }

    /** Find minimum cost link from/to selected set.
     * @param selected selected set of nodes
     * @param notSelected set of nodes not selected
     * @return minimum cost link from/to selected set
     */
    private Link getMinLink(Vector selected, Vector nonSelected)
    {
	Node parent =null;
	Node child = null;
	double minCost = Double.MAX_VALUE;

	for (int i = 0; i < selected.size(); i++)
	    for (int j = 0; j < nonSelected.size(); j++)
		if (rootIsSource)
		{
		    if (linkCost.cost((Node) selected.get(i), 
				      (Node) nonSelected.get(j)) < minCost)
		    {
			parent = (Node) selected.get(i);
			child = (Node) nonSelected.get(j);
			minCost = linkCost.cost(parent,child);
		    }
		}
		else
		{
		    if (linkCost.cost((Node) nonSelected.get(j),
				      (Node) selected.get(i)) < minCost)
		    {
			parent = (Node) selected.get(i);
			child = (Node) nonSelected.get(j);
			minCost = linkCost.cost(child,parent);
		    }
		}
		       
	if (minCost == Double.MAX_VALUE)
	    return null;
	else if (rootIsSource)
	    return new Link(parent,child);
	else
	    return new Link(child,parent);
    }

   /** Function to test MST by using it on a network and drawing the result.
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
	testRT.draw("testMSTImage.jpg", ImageFile.JPEG_TYPE, testNet, 100, 20);
    }
}
