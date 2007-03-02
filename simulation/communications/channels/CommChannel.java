package simulation.communications.channels;

import simulation.communications.nodes.*;
import simulation.communications.packets.*;
import simulation.eventbased.*;
import simulation.eventbased.mediumaccess.*;

/** Abstract class for a communication channel.
 * @author ykk
 */
public abstract class CommChannel
{
    //Members
    /** Transmission rate (in bytes per second).
     */
    public double rate;
    /** Header transmission rate (in bytes per second).
     * Defaulted to be same as rate.
     */
    public double headerRate;

    //Methods
    /** Constructor to generate communication channel.
     * @param rate transmission rate
     */
    public CommChannel(double rate)
    {
	this.rate = rate;
	this.headerRate = rate;
    }

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param headerRate transmission rate for header
     */
    public CommChannel(double rate, double headerRate)
    {
	this.rate = rate;
	this.headerRate = headerRate;
    }

    /** Return transmission duration required for n bytes.
     * @param n number of bytes to transmit
     * @return duration required to transmit bytes
     */
    public double transmitDuration(int n)
    {
	return ((double) n)/rate;
    }

    /** Return transmission duration required for packet.
     * @param packet packet to transmit
     * @return duration required to transmit 
     */
    public double transmitDuration(Packet packet)
    {
	return (((double) packet.length)/rate) +
	    (((double) packet.headerLength)/headerRate);
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted
     */
    public abstract boolean transmit(CommNode source, CommNode destination, 
				     Object packet);

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @param simulator reference to simulator
     * @return if packet is successfully transmitted (always true)
     */
    public abstract boolean transmit(MACNode source, MACNode destination, 
				     Object packet, Simulator simulator);

    /** Provide probability of success to transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @return probability of success
     */
    public abstract double transmitProb(CommNode source, CommNode destination);
}
