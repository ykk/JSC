package simulation.eventbased;

import simulation.eventbased.*;

/** Abstract class for event triggered object.
 * @author ykk
 */
public abstract class EventTriggered
{
    /** Function to run events.
     * @param event event definition
     * @param simulator reference to simulator
     */
    public void run(Event event, Simulator simulator)
    {
	run(event.time, event.event, simulator);
    }

    /** Function to run events.
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     * @see Event#event
     */
    public abstract void run(double time, String event, Simulator simulator);
}
