package simulation.timebased;

/** Interface for object to be run by discrete time {@link Simulator}.
 * @author ykk
 */
public interface RunObject
{
    /** Function to run object.
     * @param time current time
     * @param simulator reference to simulator
     */
    public void run(double time, Simulator simulator);
}