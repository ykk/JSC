package simulation.communications.queues;

import java.util.*;
import simulation.communications.queues.*;

/** Class defining First-In First-Out (FIFO) link layer queue.
 * @author ykk
 */
public class FIFO
    extends Queue
{
    //Members
    /** Vector to contain packets in order received.
     */
    public Vector queue = new Vector();
    /** Maximum queue capacity.
     * Defaulted to 0 (implies infinity).
     */
    public int queueSize = 0;

    //Methods
    /** Constructor for queue.
     * @param queueSize maximum queue capacity
     */
    public FIFO(int queueSize)
    {
	this.queueSize = queueSize;
    }

    /** Constructor for queue.
     * Maximum queue capacity is set to infinity (queueSize=0).
     */    
    public FIFO()
    {
	this.queueSize = 0;
    }

    /** Receive packet into queue.  Drops packet if queue is full.
     * @param packet packet to be received by queue
     * @return if packet is dropped
     */
    public boolean receive(Object packet)
    {
	if (queue.size() < queueSize)
	{
	    queue.add(packet);
	    return true;
	}
	else
	    return false;
    }

    public Object get()
    {
	if (queue.size() == 0)
	    return null;
	else
	    return queue.remove(0);
    }
}
