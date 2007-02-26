package simulation.communications.packets;

/** Class for communication packet with timing.
 * @author ykk
 */
public class TimedPacket
    extends Packet
{
    //Members
    /** Start time of packet.
     */
    public double startTime=0;
    /** End time of packet.
     */
    public double endTime=0;

    //Methods
    /** Constructor for packet.
     * @param length length of payload
     */
    public TimedPacket(int length)
    {
	super(length);
    }

    /** Constructor for packet with start time.
     * @param length length of payload
     * @param startTime start time of packet
     */
    public TimedPacket(int length, double startTime)
    {
	super(length);
	this.startTime = startTime;
    }

    /** Duplicate timed packet with start time.
     * @param startTime start time of duplicated packet
     */
    public TimedPacket duplicate(double startTime)
    {
	TimedPacket packet = new TimedPacket(length, startTime);
	packet.startTime = startTime;
	return packet;
    }
    

    /** Record end time of packet.
     * @param endTime end time of packet
     */
    public void recordEnd(double endTime)
    {
	this.endTime = endTime;
    }
    
    /** Return delay of packet.
     * @return delay of packet
     */
    public double delay()
    {
	if (endTime <= startTime)
	    throw new RuntimeException(this+" has delay "+(endTime-startTime)+
				       " which is less than or equal to zero");
	else
	    return endTime-startTime;
    }
}