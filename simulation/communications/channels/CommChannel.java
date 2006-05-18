package simulation.communications.channels;

import simulation.networks.nodes.*;

/** Abstract class for a communication channel.
 * @author ykk
 */
public abstract class CommChannel
{
    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted
     */
    public abstract boolean transmit(CommNode source, CommNode destination, Object packet);
}
