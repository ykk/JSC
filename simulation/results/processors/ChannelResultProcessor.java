package simulation.results.processors;

import simulation.communications.channels.*;
import simulation.communications.nodes.*;
import simulation.networks.simulator.*;

/** Interface to process channel transmission.
 * @see ResultCommChannel
 * @author ykk
 */
public interface ChannelResultProcessor
{
    /** Process transmission for result.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @param simulator reference to simulator
     */
    public abstract void process(CommNode source, CommNode destination, 
				 Object packet, Simulator simulator);
}