package simulation.utilities.packetprocessors;

/** Packet processor that maintains full queue.
 * @author ykk
 */
public class AlwaysFull
    extends PacketProcessor
{
    //Members
    /** 
     */

    //Methods
    /** Constructor for packet processor.
     */
    

    /** Function to receive packets.
     * @param packet packet received
     * @param queue queue of the node
     */
    public void receive(Object packet, simulation.communications.queues.Queue queue)
    {
	
    }

    /** Function to get next packet to send
     * @return packet to send and null if no packet available
     */
    public Object get(simulation.communications.queues.Queue queue)
    {
	return null;
    }
}