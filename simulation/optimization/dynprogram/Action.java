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
    /** Check if action is valid for state.
     * @param state state of concern
     * @return if this action can be done in the state
     */
    public boolean valid(State state)
    {
	return (validStates.indexOf(state) != -1);
    }

    /** String representation.
     * @return name of state
     */
    public String toString()
    {
	return name + "\t" +validStates;
    }
}