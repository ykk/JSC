package simulation.files.graphviz;

import java.io.*;
import java.util.*;
import simulation.files.text.*;

/** Class for representing edges in Graphviz graphs.
 * @author ykk
 */
public class GraphEdge
    implements Comparable
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

    public String toString()
    {
	return head+"-"+tail;
    }
    
    /** Comparable interface.
     * Compare strings for head and tail if GraphEdge,
     * else return -1.
     * @return 0 if GraphEdge and same, else -1
     */
    public int compareTo(Object object)
    {
	if ((object instanceof GraphEdge) &&
	    head.compareTo(((GraphEdge) object).head) == 0 &&
	    tail.compareTo(((GraphEdge) object).tail) == 0)
	    return 0;
	else
	    return -1;
    }
}