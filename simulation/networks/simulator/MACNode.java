package simulation.networks.simulator;

import simulation.communications.nodes.*;
import simulation.communications.channels.*;
import simulation.communications.queues.*;
import simulation.networks.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;
import simulation.utilities.packetprocessors.*;

/** Abstract class to define medium access control node.
 * @author ykk
 */
public abstract class MACNode
    extends CommNode
{
    //Members
    /** Packet processor.
     */
    public PacketProcessor processor;

    //Methods
    /** Constructor.
     * @param coordinate coordinate of node
     * @param channel network channel in use
     * @param commChannel communication channel
     * @param queue queue of the node
     * @param processor reference to packet processor
     */
    public MACNode(Coordinate coordinate, Channel channel, CommChannel commChannel, 
		   Queue queue, PacketProcessor processor)
    {
	super(coordinate, channel, commChannel, queue);
	this.processor = processor;
    }

    /** Receive packet from a node.
     * @param source source node of packet
     * @param packet packet delivered
     * @param simulator reference to simulator
     * @see #receive(CommNode source, Object packet)
     */
    public abstract void receive(CommNode source, Object packet, Simulator simulator);

    /** Receive packet from a node -- replaced by 
     * {@link #receive(CommNode source, Object packet, Simulator simulator)}.
     * @param source source node of packet
     * @param packet packet delivered
     * @see #receive(CommNode source, Object packet, Simulator simulator)
     */
    public void receive(CommNode source, Object packet)
    {
	throw new RuntimeException(this+" called receive(CommNode source, Object packet) function that has been replaced by receive(CommNode source, Object packet, Simulator simulator).");
    }

    /** Create new node with the specified coordinate.
     * Generate new queue for node.
     * @param coordinate coordinate of new node
     * @return new node instance
     */
    public Node newNode(Coordinate coordinate)
    {
	MACNode node = (MACNode) super.newNode(coordinate);
	node.commChannel = this.commChannel;
	node.queue = this.queue.newQueue();
	node.processor = this.processor;
	return node;
    }
}