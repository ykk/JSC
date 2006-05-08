package simulation.networks;

import simulation.networks.areas.*;
import simulation.distributions.*;

/** Network with nodes distributed by Poisson Point Process.
 * @author ykk
 */
public class PoissonNetwork
    extends Network
{
    //Members
    /** Indicate if the Poisson Point Process is to be adhered strictly.
     * Defaulted to value of true.
     */
    public boolean truePPP = true;

    //Methods
    /** Constructor for Poisson network.
     * It should be noted that a "true" Poisson Point Process 
     * has node number in any area being Poisson distribtued.  
     * Thus, given a fixed area, the number of nodes can differ.  
     * Therefore, an option is given in the constructor for the number 
     * of nodes to be truely Poisson distributed or fixed.
     * @param netArea network area definition
     * @param truePPP if the true Poisson Point Process is required
     */
    public PoissonNetwork(NetworkArea netArea, boolean truePPP)
    {
	super(netArea);
	this.truePPP = truePPP;
    }

    /** Constructor for Poisson network.
     * It should be noted that a "true" Poisson Point Process 
     * has node number in any area being Poisson distribtued.  
     * Thus, given a fixed area, the number of nodes can differ.  
     * @param netArea network area definition
     */
    public PoissonNetwork(NetworkArea netArea)
    {
	super(netArea);
    }

    /** Generate network with specified density.
     * @param density density of network
     */
    public void generate(double density)
    {
	generate((int) (density*netArea.area()));
    }


    /** Generate network with specified number of nodes.
     * Note that this function should not be used trivially since
     * the number of nodes is a Poisson Point Process is a random
     * variable.
     * @param nodeNumber number of nodes
     */
    private void generate(int nodeNumber)
    {
	Uniform x = new Uniform(netArea.minX(), netArea.maxX());
	Uniform y = new Uniform(netArea.minY(), netArea.maxY());
	Coordinate coordinate;

	while (nodes.size() <= nodeNumber)
	{
	    coordinate = new Coordinate(x.getInstance(), y.getInstance());
	    if (netArea.inArea(coordinate))
		nodes.add(coordinate);
	}	
    }
}
