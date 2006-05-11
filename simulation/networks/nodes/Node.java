package simulation.networks.nodes;

import simulation.networks.*;
import simulation.networks.channels.*;
import java.util.*;

/** Basic class for a node.
 * @author ykk
 */
public class Node
    extends Coordinate
    implements NodeFactory
{
    //Members
    /** Nodes that current node can send to.
     */
    public Vector txPartners;
    /** Channel used by the nodes.
     */
    public Channel channel;

    //Methods
    /** Constructor to get a new node.
     * @param coordinate coordinate for new node.
     */
    public Node(Coordinate coordinate, Channel channel)
    {
	super(coordinate.x, coordinate.y);
	this.channel = channel;
    }

    public Node newNode(Coordinate coordinate)
    {
	return new Node(coordinate,this.channel);
    }
}
