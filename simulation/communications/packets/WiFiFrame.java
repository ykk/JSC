package simulation.communications.packets;

/** Frame class to contain information needed for WiFi.
 * @author ykk
 */
public class WiFiFrame
    extends Frame
{
    //Members
    /** Header length.
     * Defaulted to 24 bytes, for PLCP header.
     */
    public int headerLength = 24;
    /** Packet length.
     * Default is 34 bytes, for MAC header and trailer.
     */
    public int length;
    /** Type of packet.
     * Default to {@link #TYPE_BROADCAST}
     */
    public int type = TYPE_BROADCAST;
    public static final int TYPE_BROADCAST = 0;
    public static final int TYPE_2WAY = 1;
    public static final int TYPE_4WAY = 2;

    //Methods
    /** Constructor.
     * @param packet reference to packet
     */
    public WiFiFrame(Packet packet)
    {
	super(packet);
	length = 34;
    }

    /** Duplicate frame with new instance of packet too.
     * @return duplicated frame
     */
    public Packet duplicate()
    {
	return new WiFiFrame(packet.duplicate());
    }
}