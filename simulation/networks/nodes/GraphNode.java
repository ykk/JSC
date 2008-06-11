package simulation.networks.nodes;

import simulation.utilities.structures.*;
import java.util.*;

/** Basic class for node for an undirected graph.
 * @author ykk
 */
public class GraphNode
    implements Comparable
{
    //Members
    /** Descriptor.
     */
    public String descriptor;
    /** Vector of neighbors
     */
    public Vector neighbors = new SortedUniqueVector();
    /** Indicate if self neighboring allowed.
     * True by default.
     */
    public boolean selfNeighbor = true;

    //Methods
    /** Constructor.
     * @param descriptor description of node
     */
    public GraphNode(String descriptor)
    {
	this.descriptor = descriptor;
    }

    /** Add neighbor.
     * For unique collection, comparable object must be given.
     * @param node neighbor
     * @param unique indicate if unique collection is desired
     */
    public void addNeighbor(Object node, boolean unique)
    {
	if (unique)
	    ((SortedUniqueVector) neighbors).add((Comparable) node);
	else
	    neighbors.add(node);
    }

    /** Add neighbor, maintaining unique collection
     * @param node neighbor
     */
    public void addNeighbor(Comparable node)
    {
	if (!selfNeighbor && (node.compareTo(this) == 0))
	    return;
	addNeighbor(node,true);
    }
    
    /** Compare to GraphNode, else return -1.
     * @param node node to compare to
     * @return -1 if not GraphNode else, the comparison of the descriptor.
     */
    public int compareTo(Object node)
    {
	if (node instanceof GraphNode)
	    return descriptor.compareTo(((GraphNode) node).descriptor);
	else
	    return -1;
    }

    public String toString()
    {
	return super.toString()+":"+descriptor;
    }
}
