package simulation.networks.areas;

import simulation.networks.*;

/** Class to provide rectangle area definition for a network.
 * @author ykk
 */
public class RectangleNetArea
    extends NetworkArea
{
    //Members
    /** Maximum x-axis coordinate.
     * Defaulted to 1.0. 
     */
    private double maxX = 1.0;
    /** Maximum y-axis coordinate.
     * Defaulted to 1.0. 
     */
    private double maxY = 1.0;

    //Methods
    /** Constructor to create rectangle network.
     * Network has x and y coordinates for 0 to specified maxima.
     * @param maxX maximum x-coordinate
     * @param maxY maximum y-coordinate
     */
    public RectangleNetArea(double maxX, double maxY)
    {
	this.maxX = maxX;
	this.maxY = maxY;
    }

    public double minX()
    {
	return 0.0;
    }
    
    public double minY()
    {
	return 0.0;
    }

    public double maxX()
    {
	return maxX;
    }

    public double maxY()
    {
	return maxY;
    }

    public double area()
    {
	return maxY*maxX;
    }

    public boolean inArea(Coordinate coordinate)
    {
	if (coordinate.x > 0 && coordinate.x < maxX)
	    if (coordinate.y > 0 && coordinate.y < maxY)
		return true;

	return false;
    }
}
