package simulation.socialnet;

import simulation.networks.nodes.*;

/** Class to represent edge between {@link GraphNode}.
 * @author ykk
 */
public class GraphEdge
    implements Comparable
{
    //Members
    /** Head node.
     */
    public GraphNode head;
    /** Tail node.
     */
    public GraphNode tail;
    /** Indicate if directed edge.
     * Defaulted to true.
     */
    public boolean directed = true;

    //Methods
    /** Constructor.
     * @param head head node
     * @param tail tail node
     */
    public GraphEdge(GraphNode head, GraphNode tail)
    {
	this.head = head;
	this.tail = tail;
    }

    /** Constructor.
     * @param head head node
     * @param tail tail node
     * @param directed indicated if directed edge
     */
    public GraphEdge(GraphNode head, GraphNode tail, boolean directed)
    {
	this.head = head;
	this.tail = tail;
	this.directed = directed;
    }

    /** Comparable interface.
     * Directed and undirected edges are considered not the same,
     * regardless of the head and tail nodes.
     * @return -1 if edge is not {@link GraphEdge};
     *         0 if the edges are the same, 1 else.
     */
    public int compareTo(Object edge)
    {
	if (edge instanceof GraphEdge)
	{
	    if (((GraphEdge) edge).directed != this.directed)
		return -1;

	    if ((((GraphEdge) edge).head.compareTo(this.head) == 0) && 
		(((GraphEdge) edge).tail.compareTo(this.tail) == 0))
		return 0;	    
	    if (!this.directed &&
		(((GraphEdge) edge).head.compareTo(this.tail) == 0) && 
		(((GraphEdge) edge).tail.compareTo(this.head) == 0))
		return 0;

	    return 1;
	}
	else
	    return -1;
    }
 
    /** String representation.
     * @return string representation
     */
    public String toString()
    {
	return head.descriptor+"-"+tail.descriptor;
    }
   
}
