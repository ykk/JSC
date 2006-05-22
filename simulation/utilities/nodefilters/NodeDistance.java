package simulation.utilities.nodefilters;

import simulation.networks.nodes.*;

/** Node-distance pairs used by node filters.
 * @author ykk
 */
public class NodeDistance
    implements Comparable
{
    //Members
    /** Reference to node.
     */
    public Node node;
    
    /** Distance value.
     */
    public double distance;

    //Methods
    /** Constructor for node and distance pair.
     * @param node node reference
     * @param distance distance value
     */
    public NodeDistance(Node node, double distance)
    {
	this.node = node;
	this.distance = distance;
    }

    /** Comparable interface.
     * @param object object to compare to
     */
    public int compareTo(Object object)
    {
	if (object instanceof NodeDistance)
	{
	    double value = this.distance - ((NodeDistance) object).distance;
	    if (value == 0)
		return 0;
	    else if (value > 0)
		return 1;
	    else
		return 0;
	}
	else
	    throw new RuntimeException(this+" received object "+object+" not of type NodeDistance.");
    }
}
