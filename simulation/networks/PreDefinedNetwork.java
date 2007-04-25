package simulation.networks;

import simulation.files.images.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.nodes.*;
import simulation.networks.channels.*;

/** Class to provide predefined network.
 * Uses square network and Poisson point process.
 * @author ykk
 */
public class PreDefinedNetwork
    extends Network
{
    //Members
    /** Network size, assuming square network.
     */
    public double networkSize;
    /** Density of nodes.
     */
    public double density;

    /** Reference to point process.
     * Default to Poisson point process.
     */
    public PointProcess pointProcess;

    //Methods
    /** Constructor.
     * Nodefactory defaulted to {@link Node}.
     * @param networkSize size of network, assuming square network.
     * @param density density of nodes
     */
    public PreDefinedNetwork(double networkSize, double density)
    {
	this(networkSize, density, new Node(new Coordinate(0,0), new ZeroOne(1)));
    }

    /** Constructor
     * @param networkSize size of network, assuming square network.
     * @param density density of nodes
     * @param nodeFactory factory to generate nodes
     */
    public PreDefinedNetwork(double networkSize, double density, NodeFactory nodeFactory)
    {
	super();
	this.networkSize = networkSize;
	this.density = density;
	this.nodeFactory = nodeFactory;
    }

    /** Generate new network.
     */
    public void generateNetwork()
    {
	netArea = new RectangleNetArea(networkSize, networkSize);
	pointProcess = new Poisson(density);
	generateNodes(pointProcess);
    }
    
    /** Function to test network by drawing it.
     * @param args 1st argument is density of network;
     *             2nd argument is network size
     */
    public static void main(String[] args)
    {
	PreDefinedNetwork testNet = new PreDefinedNetwork(Double.parseDouble(args[1]),
						Double.parseDouble(args[0]));
	testNet.generateNetwork();
	testNet.draw("testNetworkImage.jpg", ImageFile.JPEG_TYPE, 100, 20);
    }
}