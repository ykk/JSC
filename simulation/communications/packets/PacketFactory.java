package simulation.communications.packets;

/** Factory to duplicate packets.
 * @author ykk
 */
public interface PacketFactory
{
    //Methods
    /** Duplicate packets.
     * @return duplicated packet
     */
    public Packet duplicate();
}