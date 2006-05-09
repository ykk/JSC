package simulation.networks.pointprocesses;

import java.util.*;
import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.files.images.*;

/** Abstract class to provide point processes for networks.
 * @author ykk
 */
public abstract class PointProcess
{
    /** Function to generate coordinates.
     * @return vector of coordinates.
     * @see simulation.networks.Coordinate
     */
    public abstract Vector getCoordinates(NetworkArea netArea);

    /** Test Function.
     * @param netArea network area definition
     * @param pp point process to draw
     * @param resolution number of pixels per unit of position
     * @param nodeSize number of pixels per node occupy
     */
    public static void testImage(NetworkArea netArea, PointProcess pp, int resolution, int nodeSize)
    {
	Vector positions = pp.getCoordinates(netArea);

	PositionImage image = new PositionImage("testImage.jpg", ImageFile.JPEG_TYPE,
						netArea, nodeSize, resolution);
	image.drawPositions(positions);

	for (int i = 0;i < positions.size(); i++)
	    System.out.println(i+"\t"+((Positionable) positions.get(i)).x()+"\t"+((Positionable) positions.get(i)).y());
    }
}
