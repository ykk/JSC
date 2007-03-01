package simulation.utilities.processors;

import java.util.*;
import simulation.communications.packets.*;

/** Class for result processing of TimedPacket.
 * @see TimedPacket
 * @author ykk
 */
public class TimedPktResultProcessor
    extends ResultProcessor
{
    //Members
    /** Flag to print.
     */
    public boolean printing = false;
    /** Separator to print.
     */
    public String separator = "\t";

    //Methods
    /** Input object to process for result.
     * @param object object to be processed
     */
    public void input(Object object)
    {
	if (!(object instanceof TimedPacket))
	    throw new RuntimeException(this+" told to handle "+object);
	TimedPacket pkt = (TimedPacket) object;

	if (printing) System.out.println(string(pkt));
    }

    /** Return string for object.
     * @param pkt packet to process
     */
    public String string(TimedPacket pkt)
    {
	return pkt.seqNumber+separator+
	    pkt.startTime+separator+
	    pkt.endTime+separator+
	    pkt.delay();
    }
}