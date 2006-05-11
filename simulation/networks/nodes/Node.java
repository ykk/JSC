package simulation.networks.nodes;

import simulation.networks.*;

/** Basic class for node.
 * @author ykk
 */
public class Node
    extends Coordinate
    implements NodeFactory
{
    /** Constructor to get a new node.
     * @param coordinate coordinate for new node.
     */
    public Node(Coordinate coordinate)
    {
	super(coordinate.x, coordinate.y);
    }

    public Node newNode(Coordinate coordinate)
    {
	return new Node(coordinate);
    }
}
