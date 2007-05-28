package simulation.utilities.nodefilters;

import java.util.*;
import simulation.distributions.*;

/** Node filter that random selects a node from the set.
 * All nodes are equiprobable.
 * @author ykk
 */
public class Random
    extends NodeFilter
{
    //Members
    /** Number of nodes to return.
     * Defaulted to one.
     */
    public int nodeNumber = 1; 
    
    //Methods
    /** Constructor.
     */
    public Random()
    {
    }

    /** Constructor.
     * @param nodeNumber number of nodes to return
     */
    public Random(int nodeNumber)
    {
	this.nodeNumber = nodeNumber;
    }

    /** Function to filter and return a random set of nodes.
     * @param nodes vector of nodes to filter
     * @return resulting set of nodes after filtering
     */
    public Vector filter(Vector nodes)
    {
	Vector result, old;
	if (nodeNumber > (nodes.size()/2))
	{
	    result = (Vector) nodes.clone();
	    while (result.size() > nodeNumber)
		result.remove((int) Math.floor((new Uniform(0,nodes.size())).getInstance()));
	}
	else
	{
	    old = (Vector) nodes.clone();
	    result = new Vector();
	    while (result.size() < nodeNumber)
		result.add(old.remove((int) Math.
				      floor((new Uniform(0,nodes.size())).getInstance())));
	}
	return result;
    }
}