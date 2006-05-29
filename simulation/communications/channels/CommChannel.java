package simulation.communications.channels;

import simulation.communications.nodes.*;

/** Abstract class for a communication channel.
 * @author ykk
 */
public abstract class CommChannel
{
    //Members
    /** Transmission rate (in bytes per second).
     */
    public double rate;

    //Methods
    /** Constructor to generate communication channel.
     * @param rate transmission rate
     */
    public CommChannel(double rate)
    {
	this.rate = rate;
    }

    /** Return transmission duration required for n bytes.
     * @param n number of bytes to transmit
     * @return duration required to transmit bytes
     */
    public double transmitDuration(int n)
    {
	return ((double) n)/rate;
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted
     */
    public abstract boolean transmit(CommNode source, CommNode destination, Object packet);
}
