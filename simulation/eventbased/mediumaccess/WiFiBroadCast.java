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

/** Class for IEEE 802.11/WiFi (Wireless Fidelity).
 * Implements broadcast.
 * @author ykk
 */
public class WiFiBroadcast
    extends CSMA
{
    //Members
    /** Reference to specification.
     */
    public WiFiSpec spec;
    /** Contention window counter.
     */
    protected int cw;

    //Methods
    /** Constructor.
     * Propagation delay set at 1 us. 
     * @param coordinate coordinate of ALOHA node
     * @param channel network channel in use
     * @param commChannel communication channel
     * @param queue queue of the node
     * @param processor reference to packet processor
     * @param spec parameter specification for wifi
     */
    public WiFiBroadcast(Coordinate coordinate, Channel channel, 
			 CommChannel commChannel, Queue queue, PacketProcessor processor, 
			 WiFiSpec spec)
    {
	super(coordinate, channel, commChannel, queue, processor, null, 1e-6);
	this.spec = spec;
	cw = spec.minCW;
    }

    
}