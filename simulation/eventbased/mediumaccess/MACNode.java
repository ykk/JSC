package simulation.eventbased.mediumaccess;

import simulation.eventbased.*;
import simulation.communications.nodes.*;
import simulation.communications.channels.*;
import simulation.communications.queues.*;
import simulation.networks.*;
import simulation.networks.channels.*;

/** Abstract class to define medium access control node.
 * @author ykk
 */
public abstract class MACNode
    extends CommNode
    implements EventTriggered
{
    /** Function to trigger node in idle mode.
     */
    public abstract void trigger(Simulator simulator);

    public abstract void run(double time, String event, Simulator simulator);

    /** Receive packet from a node.
     * @param source source node of packet
     * @param packet packet delivered
     * @param simulator reference to simulator
     * @see #receive(CommNode source, Object packet)
     */
    public abstract void receive(CommNode source, Object packet, Simulator simulator);

    /** Receive packet from a node -- replaced by 
     * {@link #receive(CommNode source, Object packet, Simulator simulator)}.
     * @param source source node of packet
     * @param packet packet delivered
     * @see #receive(CommNode source, Object packet, Simulator simulator)
     */
    public void receive(CommNode source, Object packet)
    {
	throw new RuntimeException(this+" called receive(CommNode source, Object packet) function that has been replaced by receive(CommNode source, Object packet, Simulator simulator).");
    }

    public MACNode(Coordinate coordinate, Channel channel, CommChannel commChannel, Queue queue)
    {
	super(coordinate, channel, commChannel, queue);
    }
}
