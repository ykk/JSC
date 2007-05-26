package simulation.timebased.packetprocesses;

import simulation.timebased.*;
import simulation.timebased.mediumaccess.*;
import simulation.communications.packets.*;
import simulation.distributions.*;

/** Packet source for time based simulation.
 * @author ykk
 */
public class PacketSource
    extends simulation.networks.simulator.PacketSource
    implements RunObject
{
    //Members
    /** Distribution of packet number in a slot.
     */
    public Distribution pktNumber;

    //Methods
    /** Constructor.
     * @param pktNumber distribution of packet number
     * @param packetFactory factory for packets
     * @param source source node to inject packets into	    
     */
    public PacketSource(Distribution pktNumber, PacketFactory packetFactory, MACNode source)
    {
	this.pktNumber = pktNumber;
	this.packetFactory = packetFactory;
	this.source = source;
    }

    /** Constructor.
     * @param pktNumber distribution of packet number
     * @param packetFactory factory for packets
     * @param source source node to inject packets into	 
     * @param packetNumber number of packets to generate   
     */
    public PacketSource(Distribution pktNumber, PacketFactory packetFactory, 
			MACNode source, int packetNumber)
    {
	this.pktNumber = pktNumber;
	this.packetFactory = packetFactory;
	this.source = source;
	this.packetNumber = packetNumber;
    }

    public boolean run(double time, Simulator simulator) 
    {
	if ((packetNumber == 0) || (generatedNo < packetNumber))
	{
	    int thisNo = (int) Math.round(pktNumber.getInstance());
	    for (int i = 0; i < thisNo; i++)
		newPacket(simulator);
	    return true;
	}
	else
	    return false;
    }

    /** Indicate if object needs more run.
     * If infinitely generating, then always return false.
     * @return if more runs is needed
     */
    public boolean needMoreRun()
    {
	if ((packetNumber == 0) || (generatedNo >= packetNumber))
	    return false;
	else
	    return true;
    }
}