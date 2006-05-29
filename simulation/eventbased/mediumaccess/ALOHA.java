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

/** Class to implement ALOHA medium access control.
 * @author ykk
 */
public class ALOHA
    extends MACNode
{
    //Members
    /** Array of states node can be in.
     * States = "Idle", "Waiting", "Transmitting", "Receiving", "Collided"
     */
    public static final String[] states = {"Idle", "Waiting", "Transmitting", "Receiving", "Collided"};
    /** Current state of node.
     * @see #states
     */
    public int state = 0; //Idle
    /** Array of events node can process.
     * Events = "Receive Ended", 
     *          "Transmission Ended", 
     *          "Wait Ended"
     */
    public static final String[] events = {"Receive Ended", "Transmission Ended", "Wait Ended"};
    /** Number of ongoing receptions.
     */
    private int onGoing = 0;

    //Methods
    /** Event triggered interface.
     * State diagram is as shown below.
     * <BR><img src="doc-files/aloha.jpg"><BR>
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     * @see #events
     * @see #states
     */
    public void run(double time, String event, Simulator simulator)
    {
	switch (Array.indexOf(events, event))
	{
	case 0: //Receive Ended
	    break;
	case 1: //Transmission Ended
	    break;
	case 2: //Wait Ended
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
    }

    public void receive(CommNode source, Object packet, Simulator simulator)
    {
    }

    public void trigger(Simulator simulator)
    {
	if (state == 0)  //Idle state
	{
	    Object packet = queue.get();
	    if (packet != null)
		startTransmission((Packet) packet, simulator);
	}
    }

    public Node newNode(Coordinate coordinate)
    {
	return new ALOHA(coordinate, this.channel, this.commChannel, this.queue);
    }

    public ALOHA(Coordinate coordinate, Channel channel, CommChannel commChannel, Queue queue)
    {
	super(coordinate, channel, commChannel, queue);
    }
}
