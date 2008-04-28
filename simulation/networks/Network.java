package simulation.networks;

import simulation.files.images.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.nodes.*;
import simulation.networks.channels.*;
import simulation.communications.channels.*;
import simulation.communications.nodes.*;
import java.util.*;

/** Basic class for network.
 * @author ykk
 */
public class Network
{
    //Members
    /** Vector to hold nodes.
     * Defaulted to empty vector.
     */
    public Vector nodes = new Vector();

    /** Area definition for network.
     */
    public NetworkArea netArea;

    /** Node factory to generate nodes.
     */
    public NodeFactory nodeFactory;

    //Methods
    /** Empty constructor for extension.
     */
    protected Network()
    {
    }
    
    /** Constructor for generic network.
     * @param netArea network area definition
     * @param nodeFactory factory to generate nodes
     * @param pointProcess process to generate coordinates of nodes
     */
    public Network(NetworkArea netArea, NodeFactory nodeFactory, 
		   PointProcess pointProcess)
    {
	this.netArea = netArea;
	this.nodeFactory = nodeFactory;
	
	generateNodes(pointProcess);
	getNeighbors();
    }

    /** Function to generate nodes.
     * @param pointProcess process to generate coordinates of nodes
     */
    public void generateNodes(PointProcess pointProcess)
    {
	nodes.clear();

	//Create nodes
	Vector positions = pointProcess.getCoordinates(netArea);
	for (int i = 0; i < positions.size(); i++)
	    nodes.add(nodeFactory.newNode((Coordinate) positions.get(i)));
    }

    /** Function to generate neighbors of nodes.
     */
    public void getNeighbors()
    {
	//Get neighbors
	Node tmpNode;
	for (int j = 0; j < nodes.size(); j++)
	    ((Node) nodes.get(j)).getNeighbors(nodes);
    }

    /** Function to draw network.
     * @param filename name of image file
     * @param imageFormat format of image
     * @param resolution number of pixels per unit of coordinate
     * @param nodeSize size of node in number of pixels
     * @see ImageFile#imageFormat
     */
    public void draw(String filename, int imageFormat, int resolution, 
		     int nodeSize)
    {
	NetworkImage image = new NetworkImage(filename, imageFormat, 
					      this, resolution, nodeSize);
	image.draw();
	image.write();
    }
    
    /** Prune networks with communication channel.
     * This removes links that has probability of reception below specified.
     * Only for network of {@link CommNode}.
     * Caution: this assumes that nodes can interfer if and not if 
     * they can communicate, since the network channel defines the 
     * interference range, while the communication channel defines 
     * the communication reach.
     * @param commChannel communication channel used
     * @param minProb minimum reception probability
     */
    public void prune(CommChannel commChannel, double minProb)
    {
	CommNode currNode, tmpNode;
	for (int i = 0; i < nodes.size(); i++)
	{
	    currNode = (CommNode) nodes.get(i);

	    for (int j = 0; j < currNode.transmitPartners.size(); j++)
		if (commChannel.transmitProb(currNode,
					     (CommNode) currNode.
					     transmitPartners.get(j)) 
		    < minProb)
		{
		    currNode.transmitPartners.remove(j);
		    j--;
		}

	    for (int j = 0; j < currNode.receivePartners.size(); j++)
		if (commChannel.transmitProb((CommNode) currNode.
					     transmitPartners.get(j),
					     currNode) < minProb)
		{
		    currNode.receivePartners.remove(j);
		    j--;
		}
	}
    }

    /** Function to test network by drawing it.
     * @param args 1st argument is density of network
     */
    public static void main(String[] args)
    {
	Network testNet = new Network(new CircleNetArea(10), 
				      new Node(new Coordinate(0,0), 
					       new ZeroOne(1)), 
				      new Poisson(Double.
						  parseDouble(args[0])));
	testNet.draw("testNetworkImage.jpg", ImageFile.JPEG_TYPE, 100, 20);
    }
}
