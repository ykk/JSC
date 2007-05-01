package simulation.optimization.dynprogram;

/** Class to perform dynamic programming policy iteration.
 * @author ykk
 */
public class PolicyIterate
{
    //Members
    /** Flag for verbosity.
     * Default to true.
     */
    public boolean verbose = true;
    /** Reference to last found policy.
     */
    public Policy lastResult;

    //Methods
    /** Find optimal policy, starting from random policy. 
     * @param dp dynamic program reference
     * @return optimal policy
     */
    public Policy optimalPolicy(DynamicProgram dp)
    {
	Policy newPol = new Policy(dp.states);
	newPol.getRandom(dp.actions);

	return optimalPolicy(dp, newPol);
    }

    /** Find optimal policy, starting from given policy. 
     * @param dp dynamic program reference
     * @param iniPolicy initial policy
     * @return optimal policy
     */
    public Policy optimalPolicy(DynamicProgram dp, Policy iniPolicy)
    {
	lastResult = new Policy(dp.states);
	for (int i = 0; i < iniPolicy.actions.size(); i++)
	    lastResult.actions.add(iniPolicy.actions.get(i));

	return lastResult;
    }
}