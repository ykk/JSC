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

    /** Indicate if route is from source.
     * @return if route is from source.
     */
    public boolean routeFromSource()
    {
	return routeFromSource;
    }

    /** Add link.
     * @param link link to add to vector
     */
    public void add(Link link)
    {
	if (this.size() == 0)
	{
	    //Empty vector
	    if (routeFromSource)
	    {
		this.add(link.source);
		this.add(link.destination);
	    }
	    else
	    {
		this.add(link.destination);
		this.add(link.source);
	    }		
	}
	else 
	{
	    //Occupied vector
	    if (routeFromSource)
	    {
		if (this.lastElement() == link.source)
		    this.add(link.destination);
		else
		    throw new RuntimeException(this+" receive link that does not form a proper route.");
	    }
	    else
	    {
		if (this.lastElement() == link.destination)
		    this.add(link.source);
		else
		    throw new RuntimeException(this+" receive link that does not form a proper route.");
	    }
	}
    }

    /** Return nth link, from source to destination.
     * @param n index of route
     * @return nth link from source to destination
     * @see Link
     */
    public Object get(int n)
    {
	//Check index overflow
	if (n > (this.size()-1))
	    throw new RuntimeException(this+" has "+(this.size()-1)+" links, thus "+n+" link does not exist.  Index overflow.");

	//Get index
	int index = (routeFromSource)?n:this.size()-n-1;
	if (routeFromSource)
	    return new Link((Node) get(index), (Node) get(index+1));
	else
	    return new Link((Node) get(index+1), (Node) get(index));
    }   
}
