package simulation.socialnet;

import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.results.*;
import simulation.results.distribution.*;
import simulation.utilities.structures.*;
import java.util.*;

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

    /** Get nodes with maximum degree.
     * @param num number of nodes to return
     * @return nodes with maxmimum degree
     */
    public SortedVector maxDegreeNode(int num)
    {
	if (graph.nodes.size() == 0) return null;
	SortedVector nodes = new SortedVector();

	for (int i = 0; i < graph.nodes.size(); i++)
	{
	    GraphNode node = (GraphNode) graph.nodes.get(i);
	    nodes.add(new DegreeNode(node));

	    if (nodes.size() > num)
		nodes.remove(nodes.remove(0));
	}
	
	return nodes;
    }
    
    /** Return clustering coefficient, 
     * giving maximum, minimum, mean and the entire distribution.
     * @return clustering coefficient
     */
    public ResultFullLog clusterCoeff()
    {
	ResultFullLog result = new ResultFullLog();

	for (int i = 0; i < graph.nodes.size(); i++)
	    result.input((new NodeProperty((GraphNode) graph
					   .nodes.get(i))).clusterCoeff());

	return result;
    }

    /** Filter vector of {@link GraphEdge} for 
     * those that causes triadic closure.
     * @param edges vectors of edges to be addded
     * @return edges that causes triadic closure
     */
    public Vector triadicEdges(Vector edges)
    {
	Vector triEdges = new Vector();

	for (int i = 0; i < edges.size(); i++)
	{
	    GraphEdge edge = (GraphEdge) edges.get(i);
	    if (triadicEdge(edge))
		triEdges.add(edge);
	}

	return triEdges;
    }

    /** Indicate if edge 
     * @param edge edge to consider
     * @return return if edge causes triadic closure
     */
    public boolean triadicEdge(GraphEdge edge)
    {
	for (int i = 0; i < edge.head.neighbors.size(); i++)
	    for (int j = 0; j < edge.tail.neighbors.size(); j++)
		if (((GraphNode) edge.head.
		     neighbors.get(i)).compareTo(edge.tail.
						 neighbors.get(j)) == 0)
		    return true;

	return false;
    }
}