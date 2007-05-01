package simulation.optimization.dynprogram;

import java.util.*;
import simulation.distributions.*;

/** Class to store policy for dynamic program.
 * @author ykk
 */
public class Policy
{
    //Members
    /** Reference to vector of states.
     */
    public Vector states;
    /** Policy in terms of vector of actions.
     */ 
    public Vector actions = new Vector();

    //Methods
    /** Constructor.
     * @param states vector of states
     */
    public Policy(Vector states)
    {
	this.states = states;
    }

    /** Get random policy.
     * @param actions vector of actions
     */
    public void getRandom(Vector actions)
    {
	this.actions = new Vector();
	Vector validAct = new Vector();
	Uniform rv;

	for (int i = 0; i < states.size(); i++)
	{
	    validAct.clear();
	    for (int j = 0; j < actions.size(); j++)
		if (((Action) actions.get(j)).valid((State) states.get(i)))
		    validAct.add(actions.get(j));

	    if (validAct.size() == 0)
		throw new RuntimeException(this+" has state "+states.get(i)+" that has not valid action.");
	    rv = new Uniform(0,validAct.size());
	    this.actions.add(validAct.get((int) Math.floor(rv.getInstance())));
	}
    }

    /** String representation.
     * @return string of policy
     */ 
    public String toString()
    {
	String result = "Policy:";
	for (int i = 0; i < states.size(); i++)
	    result += "\n\t"+states.get(i)+"\t"+actions.get(i);

	return result;
    }
    
}