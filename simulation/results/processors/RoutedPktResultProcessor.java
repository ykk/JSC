package simulation.results.processors;

import simulation.results.*;
import simulation.communications.packets.*;
import java.util.*;

/** Class for result processing of RoutedPacket via logging.
 * @see RoutedPacket
 * @author ykk
 */
public class RoutedPktResultProcessor
    extends ResultProcessor
{
    //Members
    /** Vectors of received packets.
     */
    public Vector received = new Vector();
    /** Result holding delay, and sampleSize will be throughput.
     */
    public ResultVar delay = new ResultVar();

    //Methods
    /** Input {@link RoutedPacket} to process for result.
     * @param object routed packet to be processed
     */
    public void input(java.lang.Object object) 
    {
	if (received.indexOf(object) == -1)
	{
	    received.add(object);
	    delay.input(((RoutedPacket) object).endTime-((RoutedPacket) object).startTime);
	    
	}
    }
}