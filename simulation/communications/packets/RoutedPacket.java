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
    public Route route;

    //Methods
    /** Constructor for packet.
     * @param length length of payload
     */
    public RoutedPacket(int length)
    {
	super(length);
    }

    /** Constructor for packet with start time.
     * @param length length of payload
     * @param startTime start time of packet
     */
    public RoutedPacket(int length, double startTime)
    {
	super(length, startTime);
    }

    /** Constructor for packet with start time.
     * @param length length of payload
     * @param headerLength length of header
     * @param startTime start time of packet
     */
    public RoutedPacket(int length, int headerLength, double startTime)
    {
	super(length, headerLength, startTime);
    }

    /** Constructor for packet with start time and route.
     * @param length length of payload
     * @param headerLength length of header
     * @param startTime start time of packet
     * @param route route for packet
     */
    public RoutedPacket(int length, int headerLength, double startTime, Route route)
    {
	super(length, headerLength, startTime);
	this.route = route;
    }

    /** Duplicate routed packet without start time.
     */
    public RoutedPacket duplicate()
    {
	return new RoutedPacket(length, headerLength, 0.0, route);
    }

    /** Duplicate routed packet with start time.
     * @param startTime start time of duplicated packet
     */
    public RoutedPacket duplicate(double startTime)
    {
	return new RoutedPacket(length, headerLength, startTime, route);
    }
}