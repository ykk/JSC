package simulation.eventbased.packetprocesses;

import simulation.utilities.linkcosts.*;
import simulation.networks.*;
import simulation.eventbased.*;
import simulation.eventbased.mediumaccess.*;
import simulation.utilities.structures.*;

/** Class to manage network flow in event driven simulation.
 * @see PacketSource
 * @author ykk
 */
public class NetworkFlow
    extends simulation.networks.simulator.NetworkFlow
	    implements EventTriggered
{
    //Members
    /** Array of events possible.
     */
    public static final String[] events = {"Route Update Due"};
    /** Time interval to update route.
     */
    public double updateInterval;

    //Methods
    /** Constructor.
     * Dummy constructor for inheritance.
     */
    public NetworkFlow()
    {
    }

    /** Constructor.
     * @param source source node
     * @param sink sink node
     * @param network reference to network
     * @param linkCost reference to link cost
     * @param updateInterval interval between update of route
     */
    public NetworkFlow(MACNode source, MACNode sink, 
		       Network network, LinkCost linkCost, 
		       double updateInterval)
    {
	super(source, sink, network, linkCost);
	this.updateInterval = updateInterval;
    }

    /** Constructor.
     * Randomly chooses source-sink.
     * @param network reference to network
     * @param linkCost reference to link cost
     */
    public NetworkFlow(Network network, LinkCost linkCost, 
		       double updateInterval)
    {
	super(network,linkCost);
	this.updateInterval = updateInterval;
    }

    /** Event triggered interface.
     * Object has a single state in which it waits for the next route update.
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     * @see #events
     */
    public void run(double time, java.lang.String event, Simulator simulator)
    {
	switch(Array.indexOf(events, event))
	{
	case 0: //Route Update due
	    getRoute();
	    simulator.add(new Event(simulator.time()+updateInterval,
				    this,
				    this.events[0])); //Route Update scheduled
	    break;
	default:
	    throw new RuntimeException(this+" encounters unknown event "+event);
	}
    }

    /** Trigger start of route update.
     * @param simulator reference to simulator
     * @param delayFromNow time to start update route in terms 
     *                     of delay from current simulator time
     */
    public void trigger(Simulator simulator, double delayFromNow)
    {
	simulator.add(new Event(simulator.time()+delayFromNow,
				this,
				this.events[0])); //Route Update Due Scheduled
    }
}