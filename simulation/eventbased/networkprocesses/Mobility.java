package simulation.eventbased.networkprocesses;

import simulation.eventbased.*;
import simulation.networks.mobility.*;
import simulation.utilities.structures.*;

/** Class to manage network flow in event driven simulation.
 * @see MobilityModel
 * @author ykk
 */
public class Mobility
    implements EventTriggered
{
    //Members
    /** Array of events possible.
     */
    public static final String[] events = {"Move due"};
    /** Time interval to moves.
     */
    public double moveInterval;
    /** Reference to mobility model.
     */
    public MobilityModel mobility;

    //Methods
    /** Constructor.
     * @param model reference to mobility model
     * @param moveInterval interval between move
     */
    public Mobility(MobilityModel model, double moveInterval)
    {
	this.mobility = model;
	this.moveInterval = moveInterval;
    }

    /** Event triggered interface.
     * Object has a single state in which it waits for the next move.
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     * @see #events
     */
    public void run(double time, java.lang.String event, Simulator simulator)
    {
	switch(Array.indexOf(events, event))
	{
	case 0: //Move due
	    mobility.move();
	    simulator.add(new Event(simulator.time()+moveInterval,
				    this,
				    this.events[0])); //move scheduled
	    break;
	default:
	    throw new RuntimeException(this+" encounters unknown event "+event);
	}

    }

    /** Trigger start of route update.
     * @param simulator reference to simulator
     * @param delayFromNow time to start move in terms 
     *                     of delay from current simulator time
     */
    public void trigger(Simulator simulator, double delayFromNow)
    {
	simulator.add(new Event(simulator.time()+delayFromNow,
				this,
				this.events[0])); //Move Due Scheduled
    }
}