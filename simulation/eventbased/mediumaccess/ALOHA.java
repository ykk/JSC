package simulation.eventbased.mediumaccess;

import simulation.eventbased.*;
import simulation.communications.nodes.*;
import simulation.communications.channels.*;
import simulation.communications.queues.*;
import simulation.communications.packets.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.networks.channels.*;
import simulation.utilities.structures.*;
import simulation.utilities.packetprocessors.*;
import simulation.distributions.*;

/** Class to implement ALOHA medium access control.
 * @author ykk
 */
public class ALOHA
    extends MACNode
{
    //Members
    /** Current state of node.
     * @see #stateIdle
     * @see #stateWaiting
     * @see #stateTransmitting
     * @see #stateReceiving 
     * @see #stateCollided
     */
    public int state = stateIdle; //Idle
    /** Idle state.
     */
    public static final int stateIdle = 0;
    /** Waiting state.
     */
    public static final int stateWaiting = 1;
    /** Transmitting state.
     */
    public static final int stateTransmitting = 2;
    /** Receiving state.
     */
    public static final int stateReceiving = 3;
    /** Collided state.
     */
    public static final int stateCollided = 4;
    /** Array of events node can process.
     * Events = 0 "Receive Ended",
     *          1 "Transmission Ended", 
     *          2 "Wait Ended"
     */
    public static final String[] events = {"Receive Ended", "Transmission Ended", "Wait Ended"};
    /** Number of ongoing receptions.
     */
    private int onGoing = 0;
    /** Indicate of transmitting.
     */
    private boolean isTransmitting = false;
    /** Distribution of random wait.
     */
    Distribution waitTime;
    /** Currently receiving packet.
     */
    Object packet;

    //Methods
    /** Event triggered interface.
     * State diagram is as shown below.
     * <BR><img src="doc-files/aloha.jpg"><BR>
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     * @see #events
     * @see #state
     * @see #receive(CommNode source, Object packet, Simulator simulator)
     * @see #trigger(Simulator simulator)
     */
    public void run(double time, String event, Simulator simulator)
    {
	switch (Array.indexOf(events, event))
	{
	case 0: //Receive Ended
	    onGoing--;
	    if (!isTransmitting & (onGoing == 0))
	    {
		state = stateWaiting;
		simulator.add(new Event(simulator.time()+waitTime.getInstance(),
					this,
					this.events[2])); //Wait Ended Scheduled
	    }
	    if (state != stateCollided)
		queue.receive(packet);
	    break;
	case 1: //Transmission Ended
	    isTransmitting = false;
	    if (onGoing == 0)
	    {
		state = stateWaiting;
		simulator.add(new Event(simulator.time()+waitTime.getInstance(),
					this,
					this.events[2])); //Wait Ended Scheduled
	    }
	    break;
	case 2: //Wait Ended
	    Object packet = queue.get();
	    if (packet != null)
		startTransmission((Packet) packet, simulator);
	    else
		state = stateIdle;
	    break;
	default:
	    throw new RuntimeException(this+" encounters unknown event "+event);
	}
    }

    /** Start transmission.
     * @param packet packet to transmit
     */
    public void startTransmission(Packet packet, Simulator simulator)
    {
	if (onGoing != 0)
	    state = stateCollided;
	else
	    state = stateTransmitting;
	isTransmitting = true;
	simulator.add(new Event(simulator.time()+
				commChannel.transmitDuration(packet.totalLength()),
				this,
				this.events[1])); //Transmission Ended scheduled
    }

    public void receive(CommNode source, Object packet, Simulator simulator)
    {
	if ((state == stateTransmitting) || 
	    (state == stateReceiving) || (state == stateCollided))
	    state = stateCollided;
	else
	    state = stateReceiving;
	onGoing++;
	simulator.add(new Event(simulator.time()+
				commChannel.transmitDuration(((Packet) packet).totalLength()),
				this,
				this.events[0])); //Receive Ended scheduled
	this.packet = packet;
    }

    public void trigger(Simulator simulator)
    {
	if (state == stateIdle)
	{
	    Object packet = queue.get();
	    if (packet != null)
		startTransmission((Packet) packet, simulator);
	}
    }

    public Node newNode(Coordinate coordinate)
    {
	return new ALOHA(coordinate, this.channel, this.commChannel, 
			 this.queue, this.processor, this.waitTime);
    }

    public ALOHA(Coordinate coordinate, Channel channel, CommChannel commChannel, 
		 Queue queue, PacketProcessor processor, Distribution waitTime)
    {
	super(coordinate, channel, commChannel, queue, processor);
	this.waitTime = waitTime;
    }
}
