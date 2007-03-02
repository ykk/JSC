package simulation.communications.queues;

/** Abstract class defining link layer queue.
 * @author ykk
 */
public abstract class Queue
{
    /** Receive packet into queue.
     * @param packet packet to be received by queue
     * @return if packet is dropped
     */
    public abstract boolean receive(Object packet);

    /** Get packet from queue.
     * @return packet to be transmitted; or null if queue is empty
     */
    public abstract Object get();

    /** Duplicate queue.
     * @return new instance of queue
     */
    public abstract Queue newQueue();

    /** Check if queue is empty.
     * @return if queue is empty
     */
    public boolean isEmpty()
    {
	return (this.size() == 0);
    }

    /** Return size of queue.
     * @return queue size
     */
    public abstract int size();
}
