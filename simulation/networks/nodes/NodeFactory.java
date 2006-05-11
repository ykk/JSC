package simulation.networks.nodes;

import simulation.networks.*;

/** Interface to provide creation of nodes.
 * @author ykk
 */
public interface NodeFactory
{
    /** Get new node with specified coordinate.
     * @param coordinate coordinate for new node.
     * @return instance of a new node
     */
    public Node newNode(Coordinate coordinate);
}
