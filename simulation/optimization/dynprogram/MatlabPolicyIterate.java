package simulation.optimization.dynprogram;

import simulation.utilities.structures.*;
import simulation.files.text.*;

/** Output and uses Matlab script to do policy iteration.
 * @author ykk
 */
public class MatlabPolicyIterate
    extends PolicyIterate
{
    //Members
    /** Indicate if sparse matrices
     * Default to false.
     */
    public boolean isSparse = false;
    /** Reference to dynamic program.
     */
    public DynamicProgram dp;
    /** Script filename.
     * Defaults to policyIterate (extension m added).
     */
    public String scriptFilename = "policyIterate";
    /** Output filename.
     * Defaults to scriptOutput (extension mat added).
     * First line is the optimal policy and the second is the final cost of the policy.
     */
    public String scriptOutputFile = "scriptOutput";
    /** Run command.
     * Default at matlab -nojvm -nosplash -r {@link #scriptFilename}.
     */
    public String command = "matlab -nojvm -nosplash -r ";

    //Methods
    /** Output matlab code to find optimal policy, starting from given policy.
     * The following variables are defined for the matlab program.
     * <OL>
     * <LI> S by (A x S)  Transition probabilities matrix (TransProb) </LI>
     * <LI> S by A Cost matrix (Cost), which is NaN is state-action is not valid </LI>
     * <LI> S by 1 Policy matrix (CurrPolicy) </LI>
     * </OL>
     * where S is the number of states; A is the number of actions.
     * @param dp dynamic program reference
     * @param iniPolicy initial policy
     * @return file with matlab code
     */
    public FileVector getMatlabCode(DynamicProgram dp, Policy iniPolicy)
    {
	//Check program
	dp.checkCost();
	dp.checkProb();

	FileVector file = new FileVector(scriptFilename+".m");
	String nullProb = "";

	//Get TransProb
	for (int i = 0; i < dp.states.size(); i++)
	    nullProb += "0.0 ";
	file.content.add("TransProb = [");
	for (int i = 0; i < dp.states.size(); i++)
	{
	    String probString = "";
	    for (int j = 0; j < dp.actions.size(); j++)
	    {
		TransitProb prob = dp.getProb((State) dp.states.get(i), (Action) dp.actions.get(j));
		if (prob == null)
		    probString += nullProb;
		else
		    probString += prob.toString().replace('[',' ').replace(']',' ');
	    }
	    if ( i != (dp.states.size()-1))
		probString += ";";
	    file.content.add(probString);
	}
	file.content.add("];");

	//Get Cost
	nullProb = "NaN ";
	file.content.add("Cost = [");
	for (int i = 0; i < dp.states.size(); i++)
	{
	    String probString = "";
	    for (int j = 0; j < dp.actions.size(); j++)
	    {
		TransitProb prob = dp.getProb((State) dp.states.get(i), (Action) dp.actions.get(j));
		if (prob == null)
		    probString += nullProb;
		else
		    probString += dp.getCost((State) dp.states.get(i), (Action) dp.actions.get(j)) + " ";
	    }
	    if ( i != (dp.states.size()-1))
		probString += ";";
	    file.content.add(probString);
	}
	file.content.add("];");
	
	//Get Policy
	file.content.add("CurrPolicy = [");
	String probString = "";
	for (int i = 0; i < iniPolicy.actions.size(); i++)
	    probString += dp.actions.indexOf(iniPolicy.actions.get(i))+" ";
	file.content.add(probString+ "];");

	//Indicate sparse matrices
	file.content.add("");
	if (isSparse)
	    file.content.add("TransProb = sparse(TransProb);");
	file.content.add((verbose)?"verbose=1;":"verbose=0;");
	file.content.add("");

	//Append Code
	JarFile codeFile = new JarFile("/simulation/optimization/dynprogram/policyiteratematlab.dat");
	codeFile.read();
	file.append(codeFile.content);

	//Append data output
	file.content.add("");
	file.content.add("save -ASCII '"+scriptOutputFile+".mat' 'CurrPolicy' 'finalCost';");
	file.content.add("exit;");

	file.write();
	return file;
    }

    /** Find optimal policy, starting from given policy. 
     * Stores results in {@link #lastResult} and {@link @lastCost}.
     * @param dp dynamic program reference
     * @param iniPolicy initial policy
     * @return optimal policy
     */
    public Policy optimalPolicy(DynamicProgram dp, Policy iniPolicy)
    {
	this.dp = dp;
	getMatlabCode(dp,iniPolicy);
	
	
	return null;
    }
}
