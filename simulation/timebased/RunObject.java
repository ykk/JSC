package simulation.timebased;

/** Interface for object to be run by discrete time {@link Simulator}.
 * @author ykk
 */
public interface RunObject
{
    /** Function to run object.
     * @param time current time
     * @param simulator reference to simulator
     * @return if any event run
     */
    public boolean run(double time, Simulator simulator);

    /** Indicate if object needs more run.
     * @return if more runs is needed
     */
    public boolean needMoreRun();
}