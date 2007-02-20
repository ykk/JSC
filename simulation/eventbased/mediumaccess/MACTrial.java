package simulation.eventbased.mediumaccess;

import java.util.*;
import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.networks.channels.*;
import simulation.networks.nodes.*;
import simulation.files.images.*;
import simulation.utilities.linkcosts.*;
import simulation.utilities.routes.*;
import simulation.utilities.structures.*;
import simulation.distributions.*;
import simulation.eventbased.*;


/** Class to test medium access node.
 * @see MACNode
 * @author ykk
 */
public class MACTrial
{
    /** Main function to run trial simulation.
     */
    public static void main(String[] args)
    {
	int density = 10;
	Channel networkChannel = new ZeroOne(1.0);

	NodeFactory nodefactory = new ALOHA(new Coordinate(0,0),
					    networkChannel,
					    null,
					    null,
					    new Exponential(1.0));
	Network network = new Network(new CircleNetArea(1),
				      nodefactory,
				      new simulation.networks.
				      pointprocesses.Poisson(density));

	network.draw("MACTrialNetwork.jpg", ImageFile.JPEG_TYPE, 150, 5);
    }
}
