package simulation.networks;

import simulation.networks.areas.*;
import java.util.*;

/** Basic class for network.
 * @author ykk
 */
public abstract class Network
{
    //Members
    /** Vector to hold nodes.
     * Defaulted to empty vector.
     */
    public Vector nodes = new Vector();

    /** Area definition for network.
     * @see NetworkArea
     */
    public NetworkArea netArea;

    //Methods
    /** Constructor for generic network.
     * @param netArea network area definition
     */
    public Network(NetworkArea netArea)
    {
	this.netArea = netArea;
    }
}
