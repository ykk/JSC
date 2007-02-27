package simulation.communications.queues;

/** Class encapsulating two queues.
 * @author ykk
 */
public class TxRxQueue
    extends Queue
{
    //Members
    /** Transmit queue.
     */
    public Queue transmit;
    /** Receive queue.
     */
    public Queue receive;

    //Methods
    /** Constructor.
     * @param transmit transmit queue.
     * @param recieve receive queue.
     */
    public TxRxQueue(Queue transmit, Queue receive)
    {
	this.transmit = transmit;
	this.receive = receive;
    }
    
    /** Receive packet into receive queue.
     * @param packet packet to be received by queue
     * @return if packet is dropped
     */
    public boolean receive(Object packet)
    {
	return receive.receive(packet);
    }

    /** Get packet from transmit queue.
     * @return packet to be transmitted; or null if queue is empty
     */
    public Object get()
    {
	return transmit.get();
    }

    /** Duplicate queue.
     * @return new instance of queue
     */
    public Queue newQueue()
    {
	return new TxRxQueue(transmit.newQueue(), receive.newQueue());
    }

    /** Return size of queue, but useless due to the 2 queues.
     * @return -1
     */
    public int size()
    {
	return -1;
    }
}
