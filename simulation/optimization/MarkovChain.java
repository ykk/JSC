package simulation.optimization;

import java.util.*;
import simulation.optimization.dynprogram.*;
import Jama.*;
import simulation.utilities.structures.*;

/** Class to hold Markov Chain.
 * Uses Jama, see {@link PolicyIterate}.
 * @author ykk
 */
public class MarkovChain
{
    //Members
    /** State space.
     */
    public Vector states;
    /** Transition probabilities.
     */
    public double[][] transitProb;


    //Methods
    /** Constructor.
     * @param states vector of states
     */
    public MarkovChain(Vector states)
    {
	this.states = states;
	int stateSize = states.size();
	transitProb = new double[stateSize][stateSize];
	for (int i = 0; i < stateSize; i++)
	    for (int j = 0; j < stateSize; j++)
		transitProb[i][j] = 0.0;
    }

    /** Check chain, ensuring valid transition matrix.
     */
    public void checkChain()
    {
	int stateSize = states.size();
	for (int i = 0; i < stateSize; i++)
	{
	    double sum = 0;
	    for (int j = 0; j < stateSize; j++)
		sum += transitProb[i][j];
	    if (sum != 1.0)
		throw new RuntimeException(this+" has invalid transition matrix.");
	}
    }

    /** Get steady state probabilities.
     * @return array of steady state probabilities
     */
    public double[] getSteadyState()
    {
	int stateSize = states.size();
	double[][] p = new double[stateSize][stateSize];
	double[][] c = new double[stateSize][1];
	for (int i = 0; i < stateSize; i++)
	{
	    if (i == 0)
		c[i][0] = 1;
	    else
		c[i][0] = 0;
		
	    for (int j = 0; j < stateSize; j++)
	    {
		p[i][j] = transitProb[j][i];
		if (i == j)
		    p[i][j] -= 1.0;
		if (i == 0)
		    p[i][j] = 1.0;
	    }
	}
	Matrix pM = new Matrix(p);
	Matrix cM = new Matrix(c);
	Matrix steady = pM.solve(cM);

	return Array.changeDim(steady.getArray());
    }
    
    /** String representation.
     * @return string showing Markov Chain
     */
    public String toString()
    {
	return states+"\n"+Array.print(transitProb);
    }
}