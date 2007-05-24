package simulation.communications.channels;

import simulation.distributions.*;
import simulation.communications.channels.data.*;
import simulation.communications.nodes.*;
import simulation.networks.*;
import simulation.networks.simulator.*;

/** Unreliable communication channel from a {@link ChannelMap}.
 * @author ykk
 */
public class MappedChannel
    extends CommChannel
{
    //Members
    /** Random number.
     */
    private Uniform random = new Uniform(0,1);
    /** Channel map.
     */
    public ChannelMap channel;

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param channel channel map
     */
    public MappedChannel(double rate, ChannelMap channel)
    {
	super(rate);
	this.channel = channel;
    }

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param headerRate transmission rate for header
     * @param channel channel map
     */
    public MappedChannel(double rate, double headerRate, ChannelMap channel)
    {
	super(rate, headerRate);
	this.channel = channel;
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
	{
	    double prob = channel.channelProb(source, destination);
	    if (prob < 0.0)
		return 0.0;
	    else if (prob > 1.0)
		return 1.0;
	    else
		return prob;
	}
	else
	    return 0.0;
    }

    /** Build channel map of channel.
     */
    public void buildChannel()
    {
	channel.buildMap();
    }
}