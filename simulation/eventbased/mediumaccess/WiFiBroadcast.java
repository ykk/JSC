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
    /** Contention window size.
     * For example, size of 32 means window of 0 to 31.
     */
    protected int cw;
    /** Contention window counter.
     */
    protected int cwCount;
    /** 
     */
    /** Array of events node can process.
     * Events = 0 "Receive Ended",
     *          1 "Transmission Ended", 
     *          2 "Wait Ended"
     *          3 "Packet Reaches Destinations"
     */
    public static final String[] events = {"Receive Ended", 
					   "Transmission Ended", 
					   "Wait Ended", 
					   "Packet Reached",
					   "Waited Slot"};
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
			 CommChannel commChannel, Queue queue, 
			 PacketProcessor processor, WiFiSpec spec)
    {
	super(coordinate, channel, commChannel, queue, processor, null, 1e-6);
	this.spec = spec;
	cw = spec.minCW;
    }

    /** Event triggered interface.
     * Added WiFi wait time.
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
	//Waited slot
	if (Array.indexOf(events, event) == 4)
	{
	    if (onGoing != 0)
		return;
	    else
	    {
		cwCount--;
		if (cwCount != 0)
		    simulator.
			add(new Event(simulator.time()+spec.slotTime,
				      this,
				      this.events[4])); //Waited Slot Scheduled
		else
		     simulator.
			 add(new Event(simulator.time(),
				       this,
				       this.events[2])); //Wait Ended Scheduled
	    }
	}
	else
	    super.run(time, event, simulator);
    }

    /** Schedule wait time.
     * @param simulator reference to simulator
     */
    public void scheduleWait(Simulator simulator) 
    {
	if (!isTransmitting & (onGoing == 0))
	{
	    //Wait for DIFS
	    waitTime = new Uniform(0,cw+1);
	    cwCount = (int) Math.floor(waitTime.getInstance());
	    state = stateWaiting;
	    simulator.add(new Event(simulator.time()+
				    spec.sifs+2*spec.slotTime,
				    this,
				    this.events[4])); //Waited Slot Scheduled
	}
    }

    public Node newNode(Coordinate coordinate)
    {
	return new WiFiBroadcast(coordinate, this.channel, this.commChannel, 
				 this.queue.newQueue(), this.processor, 
				 this.spec);
    }

    /** Trial run of MAC simulation.
     * @param args 1st argument can be density of Grid network [optional]
     */
    public static void main(String[] args)
    {
	MACTrial trial = new MACTrial(new Simulator());
	if (args.length >= 1)
	    trial.pointprocess = new Grid(Double.parseDouble(args[0]));
	trial.generateNetwork(new WiFiBroadcast(new Coordinate(0,0), 
						trial.networkChannel, 
						trial.commChannel,
						trial.queue, trial.processor, 
						new WiFiSpec(WiFiSpec.IEEE802_11b)));
	//Trigger by scheduling end of .
	for (int i = 0; i < trial.network.nodes.size(); i++)
	    trial.simulator.add(new Event(trial.waitTime.getInstance(),
					  ((WiFiBroadcast) trial.network.nodes.get(i)),
					  ((WiFiBroadcast) trial.network.nodes.get(i)).
					  events[2]));
	trial.run();
    }
}