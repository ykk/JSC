package simulation.communications.packets;

/** Frame class to mark packet as data or acknowledgment.
 * @author ykk
 */
public class DataAckFrame
    extends Frame
{
    //Members
    /** Type of packet.
     * Default to {@link #TYPE_DATA}
     */
    public int type = TYPE_DATA;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_ACK = 1;

    //Methods
    /** Constructor.
     * @param packet reference to packet
     * @param type type of frame
     */
    public DataAckFrame(Packet packet, int type)
    {
	super(packet);
	this.type = type;
    }

    /** Constructor.
     * @param packet reference to packet
     */
    public DataAckFrame(Packet packet)
    {
	super(packet);
    }

    /** Duplicate frame with new instance of packet too.
     * @return duplicated frame
     */
    public Packet duplicate()
    {
	return new DataAckFrame(packet.duplicate());
    }
}