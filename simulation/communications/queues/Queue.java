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
}
