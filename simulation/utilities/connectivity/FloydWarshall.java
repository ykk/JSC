package simulation.utilities.connectivity;

import simulation.utilities.routes.*;
import simulation.utilities.linkcosts.*;

/** Class extending FloydWarshall for connectivity of network.
 * @author ykk
 */
public class FloydWarshall
    extends simulation.utilities.routes.FloydWarshall
{
    /** Constructor for a Floyd-Warshall.
     * @param linkCost link cost definition
     */
    public FloydWarshall(LinkCost linkCost)
    {
	super(linkCost);
    }

    /**
     */
}
