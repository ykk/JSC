package simulation.utilities.structures;

import java.util.*;
import simulation.networks.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;
import simulation.utilities.structures.*;
import simulation.files.images.*;

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
	if (size() == -1)
	{
	    //Empty vector
	    if (routeFromSource)
	    {
		add(link.source);
		add(link.destination);
	    }
	    else
	    {
		add(link.destination);
		add(link.source);
	    }
	}
	else 
	{
	    //Occupied vector
	    if (routeFromSource)
	    {
		if (lastElement() == link.source)
		    add(link.destination);
		else
		    throw new RuntimeException(this+" receive link "+link+" that does not form a proper route.");
	    }
	    else
	    {
		if (lastElement() == link.destination)
		    add(link.source);
		else
		    throw new RuntimeException(this+" receive link "+link+" that does not form a proper route.");
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
	if (n > this.size())
	    throw new RuntimeException(this+" has "+this.size()+" links, thus "+n+" link does not exist.  Index overflow.");

	//Get index
	int index = (routeFromSource)?n:size()-n-1;
	if (routeFromSource)
	    return new Link((Node) super.get(index), (Node) super.get(index+1));
	else
	    return new Link((Node) super.get(index+1), (Node) super.get(index));
    }   

    /** Return nth link.
     * @param n index of route
     * @return nth link, 
     *         if routeFromSource, then nth link from source to destination;
     *         if not routeFromSource, then nth link from destination to source
     * @see Link
     */
    public Link getLink(int n)
    {
	//Check index overflow
	if (n > this.size())
	    throw new RuntimeException(this+" has "+this+" links, thus "+n+" link does not exist.  Index overflow.");

	//Get index
	if (routeFromSource)
	    return new Link((Node) super.get(n), (Node) super.get(n+1));
	else
	    return new Link((Node) super.get(n+1), (Node) super.get(n));
    }

    /** Return route reversed, i.e., route from source to route from destination;
     * and vice versa.
     * @return reversed route
     */
    public Route reverseRoute()
    {
	Route route = new Route(!routeFromSource);

	for (int i = size()-1 ; i >= 0; i--)
	    route.add(this.getLink(i));

	return route;
    }

    /** Return number of links in the route.
     * @return number of links
     */
    public int size()
    {
	return super.size()-1;
    }

    /** Return string representation.
     * @return string representation
     */
    public String toString()
    {
	String string = "RoutefromSource="+routeFromSource+"\n[" + super.get(0).toString();
	for (int i = 1; i < super.size(); i++)
	    string += "\n "+super.get(i).toString();
	return string+"]";
    }

    /** Function to draw route.
     * @param filename name of image file
     * @param imageFormat format of image
     * @param resolution number of pixels per unit of coordinate
     * @param nodeSize size of node in number of pixels
     * @see ImageFile#imageFormat
     */
    public void draw(String filename, int imageFormat,  Network network, int resolution, int nodeSize)
    {
        NetworkRouteImage image = new NetworkRouteImage(filename, imageFormat, network, resolution, nodeSize);
        image.draw(this);
        image.write();
    }

    /** Test function to create route and perform operations on it.
     * @param args 1st argument is length of route in number of links
     */
    public static void main(String[] args)
    {
	int noOfLinks = Integer.parseInt(args[0]);
	Coordinate coordinate = new Coordinate(0,0);
	Channel channel = new ZeroOne(1.0);

	Route route = new Route(true);
	Node source = new Node(coordinate,channel);
	Node destination;

	for (int i = 0; i < noOfLinks; i++)
	{
	    destination = new Node(coordinate,channel);
	    route.add(new Link(source,destination));
	    source = destination;
	}
	System.out.println(route);
	System.out.println(route.getLink(0));
	System.out.println(route.get(0));
	System.out.println();

	route = route.reverseRoute();
	System.out.println(route);
	System.out.println(route.getLink(0));
	System.out.println(route.get(0));
    }
}
