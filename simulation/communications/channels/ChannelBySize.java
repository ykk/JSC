package simulation.communications.channels;

import java.util.*;
import simulation.communications.nodes.*;
import simulation.communications.packets.*;
import simulation.eventbased.*;
import simulation.eventbased.mediumaccess.*;

/** Class for choosing multiple channels, by size of packet payload.
 * Uses the first channel with matching size range.
 * @author ykk
 */
public class ChannelBySize
    extends MultiChannel
{
    //Members
    /** Lower bound for packet size range.
     */
    public Vector lowRange = new Vector();
    /** Upper bound for packet size range.
     */
    public Vector uppRange = new Vector();

    //Methods
    /** Constructor.
     * @param channel default channel
     */
    public ChannelBySize(CommChannel channel)
    {
	super(channel);
    }

    /** Add channel.
     * @param lower lower bound for packet size range to use channel
     * @param upper upper bound for packet size range to use channel
     * @param channel channel to use
     */
    public void addChannel(int lower, int upper, CommChannel channel)
    {
	lowRange.add(new Integer(lower));
	uppRange.add(new Integer(upper));
	channels.add(channel);
    }

    /** Get appropriate channel.
     * @param packet packet to be transmitted
     * @return index of appropriate channel
     */
    public int getChannelIndex(Packet packet)
    {
	for (int i = 0; i < lowRange.size(); i++)
	    if ((((Integer) lowRange.get(i)).intValue() <= packet.length) &&
		(((Integer) uppRange.get(i)).intValue() >= packet.length))
		return i+1;

	return 0;
    }
}