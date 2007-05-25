package simulation.networks.simulator;

import simulation.communications.packets.*;


/** Abstract class for packet source.
 * @author ykk
 */
public abstract class PacketSource
{
    //Members
    /** Maximum number of packets.
     * Defaulted to 0, i.e., infinite.
     */
    public int packetNumber = 0;
    /** Current number of packets generated.
     */
    protected int generatedNo = 0;
    /** Packet factory.
     */
    protected PacketFactory packetFactory;
    /** Source node.
     */
    public MACNode source;
    /** Debug flag.
     */
    public boolean debug = false;

    //Methods
    /** Create new packet.
     * Provisioned for {@link TimedPacket}.
     * @param simulator reference to simulator
     * @see #packetNumber
     */
    public void newPacket(Simulator simulator)
    {
	if ((packetNumber == 0) || (generatedNo < packetNumber))
	{
	    Packet pack;
	    if (packetFactory instanceof TimedPacket)
	    {
		pack = ((TimedPacket) packetFactory).duplicate(simulator.time());
		((TimedPacket) pack).seqNumber = generatedNo + 1;
	    }
	    else
		pack = packetFactory.duplicate();
	    if (debug) System.out.println(this + " generated "+pack+" for "+source);
	    source.queue.receive(pack);
	    generatedNo++;

	    if (source instanceof simulation.eventbased.mediumaccess.MACNode)
		((simulation.eventbased.mediumaccess.MACNode) source)
		    .trigger((simulation.eventbased.Simulator) simulator);
	}
    }    
}