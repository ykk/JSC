package simulation.communications.nodes;

import simulation.networks.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;
import simulation.communications.channels.*;
import simulation.communications.queues.*;

/** Basic class for a communication node.
 * @author ykk
 */
public abstract class CommNode
    extends Node
{
    //Members
    /** Communication channel used by the nodes.
     */
    public CommChannel commChannel;
    /** Link layer queue.
     */
    public Queue queue;

    //Methods
    /** Constructor to get a new node.
     * @param coordinate coordinate for new node.
     * @param channel channel between nodes
     * @param commChannel channel used from communicating between nodes
     */
    public CommNode(Coordinate coordinate, Channel channel, CommChannel commChannel, Queue queue)
    {
	super(coordinate, channel);
	this.commChannel = commChannel;
	this.queue = queue;
    }

    /** Receive packet from a node.
     * @param source source node of packet
     * @param packet packet delivered
     */
    public abstract void receive(CommNode source, Object packet);

    /** Transmit from current node to specified node.
     * @param destination specified node to send packet to
     * @param packet packet to deliver
     * @return if transmission is successful
     */
    public boolean transmit(CommNode destination, Object packet)
    {
	return commChannel.transmit(this, destination, packet);
    }
}
