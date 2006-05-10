package simulation.networks;

import simulation.files.images.*;

/** Class to hold a coordinate.
 * @author ykk
 */
public class Coordinate
    implements Positionable
{
    //Members
    /** Position in x coordinate.
     */
    public double x;
    /** Position in y coordinate.
     */
    public double y;

    //Methods
    /** Constructor
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Coordinate(double x, double y)
    {
	this.x = x;
	this.y = y;
    }

    /** Provide string representation.
     */
    public String toString()
    {
	return "("+x+","+y+")";
    }

    public double x()
    {
	return x;
    }

    public double y()
    {
	return y;
    }
}
