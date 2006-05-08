package simulation.networks.pointprocesses;

import java.util.*;
import simulation.networks.*;
import simulation.networks.areas.*;

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
}
