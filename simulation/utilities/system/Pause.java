package simulation.utilities.system;

/** Class to pause program for a while.
 * @author ykk
 */
public class Pause
{
    /** Pause for specified number of milliseconds.
     * @param milliSec number of milliseconds to pause
     */
    public static void pause(int milliSec)
    {
	try
	{
	    Thread.currentThread().sleep(milliSec);
	} catch (InterruptedException e)
	{
	    e.printStackTrace();
	}
    }
}
