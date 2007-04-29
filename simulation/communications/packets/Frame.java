package simulation.communications.packets;

/** Abstract class for a frame.
 * @author ykk
 */
public abstract class Frame
    extends Packet
{
    //Members
    /** Header length;
     */
    public int headerLength ;
    /** Packet length.
     */
    public int length;
    /** Reference to packet.
     */
    public Packet packet;

    //Methods
    /** Constructor.
     * @param packet reference to packet
     */
    public Frame(Packet packet)
    {
	super(0);
	this.packet = packet;
    }

    /** Return length of packet's payload in bytes, include component packet total length.
     * @return length of payload
     */
    public int payLoad()
    {
	return packet.totalLength()+length;
    }

    /** Reutrn total length of packet in bytes.
     * @return total length of packet
     */
    public int totalLength()
    {
	return headerLength+payLoad();
    }
    
    /** Duplicate frame with new instance of packet too.
     * @return duplicated frame
     */
    public abstract Packet duplicate();
}