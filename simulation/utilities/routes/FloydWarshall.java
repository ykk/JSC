package simulation.utilities.routes;

import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.networks.areas.*;
import simulation.networks.pointprocesses.*;
import simulation.networks.channels.*;
import simulation.utilities.linkcosts.*;
import simulation.utilities.structures.*;
import simulation.files.images.*;

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
     * @param linkCost link cost definition
     */
    public FloydWarshall(LinkCost linkCost)
    {
	this.linkCost = linkCost;
    }

    /** Create spst routing table using Floyd-Warshall algorithm.
     * Does not allow negative cycles to exist.
     * Reference to
     * <PRE>@InBook{Bertsekas92,
     * author = {Dimitri Bertsekas and Robert Gallager},
     * title = {Data Networks},
     * chapter = {5.2},
     * publisher = {Prentice Hall},
     * year = {1992},
     * edition = {2},
     * pages = {403--404},
     * }</PRE>
     * @param network network to calculate spst for
     * @return spst routing table
     */
    public RouteTable spst(Network network)
    {
	RouteTable table = new RouteTable(network.nodes);
	int netSize = network.nodes.size(); 
	
	//Initialise
	double[][] cost = new double[netSize][netSize];
	for (int i = 0; i < netSize; i++)
	    for (int j = 0; j < netSize; j++)
		if(i==j)
		    cost[i][j]=0;
		else
		{
		    cost[i][j]=linkCost.cost((Node) network.nodes.get(i),
					     (Node) network.nodes.get(j));
		    if (cost[i][j] < Double.POSITIVE_INFINITY)
			table.assignNextHop((Node) network.nodes.get(i),
					    (Node) network.nodes.get(j),
					    (Node) network.nodes.get(j));
		}
	
	//Improve routes
	boolean improvement=true;
	double[][] nextCost = new double[netSize][netSize];
	RouteTable nextTable = (RouteTable) table.clone();
	while (improvement)
	{
	    improvement = false;

	    for (int i = 0; i < netSize; i++)
		for (int j = 0; j < netSize; j++)
		{
		    nextCost[i][j] = cost[i][j];
		    nextTable.assignNextHop((Node) nextTable.nodes.get(i),
					    (Node) nextTable.nodes.get(j),
					    table.nextHop((Node) nextTable.nodes.get(i),
							  (Node) nextTable.nodes.get(j)));
		    for (int k = 0; k < netSize; k++)
			if ((cost[i][k] + cost[k][j]) < cost[i][j])
			{
			    improvement = true;
			    nextCost[i][j] = cost[i][k] + cost[k][j];
			    nextTable.assignNextHop((Node) nextTable.nodes.get(i),
						    (Node) nextTable.nodes.get(j),
						    table.nextHop((Node) nextTable.nodes.get(i),
								  (Node) nextTable.nodes.get(k)));
			}
		}

	    for (int i = 0; i < cost.length; i++)
	    	for (int j = 0; j < cost.length; j++)
		    cost[i][j] = nextCost[i][j];
	    table = (RouteTable) nextTable.clone();
	}
	
	return table;
    }

    /** Function to test Floyd-Warshall by using it on a network and 
     * drawing the result (one of the routing tree).
     * @param args 1st argument is density of network
     */
    public static void main(String[] args)
    {
	//Create network
	Network testNet = new Network(new CircleNetArea(5), 
				      new Node(new Coordinate(0,0), new ZeroOne(1)), 
				      new Poisson(Double.parseDouble(args[0])));
	//Get SPST
	FloydWarshall spst = new FloydWarshall(new Constant());
	RouteTable table = spst.spst(testNet);
	System.out.println("Fully connected="+table.connected());
	System.out.println("Maximum Tree Size="+table.maxTreeSize(true)+" out of "+
			   table.nodes.size()+"("+(table.nodes.size()-table.maxTreeSize(true))+")");

	//Draw result
	RouteTree testRT = table.getRouteTree((Node) testNet.nodes.get(0), true);	
	testRT.draw("testFloydWarshall-1.jpg", ImageFile.JPEG_TYPE, testNet, 100, 20);
	testRT = table.getRouteTree((Node) testNet.nodes.get(1), true);
	testRT.draw("testFloydWarshall-2.jpg", ImageFile.JPEG_TYPE, testNet, 100, 20);
    }
}
