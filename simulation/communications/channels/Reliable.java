package simulation.communications.channels;

import simulation.communications.nodes.*;
import simulation.networks.simulator.*;

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

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param headerRate transmission rate for header
     */
    public Reliable(double rate, double headerRate)
    {
	super(rate,headerRate);
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted (always true)
     */
    public boolean transmit(CommNode source, CommNode destination, Object packet)
    {
	if (source.transmitPartners.indexOf(destination) != -1)
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
	if (source.transmitPartners.indexOf(destination) != -1)
	    destination.receive(source, packet, simulator);
	return true;
    }

    /** Provide probability of success to transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @return probability of success
     */
    public double transmitProb(CommNode source, CommNode destination)
    {
	if (source.transmitPartners.indexOf(destination) != -1)
	    return 1.0;
	else
	    return 0.0;
    }
}