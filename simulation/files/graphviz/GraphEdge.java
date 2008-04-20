package simulation.files.graphviz;

import java.io.*;
import java.util.*;
import simulation.files.text.*;

/** Class for representing edges in Graphviz graphs.
 * @author ykk
 */
public class GraphEdge
{
    //Members
    /** Head end of edge.
     */
    public String head;
    /** Tail end of edge.
     */
    public String tail;

    //Methods
    /** Constructor.
     * @param head head end of the edge
     * @param tail tail end of the edge
     */
    public GraphEdge(String head, String tail)
    {
	this.head = head;
	this.tail = tail;
    }

    /** Return string for undirected edge.
     * @return graphviz representation of undirected edge
     */
    public String undirectedString()
    {
	return "\""+head+"\"--\""+tail+"\"";
    }

    /** Return string for directed edge.
     * @return graphviz representation of directed edge
     */
    public String directedString()
    {
	return "\""+head+"\"->\""+tail+"\"";
    }
}