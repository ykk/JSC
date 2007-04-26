package simulation.utilities.packetprocessors;

import simulation.communications.queues.*;
import simulation.communications.nodes.*;

/** Abstract class for packet processors.
 * @author ykk
 */
public abstract class PacketProcessor
{
    /** Function to receive packets.
     * @param source source node
     * @param currNode current  node
     * @param packet packet received
     * @param queue queue of the node
     */
    public abstract void receive(CommNode source, CommNode currNode, Object packet, 
				 simulation.communications.queues.Queue queue);

    /** Function to get next packet to send
     * @return packet to send and null if no packet available
     */
    public abstract Object get(simulation.communications.queues.Queue queue);

    /** Function to check if there is packet to send.
     * @return packet to send or not
     */
    public abstract boolean hasPkt(simulation.communications.queues.Queue queue);
}