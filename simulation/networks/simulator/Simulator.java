package simulation.networks.simulator;

import simulation.utilities.references.*;

/** Abstract class for network simulator.
 * @author ykk
 */
public abstract class Simulator
    implements TimeReference
{
    //Members
    /** Debug flag.
     * Default is false
     */
    public boolean debug = false;
    
    //Methods
    /** Return current time.
     * @return time
     */
    public abstract double time();
    
}