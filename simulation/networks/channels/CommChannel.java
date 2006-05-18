package simulation.networks.channels;

import simulation.networks.nodes.*;

/** Interface for a communication channel.
 * @author ykk
 */
public interface CommChannel
{
    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted
     */
    public boolean transmit(Node source, Node destination, Object packet);
}
