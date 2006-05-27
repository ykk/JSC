package simulation.utilities.nodefilters;

import java.util.*;
import simulation.utilities.nodefilters.*;
import simulation.networks.*;
import simulation.networks.nodes.*;

/** Node filter to get nth nodes farthest from a given point.
 * @author ykk
 */
public class Farthest
    extends Nearest
{
    /** Constructor for farthest nodes filter.
     * @param n number of points to return
     * @param point coordinate of point to test proximity to
     */
    public Farthest(int n, Coordinate point)
    {
	super(n,point);
    }

    public Vector addNode(Node node, Vector resultSet)
    {
	resultSet.add(new NodeDistance(node,point.distance(node)));
	while (resultSet.size() > n)
	    resultSet.remove(0);
	return resultSet;
    }
}
