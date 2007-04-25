package simulation.networks.pointprocesses;

import java.util.*;
import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.distributions.*;

/** Class to provide Poisson Point Process
 */
public class Poisson
    extends PointProcess
{
    //Members
    /** Indicate if "true" Poisson Point Process is required.
     * Note that a "true" Poisson Point Process has a random number
     * of nodes in an area.  Else, the number of nodes can be constant
     * and directly proportional to the network area.
     * Default is true.
     */
    public boolean truePoisson = true;

    /** Density of nodes.
     */
    public double density;

    //Methods
    /** Constructor to create Poisson Point Process.
     * @param density density of nodes in the network
     */
    public Poisson(double density)
    {
	this.density = density;
    }

    /** Constructor to create Poisson Point Process,
     * with option to indicate if "true" Poisson Point Process is required.
     * @param density density of nodes in the network
     * @param truePoisson if a "true" Poisson Point Process is needed
     */
    public Poisson(double density, boolean truePoisson)
    {
	this.density = density;
	this.truePoisson = truePoisson;
    }

    public Vector getCoordinates(NetworkArea netArea)
    {
	Vector coordinates = new Vector();
	int nodeNumber;
	double avNumber = density*netArea.area();
	if (truePoisson)
	    nodeNumber = (int) Math.round((new simulation.distributions.Poisson(avNumber)).getInstance());
	else
	    nodeNumber = (int) Math.round(avNumber);
	Uniform x = new Uniform(netArea.minX(), netArea.maxX());
	Uniform y = new Uniform(netArea.minY(), netArea.maxY());
	Coordinate tmpCoord;

	while (coordinates.size() <= nodeNumber)
	{
	    tmpCoord = new Coordinate(x.getInstance(), y.getInstance());
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
	Poisson pointprocess = new Poisson(Double.parseDouble(args[0]));
	testImage(netArea,pointprocess,100,20);
    }
}
