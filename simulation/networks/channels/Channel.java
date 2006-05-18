package simulation.networks.channels;

import simulation.networks.nodes.*;

/** Abstract class for a channel.
 * @author ykk
 */
public abstract class Channel
{
    /** Check if source node can transmit directly to destination.
     * @param source source node
     * @param destination destination node
     * @return if source node can transmit directly to desination
     */
    public abstract boolean canTransmit(Node source, Node destination);

    /** Check if destination node can receive directly to source.
     * @param destination destination node
     * @param source source node
     * @return if destination node can receive directly to source
     */
    public abstract boolean canReceive(Node destination, Node source);
}
