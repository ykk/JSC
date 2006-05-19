package simulation.utilities.structures;

import java.util.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.channels.*;
import simulation.utilities.structures.*;
import simulation.files.images.*;

/** Class to store a route tree from/to a node (i.e., root).
 * @author ykk
 */
public class RouteTree
{
    //Members
    /** List of nodes.
     */
    public Vector nodes = new Vector();    
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
	nodes.add(root);
	treeNodes.add(this.root);
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

    /** Get parent for specified node.
     * @param node node to get parent for
     * @return parent (if rootIsSource, parent is source)
     */
    public Node parent(Node node)
    {
	RouteTreeNode tmpRTNode = getRouteTreeNode(node).parent;
	return (tmpRTNode == null)?null:tmpRTNode.node;
    }

    /** Get route from/to specified node.
     * @param node node to get route for
     * @return route (if rootIsSource, route is not from source)
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
	    throw new RuntimeException(this+" cannot find parent "+parent+" in existing tree.");

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

    /** Function to draw route tree.
     * @param filename name of image file
     * @param imageFormat format of image
     * @param resolution number of pixels per unit of coordinate
     * @param nodeSize size of node in number of pixels
     * @see ImageFile#imageFormat
     */
    public void draw(String filename, int imageFormat,  Network network, int resolution, int nodeSize)
    {
	NetworkRouteImage image = new NetworkRouteImage(filename, imageFormat, network, resolution, nodeSize);
	image.draw(this);
	image.write();
    }

    /** Function to test route tree by drawing it.
     * @param args 1st argument is density of network
     */
    public static void main(String[] args)
    {
	//Create network
	Network testNet = new Network(new CircleNetArea(10), 
				      new Node(new Coordinate(0,0), new ZeroOne(1)), 
				      new Poisson(Double.parseDouble(args[0])));
	//Create random route tree
	RouteTree testRT = new RouteTree((Node) testNet.nodes.get(0), true);
	for (int i = 1; i < testNet.nodes.size(); i++)
	    testRT.add((Node) testRT.nodes.get((int) Math.floor(Math.random()*testRT.nodes.size())),
		       (Node) testNet.nodes.get(i));
	//Draw routing tree
	testRT.draw("testNetworkRouteImage.jpg", ImageFile.JPEG_TYPE, testNet, 100, 20);
    }
}
