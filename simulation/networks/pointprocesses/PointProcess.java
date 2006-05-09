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
     */
    public static void testImage(NetworkArea netArea, PointProcess pp, int resolution, int nodeSize)
    {
	
    }
}
