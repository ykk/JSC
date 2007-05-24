package simulation.timebased;

import java.util.*;
import simulation.utilities.references.*;

/** Time based simulator, i.e., discrete time simulation.
 * @author ykk
 */
public class Simulator
    extends simulation.networks.simulator.Simulator
{
    //Members
    /** Current time of simulator.
     * @see time()
     */
    private double time=0;
    /** Unit of each time increment.
     * Default is 1.
     */
    public double timeIncrement = 1;
    /** Total run time.
     * Default to zero, i.e., forever.
     */ 
    public double maxTime = 0;
    /** Objects begin activated at each time slot.
     */
    public Vector runList = new Vector();

    //Methods
    /** Add objects to list to run.
     */
    public void add(RunObject object)
    {
	runList.add(object);
    }

    /** Run next time slot.
     * @return if time is run
     */
    public boolean runNextSlot()
    {
	if (maxTime == 0 || time < maxTime)
	{
	    for (int i = 0; i < runList.size(); i++)
		((RunObject) runList.get(i)).run(time, this);
	    
	    time += timeIncrement;
	    return true;
	}
	else
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