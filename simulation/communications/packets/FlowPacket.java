package simulation.communications.packets;

import simulation.utilities.structures.*;
import simulation.networks.simulator.*;

/** Class for packet with time and route (flow).
 * Allows route to be change with time, according to a flow.
 * @author ykk
 */
public class FlowPacket
    extends TimedPacket
{
    //Members
    /** Flow reference.
     */
    public NetworkFlow flow;

    //Methods
    /** Constructor for packet.
     * @param length length of payload
     */
    public FlowPacket(int length)
    {
	super(length);
    }

    /** Constructor for packet with start time.
     * @param length length of payload
     * @param startTime start time of packet
     */
    public FlowPacket(int length, double startTime)
    {
	super(length, startTime);
    }

    /** Constructor for packet with start time.
     * @param length length of payload
     * @param headerLength length of header
     * @param startTime start time of packet
     */
    public FlowPacket(int length, int headerLength, double startTime)
    {
	super(length, headerLength, startTime);
    }

    /** Constructor for packet with start time and route.
     * @param length length of payload
     * @param headerLength length of header
     * @param startTime start time of packet
     * @param flow flow packet belongs to
     */
    public FlowPacket(int length, int headerLength, double startTime, 
			NetworkFlow flow)
    {
	super(length, headerLength, startTime);
	this.flow = flow;
    }

    /** Duplicate routed packet without start time.
     */
    public FlowPacket duplicate()
    {
	return new FlowPacket(length, headerLength, 0.0, flow);
    }

    /** Duplicate routed packet with start time.
     * @param startTime start time of duplicated packet
     */
    public FlowPacket duplicate(double startTime)
    {
	return new FlowPacket(length, headerLength, startTime, flow);
    }
}