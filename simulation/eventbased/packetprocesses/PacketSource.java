package simulation.eventbased.packetprocesses;

import simulation.communications.packets.*;
import simulation.eventbased.*;
import simulation.eventbased.mediumaccess.*;
import simulation.distributions.*;
import simulation.utilities.structures.*;

/** Source of packet.
 * @author ykk
 */
public class PacketSource
    implements EventTriggered
{
    //Members
    /** Array of events possible.
     */
    public static final String[] events = {"Waiting Ended"};
    /** Interarrival distribution.
     */
    public Distribution interarrival;
    /** Maximum number of packets.
     * Defaulted to 0, i.e., infinite.
     */
    public int packetNumber = 0;
    /** Current number of packets generated.
     */
    private int generatedNo = 0;
    /** Packet factory.
     */
    PacketFactory packetFactory;
    /** Source node.
     */
    public MACNode source;

    //Methods
    /** Constructor.
     * @param interarrival interarrival probability distribution
     * @param packetFactory factory for packets
     * @param source source node to inject packets into
     */
    public PacketSource(Distribution interarrival, PacketFactory packetFactory, 
			MACNode source)
    {
	this.interarrival = interarrival;
	this.packetFactory = packetFactory;
	this.source = source;
    }

    /** Constructor.
     * @param interarrival interarrival probability distribution
     * @param packetFactory factory for packets
     * @param source source node to inject packets into
     * @param packetNumber number of packets to generate
     */
    public PacketSource(Distribution interarrival, PacketFactory packetFactory,
			MACNode source, int packetNumber)
    {
	this(interarrival,packetFactory, source);
	this.packetNumber = packetNumber;
    }

    /** Event triggered interface.
     * Object has a single state in which it waits for the next arrival.
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     * @see #events
     */
    public void run(double time, java.lang.String event, Simulator simulator)
    {
	switch(Array.indexOf(events, event))
	{
	case 0: //Waiting Ended
	    newPacket(simulator);
	    simulator.add(new Event(simulator.time()+interarrival.getInstance(),
				    this,
				    this.events[0])); //Waiting Ended scheduled
	    break;
	default:
	    throw new RuntimeException(this+" encounters unknown event "+event);
	}
    }
    
    /** Create new packet.
     * @param simulator reference to simulator
     * @see #packetNumber
     */
    public void newPacket(Simulator simulator)
    {
	if ((packetNumber == 0) || (generatedNo < packetNumber))
	{
	    source.queue.receive(packetFactory.duplicate());
	    source.trigger(simulator);
	    generatedNo++;
	}
    }
}