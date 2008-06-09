package simulation.socialnet;

import simulation.networks.nodes.*;

/** Class to hold node and degree of node.
 * @author ykk
 */
public class DegreeNode
    implements Comparable
{
    //Members
    /** Degree of node.
     */
    public int degree;
    /** Graph node.
     */
    public GraphNode node;

    //Methods
    /** Constructor.
     * @param node graph node to reference
     */
    public DegreeNode(GraphNode node, int degree)
    {
	this.node = node;
	this.degree = degree;
    }

    /** Compare to DegreeNode, else return -1.
     * @param node node to compare to
     * @return -1 if not DegreeNode else,
     *         the difference between this node's degree with the specified
     */
    public int compareTo(Object node)
    {
	if (node instanceof DegreeNode)
	    return degree - ((DegreeNode) node).degree;
	else
	    return -1;
    }

    /** String representation.
     * @return string with degree and name
     */
    public String toString()
    {
	return node.toString()+"-"+degree+" ("+
	    (new NodeProperty(node)).clusterCoeff()+")";
    }
}