package simulation.eventbased.mediumaccess;

import simulation.eventbased.*;
import simulation.communications.nodes.*;
import simulation.communications.channels.*;
import simulation.communications.queues.*;
import simulation.communications.packets.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.channels.*;
import simulation.utilities.structures.*;
import simulation.utilities.packetprocessors.*;
import simulation.distributions.*;

/** Class for 802.11b, i.e., WiFi (Wireless Fidelity), broadcast.
 * @author ykk
 */
public class WiFi
    extends CSMA
{
    //Members
    /** 
     */
    /** Propagation delay.
     * Defaulted to 1 microsceond, as in standard specification.
     */
    public double propagation;

    //Methods
    /** Constructor.
     * @param coordinate coordinate of ALOHA node
     * @param channel network channel in use
     * @param commChannel communication channel
     * @param queue queue of the node
     * @param processor reference to packet processor
     * @param waitTime distribution of waiting time
     */
    public WiFiBroadcast(Coordinate coordinate, Channel channel, CommChannel commChannel, 
		Queue queue, PacketProcessor processor, Distribution waitTime)
    {
	super(coordinate, channel, commChannel, queue, processor, waitTime, 1e-6);
    }
}