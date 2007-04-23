package simulation.results.distribution;

import simulation.results.*;
import simulation.utilities.structures.*;
import java.util.*;

/** Class logging the complete result and produces distribution for it, including statistics of each bin.
 * @author ykk
 */
public class ResultBinStat
    extends ResultFullLog
{
    //Members
    /** Vector to hold bin statistics.
     */
    private Vector binStat = new Vector();

    //Methods
    /** Return statistics of individual bin.
     * Require call of {@link #distribution()}, else return null.
     * @param i index of bin to retrieve statistics for
     * @return statistics of bin
     */
    public ResultVar binStat(int i)
    {
	if (binStat.size() == 0)
	    return null;
	
	return (ResultVar) binStat.get(i);
    }

    /** Return distribution of results.
     */
    public ProbDistribution distribution()
    {
	double pmin = (disMin == Double.MIN_VALUE)?min:disMin;
	double pmax = (disMax == Double.MAX_VALUE)?max:disMax;
	ProbDistribution distro = new ProbDistribution(pmin, pmax, binNumber);
	int binIndex;
	double value;

	//Create bin statistics holder.
	for (int i = 0; i < binNumber; i++)
	    binStat.add(new ResultVar());
	
	//Enter data
	for (int i = 1; i < log.size(); i++)
	{
	    value = ((Double) log.get(i)).doubleValue();
	    binIndex = distro.binIndex(value);
	    distro.writeBin(binIndex, distro.readBin(binIndex)+1);
	    ((ResultVar) binStat.get(binIndex)).input(value);
	}

	//Normalize
	for (int i = 0; i < distro.binNumber; i++)
	    distro.writeBin(i, distro.readBin(i)/((double) log.size()));
	return distro;
    }
}
