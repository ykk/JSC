package simulation.networks;

import simulation.communications.channels.*;
import simulation.communications.channels.data.*;
import simulation.communications.queues.*;
import simulation.communications.nodes.*;
import simulation.eventbased.mediumaccess.*;
import simulation.networks.nodes.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.channels.*;
import simulation.files.images.*;

/** Simulation class to provide Roofnet setup.
 * @author ykk
 */
public class Linear
    extends PreDefinedNetwork
{
    //Methods
    /** Empty constructor for extension.
     */
    protected Linear()
    {
	super();
    }

    /** Constructor
     * @param networkSize size of network, used as width of neywork.
     * @param density density of nodes
     * @param nodeFactory factory to generate nodes
     */
    public Linear(double networkSize, double density, NodeFactory nodeFactory)
    {
	super(networkSize, density, nodeFactory);
    }

    /** Generate new network.
     */
    public void generateNetwork()
    {
	netArea = new RectangleNetArea(networkSize, density);
	pointProcess = new Grid(density);
	generateNodes(pointProcess);
    }
    
    /** Function to test network by drawing it.
     * @param args 1st argument is density of network;
     *             2nd argument is network length
     */
    public static void main(String[] args)
    {
	PreDefinedNetwork testNet = new Linear(Double.parseDouble(args[1]),
					       Double.parseDouble(args[0]),
					       new Node(new Coordinate(0,0), new ZeroOne(1)));
	testNet.generateNetwork();
	testNet.draw("testNetworkImage.jpg", ImageFile.JPEG_TYPE, 100, 20);
    }
}