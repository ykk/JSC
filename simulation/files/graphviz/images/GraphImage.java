package simulation.files.graphviz.images;

import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.files.graphviz.*;

/** Class to create images of {@link simulation.networks.Graph} 
 * using {@link GraphViz}.
 * @author ykk
 */
public class GraphImage
    extends simulation.files.graphviz.Graph
{
    //Methods
    /** Input graph.
     * @param graph reference to graph
     */
    public void createGraph(simulation.networks.Graph graph)
    {
	for (int i = 0; i < graph.nodes.size(); i++)
	{
	    GraphNode node = (GraphNode) graph.nodes.get(i);
	    addNode(node.descriptor);

	    for (int j = 0; j < node.neighbors.size(); j++)
	    {
		GraphNode neighbor = (GraphNode) node.neighbors.get(j);
		addEdge(node.descriptor, neighbor.descriptor);
	    }
	}
    }
}