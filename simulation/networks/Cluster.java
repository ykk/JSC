package simulation.networks;

import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to represent cluster/component in graph.
 * @author ykk
 */
public class Cluster
    extends Graph
{
    //Methods
    /** Constructor.
     * @param graph graph to get cluster from
     * @param seed node to grow cluster from
     */
    public Cluster(Graph graph, GraphNode seed)
    {
	growCluster(graph, seed);
    }
    
    /** Get cluster from seed.
     * @param graph graph to get cluster from
     * @param seed node to grow cluster from
     */
    public void growCluster(Graph graph, GraphNode seed)
    {
	UniqueVector tmpNodes = new UniqueVector();
	tmpNodes.add(seed);

	while (tmpNodes.size() != 0)
	{
	    GraphNode curr = (GraphNode) tmpNodes.remove(0);
	    this.addNode(curr);

	    for (int i = 0; i < curr.neighbors.size(); i++)
	    {
		GraphNode nei = (GraphNode) curr.neighbors.get(i);
		if (getNodeIndex(nei) == -1)
		    tmpNodes.add(nei);
	    }
	}
    }
}