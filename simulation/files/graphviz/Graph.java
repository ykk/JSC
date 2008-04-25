package simulation.files.graphviz;

import java.io.*;
import java.util.*;
import simulation.files.text.*;

/** Class to generate Graphviz graph input files for undirected graph.
 * @author ykk
 */
public class Graph
    extends GraphViz
{
    //Members
    /** Nodes of graph.
     */
    public Vector nodes = new Vector();
    /** Edges of graph.
     */
    public Vector edges = new Vector();
    /** Default node configuration.
     */
    public GraphNode defaultNode = new GraphNode("node");

    //Methods
    /** Add node.
     * @param node node to add
     */
    public void addNode(GraphNode node)
    {
	nodes.add(node);
    }

    /** Add node.
     * @param node node to add
     */
    public void addNode(String node)
    {
	nodes.add(new GraphNode(node));
    }

    /** Add edge.
     * @param head head end of the edge
     * @param tail tail end of the edge
     */
    public void addEdge(String head, String tail)
    {
	edges.add(new GraphEdge(head, tail));
    }

    /** Return input file for Graphviz.
     * @return input file
     */
    public FileVector getFile()
    {
	FileVector file = new FileVector(filename+".dot");
	file.content.add("graph {");
	file.content.add(defaultNode.quotelessString());

	for (int i = 0; i < nodes.size(); i++)
	    file.content.add(nodes.get(i));
	for (int i = 0; i < edges.size(); i++)
	    file.content.add(((GraphEdge) edges.get(i)).undirectedString());
	file.content.add("}");

	return file;
    }

    /** Remove self-loop.
     */
    public void removeSelfLoop()
    {
	for (int i = 0; i < edges.size(); i++)
	{
	    GraphEdge edge = (GraphEdge) edges.get(i);
	    if (edge.head.compareTo(edge.tail) == 0)
	    {
		edges.remove(i);
		i--;
	    }
	}
    }

    /** Remove duplicate edges.
     * Remove A->B if B->A is present, since graph is undirected.
     */
    public void removeDuplicate()
    {
	for (int i = 0; i < edges.size(); i++)
	{
	    GraphEdge curr = (GraphEdge) edges.get(i);
	    for (int j = i+1; j < edges.size(); j++)
	    {
		GraphEdge compa = (GraphEdge) edges.get(j);
		if ((curr.compareTo(compa) == 0) ||
		    (curr.compareTo(new 
				    GraphEdge(compa.tail, compa.head)) == 0))
		{
		    edges.remove(j);
		    j--;
		}
	    }
	}
    }

    /** Color nodes in a cluster/graph.
     * @param graph graph to color
     * @param color color to use
     */
    public void colorNodes(simulation.networks.Graph graph, String color)
    {
	for (int i = 0; i < nodes.size(); i++)
	{
	    GraphNode node = (GraphNode) nodes.get(i);
	    if (graph.getNodeIndex(node.descriptor) != -1)
	    {
		node.color = color;
		node.fillColor = color;
	    }
	}
    }

    /** Color edges in a cluster/graph.
     * @param graph graph to color
     * @param color color to use
     */
    public void colorEdges(simulation.networks.Graph graph, String color)
    {
	for (int i = 0; i < edges.size(); i++)
	{
	    GraphEdge edge = (GraphEdge) edges.get(i);
	    if (graph.getNodeIndex(edge.head) != -1 &&
		graph.getNodeIndex(edge.tail) != -1)
	    {
		edge.color = color;
		edge.fillColor = color;
	    }
	}
    }

    /** Get node with certain descriptor.
     * @param desc descriptor
     * @return node with descriptor or null if not found
     */
    public GraphNode getNode(String desc)
    {
	for (int i = 0; i < nodes.size(); i++)
	    if (((GraphNode) nodes.get(i)).descriptor.compareTo(desc) == 0)
		return (GraphNode) nodes.get(i);
	
	return null;
    }
}
