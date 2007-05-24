package simulation.communications.channels.data;

import simulation.utilities.structures.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.distributions.*;

/** Class to map empirical channel information to channels/links in a network.
 * @author ykk
 */
public abstract class ChannelMap
{
    //Members
    /** Reference to network.
     */
    public Network network;

    //Methods
    /** Constructor
     * @param network reference to network
     */
    public ChannelMap(Network network)
    {
	this.network = network;
    }

    /** Function to map channel probability map.
     */
    public abstract void buildMap();

    /** Return channel reception probabilities using map.
     * @param source source node
     * @param destination destination node
     * @return reception probability from source to destination
     */
    public abstract double channelProb(Node source, Node destination);
}