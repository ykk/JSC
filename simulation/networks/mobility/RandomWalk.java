package simulation.networks.mobility;

import simulation.distributions.*;
import simulation.networks.*;

/** Class for random walk mobility model.
 * Here the nodes are moved a constant step size left/right and up/down.
 * @author ykk
 */
public class RandomWalk
    extends MobilityModel
{
    //Members
    /** Step size.
     */
    public double stepSize;
    /** Random number.
     */
    Distribution bern = new Bernoulli(0.5);

    //Methods
    /** Constructor.
     * @param network network reference
     * @param stepSize size of each step
     */
    public RandomWalk(Network network, double stepSize)
    {
	super(network);
	this.stepSize = stepSize;
    }

    public void moveNode(Coordinate node)
    {
	node.x += 2*(bern.getInstance()-0.5)*stepSize;
	node.y += 2*(bern.getInstance()-0.5)*stepSize;
    }
}