package simulation.timebased.mediumaccess;

import simulation.timebased.*;
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
    extends simulation.networks.simulator.MACNode
    implements RunObject
{
    /** Receive packet from a node.
     * @param source source node of packet
     * @param packet packet delivered
     * @param simulator reference to simulator
     */
    public abstract void receive(CommNode source, Object packet, simulation.timebased.Simulator simulator);

    /** Receive packet from a node.
     * @param source source node of packet
     * @param packet packet delivered
     * @param simulator reference to simulator
     */
    public void receive(CommNode source, Object packet, simulation.networks.simulator.Simulator simulator)
    {
	receive(source, packet, (simulation.timebased.Simulator) simulator);
    }

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
	super(coordinate, channel, commChannel, queue, processor);
    }
}
