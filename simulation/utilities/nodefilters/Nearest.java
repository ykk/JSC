package simulation.utilities.nodefilters;

import java.util.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Node filter to get nth nodes closest to a given point.
 * @author ykk
 */
public class Nearest
    extends NodeFilter
{
    //Members
    /** nth number of nearest object to return.
     */
    public int n;
    /** Coordinate of point to be closest to.
     */
    public Coordinate point;

    //Methods
    /** Constructor for nearest nodes filter.
     * @param n number of points to return
     * @param point coordinate of point to test proximity to
     */
    public Nearest(int n, Coordinate point)
    {
	this.n = n;
	this.point = point;
    }

    /** Filter for nodes.
     * @param nodes input set of nodes
     * @return set of filtered nodes
     */
    public Vector filter(Vector nodes)
    {
	SortedVector result = new SortedVector();
	Vector filtered = new Vector();

	for (int i = 0; i < nodes.size(); i++)
	    result = addNode((Node) nodes.get(i), result);

	for (int i = 0; i < result.size(); i++)
	    filtered.add(((NodeDistance) result.get(0)).node);
	
	return filtered;
    }

    /** Add node to samples.
     * @param node node to add to samples
     * @param resultSet set of resulting nodes
     * @return set of n nodes
     */
    protected SortedVector addNode(Node node, SortedVector resultSet)
    {
	resultSet.add(new NodeDistance(node,point.distance(node)));
	while (resultSet.size() > n)
	    resultSet.remove(resultSet.size()-1);
	return resultSet;
    }
}
