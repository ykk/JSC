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
import simulation.distributions.*;
import simulation.eventbased.*;
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
     * @see Channel
     */
    public Channel networkChannel = new ZeroOne(2.0);
    /** Communication Channel between nodes.
     * Defaulted to Reliable with rate 128 k-bits/s.
     * @see CommChannel
     */
    public CommChannel commChannel = new Reliable(128/8);
    /** Queue for nodes.
     * Defaulted to infinite FIFO.
     * @see simulation.communications.queues.Queue
     */
    public simulation.communications.queues.Queue queue = new FIFO();
    /** Distribution of backoff time.
     * Defaulted to Exponential distribution with mean 1.0.
     * @see Distribution
     */
    public Distribution waitTime = new Exponential(1.0);
    /** Network area definition.
     * Defaulted to Circle network with radius 1.0.
     * @see NetworkArea
     */
    public NetworkArea netArea= new CircleNetArea(1.0);
    /** Distribution of nodes.
     * Defaulted to Poisson point process with density 10.
     * @see PointProcess
     */
    public PointProcess pointprocess = new simulation.networks.pointprocesses.Poisson(10.0);
    /** Reference to network generated.
     */
    public Network network;

    //Methods
    /** Main function to run trial simulation.
     */
    public void run()
    {
	generateNetwork();
    }

    /** Generate network for simulation.
     */
    public void generateNetwork()
    {
	NodeFactory nodefactory = new ALOHA(new Coordinate(0,0),
					    networkChannel,
					    commChannel,
					    queue,
					    waitTime);
	network = new Network(netArea,nodefactory,pointprocess);
    }

    /** Draw sample network simulated.
     */
    public static void main(String[] args)
    {
	MACTrial trial = new MACTrial();
	trial.generateNetwork();
	trial.network.draw("MACTrialNetwork.jpg", ImageFile.JPEG_TYPE, 150, 5);
    }
}
