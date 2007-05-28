package simulation.communications.channels;

import simulation.results.processors.*;
import simulation.communications.nodes.*;
import simulation.communications.packets.*;
import simulation.networks.simulator.*;
import java.util.*;

/** Pesudo channel for result processing of channel transmission.
 * @see ChannelResultProcessor
 * @author ykk
 */
public class ResultCommChannel
    extends CommChannel
{
    //Members
    /** Real communication channel enscapsulated.
     */
    public CommChannel channel;
    /** Vector of object to process channel
     */
    public Vector resultProcessors = new Vector();

    //Methods
    /** Constructor.
     * @param channel communication channel encapsulated
     */
    public ResultCommChannel(CommChannel channel)
    {
	super(channel.rate, channel.headerRate);
	this.channel = channel;
    }

    public double transmitDuration(int n)
    {
	return channel.transmitDuration(n);
    }

    public double transmitDuration(Packet packet)
    {
	return channel.transmitDuration(packet);
    }

    /** Transmit packet from source to destination.
     * Calls {@link #process(CommNode source, CommNode destination, Object packet, Simulator simulator)},
     * with simulator as null.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted
     */
    public boolean transmit(CommNode source, CommNode destination, 
			    Object packet)
    {
	for (int i = 0; i < resultProcessors.size(); i++)
	    ((ChannelResultProcessor) resultProcessors.get(i)).process(source, destination, packet, null);
	return channel.transmit(source, destination, packet);
    }

    /** Transmit packet from source to destination.
     * Calls {@link #process(CommNode source, CommNode destination, Object packet, Simulator simulator)}.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @param simulator reference to simulator
     * @return if packet is successfully transmitted (always true)
     */
    public boolean transmit(MACNode source, MACNode destination, 
			    Object packet, Simulator simulator)
    {
	for (int i = 0; i < resultProcessors.size(); i++)
	    ((ChannelResultProcessor) resultProcessors.get(i)).process(source, destination, packet, simulator);
	return channel.transmit(source, destination, packet, simulator);
    }

    /** Provide probability of success to transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @return probability of success
     */
    public double transmitProb(CommNode source, CommNode destination)
    {
	return channel.transmitProb(source, destination);
    }
}