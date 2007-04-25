package simulation.communications.channels;

import simulation.communications.channels.data.*;
import simulation.communications.nodes.*;

/** Unreliable communication channel from a {@link ChannelMap}.
 * @author ykk
 */
public class MappedChannel
    extends PathLoss
{
    //Members
    /** Path loss exponent.
     * Overloaded to private.
     */
    private double exponent;
    /** Channel map.
     */
    public ChannelMap channel;

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param channel channel map
     */
    public MappedChannel(double rate, ChannelMap channel)
    {
	super(rate, 0);
	this.channel = channel;
    }

    /** Constructor to generate communication channel.
     * @param rate transmission rate
     * @param headerRate transmission rate for header
     * @param channel channel map
     */
    public MappedChannel(double rate, double headerRate, ChannelMap channel)
    {
	super(rate, headerRate, 0);
	this.channel = channel;
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
}