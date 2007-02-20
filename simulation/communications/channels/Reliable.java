package simulation.communications.channels;

import simulation.communications.nodes.*;

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
     * @return if packet is successfully transmitted
     */
    public boolean transmit(CommNode source, CommNode destination, Object packet)
    {
	return true;
    }  
}