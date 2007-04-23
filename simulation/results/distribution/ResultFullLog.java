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
    /** Distribution maximum.
     * Defaulted to Double.MAX_VALUE for autoscale.
     */
    public double disMax = Double.MAX_VALUE;
    /** Distribution minimum.
     * Defaulted to Double.MIN_VALUE for autoscale.
     */
    public double disMin = Double.MIN_VALUE;

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
     * @return return probability distribution
     */
    public Distributions distribution()
    {
	double pmin = (disMin == Double.MIN_VALUE)?min:disMin;
	double pmax = (disMax == Double.MAX_VALUE)?max:disMax;
	ProbDistribution distro = new ProbDistribution(pmin, pmax, binNumber);
	int binIndex;
	double value;
	for (int i = 1; i < log.size(); i++)
	{
	    value = ((Double) log.get(i)).doubleValue();
	    binIndex = distro.binIndex(value);
	    distro.writeBin(binIndex, distro.readBin(binIndex)+1);
	}

	//Normalize
	for (int i = 0; i < distro.binNumber; i++)
	    distro.writeBin(i, distro.readBin(i)/((double) log.size()));
	return distro;
    }
}