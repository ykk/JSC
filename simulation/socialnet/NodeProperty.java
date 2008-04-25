package simulation.socialnet;

import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to process nodes.
 */
public class NodeProperty
{
    //Members
    /** Reference to node.
     */
    public GraphNode node;

    //Methods
    /** Constructor.
     * @param node reference to node
     */
    public NodeProperty(GraphNode node)
    {
	this.node = node;
    }

    /** Return clustering coefficient of node, i.e.,
     * number of neighbors that are mutual neighbors, over all possible.
     * @return clustering coefficient.
     */
    public double clusterCoeff()
    {
	int count = 0;
	int tcount = 0;

	for (int i = 0; i < (node.neighbors.size()-1); i++)
	{
	    GraphNode neigh = (GraphNode) node.neighbors.get(i);
	    for (int j = i+1; j < node.neighbors.size(); j++)
	    {
		if (((UniqueVector) neigh.neighbors).
		    index((GraphNode) node.neighbors.get(j)) != -1)
		    count++;
		tcount++;
	    }
	}

	if (count == 0)
	    return 0.0;
	else
	    return ((double) count)/((double) tcount);
    }
}