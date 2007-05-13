package simulation.optimization.dynprogram;

import simulation.utilities.structures.*;
import simulation.files.text.*;
import java.util.*;
import java.io.*;

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
	this.dp = dp;

	//Check program
	dp.checkCost();
	dp.checkProb();

	FileVector file = new FileVector(scriptFilename+".m");
	String nullProb = "";

	//Get TransProb
	if (isSparse)
	{
	    //Sparse matrix
	    file.content.add("TransProb = zeros("+dp.states.size()+","+(dp.states.size()*dp.actions.size())+");");
	    file.content.add("TransProb = sparse(TransProb);");
	    for (int i = 0; i < dp.states.size(); i++)
		for (int j = 0; j < dp.actions.size(); j++)
		{
		    TransitProb prob = dp.getProb((State) dp.states.get(i), (Action) dp.actions.get(j));
		    if (prob != null)
			for (int k = 0; k < dp.states.size(); k++)
			{
			    double sProb = prob.getProb(k);
			    if (sProb != 0)
				file.content.add("TransProb("+(i+1)+","+(j*dp.states.size()+k+1)+")="+
						 sProb+";");
			}
		}
	}
	else
	{
	    //Proper matrix
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
	}

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
	file.content.add((checkCost)?"checkCost=1;":"checkCost=0;");
	file.content.add((verbose)?"verbose=1;":"verbose=0;");
	file.content.add("");

	//Append Code
	JarFile codeFile = new JarFile("/simulation/optimization/dynprogram/policyiteratematlab.dat");
	codeFile.read();
	file.append(codeFile.content);

	//Append data output
	file.content.add("");
	file.content.add("save -ASCII '"+scriptOutputFile+".mat' 'CurrPolicy' 'CurrCost' 'steadyState';");
	file.content.add("exit;");

	file.write();
	return file;
    }

    /** Read script output.
     * Format is line 1 = optimal policy; line 2 = cost of policy; and line 3 is steady state distribution.
     */
    public void readOutput()
    {
	FileVector file = new FileVector(scriptOutputFile+".mat");
	file.read();
	lastResult = new Policy(dp.states);

	lastResult.actions.clear();
	StringTokenizer tokens = new StringTokenizer((String) file.content.get(0));
	while (tokens.hasMoreTokens())
	    lastResult.actions.add(dp.actions.get((int) Double.parseDouble(tokens.nextToken())));
	
	lastCost = Double.parseDouble((String) file.content.get(1));

	lastResult.prob.clear();
	tokens = new StringTokenizer((String) file.content.get(2));
	while (tokens.hasMoreTokens())
	    lastResult.prob.add(new Double(Double.parseDouble(tokens.nextToken())));
    }

    /** Find optimal policy, starting from given policy. 
     * Stores results in {@link #lastResult} and {@link @lastCost}.
     * @param dp dynamic program reference
     * @param iniPolicy initial policy
     * @return optimal policy
     */
    public Policy optimalPolicy(DynamicProgram dp, Policy iniPolicy)
    {
	String output;

	getMatlabCode(dp,iniPolicy);
	try
	{
	    Process process = Runtime.getRuntime().exec(command+scriptFilename);
	    BufferedReader outcome = new 
		BufferedReader(new InputStreamReader(process.getInputStream()));
	    while ((output = outcome.readLine()) != null)
		System.out.println(output);
	} catch (IOException err)
	{
	    System.err.println(this + " generates "+ err);
	}	
	readOutput();

	return lastResult;
    }
}
