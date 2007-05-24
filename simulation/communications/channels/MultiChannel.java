package simulation.communications.channels;

import java.util.*;
import simulation.communications.nodes.*;
import simulation.communications.packets.*;
import simulation.networks.simulator.*;

/** Abstract class for multiple channels, where channel is chosen based on properties of packets.
 * @author ykk
 */
public abstract class MultiChannel
    extends CommChannel
{
    //Members
    /** Transmission rate (in bytes per second).
     * Dummy.
     */
    private double rate;
    /** Header transmission rate (in bytes per second).
     * Defaulted to be same as rate.
     * Dummy.
     */
    private double headerRate;
    /** Vectors of channels.
     */
    public Vector channels = new Vector();

    //Methods
    /** Constructor.
     * @param channel default channel
     */
    public MultiChannel(CommChannel channel)
    {
	super(0,0);
	channels.add(0,channel);
    }

    /** Get appropriate channel.
     * @param packet packet to be transmitted
     * @return index of appropriate channel
     */
    public abstract int getChannelIndex(Packet packet);
    

    /** Get appropriate channel for channel.
     * @param packet packet to be transmitted
     * @return index of appropriate channel
     */
    public CommChannel getChannel(Packet packet)
    {
	return (CommChannel) channels.get(getChannelIndex(packet));
    }

    /** Return transmission duration required for n bytes, (overloaded with dummy).
     * @param n number of bytes to transmit
     * @return duration required to transmit bytes
     */
    public double transmitDuration(int n)
    {
	throw new RuntimeException(this+"'s transmitDuration function is not functional.  Please seek individual channels.");
    }

    /** Return transmission duration required for packet, (overloaded with dummy).
     * @param packet packet to transmit
     * @return duration required to transmit 
     */
    public double transmitDuration(Packet packet)
    {
	throw new RuntimeException(this+"'s transmitDuration function is not functional.  Please seek individual channels.");
    }

    /** Transmit packet from source to destination.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @return if packet is successfully transmitted
     */
    public boolean transmit(CommNode source, CommNode destination, Object packet)
    {
	return getChannel((Packet) packet).transmit(source, destination, packet);
    }

    /** Build any {@link MappedChannel} in the channel list.
     */
    public void buildChannel()
    {
	for (int i = 0; i < channels.size(); i++)
	    if (channels.get(i) instanceof MappedChannel)
		((MappedChannel) channels.get(i)).buildChannel();
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
	return getChannel((Packet) packet).transmit(source, destination, packet, simulator);
    }

    /** Provide probability of success to transmit packet from source to destination, (overloaded with dummy).
     * @param source source node
     * @param destination destination node
     * @return probability of success
     */
    public double transmitProb(CommNode source, CommNode destination)
    {
	throw new RuntimeException(this+"'s transmitProb function is not functional.  Please seek individual channels.");
    }
}