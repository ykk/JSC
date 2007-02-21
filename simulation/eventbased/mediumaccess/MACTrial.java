package simulation.eventbased.mediumaccess;

import java.util.*;
import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;
import simulation.networks.pointprocesses.*;
import simulation.files.images.*;
import simulation.utilities.linkcosts.*;
import simulation.utilities.routes.*;
import simulation.utilities.structures.*;
import simulation.utilities.packetprocessors.*;
import simulation.distributions.*;
import simulation.eventbased.*;
import simulation.eventbased.mediumaccess.*;
import simulation.communications.channels.*;
import simulation.communications.queues.*;

/** Class to test medium access node.
 * @see MACNode
 * @author ykk
 */
public class MACTrial
{
    //Members
    /** Network Channel.
     * Defaulted to ZeroOne with radius 2.0.
     * @see ZeroOne
     */
    public Channel networkChannel = new ZeroOne(2.0);
    /** Communication Channel between nodes.
     * Defaulted to Reliable with rate 128 k-bits/s.
     * @see Reliable
     */
    public CommChannel commChannel = new Reliable(128/8);
    /** Queue for nodes.
     * Defaulted to infinite FIFO.
     * @see FIFO
     */
    public simulation.communications.queues.Queue queue = new FIFO();
    /** Distribution of backoff time.
     * Defaulted to Exponential distribution with mean 1.
     * @see Exponential
     */
    public Distribution waitTime = new Exponential(1.0);
    /** Network area definition.
     * Defaulted to Circle network with radius 1.0.
     * @see CircleNetArea
     */
    public NetworkArea netArea= new CircleNetArea(1.0);
    /** Distribution of nodes.
     * Defaulted to Poisson point process with density 8.
     * @see simulation.networks.pointprocesses.Poisson
     */
    public PointProcess pointprocess = new simulation.networks.pointprocesses.Poisson(8.0);
    /** Packet processor.
     * Defined AlwaysFull with length 128 bytes.
     */
    public AlwaysFull processor = new AlwaysFull(128);
    /** Reference to network generated.
     */
    public Network network;
    /** Reference to simulator.
     */
    public Simulator simulator;
    /** Number of samples to collect.
     * Defaulted to 100.
     */
    public int sampleNeeded = 100;

    //Methods
    /** Constructor for MAC trial.
     * @param simulator simulator
     */
    public MACTrial(Simulator simulator)
    {
	this.simulator = simulator;
	((AlwaysFull) processor).timeRef = simulator;
    }

    /** Main function to run trial simulation.
     * @param nodefactory factory to generate network.
     */
    public void run(NodeFactory nodefactory)
    {
	generateNetwork(nodefactory);
	for (int i = 0; i < network.nodes.size(); i++)
	    ((ALOHA) network.nodes.get(i)).trigger(simulator);
	while ((processor.delay.sampleSize < sampleNeeded) && (simulator.queue.size() != 0))
	{
	    simulator.runNextEvent();
	    System.out.println(simulator.queue.size());
	}

	System.out.println("Sample Size ="+processor.delay.sampleSize);
	System.out.println("Mean delay ="+processor.delay.mean);
    }

    /** Generate network for simulation.
     * @param nodefactory node factory to generate nodes
     */
    public void generateNetwork(NodeFactory nodefactory)
    {
	network = new Network(netArea,nodefactory,pointprocess);
    }

    /** Draw sample network simulated.
     */
    public static void main(String[] args)
    {
	MACTrial trial = new MACTrial(null);
	trial.generateNetwork(new Node(new Coordinate(0,0), trial.networkChannel));
	trial.network.draw("MACTrialNetwork.jpg", ImageFile.JPEG_TYPE, 150, 5);
    }
}
