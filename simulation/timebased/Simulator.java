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
    /** Last run has events.
     */
    public boolean lastRunHasEvent = true;
    /** Indicates if any object needs more run.
     */
    public boolean needMoreRun = true;

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
	RunObject runObj;
	boolean objRun;
	lastRunHasEvent = false;
	needMoreRun = false;

	if (debug) System.out.println("Time="+time);
	if (maxTime == 0 || time < maxTime)
	{
	    for (int i = 0; i < runList.size(); i++)
	    {
		runObj = (RunObject) runList.get(i);
		objRun = runObj.run(time, this);
		lastRunHasEvent = lastRunHasEvent || objRun;
		needMoreRun = needMoreRun || runObj.needMoreRun();
		if (debug) System.out.println("\t"+runList.get(i)+"\t"+
					      objRun+"/"+runObj.needMoreRun());
	    }

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