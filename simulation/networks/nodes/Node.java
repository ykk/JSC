package simulation.networks.nodes;

import simulation.networks.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;
import java.util.*;

/** Basic class for a network node.
 * @author ykk
 */
public class Node
    extends Coordinate
    implements NodeFactory
{
    //Members
    /** Nodes that current node can send to.
     * Defaulted to null.
     * @see #getNeighbors(Vector nodes)
     */
    public Vector transmitPartners;
    /** Nodes that current node can receive from.
     * Defaulted to null.
     * @see #getNeighbors(Vector nodes)
     */
    public Vector receivePartners;
    /** Channel used by the nodes.
     */
    public Channel channel;

    //Methods
    /** Constructor to get a new node.
     * @param coordinate coordinate for new node
     * @param channel channel between nodes
     */
    public Node(Coordinate coordinate, Channel channel)
    {
	super(coordinate.x, coordinate.y);
	this.channel = channel;
    }

    /** Function to search for neighbors.
     * Will create vector objects for {@link #transmitPartners} and 
     * {@link #receivePartners}.  If channel is symmetrical, the object
     * will be shared, i.e, the same object.
     * @param nodes vector of nodes that can be neighbors
     * @see #transmitPartners
     * @see #receivePartners
     * @see SymmetryChannel
     */
    public void getNeighbors(Vector nodes)
    {
	//Create vectors
	transmitPartners = new Vector();
	if (channel instanceof SymmetryChannel)
	    receivePartners = transmitPartners;
	else
	    receivePartners = new Vector();
	
	//Find neighbors
	Node tmpNode;
	for (int i = 0; i < nodes.size(); i++)
	{
	    tmpNode = (Node) nodes.get(i);
	    if (this.canTransmit(tmpNode))
		transmitPartners.add(tmpNode);

	    if (!(channel instanceof SymmetryChannel))
		if (this.canReceive(tmpNode))
		    receivePartners.add(tmpNode);
	}
    }

    /** To check if it is able to transmit to a specified node.
     * @param destination destination node to transmit to
     * @return if able to transmit to destination
     */
    public boolean canTransmit(Node destination)
    {
	return channel.canTransmit(this, destination);
    }

    /** To check if it is able to receive from a specified node.
     * @param source source node to receive from
     * @return if able to receive from source
     */
    public boolean canReceive(Node source)
    {
	return channel.canReceive(this, source);
    }

    public Node newNode(Coordinate coordinate)
    {
	return new Node(coordinate,this.channel);
    }
}
