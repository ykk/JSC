package simulation.networks.channels;

import simulation.networks.channels.*;
import simulation.networks.nodes.*;

/** Abstract class for a symmetrical channel.
 * @author ykk
 */
public abstract class SymmetryChannel
    extends Channel
{
    /** Check if source node can communicate directly to destination.
     * @param source source node
     * @param destination destination node
     * @return if source node can communicate (i.e., transmit and receive)
     *         directly to destination
     */
    public abstract boolean canCommunicate(Node source, Node destination);

    /** Check if source node can transmit directly to destination.
     * @param source source node
     * @param destination destination node
     * @return if source node can transmit directly to destination
     * @see #canCommunicate(Node source, Node destination)
     */
    public boolean canTransmit(Node source, Node destination)
    {
	return canCommunicate(source,destination);
    }

    /** Check if destination node can receive directly to source.
     * @param destination destination node
     * @param source source node
     * @return if destination node can receive directly to source
     * @see #canCommunicate(Node source, Node destination)
     */
    public boolean canReceive(Node destination, Node source)
    {
	return canCommunicate(source,destination);
    }
}
