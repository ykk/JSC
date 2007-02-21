package simulation.communications.packets;

/** Class for communication packet with only payload
 * @author ykk
 */
public class Packet
    implements PacketFactory
{
    //Members
    /** Packet length.
     */
    public int length;

    //Methods
    /** Constructor for packet.
     * @param length length of payload
     */
    public Packet(int length)
    {
	this.length = length;
    }
    
    /** Return length of packet's payload in bytes.
     * @return length of payload
     */
    public int payLoad()
    {
	return length;
    }

    /** Reutrn total length of packet in bytes.
     * @return total length of packet
     */
    public int totalLength()
    {
	return length;
    }

    /** Duplicate packets.
     * @return duplicated packet
     */
    public Packet duplicate()
    {
	return new Packet(length);
    }
}
