package simulation.networks.areas;

import simulation.networks.*;

/** Class to provide circle area definition for a network.
 * @author ykk
 */
public class CircleNetArea
    extends NetworkArea
{
    //Members
    /** Radius of network.
     * Defaulted to 1.0. 
     */
    private double radius=1.0;

    //Methods
    /** Constructor to create circle network.
     * Network is centralized at origin with specified radius.
     * @param radius radius of network
     */
    public CircleNetArea(double radius)
    {
	this.radius = radius;
    }

    public double minX()
    {
	return -1.0*radius;
    }
    
    public double minY()
    {
	return -1.0*radius;
    }

    public double maxX()
    {
	return radius;
    }

    public double maxY()
    {
	return radius;
    }

    public double area()
    {
	return Math.PI*Math.pow(radius,2.0);
    }

    public boolean inArea(Coordinate coordinate)
    {
	if ((Math.sqrt(Math.pow(coordinate.x,2.0)+Math.pow(coordinate.y,2.0))) <= radius)
	    return true;
	else
	    return false;
    }
}
   
