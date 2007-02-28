package simulation.eventbased;

/** Event for processing by an object.
 * @author ykk
 */
public class Event
    implements Comparable
{
    //Members
    /** Time to process event.
     */
    public double time;
    /** Object to process event.
     */
    public EventTriggered object;
    /** String reference of event.
     * Note that the content of the string is not being compared.
     * The string must point to the same reference as the ones defined
     * in the event triggered object.
     * It is advised that these strings are declared as final, in the form 
     * a string array in the event triggered objects.
     */
    public String event;

    //Methods
    /** Constructor for event.
     * @param time time to run event
     * @param object event triggered object to process event
     * @param event event string reference
     * @see #event
     */
    public Event(double time, EventTriggered object, String event)
    {
	this.time = time;
	this.object = object;
	this.event = event;
    }
    
    /** Comparable interface.
     * Compared by time.
     * @param object object to compare this object to
     * @return -1 if smaller; 0 if equal; and 1 if bigger
     */
    public int compareTo(Object object)
    {
	if (!(object instanceof Event))
	    throw new RuntimeException(object+" is not an instance of the Event class. It cannot be compared to it as a result.");

	return compareTime(((Event) object).time);
    }

    /** Indicate if event precedes time indicated.
     * @param time time to compare to
     * @return -1 if smaller; 0 if equal; and 1 if bigger
     */
    public int compareTime(double time)
    {
	if (this.time == time)
	    return 0;
	else if (this.time > time)
	    return 1;
	else
	    return -1;
    }

    /** Return string representation of event.
     * @return string representation
     */
    public String toString()
    {
	return time+"\t"+event+" by "+object;
    }
}
