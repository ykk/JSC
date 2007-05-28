package simulation.results.processors;

import simulation.communications.channels.*;
import simulation.communications.nodes.*;
import simulation.communications.packets.*;
import simulation.networks.simulator.*;
import simulation.results.*;
import java.util.*;

/** Class to process channel transmission with DataAckFrame.
 * @see DataAckFrame
 * @author ykk
 */
public class DataAckChannelProcessor
    implements ChannelResultProcessor
{
    //Members
    /** Vector to hold results for each node.
     */
    public Vector results = new Vector();
    /** References to nodes.
     */
    public Vector nodes;
    /** Size of packet.
     */
    public double packetSize;
    /** Size of acknowledgment.
     */
    public double ackSize;
    /** Last transmitting source.
     */
    protected CommNode lastSource;
    /** Time of last transmission.
     */
    protected double lastTxTime;

    //Methods
    /** Constructor.
     * @param nodes reference to nodes
     * @param packetSize size of packet
     * @param ackSize size of acknowledgment
     */
    public DataAckChannelProcessor(Vector nodes, double packetSize, double ackSize)
    {
	this.nodes = nodes;
	this.packetSize = packetSize;
	this.ackSize = ackSize;

	for (int i = 0; i < nodes.size(); i++)
	    results.add(new ResultVar());
    }

    /** Process transmission for result.
     * @param source source node
     * @param destination destination node
     * @param packet packet to transmit
     * @param simulator reference to simulator
     */
    public void process(CommNode source, CommNode destination, 
			Object packet, Simulator simulator)
    {
	if ((lastSource != source) || (lastTxTime != simulator.time()))
	{
	    lastSource = source;
	    lastTxTime = simulator.time();
	    if (((DataAckFrame) packet).type == DataAckFrame.TYPE_ACK)
		((ResultVar) results.get(nodes.indexOf(source))).input(ackSize);
	    else
		((ResultVar) results.get(nodes.indexOf(source))).input(packetSize);
	}
    }
}