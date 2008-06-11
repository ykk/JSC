package simulation.socialnet;

import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;

/** Graph to return difference in graphs.
 * @author ykk
 */
public class GraphDiff
{
    //Methods
    /** Return edges added into two graphs. 
     * @param former graph considered to be earlier in timeline
     * @param latter graph considered to be later in timeline
     * @param directed indicated if edge is directed
     * @return list of edges added
     */
    public static UniqueVector edgeAdded(Graph former, Graph latter,
					 boolean directed)
    {
	UniqueVector added = new UniqueVector();
	boolean isNewNode;

	for (int i = 0; i < latter.nodes.size(); i++)
	{
	    GraphNode latterNode = (GraphNode) latter.nodes.get(i);
	    isNewNode = true;
	    for (int j = 0; j < former.nodes.size(); j++)
	    {
		GraphNode formerNode = (GraphNode) former.nodes.get(j);
		if (formerNode.compareTo(latterNode) == 0)
		{
		    isNewNode = false;
		    boolean isNewEdge;
		    for (int k = 0; k < latterNode.neighbors.size(); k++)
		    {
			isNewEdge = true;
			GraphNode latterNeigh = (GraphNode)
			    latterNode.neighbors.get(k);
			for (int l = 0; l < formerNode.neighbors.size(); l++)
			    if (latterNeigh.compareTo(formerNode.
						      neighbors.get(l)) == 0)
				isNewEdge = false;

			if (isNewEdge)
			    added.add(new GraphEdge(latterNode,
						    latterNeigh,
						    directed));
		    }
		}
	    }

	    //New Node => Add all neighbors
	    if (isNewNode)
	    {
		System.out.println("Adding node "+latterNode);
		for (int j = 0; j < latterNode.neighbors.size(); j++)
		    added.add(new GraphEdge(latterNode,
					    (GraphNode) 
					    latterNode.neighbors.get(j),
					    directed));
	    }
	}
	
	return added;
    }

    /** Test function to shown difference in network.
     * @param args arguments
     */
    public static void main(String[] args)
    {
	int n = 4;
	Graph g = new Graph();
	Graph h = new Graph();
	GraphNode[] nodes = new GraphNode[n]; 
	GraphNode[] nodesh = new GraphNode[n]; 
	for (int i = 0 ; i < n; i++)
	{
	    nodes[i] = new GraphNode((new Integer(i)).toString());
	    g.addNode(nodes[i]);

	    nodesh[i] = new GraphNode((new Integer(i)).toString());
	    h.addNode(nodesh[i]);
	}

	g.nodes.remove(nodes[3]);

	nodes[1].neighbors.add(nodes[0]);
	nodes[0].neighbors.add(nodes[1]);
	nodes[2].neighbors.add(nodes[1]);
	nodes[1].neighbors.add(nodes[2]);

	nodesh[1].neighbors.add(nodesh[0]);
	nodesh[0].neighbors.add(nodesh[1]);
	nodesh[2].neighbors.add(nodesh[1]);
	nodesh[1].neighbors.add(nodesh[2]);

	nodesh[3].neighbors.add(nodesh[1]);
	nodesh[1].neighbors.add(nodesh[3]);
	nodesh[0].neighbors.add(nodesh[2]);
	nodesh[2].neighbors.add(nodesh[0]);
	
	System.out.println(GraphDiff.edgeAdded(g,h,false));
    }
}