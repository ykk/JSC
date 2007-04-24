package simulation.communications.channels.data;

import simulation.files.text.*;

/** Data from Roofnet.
 * Provides {@link ReceiveProbVar}
 * @author ykk
 */
public class RoofnetChannel
{
    //Members
    /** JarTable for data.
     */
    private JarTable data = new JarTable("/simulation/communications/channels/data/roofnetChannel.dat");
    /** Short packets [60 bytes] at 1 Mbps.
     * Value is 1.
     */
    public static final int SHORT_1MBPS = 1;
    /** Long packets [1500 bytes] at 1 Mbps.
     * Value is 2.
     */
    public static final int LONG_1MBPS = 2;
    /** Long packets [1500 bytes] at 2 Mbps.
     * Value is 3.
     */
    public static final int LONG_2MBPS = 3;
    /** Long packets [1500 bytes] at 5.5 Mbps.
     * Value is 4.
     */
    public static final int LONG_5MBPS = 4;
    /** Long packets [1500 bytes] at 11 Mbps.
     * Value is 5.
     */
    public static final int LONG_11MBPS = 5;


    //Methods
    /** Constructor.
     */
    public RoofnetChannel()
    {
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

	RoofnetChannel channel = new RoofnetChannel();
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
}