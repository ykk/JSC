package simulation.networks.areas;

import simulation.networks.areas.*;

/** Class to provide square area definition for a network.
 * @author ykk
 */
public class SquareNetArea
    extends RectangleNetArea
{
    /** Constructor to create square network.
     * Network has x and y coordinates for 0 to specified maxima.
     * @param max maximum x and y coordinate
     */
    public SquareNetArea(double max)
    {
	super(max,max);
    }    
}
