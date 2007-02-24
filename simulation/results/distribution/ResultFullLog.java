package simulation.results.distribution;

import java.util.*;
import simulation.utilities.structures.*;

/** Class logging the complete result and produces distribution for it.
 * @author ykk
 */
public class ResultFullLog
    extends ResultDistribution
{
    //Members
    /** Vector to hold all inputs.
     */
    public Vector log = new Vector();
    /** Number of bins.
     * Defaulted to 20. 
     */
    public int binNumber = 20;
    
    //Members
    /** Function to take in a sample of result.
     * Maintain the first and second order statistics while logging all inputs.
     * @see #log
     * @param inputValue sample to be taken in
     */
    public void input(double inputValue)
    {
	super.input(inputValue);
	log.add(new Double(inputValue));
    }

    /** Return distribution of results.
     */
    public ProbDistribution distribution()
    {
	ProbDistribution distro = new ProbDistribution(min, max, binNumber);
	int binIndex;
	double value;
	for (int i = 1; i < log.size(); i++)
	{
	    value = ((Double) log.get(i)).doubleValue();
	    binIndex = distro.binIndex(value);
	    distro.writeBin(binIndex, distro.readBin(binIndex)+1);
	}
	return distro;
    }
}