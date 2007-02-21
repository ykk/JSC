package simulation.eventbased;

import simulation.eventbased.*;
import simulation.utilities.structures.*;
import simulation.utilities.references.*;

/** Event based simulators.
 * @author ykk
 */
public class Simulator
    implements TimeReference
{
    //Members
    /** Current time of simulator.
     * @see time()
     */
    private double time=0;
    /** Event queue.
     */
    public SortedVector queue = new SortedVector();
    /** Debug flag.
     * Default is false
     */
    public boolean debug = false;
   
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
	runEvent((Event) queue.remove(0));
    }

    /** Run specified event. 
     * @param event event definition
     * @see EventTriggered#run(double time, String event, Simulator simulator)
     */
    public void runEvent(Event event)
    {
	if (debug) System.out.println(event);
	time = event.time;
	event.object.run(event.time, event.event, this);
    }

    /** Return current time.
     * @return time
     */
    public double time()
    {
	return this.time;
    }
}
