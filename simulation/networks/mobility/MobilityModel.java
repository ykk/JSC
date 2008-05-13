package simulation.networks.mobility;

import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.networks.nodes.*;

/** Abstract class for mobility model.
 * At the edge, the node is looped around, i.e., reflect over the edge.
 * Does not handle {@link CircleNetArea}
 * @author ykk
 */
public abstract class MobilityModel
{
    //Members
    /** Reference to network.
     */
    Network network;

    //Methods
    /** Constructor.
     * Cannot handle {@link CircleNetArea}.
     * @param network network reference
     */
    public MobilityModel(Network network)
    {
	this.network = network;
	if (network.netArea instanceof CircleNetArea)
	    throw new RuntimeException(network + " uses circular area, where "+
				       "loop around in edges is ambiguous.");
    }

    /** Move the network once.
     */
    public void move()
    {
	for (int i = 0 ; i < network.nodes.size(); i++)
	    moveNodeLoop((Node) network.nodes.get(i));

	network.getNeighbors();
    }

    /** Move node and loop around at edges.
     * @param node reference to node
     */
    public void moveNodeLoop(Coordinate node)
    {
	moveNode(node);
	if (!network.netArea.inArea(node))
	{
	    if (node.x > network.netArea.maxX())
		node.x = 2.0*network.netArea.maxX()-node.x;
	    else if (node.x < network.netArea.minX())
		node.x = 2.0*network.netArea.minX()-node.x;

	    if (node.y > network.netArea.maxY())
		node.y = 2.0*network.netArea.maxY()-node.y;
	    else if (node.y < network.netArea.minY())
		node.y = 2.0*network.netArea.minY()-node.y;
	}
    }

    /** Move network once.
     * @param node reference to node
     */
    public abstract void moveNode(Coordinate node);
}