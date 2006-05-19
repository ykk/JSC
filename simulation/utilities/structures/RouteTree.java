package simulation.utilities.structures;

import java.util.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Class to store a route tree from/to a node (i.e., root).
 * @author ykk
 */
public class RouteTree
{
    //Members
    /** List of nodes.
     */
    private Vector nodes = new Vector();    
    /** List of route tree nodes.
     */
    private Vector treeNodes = new Vector();
    /** Root node
     */
    private RouteTreeNode root;
    /** Type of root.
     */
    private boolean rootIsSource;

    //Methods
    /** Constructor to create route table.
     * @param root root node, i.e., source/destination
     * @param rootIsSource indicate if root node is source, 
     *                     else is destination
     * @see RouteTreeNode
     */
    public RouteTree(Node root, boolean rootIsSource)
    {
	this.root = new RouteTreeNode(null, root);
	this.rootIsSource = rootIsSource;
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
	return root.node;
    }

    /** Get route from/to specified node.
     * @return route (if rootIsSource, route is not from source).
     */
    public Route getRoute(Node node)
    {
	Route route = new Route(!rootIsSource);
	RouteTreeNode currNode = getRouteTreeNode(node);
	while (currNode.parent != null)
	    if (rootIsSource)
		route.add(new Link(currNode.parent.node, currNode.node));
	    else
		route.add(new Link(currNode.node, currNode.parent.node));

	return route;
    }

    /** Get route tree node of node.
     * @param node node to get route tree node for
     * @return route tree node with node as reference
     */
    private RouteTreeNode getRouteTreeNode(Node node)
    {
	return (RouteTreeNode) treeNodes.get(nodes.indexOf(node));
    }
    
    /** Add child node to parent.
     * @param parent parent node
     * @param node child node
     */
    public void add(Node parent, Node node)
    {
	if (nodes.indexOf(parent) == -1)
	    throw new RuntimeException(this+" cannot from parent "+parent+" in existing tree.");

	RouteTreeNode treeParent =  getRouteTreeNode(parent);
	nodes.add(node);
	treeNodes.add(new RouteTreeNode(treeParent, node));
    }

    /** Add link.
     * @param link link to add to tree
     */
    public void add(Link link)
    {
	if (rootIsSource)
	    add(link.source, link.destination);
	else
	    add(link.destination, link.source);
    }
}
