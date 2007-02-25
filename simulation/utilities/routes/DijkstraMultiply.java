package simulation.utilities.routes;

import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.utilities.linkcosts.*;
import simulation.utilities.structures.*;

/** Class to discover routes using Dijsktra algorithm, using multiplication instead of sum.
 * Link cost must be between 0 and 1, inclusively.
 * @author ykk
 */
public class DijkstraMultiply
    extends Dijkstra   
{
     //Methods
    /** Constructor for creating DijkstraMultiply.
     * @param linkCost link cost definition between nodes
     * @param rootIsSource indicate if root is source or sink
     */
    public DijkstraMultiply(LinkCost linkCost, boolean rootIsSource)
    {
	super(linkCost, rootIsSource);
    }   

    /** Return route between 2 nodes.
     * @param network reference to network
     * @param linkcost link cost definition
     * @param source reference to source
     * @param destination to destination
     * @return shortest path route between root and child
     */
    public static Route shortestpath(Network network, LinkCost linkCost, 
			      Node source, Node destination)
    {
	DijkstraMultiply spst = new DijkstraMultiply(linkCost, true);
	RouteTree tree = spst.spst(network, source);
	return tree.getRoute(destination);
    }

    /** Function to sum two link costs.
     * @param cost1 first cost
     * @param cost2 second cost
     * @return multiplication of costs
     */
    protected double costSum(double cost1, double cost2)
    {
	if ((cost1 < 0) || (cost1 > 1) || 
	    (cost2 < 0) || (cost2 > 1))
	    throw new RuntimeException(this+" experiences link cost of "+
				       cost1 +" or/and "+cost2 +" which is not allowed.");
	return cost1+cost2;
    }
}