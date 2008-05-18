package simulation.communications.queues;

import simulation.communications.packets.*;
import java.util.*;

/** Abstract class defining link layer queue.
 * @author ykk
 */
public abstract class Queue
    extends Vector
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

    /** Check if queue is in queue.
     * @param packet packet reference
     */
    public boolean inQueue(Object packet)
    {
	return (indexOf(packet) != -1);
    }

    /** Test function for queue.
     * Events are:
     * Add 10 packets;
     * Get 4 packets;
     * Remove 3nd and 1st packet; and
     * Get 2 packets.
     * @param queue queue to be tested
     */
    public static void test(Queue queue)
    {
	Packet pack = new Packet(100);

	//Add 10 packets
	for (int i = 0; i < 10; i++)
	    System.out.println(i+"\tAdd"+queue.receive(pack.duplicate()));
	
	//Get 4 packets
	for (int i = 0; i < 4; i++)
	{
	    System.out.println(queue);
	    System.out.println("\tGet"+i+"\t"+queue.get());
	}

	//Remove 3, 1 packets
	Object obj;
	System.out.println(queue);
	obj = queue.get(2);
	queue.remove(obj);
	System.out.println("\tRemove"+3+"\t"+obj);	
	System.out.println(queue);
	obj = queue.get(0);
	queue.remove(obj);
	System.out.println("\tRemove"+1+"\t"+obj);
	System.out.println(queue);

	//Get 2 packets
	for (int i = 0; i < 2; i++)
	{
	    System.out.println(queue);
	    System.out.println("\tGet"+i+"\t"+queue.get());
	}
    }
}
