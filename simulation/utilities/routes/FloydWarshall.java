package simulation.utilities.routes;

import simulation.utilities.linkcosts.*;

/** Class to discover routes using Floyd-Warshall algorithm.
 * @author ykk
 */
public class FloydWarshall
{
    //Members
    /** Link cost reference.
     */
    public LinkCost linkCost;

    //Methods
    /** Constructor for a Floyd-Warshall.
     */
    public FloydWarshall(LinkCost linkCost)
    {
	this.linkCost = linkCost;
    }
}
