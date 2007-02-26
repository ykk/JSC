package simulation.communications.packets;

import simulation.utilities.structures.*;

/** Class for packet with time and route.
 * @author ykk
 */
public class RoutedPacket
    extends TimedPacket
{
    //Members
    /** Route reference.
     */
    Route route;

    //Methods
    /** Constructor for packet.
     * @param length length of payload
     */
    public RoutedPacket(int length)
    {
	super(length);
    }
}