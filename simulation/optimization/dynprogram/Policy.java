package simulation.optimization.dynprogram;

import java.util.*;
import simulation.distributions.*;

/** Class to store policy for dynamic program.
 * @author ykk
 */
public class Policy
    implements Comparable
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


    /** Add null actions.
     */
    public void addNullAct()
    {
	for (int i = 0; i < states.size(); i++)
	    actions.add(null);
    }

    /** Get policy using last action.
     * @param actions vector of actions
     */
    public void getLast(Vector actions)
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
	    this.actions.add(validAct.get(validAct.size()-1));
	}
    }

    /** Get policy using first action.
     * @param actions vector of actions
     */
    public void getFirst(Vector actions)
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
	    this.actions.add(validAct.get(0));
	}
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


    /** Duplicate.
     * @return duplicated policy.
     */
    public Policy duplicate()
    {
	Policy newP = new Policy(states);
	for (int i = 0; i < actions.size(); i++)
	    newP.actions.add(actions.get(i));

	return newP;
    }

    /** Comparable interface.
     * @param object policy to compare to
     * @return 0 if same policy and -1 else
     */
    public int compareTo(Object object)
    {
	for (int i = 0; i < actions.size(); i++)
	    if (actions.get(i) != ((Policy) object).actions.get(i))
		return -1;
	
	return 0;
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