package simulation.communications.channels;

import simulation.distributions.*;
import simulation.communications.nodes.*;
import simulation.networks.simulator.*;

/** Unreliable communication channel modeling path loss.
 * @see simulation.utilities.linkcosts.Reliability
 * @author ykk
 */
public class PathLoss
    extends CommChannel
{
    //Members
    /** Path loss exponent.
     * Must be negative.
     */
    public double exponent;
    /** Random number.
     */
    private Uniform random = new Uniform(0,1);

    //Methods
    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param exponent path loss exponent
     */
    public PathLoss(double rate, double exponent)
    {
	super(rate);
	if (exponent < 0)
	    this.exponent = exponent;
	else
	    throw new RuntimeException(this+" given exponent "+exponent+" which is not allowed.");
    }

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param headerRate transmission rate for header
     * @param exponent path loss exponent
     */
    public PathLoss(double rate, double headerRate, double exponent)
    {
	super(rate, headerRate);
	if (exponent < 0)
	    this.exponent = exponent;
	else
	    throw new RuntimeException(this+" given exponent "+exponent+" which is not allowed.");
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted (always true)
     */
    public boolean transmit(CommNode source, CommNode destination, Object packet)
    {
	if (txSuccess(source,destination))
	{
	    destination.receive(source, packet);
	    return true;
	}
	else
	    return false;
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
	if (txSuccess(source,destination))
	    destination.receive(source, packet, simulator);
	return true;
    }

    /** Return an instance of whether an transmission is successful.
     * @param source source node
     * @param destination destination node
     * @return if an instance of transmission is successful
     */
    private boolean txSuccess(CommNode source, CommNode destination)
    {
	return (random.getInstance() < transmitProb(source,destination));
    }


    /** Provide probability of success to transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @return probability of success
     */
    public double transmitProb(CommNode source, CommNode destination)
    {
	if (source.transmitPartners.indexOf(destination) != -1)
	    return Math.pow(Math.E,source.distance(destination)*exponent);
	else
	    return 0.0;
    }
}