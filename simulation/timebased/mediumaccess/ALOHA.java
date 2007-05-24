package simulation.timebased.mediumaccess;

import simulation.timebased.*;
import simulation.utilities.packetprocessors.*;
import simulation.utilities.references.*;
import simulation.communications.nodes.*;
import simulation.communications.queues.*;
import simulation.communications.channels.*;
import simulation.communications.packets.*;
import simulation.networks.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;

/** Class to implement p-persistent slotted ALOHA.
 * @author ykk
 */
public class ALOHA
    extends MACNode
{
    //Member
    /** Probability of transmission, when there is packet.
     */ 
    public double p;
    /** Currently receiving packet.
     */
    protected Object packet;
    /** Currently receiving from.
     */
    protected CommNode currSource;
    /** Number of ongoing receptions.
     */
    protected int onGoing = 0;
    /** Last receive time.
     */
    protected double lastReceiveTime;

    //Methods
    /** Constructor.
     * @param coordinate coordinate of node
     * @param channel network channel in use
     * @param commChannel communication channel
     * @param queue queue of the node
     * @param processor reference to packet processor
     * @param p probability of transmission
     */
    public ALOHA(Coordinate coordinate, Channel channel, CommChannel commChannel, 
		 Queue queue, PacketProcessor processor, double p) 
    {
	super(coordinate, channel, commChannel, queue, processor);
	this.p = p;
    }

    public void run(double time, Simulator simulator)
    {
	flushReceived(simulator);
	
	if (processor.hasPkt(queue))
	    for (int i = 0; i < transmitPartners.size(); i++)
		commChannel.transmit(this, 
				     (ALOHA) transmitPartners.get(i), 
				     (Packet) processor.get(queue),
				     simulator);
    }

    public void receive(CommNode source, Object packet, Simulator simulator)
    {	
	flushReceived(simulator);

	this.onGoing++;
	this.packet = packet;
	this.currSource = source;
    }

    /** Process received packet from last slot.
     * @param time time reference
     */
    protected void flushReceived(TimeReference time)
    {
	if (lastReceiveTime != time.time())
	{
	    if (onGoing == 1)
		processor.receive(currSource, this, packet, queue);
	    onGoing = 0;
	}

	lastReceiveTime = time.time();
    }

    public Node newNode(Coordinate coordinate)
    {
	return new ALOHA(coordinate, this.channel, this.commChannel, 
			 this.queue.newQueue(), this.processor, this.p);
    }
}