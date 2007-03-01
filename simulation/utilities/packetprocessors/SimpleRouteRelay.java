package simulation.utilities.packetprocessors;

import simulation.communications.packets.*;
import simulation.communications.nodes.*;
import simulation.communications.queues.*;
import simulation.results.*;
import simulation.utilities.references.*;
import simulation.utilities.processors.*;

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
    /** Result processing object.
     */
    public ResultProcessor result;
    /** Objects to associated with delay
     */
    public static final String delay = "delay";
    public static final String start = "starttime";
    public static final String end = "endtime";

    //Methods
    /** Constructor for packet processor.
     * @param timeRef time reference for packet generation
     */
    public SimpleRouteRelay(TimeReference timeRef)
    {
	this.timeRef = timeRef;
	result = new ResultProcessor();
	result.register(delay, new ResultPrint());
	result.register(start, new ResultPrint());
	result.register(end, new ResultPrint());
    }

    /** Constructor for packet processor with result reference.
     * @param timeRef time reference for packet generation
     * @param result result object
     */
    public SimpleRouteRelay(TimeReference timeRef, ResultProcessor result)
    {
	this.timeRef = timeRef;
	this.result = result;
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

	if (pkt.route.nextHop(source) == currNode)
	    if (pkt.route.destination() == currNode)
	    {
		pkt.recordEnd(timeRef.time());
		result.input(delay, pkt.delay());
		result.input(start, pkt.startTime);
		result.input(end, pkt.endTime);
	    }
	    else
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