package simulation.optimization.dynprogram;

import simulation.optimization.*;
import java.util.*;

/** Definition dynamic program.
 * @author ykk
 */
public class DynamicProgram
{
    //Members
    /** Vector of state.
     */
    public Vector states = new Vector();
    /** Vector of actions.
     */
    public Vector actions = new Vector();
    /** Array of cost.
     */
    public double[][] costs;
    /** Array of transition probabilities.
     */
    public TransitProb[][] transitProb;

    //Methods
    /** Output Markov Chain for given policy.
     * @param policy policy to use
     * @return Markov chain when using policy.
     */
    public MarkovChain getMarkovChain(Policy policy)
    {
	MarkovChain mc = new MarkovChain(states);
	TransitProb tp;
	for (int i = 0; i < policy.actions.size(); i++)
	{
	    tp = getProb((State) states.get(i),
			 (Action) policy.actions.get(i));
	    for (int j = 0; j < tp.prob.length; j++)
		mc.transitProb[i][j] = tp.prob[j];
	}

	return mc;
    }

    /** Cost of policy.
     * @param policy specified policy
     * @return cost of policy
     */
    public double policyCost(Policy policy)
    {
	MarkovChain mc = getMarkovChain(policy);
	double[] steadystate = mc.getSteadyState();
	double cost = 0;

	for (int i = 0; i < steadystate.length; i++)
	    cost += steadystate[i]*getCost((State) states.get(i),
					   (Action) policy.actions.get(i));
	
	return cost;
    }

    /** Initiate transition probability array.
     * Default all entries to null.
     */
    public void initiateProb()
    {
	transitProb = new TransitProb[actions.size()][states.size()];

	for (int i = 0 ; i < actions.size(); i++)
	    for (int j = 0; j < states.size(); j++)
		transitProb[i][j] = null;
    }

    /** Check transition probabilities, verify all valid action-state pairs has one.
     */
    public void checkProb()
    {
	Action act;
	for (int i = 0; i < actions.size(); i++)
	{
	    act = (Action) actions.get(i);
	    for (int j = 0; j < act.validStates.size(); j++)
		if (transitProb[i][states.indexOf(act.validStates.get(j))] == null)
		    throw new RuntimeException("Action-state pair\n\t("+act+","+
					       act.validStates.get(j)+
					       ")\n\trequires a transition probability.");
	}
    }

    /** Add transition probility to all valid states of given action.
     * @param action reference to action
     * @param prob transmit probability associated
     */
    public void addProb(Action action, TransitProb prob)
    {
	for (int i = 0; i < action.validStates.size(); i++)
	    transitProb[actions.indexOf(action)][states.indexOf(action.validStates.get(i))] = prob;
    }

    /** Add transition probility to state-action pair.
     * @param state reference to state
     * @param action reference to action
     * @param prob transmit probability associated
     */
    public void addProb(State state, Action action, TransitProb prob)
    {
	    transitProb[actions.indexOf(action)][states.indexOf(state)] = prob;
    }

    /** Get transition probability for state and action given.
     * @param state reference to state
     * @param action reference to action
     * @return transmit probability associated
     */
    public TransitProb getProb(State state, Action action)
    {
	return transitProb[actions.indexOf(action)][states.indexOf(state)];
    }

    /** Initiate cost array.
     * Default all entries to Double.MAX_VALUE.
     */
    public void initiateCost()
    {
	costs = new double[actions.size()][states.size()];

	for (int i = 0 ; i < actions.size(); i++)
	    for (int j = 0; j < states.size(); j++)
		costs[i][j] = Double.MAX_VALUE;
    }

    /** Check cost, verify all valid action-state pairs has a cost.
     */
    public void checkCost()
    {
	Action act;
	for (int i = 0; i < actions.size(); i++)
	{
	    act = (Action) actions.get(i);
	    for (int j = 0; j < act.validStates.size(); j++)
		if (costs[i][states.indexOf(act.validStates.get(j))] == Double.MAX_VALUE)
		    throw new RuntimeException("Action-state pair\n\t("+act+","+
					       act.validStates.get(j)+")\n\trequires a cost.");
	}
    }

    /** Add cost to all valid states of given action.
     * @param action reference to action
     * @param cost cost associated
     */
    public void addCost(Action action, double cost)
    {
	for (int i = 0; i < action.validStates.size(); i++)
	    costs[actions.indexOf(action)][states.indexOf(action.validStates.get(i))] = cost;
    }

    /** Get cost for state and action given.
     * @param state reference to state
     * @param action reference to action
     * @return cost associated
     */
    public double getCost(State state, Action action)
    {
	return costs[actions.indexOf(action)][states.indexOf(state)];
    }

    /** Add cost.
     * @param state reference to state
     * @param action reference to action
     * @param cost cost associated
     */
    public void addCost(State state, Action action, double cost)
    {
	costs[actions.indexOf(action)][states.indexOf(states)] = cost;
    }

    /** Retrieve valid action for state.
     * @param state state of concern
     * @return vector of valid actions
     */ 
    public Vector getValidAct(State state)
    {
	Vector vAct = new Vector();

	for (int i = 0; i < actions.size(); i++)
	    if (((Action) actions.get(i)).validStates.indexOf(state) != -1)
		vAct.add(actions.get(i));
	
	return vAct;
    }

    /** String representation.
     * @return string describing program
     */
    public String toString()
    {
	String result = super.toString();

	result += "\nStates:\n\t"+states;

	Action act;
	result += "\nActions:";
	for (int i = 0; i < actions.size(); i++)
	{
	    act = (Action) actions.get(i);
	    result += "\n\t"+act.name;
	    for (int j = 0; j < act.validStates.size(); j++)
		result+= "\n\t\t"+act.validStates.get(j)+"\t"+
		    costs[i][states.indexOf(act.validStates.get(j))]+
		    "\t"+transitProb[i][states.indexOf(act.validStates.get(j))];
	}

	return result;
    }
}