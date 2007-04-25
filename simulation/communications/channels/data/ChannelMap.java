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
     * Defaulted to Gaussian.
     */
    MeanVarDistribution distro = new Gaussian(0,0);
    /** Indicates if channel map is symmetrical.
     * Defaulted to true.
     */ 
    public boolean symmetricalChannel = true;
    /** Reference to network.
     */
    public Network network;
    /** Reference to channel probabilities map.
     */
    public double[][] chanProb;

    //Methods
    /** Constructor
     * @param network reference to network
     * @param chanData reference to channel data
     * @param distro distribution defined by mean and variance
     */
    public ChannelMap(Network network, ChannelData chanData, MeanVarDistribution distro)
    {
	this.network = network;
	this.chanData = chanData;
	this.distro = distro;

	buildMap();
    }

    /** Constructor
     * @param network reference to network
     * @param chanData reference to channel data
     */
    public ChannelMap(Network network, ChannelData chanData)
    {
	this.network = network;
	this.chanData = chanData;

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
		    {
			chanProb[i][j] = chanDistro.getInstance();
			if (symmetricalChannel)
			    chanProb[j][i] = chanProb[i][j];		    }
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

    /** Return channel reception probabilities using map.
     * @param source source node
     * @param destination destination node
     * @return reception probability from source to destination
     */
    public double channelProb(Node source, Node destination)
    {
	int srcIndex = network.nodes.indexOf(source);
	int dstIndex = network.nodes.indexOf(destination);
	return chanProb[srcIndex][dstIndex];
    }

    /** Construct new channel map taking ratio of mean of reception probability.
     * @param newChanData new channel data
     * @return new channel map that is a ratio of this one
     */
    public ChannelMap newMapByRatio(ChannelData newChanData)
    {
	ReceiveProbVar oldChanInfo, newChanInfo;
	double distance;
	int networkSize = network.nodes.size();
	ChannelMap newMap = new ChannelMap(network, newChanData, distro);

	double[][] newChanProb = new double[networkSize][networkSize];

	for (int i = 0; i < networkSize; i++)
	    for (int j = 0; j < networkSize; j++)
		if (chanProb[i][j] != 0)
		{
		    distance = ((Node) network.nodes.get(i)).distance((Node) network.nodes.get(j));
		    oldChanInfo = getChanInfo(distance);
		    newChanInfo = newMap.getChanInfo(distance);
		    newMap.chanProb[i][j] = (newChanInfo.probability/oldChanInfo.probability)*chanProb[i][j];
		    if (newMap.chanProb[i][j] > 1)
			newMap.chanProb[i][j] = 1.0;
		}
		else
		    newMap.chanProb[i][j] = chanProb[i][j];

	return newMap;
    }

    /** Construct new channel map taking difference of mean of reception probability.
     * @param newChanData new channel data
     * @return new channel map that is a shift of this one
     */
    public ChannelMap newMapByDiff(ChannelData newChanData)
    {
	ReceiveProbVar oldChanInfo, newChanInfo;
	double distance;
	int networkSize = network.nodes.size();
	ChannelMap newMap = new ChannelMap(network, newChanData, distro);

	double[][] newChanProb = new double[networkSize][networkSize];

	for (int i = 0; i < networkSize; i++)
	    for (int j = 0; j < networkSize; j++)
		if (chanProb[i][j] != 0)
		{
		    distance = ((Node) network.nodes.get(i)).distance((Node) network.nodes.get(j));
		    oldChanInfo = getChanInfo(distance);
		    newChanInfo = newMap.getChanInfo(distance);
		    newMap.chanProb[i][j] = newChanInfo.probability-oldChanInfo.probability+chanProb[i][j];
		    if (newMap.chanProb[i][j] > 1)
			newMap.chanProb[i][j] = 1.0;
		    if (newMap.chanProb[i][j] < 0)
			newMap.chanProb[i][j] = 0.0;
		}
		else
		    newMap.chanProb[i][j] = chanProb[i][j];

	return newMap;
    }
}