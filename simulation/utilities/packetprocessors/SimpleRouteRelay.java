package simulation.utilities.packetprocessors;

import simulation.communications.packets.*;
import simulation.communications.nodes.*;
import simulation.communications.queues.*;
import simulation.results.*;
import simulation.utilities.references.*;

/** Packet processor that simply rely packets.
 * @author ykk
 */
public class SimpleRouteRelay
    extends PacketProcessor
{
    //Members
    /** Time reference.
     */
    public TimeReference timeRef;
    /*

    //Methods
    /** Constructor for packet processor.
     * @param timeRef time reference for packet generation
     */
    public SimpleRouteRelay(TimeReference timeRef)
    {
	this.timeRef = timeRef;
    }

    /** Overwritten.
     */
    public void receive(CommNode source, Object packet, simulation.communications.queues.Queue queue)
    {
    }

    /** Function to receive packets.
     * Overwriting receive function.
     * @param source source node
     * @param currNode current node
     * @param packet packet received
     * @param queue queue of the node
     */
    public void receive(CommNode source, CommNode currNode, Object packet, 
			simulation.communications.queues.Queue queue)
    {
	RoutedPacket pkt = (RoutedPacket) packet;
	if (pkt.route.destination() == currNode)
	    System.out.println(this+" received "+pkt);
	else if (pkt.route.inRoute(currNode))
	    queue.receive(packet);
    }

    /** Function to get next packet to send
     * @return packet to send and null if no packet available
     */
    public Object get(simulation.communications.queues.Queue queue)
    {
	return queue.get();
    }
}