package simulation.eventbased.packetprocesses;

import simulation.eventbased.*;
import simulation.communications.packets.*;

/** Interface for processing packets.
 * @see PacketDelay
 * @author ykk
 */
public interface PacketProcess
{
    //Methods
    /** Function to process packet.
     * @param packet packet to process
     * @param objectRef reference object for processing
     */
    public void process(Packet packet, Object objectRef);
}