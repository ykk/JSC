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
    public abstract boolean canTx(Node source, Node destination);
}
