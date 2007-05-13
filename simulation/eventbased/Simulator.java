package simulation.eventbased;

import java.util.*;
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
    private void runEvent(Event event)
    {
	if (debug) System.out.println(event);
	time = event.time;
	event.object.run(event.time, event.event, this);
    }

    /** Cancel first event found with the same string reference and object associated.
     * This means that it is the next event of the same string reference and object
     * associated to be run from the queue.
     * @param event event string reference
     * @param object object associated
     * @return returns if event is cancelled
     */
    public boolean cancel(String event, EventTriggered object)
    {
	for (int i = 0; i < queue.size(); i++)
	    if ((((Event) queue.get(i)).event == event) &&
		(((Event) queue.get(i)).object == object))
	    {
		queue.remove(i);
		return true;
	    }
	return false;
    }

    /** Return vector of events lined up for an object.
     * @param object object events to be associated with
     * @return vector of events associated with object
     */
    public Vector eventLinedUp(EventTriggered object)
    {
	Vector interestEvents = new Vector();
	for (int i = 0; i < queue.size(); i++)
	    if (((Event) queue.get(i)).object == object)
		interestEvents.add(queue.get(i));
	    
	return interestEvents;
    }

    /** Cancel first event found with the same string reference and object associated.
     * This means that it is the next event of the same string reference and object
     * associated to be run from the queue.  Time of the event is ignored.
     * @param event event reference
     * @return returns if event is cancelled
     */
    public boolean cancel(Event event)
    {
	return cancel(event.event, event.object);
    }

    /** Cancel first event found with the same string reference.
     * This means that it is the next event of the same string reference to be
     * run from the queue.
     * @param event event string reference
     * @return returns if event is cancelled
     */
    public boolean cancel(String event)
    {
	for (int i = 0; i < queue.size(); i++)
	    if (((Event) queue.get(i)).event == event)
	    {
		queue.remove(i);
		return true;
	    }
	return false;
    }

    /** Return current time.
     * @return time
     */
    public double time()
    {
	return this.time;
    }
}
