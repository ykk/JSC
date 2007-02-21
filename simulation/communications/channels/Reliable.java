package simulation.communications.channels;

import simulation.communications.nodes.*;
import simulation.eventbased.*;
import simulation.eventbased.mediumaccess.*;

/** Reliable communication channel.
 * @author ykk
 */
public class Reliable
    extends CommChannel
{
    //Methods
    /** Constructor to generate communication channel.
     * @param rate transmission rate
     */
    public Reliable(double rate)
    {
	super(rate);
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted (always true)
     */
    public boolean transmit(CommNode source, CommNode destination, Object packet)
    {
	destination.receive(source, packet);
	return true;
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @param simulator reference to simulator
     * @return if packet is successfully transmitted (always true)
     */
    public boolean transmit(MACNode source, MACNode destination, 
			    Object packet, Simulator simulator)
    {
	destination.receive(source, packet, simulator);
	return true;
    }
}