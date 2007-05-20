package simulation.utilities.structures;

import java.util.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to encapsulate a node to form a tree.
 * @author ykk
 */
public class RouteTreeNode
{
    //Members
    /** Node reference.
     */
    public Node node;
    /** Reference to parent.
     */
    public RouteTreeNode parent;

    //Methods
    /** Constructor for a route tree node.
     * @param parent parent of node
     * @param node node reference
     * @see RouteTree
     */
    public RouteTreeNode(RouteTreeNode parent, Node node)
    {
	this.parent = parent;
	this.node = node;
    }

    /** String representation.
     * @param string represenation.
     */
    public String toString()
    {
	return parent+"->"+node;
    }
}
