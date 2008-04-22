package simulation.socialnet;

import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.results.distribution.*;
import simulation.utilities.structures.*;

/** Class to process graph.
 * @author ykk
 */
public class GraphProperty
{
    //Members
    /** Reference of graph.
     */
    public Graph graph;

    //Methods
    /** Constructor.
     * @param graph reference to graph
     */
    public GraphProperty(Graph graph)
    {
	this.graph = graph;
    }

    /** Return degree distribution.
     */
    public DataDistribution degreeDistri()
    {
	ResultBins bin = new ResultBins(new 
					DataDistribution(maxDegree()+1,
							 0,
							 1.0));
	for (int i = 1; i < graph.nodes.size(); i++)
	    bin.input(((GraphNode) graph.nodes.get(i)).neighbors.size(), 1);

	return bin.countDistribution();
    }

    /** Get maximum degree.
     * @return max degree
     */
    public int maxDegree()
    {
	GraphNode maxNode = maxDegreeNode();
	if (maxNode == null)
	    return -1;
	else
	    return maxNode.neighbors.size();
    }

    /** Get node with maximum degree.
     * @return node with maxmimum degree
     */
    public GraphNode maxDegreeNode()
    {
	if (graph.nodes.size() == 0) return null;

	GraphNode node = (GraphNode) graph.nodes.get(0);
	for (int i = 1; i < graph.nodes.size(); i++)
	    if (((GraphNode) graph.nodes.get(i)).neighbors.size() > 
		node.neighbors.size())
		node = (GraphNode) graph.nodes.get(i);
	
	return node;
    }
}