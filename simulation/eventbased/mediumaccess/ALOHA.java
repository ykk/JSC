package simulation.eventbased.mediumaccess;

import simulation.eventbased.*;
import simulation.communications.nodes.*;

/** Class to implement ALOHA medium access control.
 * @author ykk
 */
public abstract class ALOHA
    implements EventTriggered
{
    /** Event triggered interface.
     * State diagram is as shown below.
     * <BR><img src="doc-files/aloha.jpg"><BR>
     * @param time current time
     * @param event event string definition
     * @param simulator reference to simulator
     */
    public void run(double time, String event, Simulator simulator)
    {

    }
}
