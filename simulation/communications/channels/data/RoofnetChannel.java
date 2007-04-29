package simulation.communications.channels.data;

import simulation.files.text.*;
import simulation.communications.channels.*;
import simulation.networks.*;

/** Data from Roofnet.
 * Provides {@link ReceiveProbVar} for mapping to channels.
 * @author ykk
 */
public class RoofnetChannel
    extends ChannelBySize
{
    //Members
    /** Reference to network.
     */
    public Network network;
    /** JarTable for data.
     */
    private JarTable data = new JarTable("/simulation/communications/channels/data/roofnetChannel.dat");
    /** Short packets [60 bytes] at 1 Mbps.
     * Value is 1.
     * <BR><img src="doc-files/roofnetchannel1.png"><BR>
     */
    public static final int SHORT_1MBPS = 1;
    /** Long packets [1500 bytes] at 1 Mbps.
     * Value is 2.
     * <BR><img src="doc-files/roofnetchannel2.png"><BR>
     */
    public static final int LONG_1MBPS = 2;
    /** Long packets [1500 bytes] at 2 Mbps.
     * Value is 3.
     * <BR><img src="doc-files/roofnetchannel3.png"><BR>
     */
    public static final int LONG_2MBPS = 3;
    /** Long packets [1500 bytes] at 5.5 Mbps.
     * Value is 4.
     * <BR><img src="doc-files/roofnetchannel4.png"><BR>
     */
    public static final int LONG_5MBPS = 4;
    /** Long packets [1500 bytes] at 11 Mbps.
     * Value is 5.
     * <BR><img src="doc-files/roofnetchannel5.png"><BR>
     */
    public static final int LONG_11MBPS = 5;
    /** Channel rates.
     */
    public static final double[] rates = {1e6, 1e6, 2e6, 5.5e6, 11e6};
    /** Channel rate.
     */
    public int rate;

    //Methods
    /** Constructor.
     * The rate of the larger packets (200 to 4096 bytes) are to be chosen.
     * @see #LONG_1MBPS
     * @see #LONG_2MBPS
     * @see #LONG_5MBPS
     * @see #LONG_11MBPS
     * @param datarate rate of usual data
     * @param network reference to network
     */
    public RoofnetChannel(int datarate, Network network)
    {
	super(null);
	rate = datarate;
	this.network = network;

	data.read();
	for (int i = 0; i < 11; i++)
	    data.content.stringToDouble(i);
    }

    /** Output data for roofnet channel.
     * @param args 1st argument to indicate channel,
     *             see {@link #SHORT_1MBPS}, {@link #LONG_1MBPS},{@link #LONG_2MBPS},
     *             {@link #LONG_5MBPS},{@link #LONG_11MBPS}
     */
    public static void main(String[] args)
    {
	int index = Integer.parseInt(args[0]);

	RoofnetChannel channel = new RoofnetChannel(0, null);
	System.out.println(channel.getChannel(index));
    }

    /** Get channel.
     * @param index index of channel
     * @see #SHORT_1MBPS
     * @see #LONG_1MBPS
     * @see #LONG_2MBPS
     * @see #LONG_5MBPS
     * @see #LONG_11MBPS
     */
    public ChannelData getChannel(int index)
    {
	ChannelData chanData = new ChannelData();

	for (int i = 0; i < data.content.size(); i++)
	    chanData.add(((Double) data.content.getItem(i,0)).doubleValue(),
			 ((Double) data.content.getItem(i,(index-1)*2+1)).doubleValue()/100,
			 ((Double) data.content.getItem(i,(index-1)*2+2)).doubleValue()/100);
	return chanData;
    }

    /** Provide roofnet channel.
     */
    public void buildChannel()
    {
	channels.clear();
	ChannelMap channelMap =  new ChannelMap(network, getChannel(SHORT_1MBPS));
	MappedChannel dChannel = new MappedChannel((1e6)/8.0, (1e6)/8.0, channelMap);
	dChannel.buildChannel();
	channels.add(dChannel);
	addChannel(200,4096,
		   new MappedChannel(rates[rate]/8.0,(1e6)/8.0,
				     channelMap.newMapByDiff(getChannel(rate))));
    }
}