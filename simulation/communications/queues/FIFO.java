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

    /** Check if queue is in queue.
     * @param packet packet reference
     */
    public boolean inQueue(Object packet)
    {
	return (queue.indexOf(packet) != -1);
    }

    /** Remove packet from queue.
     * @para packet reference
     */
    public void remove(Object packet)
    {
	queue.remove(queue.indexOf(packet));
    }

    /** Receive packet into queue.  Drops packet if queue is full.
     * @param packet packet to be received by queue
     * @return if packet is dropped
     */
    public boolean receive(Object packet)
    {
	if ((queueSize == 0) || (queue.size() < queueSize))
	{
	    queue.add(packet);
	    return true;
	}
	else
	    return false;
    }

    public int size()
    {
	return queue.size();
    }

    public Object get()
    {
	if (queue.size() == 0)
	    return null;
	else
	    return queue.remove(0);
    }

    public Queue newQueue()
    {
	return new FIFO(this.queueSize);
    }
}
