package simulation.utilities.packetprocessors;

import simulation.communications.packets.*;
import simulation.results.*;
import simulation.utilities.references.*;

/** Packet processor that maintains full queue.
 * @author ykk
 */
public class AlwaysFull
    extends PacketProcessor
{
    //Members
    /** Packet factory to generate packet.
     * Length defaulted to 8 bytes.
     */
    public TimedPacket packetFactory;
    /** Time reference.
     */
    public TimeReference timeRef;
    /** Delay result container.
     */
    public Result delay = new Result();
    /** Reference to last packet.
     */
    private TimedPacket lastPacket=null;

    //Methods
    /** Constructor for packet processor.
     * @param length length of packet to generate (in bytes)
     * @param timeRef time reference for packet generation
     */
    public AlwaysFull(int length)
    {
	packetFactory = new TimedPacket(length);
	this.timeRef = timeRef;
    }

    /** Function to receive packets.
     * @param packet packet received
     * @param queue queue of the node
     */
    public void receive(Object packet, simulation.communications.queues.Queue queue)
    {
	if (packet == lastPacket) return;
	((TimedPacket) packet).endTime = timeRef.time();
	delay.input(((TimedPacket) packet).delay());
	lastPacket = (TimedPacket) packet;
    }

    /** Function to get next packet to send
     * @return packet to send and null if no packet available
     */
    public Object get(simulation.communications.queues.Queue queue)
    {
	return packetFactory.duplicate(timeRef.time());
    }
}