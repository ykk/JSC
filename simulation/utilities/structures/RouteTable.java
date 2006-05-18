package simulation.utilities.structures;

import java.util.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to store route table from/to a node.
 * @author ykk
 */
public class RouteTable
{
    //Members
    /** Route table.
     */
    int[][] table;
    /** List of nodes.
     */
    private Vector nodes;
    /** Root node
     */
    private Node root;
    /** Type of root.
     */
    private boolean rootIsSource;

    //Methods
    /** Constructor to create route table.
     * @param nodes vector of nodes
     * @param root root node, i.e., source/destination
     * @param rootIsSource indicate if root node is source, 
     *                     else is destination
     */
    public RouteTable(Vector nodes, Node root, boolean rootIsSource)
    {
	this.nodes = nodes;
	this.root = root;
	this.rootIsSource = rootIsSource;
	table = new int[nodes.size()][nodes.size()];
    }

    /** Indicate if root node is source.
     * @return if root node is source, else is destination
     */
    public boolean rootIsSource()
    {
	return rootIsSource;
    }

    /** Return root node.
     * @return reference to root node
     */
    public Node root()
    {
	return root;
    }
}
