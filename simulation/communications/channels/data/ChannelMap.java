package simulation.communications.channels.data;

import simulation.utilities.structures.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.distributions.*;

/** Class to map empirical channel information to channels/links in a network.
 * Data is in the form of a vector distance and reception probabilities, with standard deviation.
 * @author ykk
 */
public class ChannelMap
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
    public ChannelMap(Network network, ChannelData chanData, MeanVarDistribution distro)
    {
	this.network = network;
	this.chanData = chanData;
	this.distro = distro;

	buildMap();
    }

    /** Function to map channel probability map.
     */
    public void buildMap()
    {
	ReceiveProbVar chanInfo;
	Distribution chanDistro;
	int networkSize = network.nodes.size();
	this.chanProb = new double[networkSize][networkSize];
	
	for (int i = 0; i < networkSize; i++)
	    for (int j = 0; j < networkSize; j++)
		if (i != j ||
		    ((Node) network.nodes.get(i)).transmitPartners.indexOf((Node) network.nodes.get(j)) != -1)
		{
		    chanProb[i][j] = -1;
		    chanInfo = getChanInfo(((Node) network.nodes.get(i)).distance((Node) network.nodes.get(j)));
		    chanDistro = distro.newDistribution(chanInfo.probability, Math.pow(chanInfo.stdDev,2.0));
		    while(chanProb[i][j] < 0 || chanProb[i][j] > 1)
			chanProb[i][j] = chanDistro.getInstance();
		}
		else
		    chanProb[i][j] = 0;
    }

    /** Get channel information for distance, based on nearest distance.
     * @param distance distance
     * @return channel information
     */
    public ReceiveProbVar getChanInfo(double distance)
    {
	int next = chanData.index(new ReceiveProbVar(distance, 0, 0));
	int prev = (next < 1)? 0: next-1;

	ReceiveProbVar prevChan = (ReceiveProbVar) chanData.get(prev);
	ReceiveProbVar nextChan = (ReceiveProbVar) chanData.get(next);
	
	if ((prevChan.distance - distance) > (nextChan.distance-distance))
	    return nextChan;
	else
	    return prevChan;
    }


}