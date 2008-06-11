package simulation.networks;

import simulation.utilities.structures.*;
import simulation.networks.nodes.*;
import simulation.files.graphviz.images.*;
import simulation.files.graphviz.GraphViz;
import java.util.*;

/** Basic class for graph.
 * @see GraphNode
 * @author ykk
 */
public class Graph
{
    //Members
    /** Vector to hold nodes.
     * Defaulted to empty vector.
     */
    public Vector nodes = new SortedUniqueVector();

    //Methods
    /** Add node.
     * For unique collection, comparable object must be given.
     * @param node node to add
     * @param unique indicate if unique collection is desired
     */
    public void addNode(Object node, boolean unique)
    {
	if (unique)
	    ((SortedUniqueVector) nodes).add((Comparable) node);
	else
	    nodes.add(node);
    }

    /** Add node, maintaining unique collection
     * @param node node to add
     */
    public void addNode(Comparable node)
    {
	addNode(node,true);
    }

    /** Get node index.
     * @param object node to compare with
     * @return index if found, else -1.
     * @see GraphNode
     */
    public int getNodeIndex(GraphNode object)
    {
	int i = ((SortedVector) nodes).index(object);
	if (i != 0)
	{
	    Comparable inSet = (Comparable) nodes.get(i-1);
	    if (object.compareTo(inSet) == 0)
		return i-1;
	}

	return -1;
    }

    /** Get node index by descriptor.
     * Ensures that nodes are arranged by descriptor.
     * @param descriptor descriptor of node
     * @return index if found, else -1.
     * @see GraphNode
     */
    public int getNodeIndex(String descriptor)
    {
	return getNodeIndex(new GraphNode(descriptor));
    }

    /** Get node.
     * @param object node to compare with
     * @return node if found, else null
     */
    public GraphNode getNode(GraphNode object)
    {
	int i = getNodeIndex(object);
	if (i == -1)
	    return null;
	else
	    return (GraphNode) nodes.get(i);
    }

    /** Get node by descriptor.
     * @param descriptor descriptor of node
     * @return node if found, else null
     */
    public GraphNode getNode(String descriptor)
    {
	int i = getNodeIndex(descriptor);
	if (i == -1)
	    return null;
	else
	    return (GraphNode) nodes.get(i);
    }

    /** Add missing edges in graph, i.e., ensure bidirectionality.
     */
    public void addBiEdges()
    {
	for (int i = 0; i < nodes.size(); i++)
	{
	    GraphNode node = (GraphNode) nodes.get(i);
	    for (int j = 0; j < node.neighbors.size(); j++)
		((GraphNode) node.neighbors.get(j)).addNeighbor(node);
	}
    }

    /** Draw graph using Graphviz.
     * @param filename filename of output
     * @param format format of output (see {@link GraphViz#format})
     * @param command format of output (see {@link GraphViz#command})
     */
    public void drawGraph(String filename, String format, String command)
    {
	drawGraph(filename, format, command, false, false);
    }

    /** Draw graph using Graphviz.
     * @param filename filename of output
     * @param format format of output (see {@link GraphViz#format})
     * @param command format of output (see {@link GraphViz#command})
     * @param removeSelfLoop indicate if to remove self loop
     * @param removeDup indicate if to remove duplicates of edge
     */
    public void drawGraph(String filename, String format, String command,
			  boolean removeSelfLoop, boolean removeDup)
    {
	getGraph(filename, format, command,
		 removeSelfLoop, removeDup).execPlot();
    }

    /** Draw graph using Graphviz.
     * @param filename filename of output
     * @param format format of output (see {@link GraphViz#format})
     * @param command format of output (see {@link GraphViz#command})
     * @param removeSelfLoop indicate if to remove self loop
     * @param removeDup indicate if to remove duplicates of edge
     * @return image to be drawn
     */
    public GraphImage getGraph(String filename, String format, String command,
			  boolean removeSelfLoop, boolean removeDup)
    {
	GraphImage image = new GraphImage();
	image.graphFilename = filename;
	image.command = command;
	image.format = format;
	image.createGraph(this);
	if (removeSelfLoop) image.removeSelfLoop();
	if (removeDup) image.removeDuplicate();
	return image;
    }
}
