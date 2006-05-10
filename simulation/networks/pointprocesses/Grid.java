package simulation.networks.pointprocesses;

import java.util.*;
import simulation.networks.*;
import simulation.networks.areas.*;

/** Class to provide Grid point process.
 */
public class Grid
    extends PointProcess
{
    //Members
    /** Density of nodes.
     */
    public double density;

    //Methods
    /** Constructor to create Grid point process.
     * @param density density of nodes in the network
     */
    public Grid(double density)
    {
	this.density = density;
    }

    /** Get interval between each node.
     * @param density density of number
     * @return interval between each node
     */
    public double interval(double density)
    {
	return Math.sqrt(1.0/density);
    }

    public Vector getCoordinates(NetworkArea netArea)
    {
	Vector coordinates = new Vector();
	double nodeInt = interval(density);
	Coordinate tmpCoord;

	for (double x = netArea.minX(); x < netArea.maxX(); x+=nodeInt)
	    for (double y = netArea.minY(); y < netArea.maxY(); y+=nodeInt)
	    {
		tmpCoord = new Coordinate(x, y);
		if (netArea.inArea(tmpCoord))
		    coordinates.add(tmpCoord);
	    }
	
	return coordinates;
    }

    /** Main function to draw a sample of the point process.
     * @param args 1st argument is density of process
     */
    public static void main(String[] args)
    {
	NetworkArea netArea = new CircleNetArea(10);
	PointProcess pointprocess = new Grid(Double.parseDouble(args[0]));
	testImage(netArea,pointprocess,100,20);
    }
}
