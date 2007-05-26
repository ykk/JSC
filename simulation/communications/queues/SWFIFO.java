package simulation.communications.queues;

import simulation.communications.queues.*;

/** Class defining First-In First-Out (FIFO) link layer queue, with sliding window.
 * Note that the packet is not removed from the queue using {@link #get} in such a queue.
 * @author ykk
 */
public class SWFIFO
    extends FIFO
{
    //Members
    /** Pointer to next packet.
     */
    protected int lastGetIndex= 0;
    /** Window size.
     * Default to 0, meaning infinite sliding window.
     */
    public int windowSize = 0;

    //Methods
    /** Constructor for queue.
     * @param queueSize maximum queue capacity
     * @param windowSize maximum sliding window size
     */
    public SWFIFO(int queueSize, int windowSize)
    {
	super(queueSize);
	this.windowSize = windowSize;
    }

    /** Constructor for queue.
     * @param queueSize maximum queue capacity
     */
    public SWFIFO(int queueSize)
    {
	super(queueSize);
    }

    /** Constructor for queue.
     * Maximum queue capacity and window size are set to infinity (queueSize=0).
     */    
    public SWFIFO()
    {
	super();
    }

    public Object get()
    {
	if (this.size() == 0)
	    return null;

	lastGetIndex++;
	if (windowSize != 0)
	    lastGetIndex %= windowSize;
	lastGetIndex %= this.size();
	return this.get(lastGetIndex);
    }

    public boolean remove(Object packet)
    {
	if ((this.indexOf(packet) != -1) &&
	    (this.indexOf(packet) >= lastGetIndex))
	{
	    lastGetIndex--;
	    if (lastGetIndex < 0) lastGetIndex +=windowSize;
	}

	return super.remove(packet);
    }

    public Queue newQueue()
    {
	return new SWFIFO(this.queueSize, this.windowSize);
    }

    /** Testing queue.
     * With queue size of 9 and window size of 2. 
     */
    public static void main(String[] args)
    {
	SWFIFO q = new SWFIFO(9,2);
	test(q);
    }
}