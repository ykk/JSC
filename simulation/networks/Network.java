package simulation.networks;

import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.nodes.*;
import java.util.*;

/** Basic class for network.
 * @author ykk
 */
public abstract class Network
{
    //Members
    /** Vector to hold nodes.
     * Defaulted to empty vector.
     */
    public Vector nodes = new Vector();

    /** Area definition for network.
     */
    public NetworkArea netArea;

    /** Node factory to generate nodes.
     */
    public NodeFactory nodeFactory;

    //Methods
    /** Constructor for generic network.
     * @param netArea network area definition
     * @param nodeFactory factory to generate nodes
     * @param pointProcess process to generate coordinates of nodes
     */
    public Network(NetworkArea netArea, NodeFactory nodeFactory, PointProcess pointProcess)
    {
	this.netArea = netArea;
	this.nodeFactory = nodeFactory;

	Vector positions = pointProcess.getCoordinates(netArea);
	for (int i = 0; i < positions.size(); i++)
	    nodes.add(nodeFactory.newNode((Coordinate) positions.get(i)));
    }
}
