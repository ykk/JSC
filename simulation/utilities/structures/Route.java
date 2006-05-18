package simulation.utilities.structures;

import java.util.*;
import simulation.networks.nodes.*;

/** Class to store route.
 * @author ykk
 */
public class Route
    extends Vector
{
    //Members
    /** Indicate if route is from source to destination.
     */
    private boolean routeFromSource;

    //Methods
    /** Create new route.
     * @param routeFromSource indicate if route is from source to destination,
     *                        else it is from destination to source.
     */
    public Route(boolean routeFromSource)
    {
	super();
	this.routeFromSource = routeFromSource;
    }

    /** Add link.
     * @param link
     */
    public void add(Link link)
    {
	
    }

    /** Return nth link, from source to destination.
     * @param n index of route
     * @return nth link from source to destination
     * @see Link
     */
    public Object get(int n)
    {
	if (n > (this.size()-1))
	    throw new RuntimeException(this+" has "+(this.size()-1)+" links, thus "+n+" link does not exist.  Index overflow.");

	
	
	return null;
    }
    
}
