package simulation.results.processors;

import simulation.communications.channels.*;
import simulation.communications.nodes.*;
import simulation.communications.packets.*;
import simulation.networks.simulator.*;
import java.util.*;

/** Class to process channel transmission with DataAckFrame.
 * @see DataAckFrame
 * @author ykk
 */
public class DataAckChannelProcessor
    implements ChannelResultProcessor
{
    //Members
    /** Vector to hold results for each node.
     */
    public Vector results;
    /** Size of packet.
     */

    //Methods
    /** Process transmission for result.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @param simulator reference to simulator
     */
    public void process(CommNode source, CommNode destination, 
			Object packet, Simulator simulator)
    {

    }
}