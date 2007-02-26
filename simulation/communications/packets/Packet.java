package simulation.communications.packets;

/** Class for communication packet with header and payload.
 * @author ykk
 */
public class Packet
    implements PacketFactory
{
    //Members
    /** Packet length.
     */
    public int length;
    /** Header length.
     * Defaulted to 0.
     */
    public int headerLength = 0;

    //Methods
    /** Constructor for packet.
     * @param length length of payload
     */
    public Packet(int length)
    {
	this.length = length;
    }
    
    /** Constructor for packet.
     * @param length length of payload
     * @param headerLength length of header
     */
    public Packet(int length, int headerLength)
    {
	this.length = length;
	this.headerLength = headerLength;
    }
    

    /** Return length of packet's payload in bytes.
     * @return length of payload
     */
    public int payLoad()
    {
	return length;
    }

    /** Return length of packet's header in bytes.
     * @return length of header
     */
    public int header()
    {
	return headerLength;
    }

    /** Reutrn total length of packet in bytes.
     * @return total length of packet
     */
    public int totalLength()
    {
	return headerLength+length;
    }

    /** Duplicate packets.
     * @return duplicated packet
     */
    public Packet duplicate()
    {
	return new Packet(length, headerLength);
    }
}
