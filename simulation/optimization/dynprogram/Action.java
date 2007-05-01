package simulation.optimization.dynprogram;

import java.util.*;

/** Class to store action for dynamic program.
 * @author ykk
 */
public class Action
{
    //Members
    /** Name of action.
     */
    public String name;
    /** Vector of states that action is valid in.
     */
    public Vector validStates = new Vector();

    //Methods
    /** String representation.
     * @return name of state
     */
    public String toString()
    {
	return name + "\t" +validStates;
    }
}