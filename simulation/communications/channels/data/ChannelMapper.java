package simulation.communications.channels.data;

import simulation.utilities.structures.*;
import simulation.networks.*;
import simulation.distributions.*;

/** Class to map empirical channel information to channels/links in a network.
 * Data is in the form of a vector distance and reception probabilities, with standard deviation.
 * @author ykk
 */
public class ChannelMapper
{
    //Members
    /** Reference to channel data.
     * Requires vector of {@link ReceiveProbVar}
     */
    public ChannelData chanData;
    /** Distribution that can be defined by mean and variance.
     */
    MeanVarDistribution distro;
    /** Reference to network.
     */
    public Network network;
    /** Reference to channel probabilities map.
     */
    public double[][] chanProb;

    //Methods
    /** Constructor
     */
    public ChannelMapper(Network network, ChannelData chanData, MeanVarDistribution distro)
    {
	this.network = network;
	this.chanData = chanData;
	this.distro = distro;
    }

    /** Function to map channel probability map.
     */
    public void buildMap()
    {
	int networkSize = network.nodes.size();
	this.chanProb = new double[networkSize][networkSize];

	
    }
}