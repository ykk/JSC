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

    //Methods
    /** Add node.
     * @param node node to add
     */
    public void addNode(String node)
    {
	nodes.add(node);
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
	for (int i = 0; i < nodes.size(); i++)
	    file.content.add("\""+nodes.get(i)+"\"");
	for (int i = 0; i < edges.size(); i++)
	    file.content.add(((GraphEdge) edges.get(i)).undirectedString());
	file.content.add("}");
	return file;
    }
}
