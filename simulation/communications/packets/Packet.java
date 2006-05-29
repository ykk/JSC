package simulation.communications.packets;

/** Class for communication packet definition.
 * @author ykk
 */
public class Packet
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
    
    /** Return length of packet's payload.
     * @return length of payload
     */
    public int payLoad()
    {
	return length;
    }
}
