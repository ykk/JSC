package simulation.networks;

import simulation.communications.channels.*;
import simulation.communications.channels.data.*;
import simulation.communications.queues.*;
import simulation.communications.nodes.*;
import simulation.eventbased.mediumaccess.*;
import simulation.networks.nodes.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.channels.*;
import simulation.files.images.*;

/** Simulation class to provide Roofnet setup.
 * @author ykk
 */
public class Roofnet
    extends PreDefinedNetwork
{
    //Members
    /** Communication channel.
     * Uses {@link RoofnetChannel}, with default rate of 11 Mbps.
     */
    public CommChannel commChannel;
   /** Network size, assuming square network.
     */
    private double networkSize;
    /** Network width = 3.
     */
    public double networkWidth = 3;
    /** Network height = 2.
     */
    public double networkHeight = 2;
    /** Density of nodes = 38/6.
     */
    public double density = 38.0/6.0;

    //Methods
    /** Constructor with default settings.
     */
    public Roofnet()
    {
	super();
	commChannel = RoofnetChannel.channel(this, RoofnetChannel.LONG_11MBPS);
    }

    /** Constructor.
     * @param channelRate rate of communication channel, see {@link RoofnetChannel}
     * @param nodeFactory node factory
     */
    public Roofnet(int channelRate, NodeFactory nodeFactory)
    {
	super();
	commChannel = RoofnetChannel.channel(this, channelRate);
	this.nodeFactory = nodeFactory;
    }

    /** Generate new network.
     */
    public void generateNetwork()
    {
	netArea = new RectangleNetArea(networkWidth, networkHeight);
	pointProcess = new Poisson(density);
	generateNodes(pointProcess);
    }

    /** Function to test network by drawing it.
     */
    public static void main(String[] args)
    {
	Roofnet testNet = new Roofnet();
	testNet.nodeFactory = new ALOHA(new Coordinate(0,0), new ZeroOne(2.1), testNet.commChannel, 
					new FIFO(),null,null);
	testNet.generateNetwork();
	testNet.prune((CommChannel) ((ChannelBySize) testNet.commChannel).channels.get(0),0);
	testNet.draw("testNetworkImage.jpg", ImageFile.JPEG_TYPE, 100, 20);
    }
}