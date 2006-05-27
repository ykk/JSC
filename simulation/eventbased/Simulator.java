package simulation.eventbased;

import simulation.eventbased.*;
import simulation.utilities.structures.*;

/** Event based simulators.
 * @author ykk
 */
public class Simulator
{
    //Members
    /** Current time of simulator.
     * @see time()
     */
    private double time=0;
    /** Event queue.
     */
    public SortedVector queue = new SortedVector();
    
    //Methods
    /** Add event to queue.
     */
    public void add(Event event)
    {
	if (event.compareTime(time) == -1)
	    throw new RuntimeException(event+" NON-CAUSALLY scheduled when simulator time is"+time);

	queue.add(event);
    }

    /** Run next event.
     */
    public void runNextEvent()
    {
	Event event = (Event) queue.remove(0);
	event.object.run(event,this);
    }

    /** Return current time.
     * @return time
     */
    public double time()
    {
	return this.time;
    }
}
