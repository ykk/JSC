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

/** Class to implement CSMA medium access control.
 * Added to ALOHA, a carrier sense before transmission and
 * the delayed triggered of receive in destinations due to propagation delay.
 * @author ykk
 */
public class CSMA
    extends ALOHA
{
    //Members
    /** Array of events node can process.
     * Events = 0 "Receive Ended",
     *          1 "Transmission Ended", 
     *          2 "Wait Ended"
     *          3 "Packet Reaches Destinations"
     */
    public static final String[] events = {"Receive Ended", "Transmission Ended", 
					   "Wait Ended", "Packet Reached"};
    /** Propagation delay.
     */
    public double propagation = 0;
    /** Currently transmitting packet;
     */
    protected Packet txPacket;

    //Methods
    /** Event triggered interface.
     * Added 
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
	//Packet reaches destination
	if (Array.indexOf(events, event) == 3)
	{
	    //Set neighbors' receive
	    for (int i = 0; i < transmitPartners.size(); i++)
		commChannel.transmit(this, (ALOHA) transmitPartners.get(i),
				     txPacket,simulator);
	}
	else
	    super.run(time, event, simulator);
    }

    /** Constructor.
     * @param coordinate coordinate of ALOHA node
     * @param channel network channel in use
     * @param commChannel communication channel
     * @param queue queue of the node
     * @param processor reference to packet processor
     * @param waitTime distribution of waiting time
     * @param propagation propagation time for packet
     */
    public CSMA(Coordinate coordinate, Channel channel, CommChannel commChannel, 
		Queue queue, PacketProcessor processor, Distribution waitTime, double propagation)
    {
	super(coordinate, channel, commChannel, queue, processor, waitTime);
	this.propagation = propagation;
    }

    /** Start transmission.
     * @param packet packet to transmit
     */
    public void startTransmission(Simulator simulator)
    {
	//Check own state
	if (onGoing != 0)
	    return;

	//Send packet
	Packet packet = (Packet) processor.get(queue);
	state = stateTransmitting;
	isTransmitting = true;
	simulator.add(new Event(simulator.time()+
				commChannel.transmitDuration(packet),
				this,
				this.events[1])); //Transmission Ended scheduled
	txPacket = packet;
	simulator.add(new Event(simulator.time()+propagation,
				this,
				this.events[3])); //Packet Reaches Destination scheduled
    }

    public Node newNode(Coordinate coordinate)
    {
	return new CSMA(coordinate, this.channel, this.commChannel, 
			this.queue.newQueue(), this.processor, this.waitTime, propagation);
    }

    /** Trial run of MAC simulation.
     * @param args 1st argument can be density of Grid network [optional]
     */
    public static void main(String[] args)
    {
	MACTrial trial = new MACTrial(new Simulator());
	if (args.length >= 1)
	    trial.pointprocess = new Grid(Double.parseDouble(args[0]));
	trial.generateNetwork(new CSMA(new Coordinate(0,0), trial.networkChannel, trial.commChannel,
				       trial.queue, trial.processor, trial.waitTime,1e-6));
	//Trigger by scheduling end of wait.
	for (int i = 0; i < trial.network.nodes.size(); i++)
	    trial.simulator.add(new Event(trial.waitTime.getInstance(),
					  ((CSMA) trial.network.nodes.get(i)),
					  ((CSMA) trial.network.nodes.get(i)).events[2]));
	trial.run();
    }
}