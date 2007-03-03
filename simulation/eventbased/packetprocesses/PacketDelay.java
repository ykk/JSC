package simulation.eventbased.packetprocesses;

import simulation.eventbased.*;
import simulation.utilities.structures.*;
import simulation.communications.packets.*;

/** Class implementing packet delay.
 * @see PacketProcess
 * @author ykk
 */
public class PacketDelay
    implements EventTriggered
{
    //Members
    /** Array of events.
     * Only have Delay Ended as event.
     */
    public String[] events = {"Delay Ended"};
    /** Packet to delay.
     */
    public Packet packet;
    /** Reference object.
     * Object used for packet processing once delay ended.
     * Defaulted to null.
     */
    public Object objectRef = null;
    /** Reference to packet processing object.
     */
    public PacketProcess processor;

    //Methods
    /** Constructor.
     * @param packet packet reference
     * @param processor packet processor
     */
    public PacketDelay(Packet packet, PacketProcess processor)
    {
	this.packet = packet;
	this.processor = processor;
    }

    /** Constructor.
     * @param packet packet reference
     * @param processor packet processor
     * @param objectRef object reference
     */
    public PacketDelay(Packet packet, PacketProcess processor, Object objectRef)
    {
	this.packet = packet;
	this.processor = processor;
	this.objectRef = objectRef;
    }

    /** EventTriggered function to run events.
     * @param time time to run events
     * @param event event to run
     * @param simulator reference to simulator
     */
    public void run(double time, java.lang.String event, Simulator simulator)
    {
	switch (Array.indexOf(events,event))
	{
	case 0: //Delay Ended
	    processor.process(packet, objectRef);
	    break;
	default:
	    throw new RuntimeException(this+" receive unknown event "+event+" to be run");
	}
    }

    /** Schedule packet with delay.
     * @param packet packet to be delayed
     * @param delay amount of time to delay packet
     * @param simulator reference to simulator
     * @param pktProcess processor to process object
     * @param objectRef reference to object associated with packet processing
     */
    public static void schedule(Packet packet, double delay, Simulator simulator, 
				PacketProcess pktProcess, Object objectRef)
    {
	PacketDelay delayObj = new PacketDelay(packet, pktProcess, objectRef);
	simulator.add(new Event(simulator.time()+delay, delayObj, delayObj.events[0]));
    }
}