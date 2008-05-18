package simulation.communications.queues;

/** Class encapsulating two queues.
 * Default queue in {@link Queue} is not used.
 * @author ykk
 */
public class DoubleQueue
    extends Queue
{
    //Members
    /** First queue.
     */
    public Queue queue1;
    /** Second queue.
     */
    public Queue queue2;

    //Methods
    /** Constructor.
     * @param queue1 first queue.
     * @param queue2 second queue.
     */
    public DoubleQueue(Queue queue1, Queue queue2)
    {
	this.queue1 = queue1;
	this.queue2 = queue2;
    }
    
    /** Dummy function since both queues are managed on their own.
     * @param packet packet to be received by queue
     * @return if packet is dropped
     */
    public boolean receive(Object packet)
    {
	throw new RuntimeException(this+" has two queues that are managed"+
				   " on their own!");
    }

    /** Dummy function since both queues are managed on their own.
     * @return packet to be transmitted; or null if queue is empty
     */
    public Object get()
    {
	throw new RuntimeException(this+" has two queues that are managed"+
				   " on their own!");
    }

    /** Check if packet is in either queue.
     * @param packet packet reference
     * @return if packet is in either queue
     */
    public boolean inQueue(Object packet)
    {
	return queue1.inQueue(packet) || queue2.inQueue(packet);
    }

    /** Check if both queues are empty.
     * @return if both queues are empty
     */
    public boolean isEmpty()
    {
	return queue1.isEmpty() && queue2.isEmpty();
    }

    /** Dummy function since both queues are managed on their own.
     * @param packet reference to packet
     */
    public boolean remove(Object packet)
    {
	throw new RuntimeException(this+" has two queues that are managed"+
				   " on their own!");
    }

    /** Duplicate queue.
     * @return new instance of queue
     */
    public Queue newQueue()
    {
	return new DoubleQueue(queue1.newQueue(), queue2.newQueue());
    }

    /** Return size of queue, but useless due to the 2 queues.
     * @return -1
     */
    public int size()
    {
	return -1;
    }
}
